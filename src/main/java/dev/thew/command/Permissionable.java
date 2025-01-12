package dev.thew.command;

import dev.thew.message.Message;
import lombok.NonNull;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;

public interface Permissionable {

    default boolean hasPermission(@NonNull CommandSender sender, Permission permission, Message permissionMessage) {
        if (permission != null && !sender.hasPermission(permission)) {
            if (permissionMessage != null) permissionMessage.push(sender);
            return false;
        }
        return true;
    }
}
