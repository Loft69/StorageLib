package dev.thew.item;

import dev.thew.item.meta.MetaBuilder;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

@Getter
public class ItemStackWrapper {
    private final ItemStack itemStack;

    public ItemStackWrapper(Material material) {
        this.itemStack = new ItemStack(material);
    }

    public ItemStackWrapper(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public void applyMeta(List<MetaBuilder> builders) {
        ItemMeta meta = this.itemStack.getItemMeta();
        if (meta != null) {
            for (MetaBuilder builder : builders) builder.apply(meta);
            itemStack.setItemMeta(meta);
        }
    }
}