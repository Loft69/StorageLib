package dev.thew.message.type;

import dev.thew.message.Message;
import lombok.NonNull;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EmptyMessage implements Message {
    @Override
    public void push(@NonNull CommandSender sender) {

    }

    @Override
    public void push(@NonNull Player player) {

    }
}
