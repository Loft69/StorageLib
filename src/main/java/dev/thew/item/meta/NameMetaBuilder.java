package dev.thew.item.meta;

import lombok.RequiredArgsConstructor;
import org.bukkit.inventory.meta.ItemMeta;

@RequiredArgsConstructor
public class NameMetaBuilder implements MetaBuilder {
    private final String name;

    @Override
    public void apply(ItemMeta meta) {
        meta.setDisplayName(name);
    }
}
