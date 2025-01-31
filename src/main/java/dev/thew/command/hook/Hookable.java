package dev.thew.command.hook;

import dev.thew.command.AbstractCommand;
import org.bukkit.plugin.java.JavaPlugin;

public interface Hookable {

    void hook(JavaPlugin instance, AbstractCommand abstractCommand);

}
