package commands;

import exceptions.*;
import managers.CollectionManager;
import managers.InputDataManager;
import models.Product;

/**
 * A command that moves a product with a key marked with a new product if the product
 * is smaller than the new product(products are compared based on their price)
 */
public class ReplaceIfLoweCommand implements Command {
    /**
     * An object of class {@code CollectionManager} to access the methods replace_if_lowe(int key,Product product) and
     * generateId() and the collection(HashTable<Integer,Product>)
     */
    private final CollectionManager collectionManager;
    /**
     * An object of class {@code InputDataManager} to get new product information from the user
     */
    private final InputDataManager inputDataManager;

    /**
     * Constructor {@code ReplaceIfLoweCommand} with the specified object of {@code CollectionManager}
     * and initializes object of class InputDataManager
     *
     * @param collectionManager Specified object of {@code CollectionManager}
     */
    public ReplaceIfLoweCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
        inputDataManager = new InputDataManager(collectionManager);
    }

    /**
     * It's a method that replaces a new product received from the user with a product
     * with specified key if the price of the new product is lower
     *
     * @param argument It's a string that shows the product key in the collection that
     *                 is to be replaced
     * @return If the new product is replaced successfully, the method returns true
     */
    @Override
    public boolean execute(String argument) {
        boolean success = false;
        if (collectionManager.getCollection().isEmpty())
            throw new CollectionEmptyException("collection is empty , there is no products to replace");
        if (!argument.equals("")) {
            try {
                int key = Integer.parseInt(argument);
                if (containsKey(key)) {
                    System.out.println("Product information with key \"" + key + "\" :");
                    System.out.println(collectionManager.getCollection().get(key) + "\n" + "-".repeat(70));
                    if (collectionManager.replace_if_lowe(key, inputDataManager.readUserProduct())) {
                        System.out.println("\n\t\t\t\t\t\t\t\tThe inputted product successfully replaced");
                        success = true;
                    } else
                        System.err.println("The inputted product is NOT LOWER than a product with key \"" + key + "\"");
                } else {
                    System.out.println("" + collectionManager.getCollection().keySet());
                    throw new ArgumentException("This key \"" + argument + "\" not exist in the collection!!!");
                }
            } catch (NumberFormatException exception) {
                throw new ArgumentException("Invalid KEY!!!");
            }
        } else throw new ArgumentException("No argument!!!");
        return success;
    }

    /**
     * It's a method that replaces a new product sent to the method with a product
     * with specified key if the price of the new product is lower
     *
     * @param argument It's a string that shows the product key in the collection that
     *                 is to be replaced
     * @param product  It's a product that's going to be replaced
     * @return If the new product is replaced successfully, the method returns true
     */
    public boolean execute(String argument, Product product) {
        product.setId(collectionManager.generateId());
        if (product.validate()) {
            boolean success = false;
            if (collectionManager.getCollection().isEmpty())
                throw new CollectionEmptyException("collection is empty , there is no products to replace");
            if (!argument.equals("")) {
                try {
                    int key = Integer.parseInt(argument);
                    if (containsKey(key)) {
                        System.out.println("Product information with key \"" + key + "\" :");
                        System.out.println(collectionManager.getCollection().get(key) + "\n" + "-".repeat(70));
                        if (collectionManager.replace_if_lowe(key, product)) {
                            System.out.println("\n\t\t\t\t\t\t\t\tThe inputted product successfully replaced");
                            success = true;
                        } else
                            System.err.println("The inputted product is NOT LOWER than a product with key \"" + key + "\"");
                    } else throw new ArgumentException("This key \"" + argument + "\" not exist in the collection!!!");
                } catch (NumberFormatException exception) {
                    throw new ArgumentException("Invalid KEY!!!");
                }
            } else throw new ArgumentException("No argument!!!");
            return success;
        } else throw new InvalidDataException();
    }

    /**
     * It's a method that checks if there's a key in the collection
     *
     * @param key Is the key to be checked
     * @return If there is a key in the collection, method returns true
     */
    private boolean containsKey(int key) {
        return collectionManager.getCollection().keySet().contains(key);
    }

    /**
     * A method for providing descriptions about the command
     *
     * @return Returns a descriptions the {@code ReplaceIfLoweCommand}
     */
    @Override
    public String getDescription() {
        return "replace the value by key if the new value is less than the old.";
    }
}
