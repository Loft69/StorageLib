package dev.thew.command.base;

import dev.thew.command.SubCommand;
import dev.thew.command.permission.Permissionable;
import dev.thew.message.Message;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import java.util.*;

@RequiredArgsConstructor
public abstract class BaseCommand implements CommandInterface, Permissionable {

    private final CommandRegistry registry = new CommandRegistry();
    private final Permission permission;
    private final Message permissionMessage;

    /* SpigotAPI */

    public final boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {
        return onCommand(sender, args);
    }

    public final List<String> onTabComplete(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {
        return onTabComplete(sender, args);
    }

    /* SpigotAPI END */

    public final boolean onCommand(@NonNull CommandSender sender, String @NonNull [] args) {
        if (!hasPermission(sender, permission, permissionMessage)) return false;
        return executeCommand(sender, args);
    }

    public final List<String> onTabComplete(@NonNull CommandSender sender, String @NonNull [] args) {
        if (!hasPermission(sender, permission, permissionMessage)) return Collections.emptyList();

        if (args.length > 0) {
            Optional<SubCommand> subCommand = registry.getSubCommand(args[0]);
            if (subCommand.isPresent()) return subCommand.get().onTabComplete(sender, Arrays.copyOfRange(args, 1, args.length));
        }

        return tabHandle(sender, args);
    }

    private boolean executeCommand(CommandSender sender, String @NonNull [] args) {
        if (args.length > 0) {
            Optional<SubCommand> optionalSubCommand = registry.getSubCommand(args[0]);
            if (optionalSubCommand.isPresent()) {
                SubCommand subCommand = optionalSubCommand.get();
                return subCommand.onCommand(sender, Arrays.copyOfRange(args, 1, args.length));
            }
        }

        Message message = execute(sender, args);
        if (message != null) message.push(sender);
        return true;
    }

    @Override
    public final boolean hasPermission(@NonNull CommandSender sender, Permission permission, Message permissionMessage) {
        if (permission != null && !sender.hasPermission(permission)) {
            if (permissionMessage != null) permissionMessage.push(sender);
            return false;
        }
        return true;
    }

    private @NonNull List<String> tabHandle(@NonNull CommandSender sender, String @NonNull [] args) {
        List<String> result = registry.getSubArguments();
        List<String> tab = tabList(sender, args);
        if (tab == null || tab.isEmpty()) {
            tab = new ArrayList<>();
            for (Player player : Bukkit.getOnlinePlayers()) tab.add(player.getName());
        }

        result.addAll(tab);
        return result;
    }

    public final BaseCommand subCommand(@NonNull SubCommand... addedCommands) {
        registry.addSubCommand(addedCommands);
        return this;
    }
}
