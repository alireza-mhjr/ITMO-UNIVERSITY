package commands;

import exceptions.ArgumentException;
import exceptions.CollectionEmptyException;
import managers.CollectionManager;
import models.Product;

import java.util.ArrayList;

/**
 * A command to output Products whose name of product field value contains the specified substring
 */
public class FilterStartsWithNameCommand implements Command {
    /**
     * An object of class {@code CollectionManager} to access the method filter_starts_with_name and the collection(HashTable<Integer,Product>)
     */
    private final CollectionManager collectionManager;

    /**
     * Constructor {@code ClearCommand} with the specified object of {@code CollectionManager}
     *
     * @param collectionManager Specified object of {@code CollectionManager}
     */
    public FilterStartsWithNameCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Prints information on all products where the product name field starts with the specified substring
     *
     * @param argument a String used to filter name of product field in collection
     * @return The command has been successfully executed or not
     */
    @Override
    public boolean execute(String argument) {
        boolean success = false;
        if (collectionManager.getCollection().isEmpty()) throw new CollectionEmptyException("collection is empty");
        if (!argument.equals("")) {
            ArrayList<Product> products = collectionManager.filter_starts_with_name(argument);
            if (!products.isEmpty()) {
                System.out.println("\n\t\t\t\t\t\t\t\tName the following product(s) starts whit the String \"" + argument + "\" :");
                products.forEach(product -> {
                    System.out.println("-".repeat(70) + "\n" + product);
                });
                success = true;
            } else System.err.println("no product found which it's name field value starts with \"" + argument + "\"");
        } else throw new ArgumentException("No argument!!!");
        return success;
    }

    /**
     * A method for providing descriptions about the command
     *
     * @return Returns a String that descriptions the {@code FilterStartsWithNameCommand}
     */
    @Override
    public String getDescription() {
        return "output elements whose name field value starts with the specified substring.";
    }
}
