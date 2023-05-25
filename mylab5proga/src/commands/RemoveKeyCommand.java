package commands;

import exceptions.ArgumentException;
import exceptions.CollectionEmptyException;
import exceptions.InvalidKeyException;
import managers.CollectionManager;

/**
 * A command that removes a product with the specified key from the collection
 */
public class RemoveKeyCommand implements Command {

    /**
     * An object of class {@code CollectionManager} to access the methods remove_key() and the collection(HashTable<Integer,Product>)
     */
    private final CollectionManager collectionManager;

    /**
     * Constructor {@code InsertCommand} with the specified object of {@code CollectionManager}
     *
     * @param collectionManager Specified object of {@code CollectionManager}
     */
    public RemoveKeyCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * The method that removes a product from the collection with the key it receives
     *
     * @param argument It shows the key that its product is going to be removed from the collection
     * @return If the product is successfully removed from the collection, the method returns true
     */
    @Override
    public boolean execute(String argument) {
        if (collectionManager.getCollection().isEmpty())
            throw new CollectionEmptyException("collection is empty , there is no products to remove");
        boolean success = false;
        if (!argument.equals("")) {
            try {
                Integer key = Integer.parseInt(argument);
                if (collectionManager.remove_key(key)) {
                    System.out.printf("\n\t\t\t\t\t\t\t\tA product with the key \"%s\" was successfully removed from the collection%n", argument);
                    success = true;
                } else {
                    System.err.printf("There is no product with key \"%s\" in the collection%n", argument);
                    System.out.println("List of keys that exist in collection : \n" + collectionManager.getCollection().keySet());
                }
            } catch (NumberFormatException exception) {
                throw new InvalidKeyException("Invalid key!!!");
            }
        } else throw new ArgumentException("No argument!!!");
        return success;
    }

    /**
     * A method for providing descriptions about the command
     *
     * @return Returns a descriptions the {@code RemoveKeyCommand}
     */
    @Override
    public String getDescription() {
        return "remove an item from the collection by its key.";
    }
}
