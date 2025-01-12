package dev.thew.command;

import dev.thew.message.Message;
import org.bukkit.command.CommandSender;

import java.util.List;

public interface CommandInterface {

    Message execute(CommandSender sender, String[] args);
    List<String> tabList(CommandSender sender, String[] args);

}
