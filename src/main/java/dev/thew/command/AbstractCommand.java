package dev.thew.command;

import dev.thew.command.base.BaseCommand;
import dev.thew.command.hook.Hookable;
import dev.thew.message.Message;
import org.bukkit.command.TabExecutor;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Optional;

public abstract class AbstractCommand extends BaseCommand implements TabExecutor, Hookable {

    private final String command;

    public AbstractCommand(String command, Permission permission, Message permissionMessage) {
        super(permission, permissionMessage);
        this.command = command;
    }

    public AbstractCommand(String command) {
        super(null, null);
        this.command = command;
    }

    public AbstractCommand(String command, Permission permission) {
        super(permission, null);
        this.command = command;
    }

    @Override
    public final void hook(JavaPlugin instance, AbstractCommand abstractCommand) {
        Optional.ofNullable(instance.getCommand(this.command))
                .ifPresentOrElse(pluginCommand -> {
                    pluginCommand.setExecutor(this);
                    pluginCommand.setTabCompleter(this);
                }, () -> {
                    throw new IllegalArgumentException("Plugin command '" + this.command + "' not found in plugin.yml");
                });
    }

    public final void hook(JavaPlugin instance) {
        hook(instance, this);
    }

}
