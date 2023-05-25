package commands;

import exceptions.ArgumentException;
import exceptions.CollectionEmptyException;
import exceptions.InvalidKeyException;
import managers.CollectionManager;

/**
 * A command that removes all products whose key is less than the specified key
 */
public class RemoveLowerKeyCommand implements Command {
    /**
     * An object of class {@code CollectionManager} to access the remove_lower_key(int key) and the collection(HashTable<Integer,Product>)
     */
    private final CollectionManager collectionManager;

    /**
     * Constructor {@code RemoveLowerKeyCommand} with the specified object of {@code CollectionManager}
     *
     * @param collectionManager Specified object of {@code CollectionManager}
     */
    public RemoveLowerKeyCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * A method that removes all products whose keys are smaller than the specified key
     *
     * @param argument It's a string that shows the key that all products are going to be removed with a smaller key than that
     * @return a boolean variable that indicates whether the command has been successfully executed
     */
    @Override
    public boolean execute(String argument) {
        boolean success = false;
        if (collectionManager.getCollection().isEmpty())
            throw new CollectionEmptyException("collection is empty , there is no products to remove");
        if (!argument.equals("")) {
            int primarySize = collectionManager.getCollection().size();
            try {
                Integer key = Integer.parseInt(argument);
                if (collectionManager.remove_lower_key(key)) {
                    int productRemoved = primarySize - collectionManager.getCollection().size();
                    System.out.println("\n\t\t\t\t\t\t\t\t" + productRemoved + " product(s) with key lower than \"" + argument + "\" were successfully removed from the collection");
                    success = true;
                } else System.out.println("No product found that its key is lower then the \"" + argument + "\"");
            } catch (NumberFormatException exception) {
                throw new InvalidKeyException("Invalid KEY!!!");
            }
        } else throw new ArgumentException("No argument!!!");
        return success;
    }

    /**
     * A method for providing descriptions about the command
     *
     * @return Returns a descriptions the {@code RemoveLowerKeyCommand}
     */
    @Override
    public String getDescription() {
        return "remove from the collection all elements whose key is less than the specified one.";
    }
}
