package dev.thew.message.type;

import dev.thew.message.Message;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@AllArgsConstructor
public final class ActionBarMessage implements Message {

    private String message;

    @Override
    public void push(@NonNull CommandSender sender) {
        if (!(sender instanceof Player player)) return;
        push(player);
    }

    @Override
    public void push(@NonNull Player player) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.translateAlternateColorCodes('&', message)));
    }

    public static Message of(String message) {
        return new ActionBarMessage(message);
    }
}
