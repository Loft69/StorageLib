package dev.thew.command.message.type;

import dev.thew.command.message.Message;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@AllArgsConstructor
public final class TitleMessage implements  Message {

    private String title;
    private String subtitle;
    private int fadeIn;
    private int stay;
    private int fadeOut;

    @Override
    public void push(@NonNull CommandSender sender) {
        if (!(sender instanceof Player player)) return;
        push(player);
    }

    @Override
    public void push(@NonNull Player player) {
        player.sendTitle(ChatColor.translateAlternateColorCodes('&', title),
                ChatColor.translateAlternateColorCodes('&', subtitle),
                fadeIn, stay, fadeOut);
    }

    public static Message of(String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        return new TitleMessage(title, subtitle, fadeIn, stay, fadeOut);
    }
}
