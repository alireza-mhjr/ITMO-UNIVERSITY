package commands;

import exceptions.ArgumentException;
import managers.CommandManager;

/**
 * A command to print all the commands with their description
 */
public class HelpCommand implements Command {
    /**
     * An object of class {@code CommandManager} to access all commands
     */
    private final CommandManager commandManager;

    /**
     * Constructor {@code ExecuteScriptCommand} with the specified object of {@code CommandManager}
     *
     * @param commandManager Specified object of {@code CommandManager}
     */
    public HelpCommand(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    /**
     * @param argument It is a string used to check the correctness of the inserted command(For this command the argument must be empty)
     * @return The command has been successfully executed or not
     */
    @Override
    public boolean execute(String argument) {
        if (!argument.equals("")) throw new ArgumentException("this command can't have argument");
        System.out.printf("%-30s-%s%n", "help", getDescription());
        commandManager.getCommandMap().forEach((s, command) -> {
            if (!s.equals("help")) System.out.printf("%-30s-%s%n", s, command.getDescription());
        });
        return true;
    }

    /**
     * A method for providing descriptions about the command
     *
     * @return Returns a descriptions the {@code HelpCommand}
     */
    @Override
    public String getDescription() {
        return "display help for available commands.";
    }
}
