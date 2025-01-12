package dev.thew.item.meta;

import lombok.RequiredArgsConstructor;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

@RequiredArgsConstructor
public class TagMetaBuilder<T, V> implements MetaBuilder {
    private final String key;
    private final V value;
    private final PersistentDataType<T, V> type;
    private final JavaPlugin instance;

    @Override
    public void apply(ItemMeta meta) {
        meta.getPersistentDataContainer().set(new NamespacedKey(instance, key), type, value);
    }
}