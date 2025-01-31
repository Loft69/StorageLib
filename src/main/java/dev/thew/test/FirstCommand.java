package dev.thew.test;

import dev.thew.command.AbstractCommand;
import dev.thew.message.Message;
import dev.thew.message.type.ChatMessage;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;

import java.util.List;

public class FirstCommand extends AbstractCommand {

    public FirstCommand(String command, Permission permission, Message permissionMessage) {
        super(command, permission, permissionMessage);
    }

    @Override
    public Message execute(CommandSender sender, String[] args) {
        return ChatMessage.of("FirstCommand");
    }

    @Override
    public List<String> tabList(CommandSender sender, String[] args) {
        return List.of();
    }
}
