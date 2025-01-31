package dev.thew.command.permission;

import dev.thew.message.Message;
import lombok.NonNull;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;

public interface Permissionable {

    boolean hasPermission(@NonNull CommandSender sender, Permission permission, Message permissionMessage);

}
