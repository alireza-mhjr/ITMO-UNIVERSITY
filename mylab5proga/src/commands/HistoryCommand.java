package commands;

import exceptions.ArgumentException;

import java.util.ArrayList;

/**
 * A command that prints 9 recently used commands
 */
public class HistoryCommand implements Command {
    /**
     * An ArrayList to store the commands used
     */
    private final ArrayList<String> commandsHistory;

    /**
     * A constructor that initializes ArrayList<String> commandsHistory
     */
    public HistoryCommand() {
        commandsHistory = new ArrayList<>();
    }

    /**
     * A method for printing up to 9 recent commands
     *
     * @param argument It is a string used to check the correctness of the inserted command(For this command the argument must be empty)
     * @return The command has been successfully executed or not
     */
    @Override
    public boolean execute(String argument) {
        boolean success = false;
        if (!argument.equals("")) throw new ArgumentException("this command can't have argument");
        if (!commandsHistory.isEmpty()) {
            System.out.println("\n\t\t\t\t\t\t\t\tList of last 9 commands (without their arguments)\n");
            for (int i = 0; i < commandsHistory.size(); i++) {
                System.out.printf("%-16s%s%n", i + 1 + "th command : ", commandsHistory.get(i));
            }
            success = true;
        } else {
            System.out.println("No commands in History");
            success = true;
        }
        return success;
    }

    /**
     * A method for adding used command to ArrayList<String>(If there are currently
     * 9 commands in the ArrayList<String> it will delete the one that was used earlier than the others)
     *
     * @param command String of the used command name that to be added in the ArrayList<String> commandsHistory
     */
    public void addToHistory(String command) {
        if (historyIsFull()) commandsHistory.remove(0);
        commandsHistory.add(command);
    }

    /**
     * Its method checks whether the ArrayList<String> commandsHistory currently has the highest number of commands.
     *
     * @return Method returns true, if the ArrayList<String> commandsHistory currently has the highest number of commands
     */
    private boolean historyIsFull() {
        int MAX = 9;
        return commandsHistory.size() == MAX;
    }

    /**
     * A method for providing descriptions about the command
     *
     * @return Returns a descriptions the {@code HistoryCommand}
     */
    @Override
    public String getDescription() {
        return "output the last 9 commands (without their arguments).";
    }

}
