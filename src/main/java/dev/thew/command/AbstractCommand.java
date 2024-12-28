package dev.thew.command;

import dev.thew.command.message.Message;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

@RequiredArgsConstructor
public abstract class AbstractCommand implements TabExecutor, CommandInterface {

    private final CommandRegistry registry = new CommandRegistry();

    private final String command;
    private final Permission permission;
    private final Message permissionMessage;

    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {
        if (!hasPermission(sender)) return true;

        if (args.length > 0) {
            Optional<SubCommand> subCommand = registry.getSubCommand(args[0]);
            if (subCommand.isPresent()) return subCommand.get().onCommand(sender, Arrays.copyOfRange(args, 1, args.length));
        }

        Message message = execute(sender, args);
        message.push(sender);
        return true;
    }

    @Override
    public List<String> onTabComplete(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {
        if (!hasPermission(sender)) return Collections.emptyList();

        if (args.length > 0) {
            Optional<SubCommand> subCommand = registry.getSubCommand(args[0]);
            if (subCommand.isPresent()) return subCommand.get().onTabComplete(sender, Arrays.copyOfRange(args, 1, args.length));
        }

        return tabHandle(sender, args);
    }

    private List<String> tabHandle(@NonNull CommandSender sender, String @NonNull [] args) {
        List<String> result = registry.getSubArguments();
        List<String> tab = tabList(sender, args);
        if (tab == null || tab.isEmpty()) return result;

        result.addAll(tab);
        return result;
    }

    private boolean hasPermission(@NonNull CommandSender sender) {
        if (this.permission != null && !sender.hasPermission(this.permission)) {
            if (this.permissionMessage != null) this.permissionMessage.push(sender);
            return false;
        }
        return true;
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

    public void addSubCommand(@NonNull SubCommand... addedCommands) {
        registry.addSubCommand(addedCommands);
    }
}
