package dev.thew.message;

import lombok.NonNull;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public interface Message {
    void push(@NonNull CommandSender sender);
    void push(@NonNull Player player);

}
