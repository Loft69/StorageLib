package dev.thew.test;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        FirstCommand command = new FirstCommand("tested", null, null);
        FirstSubCommand subCommand = new FirstSubCommand("hello1", null, null);
        SecondSubCommand subCommand2 = new SecondSubCommand("hello2", null, null);

        command.subCommand(subCommand.nestedCommand(subCommand2));
        command.hook(this);
    }


}
