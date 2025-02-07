package dev.thew.command;

import dev.thew.message.Message;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

@RequiredArgsConstructor
public abstract class AbstractCommand implements TabExecutor, CommandInterface, Permissionable {

    private final CommandRegistry registry = new CommandRegistry();

    private final String command;
    private final Permission permission;
    private final Message permissionMessage;

    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {
        if (!hasPermission(sender, permission, permissionMessage)) return true;

        if (args.length > 0) {
            Optional<SubCommand> subCommand = registry.getSubCommand(args[0]);
            if (subCommand.isPresent()) return subCommand.get().onCommand(sender, Arrays.copyOfRange(args, 1, args.length));
        }

        Message message = execute(sender, args);
        if (message != null) message.push(sender);
        return true;
    }

    @Override
    public List<String> onTabComplete(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {
        if (!hasPermission(sender, permission, permissionMessage)) return Collections.emptyList();

        if (args.length > 0) {
            Optional<SubCommand> subCommand = registry.getSubCommand(args[0]);
            if (subCommand.isPresent()) return subCommand.get().onTabComplete(sender, Arrays.copyOfRange(args, 1, args.length));
        }

        return tabHandle(sender, args);
    }

    private List<String> tabHandle(@NonNull CommandSender sender, String @NonNull [] args) {
        List<String> result = registry.getSubArguments();
        List<String> tab = tabList(sender, args);
        if (tab == null || tab.isEmpty()) {
            tab = new ArrayList<>();
            for (Player player : Bukkit.getOnlinePlayers()) tab.add(player.getName());
        }

        result.addAll(tab);
        return result;
    }

    public void hook(JavaPlugin instance) {
        Optional.ofNullable(instance.getCommand(this.command))
                .ifPresentOrElse(pluginCommand -> {
                    pluginCommand.setExecutor(this);
                    pluginCommand.setTabCompleter(this);
                }, () -> {
                    throw new IllegalArgumentException("Plugin command '" + this.command + "' not found in plugin.yml");
                });
    }

    public AbstractCommand subCommand(@NonNull SubCommand... addedCommands) {
        registry.addSubCommand(addedCommands);
        return this;
    }
}
