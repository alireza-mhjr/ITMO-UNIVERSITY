package commands;

import exceptions.ArgumentException;
/**
 * A command to stop the program
 */
public class ExitCommand implements Command{
    /**
     *
     * @param argument It is a string used to check the correctness of the inserted command(For this command the argument must be empty)
     */
    @Override
    public boolean execute(String argument) {
        if(!argument.equals("")) throw new ArgumentException("this command can't have argument");
        System.out.println("\t\t\t\t\t\t\t\tSEE YOU SOON ))");
        System.exit(0);
        return true;
    }
    /**
     * A method for providing descriptions about the command
     *
     * @return Returns a String that descriptions the {@code ExitCommand}
     */
    @Override
    public String getDescription() {
        return "terminate the program (without saving to a file).";
    }
}
