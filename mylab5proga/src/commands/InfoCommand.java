package commands;

import exceptions.ArgumentException;
import managers.CollectionManager;

/**
 * A command to print information about the collection
 */
public class InfoCommand implements Command {
    /**
     * An object of class {@code CollectionManager} to access the method collectionInfo()
     */
    private final CollectionManager collectionManager;

    /**
     * Constructor {@code InfoCommand} with the specified object of {@code CollectionManager}
     *
     * @param collectionManager Specified object of {@code CollectionManager}
     */
    public InfoCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * @param argument It is a string used to check the correctness of the inserted command(For this command the argument must be empty)
     */
    @Override
    public boolean execute(String argument) {
        if (!argument.equals("")) throw new ArgumentException("this command can't have argument");
        System.out.println(collectionManager.collectionInfo());
        return true;
    }

    /**
     * A method for providing descriptions about the command
     *
     * @return Returns a descriptions the {@code InfoCommand}
     */
    @Override
    public String getDescription() {
        return "out to the standard output stream information about the collection (type, initialization date, number of elements, etc.).";
    }
}
