package dev.thew.command.base;

import dev.thew.command.SubCommand;
import lombok.NonNull;

import java.util.*;

public final class CommandRegistry {

    private final Map<String, SubCommand> subCommands = new HashMap<>();

    void addSubCommand(@NonNull SubCommand... addedCommands) {
        for (SubCommand subCommand : addedCommands) {
            String commandName = subCommand.getCommand().toLowerCase();

            if (subCommands.containsKey(commandName))
                throw new IllegalArgumentException("Subcommand '" + commandName + "' already exists");

            subCommands.put(commandName, subCommand);
        }
    }

    private boolean removeSubCommand(@NonNull String commandName) {
        commandName = commandName.toLowerCase();
        return subCommands.remove(commandName) != null;
    }

    List<String> getSubArguments() {
        return new ArrayList<>(subCommands.keySet());
    }

    Optional<SubCommand> getSubCommand(String arg) {
        return Optional.ofNullable(subCommands.get(arg.toLowerCase()));
    }

}
