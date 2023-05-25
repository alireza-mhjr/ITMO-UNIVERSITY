package commands;

import java.io.IOException;

/**
 * It's an Interface where all the command classes implement his.
 */
public interface Command {
    /**
     * A method for executing the command
     *
     * @param argument Is a string containing the argument(ID Product, Key of Product in the collection, string
     *                 for filtering names and Part Number field, address of file) for executing the command.
     * @return a boolean variable that indicates whether the command has been successfully executed
     * @throws IOException Exceptions may occur in commands that work with the file address
     */
    boolean execute(String argument) throws IOException;

    /**
     * A method for providing description about the command
     *
     * @return Returns a String that description the command
     */
    String getDescription();
}
