package dev.thew.item;

import dev.thew.item.meta.*;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class ItemBuilder {
    private final ItemStackWrapper itemStackWrapper;
    private final List<MetaBuilder> metaBuilders = new ArrayList<>();

    public ItemBuilder(Material material) {
        this.itemStackWrapper = new ItemStackWrapper(material);
    }

    public ItemBuilder(ItemStack item) {
        this.itemStackWrapper = new ItemStackWrapper(item);
    }

    public ItemBuilder name(String name) {
        metaBuilders.add(new NameMetaBuilder(name));
        return this;
    }

    public ItemBuilder lore(List<String> lore) {
        metaBuilders.add(new LoreMetaBuilder(lore));
        return this;
    }

    public ItemBuilder customModelData(int value) {
        metaBuilders.add(new CustomModelDataMetaBuilder(value));
        return this;
    }

    public <T, C> ItemBuilder tag(String key, C value, PersistentDataType<T, C> type, JavaPlugin instance) {
        metaBuilders.add(new TagMetaBuilder<>(key, value, type, instance));
        return this;
    }

    public ItemStack build() {
        itemStackWrapper.applyMeta(metaBuilders);
        return itemStackWrapper.getItemStack();
    }
}
