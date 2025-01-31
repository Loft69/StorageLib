package dev.thew.command;

import dev.thew.command.base.BaseCommand;
import dev.thew.message.Message;
import lombok.Getter;
import lombok.NonNull;
import org.bukkit.permissions.Permission;

@Getter
public abstract class SubCommand extends BaseCommand {

    private final String command;

    public SubCommand(String command, Permission permission, Message permissionMessage) {
        super(permission, permissionMessage);
        this.command = command;
    }

    public SubCommand nestedCommand(@NonNull SubCommand... addedCommands) {
        subCommand(addedCommands);
        return this;
    }

}
