package commands;

import exceptions.ArgumentException;
import exceptions.CollectionEmptyException;
import managers.CollectionManager;

/**
 * A command that prints the information af all the products in the collection
 */
public class ShowCommand implements Command {
    /**
     * An object of class {@code CollectionManager} to access the product in the collection(HashTable<Integer,Product>)
     */
    private final CollectionManager collectionManager;

    /**
     * Constructor {@code InsertCommand} with the specified object of {@code CollectionManager}
     *
     * @param collectionManager Specified object of {@code CollectionManager}
     */
    public ShowCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * It's a Method that prints information an all the collection products
     *
     * @param argument It is a string used to check the correctness of the inserted command(For this command the argument must be empty)
     * @return If the information on all the collection products is successfully printed, method returns true
     */
    @Override
    public boolean execute(String argument) {
        if (!argument.equals("")) throw new ArgumentException("this command can't have argument");
        if (collectionManager.getCollection().isEmpty()) throw new CollectionEmptyException("collection is empty");
        System.out.println("\n\t\t\t\t\t\t\t\tAll products in collection : ");
        collectionManager.getCollection().forEach((key, product) -> {
            System.out.println("-".repeat(70));
            System.out.printf("key of product in collection: %s%n%s", key, product);
        });
        return true;
    }

    /**
     * A method for providing descriptions about the command
     *
     * @return Returns a descriptions the {@code ShowCommand}
     */
    @Override
    public String getDescription() {
        return "output to the standard output stream all the elements of the collection in a string representation.";
    }
}
