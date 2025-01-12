package dev.thew.command;

import lombok.NonNull;

import java.util.*;

public class CommandRegistry {

    private final HashMap<String, SubCommand> subCommands = new HashMap<>();

    protected void addSubCommand(@NonNull SubCommand... addedCommands) {
        for (SubCommand subCommand : addedCommands) subCommands.put(subCommand.getCommand().toLowerCase(), subCommand);
    }

    protected List<String> getSubArguments(){
        List<String> args = new ArrayList<>();
        for (SubCommand subCommand : subCommands.values()) args.add(subCommand.getCommand());
        return args;
    }

    protected Optional<SubCommand> getSubCommand(String arg) {
        return Optional.ofNullable(subCommands.get(arg.toLowerCase()));
    }

}
