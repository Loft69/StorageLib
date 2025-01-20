
# Framework

This is a lightweight command framework designed for Bukkit/Spigot plugins, providing a structured way to manage commands and subcommands. It supports tab completion, permissions, and customizable messages.

## Command

### 1. Create Your Command

Create a class that extends `AbstractCommand`:

```java
import dev.thew.command.AbstractCommand;
import dev.thew.message.Message;
import dev.thew.message.type.ChatMessage;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class MyCommand extends AbstractCommand {

    public MyCommand(JavaPlugin instance) {
        super("examplecommand", new Permission("example.command"), ChatMessage.of("&cYou don't have permission!"));
    }

    @Override
    public Message execute(CommandSender sender, String[] args) {
        return ChatMessage.of("&aHello, command executed!");
    }

    @Override
    public List<String> tabList(CommandSender sender, String[] args) {
        return List.of();
    }
}
```

### 2. SubCommand

Create a class that extends SubCommand:

```java
import dev.thew.command.SubCommand;
import dev.thew.message.Message;
import dev.thew.message.type.ChatMessage;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;

import java.util.List;

public class MySubCommand extends SubCommand {

    public MySubCommand() {
        super("mysubcommand", new Permission("example.command.subcommand"), ChatMessage.of("&cYou don't have permission!"));
    }

    @Override
    public Message execute(CommandSender sender, String[] args) {
        return ChatMessage.of("&aHello, sub-command executed!");
    }

    @Override
    public List<String> tabList(CommandSender sender, String[] args) {
        return List.of();
    }
}
```

### 3. Register the Command

In your plugin’s onEnable method:

```java
@Override
public void onEnable() {
    new MyCommand()
            .subCommand(new MySubCommand())
            .hook(this);
}
```

## Nested Subcommands

The API also supports creating subcommands within subcommands, allowing for a deeper, more organized command structure. This nesting allows you to create complex command hierarchies for advanced plugin features.

### Example

Here's an example of how to create nested subcommands:

```java
import dev.thew.command.AbstractCommand;
import dev.thew.command.SubCommand;
import dev.thew.message.Message;
import dev.thew.message.type.ChatMessage;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class MyCommand extends AbstractCommand {

    public MyCommand(JavaPlugin plugin) {
        super("mycommand", new Permission("myplugin.mycommand"), ChatMessage.of("&cYou don't have permission!"));
    }

    @Override
    public Message execute(CommandSender sender, String[] args) {
        return ChatMessage.of("&aHello from my command!");
    }

    @Override
    public List<String> tabList(CommandSender sender, String[] args) {
        return List.of();
    }

    private static class SubCommand1 extends SubCommand {

        public SubCommand1() {
            super("sub1", new Permission("myplugin.mycommand.sub1"), ChatMessage.of("&cYou don't have permission for sub1!"));
        }

        @Override
        public Message execute(CommandSender sender, String[] args) {
            return ChatMessage.of("&aHello from SubCommand 1!");
        }

        @Override
        public List<String> tabList(CommandSender sender, String[] args) {
            return List.of();
        }

        // Nested SubCommand
        private static class NestedSubCommand extends SubCommand {

            public NestedSubCommand() {
                super("nested", new Permission("myplugin.mycommand.sub1.nested"), ChatMessage.of("&cYou don't have permission for nested command!"));
            }

            @Override
            public Message execute(CommandSender sender, String[] args) {
                return ChatMessage.of("&aHello from nested SubCommand!");
            }

            @Override
            public List<String> tabList(CommandSender sender, String[] args) {
                return List.of();
            }
        }
    }

    // SubCommand 2
    private static class SubCommand2 extends SubCommand {

        public SubCommand2() {
            super("sub2", new Permission("myplugin.mycommand.sub2"), ChatMessage.of("&cYou don't have permission for sub2!"));
        }

        @Override
        public Message execute(CommandSender sender, String[] args) {
            return ChatMessage.of("&aHello from SubCommand 2!");
        }

        @Override
        public List<String> tabList(CommandSender sender, String[] args) {
            return List.of();
        }
    }
}
```

```java
@Override
public void onEnable() {
    new MyCommand()
            .subCommand(
                    new SubCommand1(), 
                    new SubCommand2()
                            .subCommand(new NestedSubCommand())
            )
            .hook(this);
}
```

## Messages

You can create instances of ChatMessage, ActionBarMessage, and TitleMessage using their of methods to create messages:

```java
ChatMessage chatMessage = ChatMessage.of("&aThis is a chat message");
ActionBarMessage.of("&bThis is an action bar message").push(player);
TitleMessage.of("&cTitle", "&dSubtitle", 10, 20, 10).push(player);

chatMessage.push(player);
```

## ItemBuilder

ItemBuilder is a convenient tool for creating and configuring items in Minecraft using the Bukkit API.

### Features

- Easy item creation through method chaining.
- Setting item names.
- Adding lore (descriptions).
- Custom model data support.
- Adding custom tags.

### Usage

```java
import dev.thew.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

ItemStack customItem = new ItemBuilder(Material.DIAMOND_SWORD)
        .name("\u00A7bLegendary Sword")
        .lore(List.of("\u00A77A sword of legends", "\u00A7eUnbreakable"))
        .customModelData(123)
        .build();
```

### API

- `ItemBuilder(Material material)` - creates an item from the specified material.
- `ItemBuilder(ItemStack item)` - wraps an existing item.
- `name(String name)` - sets the item's name.
- `lore(List<String> lore)` - adds item lore.
- `customModelData(int value)` - sets custom model data.
- `tag(String key, C value, PersistentDataType<T, C> type, JavaPlugin instance)` - adds a custom tag.
- `build()` - creates the final `ItemStack` object.


