package dev.thew.test.sub;

import dev.thew.command.SubCommand;
import dev.thew.message.Message;
import dev.thew.message.type.ChatMessage;
import org.bukkit.command.CommandSender;

import java.util.List;

public class ExampleSub extends SubCommand {

    public ExampleSub() {
        super("example", null, null);
    }

    @Override
    public Message execute(CommandSender sender, String[] args) {
        return ChatMessage.of("Hello from Example Sub!");
    }

    @Override
    public List<String> tabList(CommandSender sender, String[] args) {
        return List.of("");
    }
}
