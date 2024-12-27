package dev.thew.test.sub;

import dev.thew.command.SubCommand;
import dev.thew.command.message.Message;
import dev.thew.command.message.type.ChatMessage;
import dev.thew.test.sub.nested.NestedSub;
import org.bukkit.command.CommandSender;

import java.util.List;

public class ExampleSub extends SubCommand {

    public ExampleSub() {
        super("example", null, null);
        addSubCommand(new NestedSub());
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
