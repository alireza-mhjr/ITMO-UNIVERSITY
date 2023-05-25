package commands;

import exceptions.ArgumentException;
import exceptions.CollectionEmptyException;
import managers.CollectionManager;

/**
 * A command to clear the collection
 */
public class ClearCommand implements Command {
    /**
     * An object of class {@code CollectionManager} to access the method clear and the collection(HashTable<Integer,Product>)
     */
    private final CollectionManager collectionManager;

    /**
     * Constructor {@code ClearCommand} with the specified object of {@code CollectionManager}
     *
     * @param collectionManager Specified object of {@code CollectionManager}
     */
    public ClearCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * This method clears the collection(HashTable<Integer,Product>)
     *
     * @param argument It is a string used to check the correctness of the inserted command(For this command the argument must be empty)
     * @return The command has been successfully executed or not
     */
    @Override
    public boolean execute(String argument) {

        if (!argument.equals("")) throw new ArgumentException("this command can't have argument");
        if (collectionManager.getCollection().isEmpty())
            throw new CollectionEmptyException("The collection is already empty");
        else {
            collectionManager.clear();
            System.out.println("\n\t\t\t\t\t\t\t\tcollection successfully cleared");
        }
        return true;
    }
    /**
     * A method for providing descriptions about the command
     *
     * @return Returns a String that descriptions the {@code clearCommand}
     */
    @Override
    public String getDescription() {
        return "Clear the collection.";
    }
}
