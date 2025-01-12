package dev.thew.test;

import dev.thew.command.AbstractCommand;
import dev.thew.message.Message;
import dev.thew.message.type.ChatMessage;
import org.bukkit.command.CommandSender;

import java.util.List;

public class ExampleCommand extends AbstractCommand {

    public ExampleCommand() {
        super("tested", null, null);
    }

    @Override
    public Message execute(CommandSender sender, String[] args) {
        return ChatMessage.of("Hello from Example Command");
    }

    @Override
    public List<String> tabList(CommandSender sender, String[] args) {
        return List.of("");
    }
}
