package dev.thew.item.meta;

import lombok.RequiredArgsConstructor;
import org.bukkit.inventory.meta.ItemMeta;

@RequiredArgsConstructor
public class CustomModelDataMetaBuilder implements MetaBuilder {
    private final int value;

    @Override
    public void apply(ItemMeta meta) {
        meta.setCustomModelData(value);
    }
}
