package dev.thew.item.meta;

import lombok.RequiredArgsConstructor;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

@RequiredArgsConstructor
public class LoreMetaBuilder implements MetaBuilder {
    private final List<String> lore;

    @Override
    public void apply(ItemMeta meta) {
        meta.setLore(lore);
    }
}
