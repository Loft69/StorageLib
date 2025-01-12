package dev.thew.test.sub.nested;

import dev.thew.command.SubCommand;
import dev.thew.message.Message;
import dev.thew.message.type.ChatMessage;
import org.bukkit.command.CommandSender;

import java.util.List;

public class NestedSub extends SubCommand {

    public NestedSub() {
        super("nested", null, null);
    }

    @Override
    public Message execute(CommandSender sender, String[] args) {
        return ChatMessage.of("Hello from Nested Sub!");
    }

    @Override
    public List<String> tabList(CommandSender sender, String[] args) {
        return List.of("");
    }

}
