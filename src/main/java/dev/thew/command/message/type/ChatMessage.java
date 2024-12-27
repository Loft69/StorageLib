package dev.thew.command.message.type;

import dev.thew.command.message.Message;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@AllArgsConstructor
public final class ChatMessage implements Message {

    private String message;

    @Override
    public void push(@NonNull CommandSender sender) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    @Override
    public void push(@NonNull Player player) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    public static Message of(String message) {
        return new ChatMessage(message);
    }
}
