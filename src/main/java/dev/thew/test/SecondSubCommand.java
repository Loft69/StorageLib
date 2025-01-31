package dev.thew.test;

import dev.thew.command.SubCommand;
import dev.thew.message.Message;
import dev.thew.message.type.ChatMessage;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;

import java.util.List;

public class SecondSubCommand extends SubCommand {

    public SecondSubCommand(String command, Permission permission, Message permissionMessage) {
        super(command, permission, permissionMessage);
    }

    @Override
    public Message execute(CommandSender sender, String[] args) {
        return ChatMessage.of("SecondSubCommand");
    }

    @Override
    public List<String> tabList(CommandSender sender, String[] args) {
        return List.of();
    }
}
