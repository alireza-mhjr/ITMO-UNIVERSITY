package commands;

import exceptions.*;
import managers.CollectionManager;
import managers.InputDataManager;
import models.Product;

/**
 * A command that updates product information with a specified ID
 */
public class UpdateCommand implements Command {
    /**
     * An object of class {@code CollectionManager} to access the methods getId() and update(long id, Product product) the collection(HashTable<Integer,Product>)
     */
    private final CollectionManager collectionManager;
    /**
     * An object of class {@code InputDataManager} to get new product information from the <b>user</b>
     */
    private final InputDataManager inputDataManager;

    /**
     * Constructor {@code UpdateCommand} with the specified object of {@code CollectionManager}
     * and initializes object of class {@code InputDataManager}
     *
     * @param collectionManager Specified object of {@code CollectionManager}
     */
    public UpdateCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
        inputDataManager = new InputDataManager(collectionManager);
    }

    /**
     * It's a method that updates product information taken from the <b>user</b> with a specified ID
     *
     * @param argument Is a string that shows the product ID that is going to be updated
     * @return If a product with a specified ID has been successfully updated, the method returns true
     */
    @Override
    public boolean execute(String argument) {
        if (collectionManager.getCollection().isEmpty()) {
            throw new CollectionEmptyException("collection is empty , there is no products to replace");
        }
        boolean success = false;
        if (!argument.equals("")) {
            try {
                long id = Long.parseLong(argument);
                if (id <= 0) throw new InvalidKeyException("id should be greater than 0 ");
                if (collectionManager.getIds().contains(id)) {
                    if (collectionManager.update(id, inputDataManager.readUserProduct())) {
                        System.out.println("\n\t\t\t\t\t\t\t\tThe product with ID \"" + id + "\" has been updated");
                        success = true;
                    }
                } else {
                    System.err.println("no product has been found with id \"" + id + "\"");
                    System.out.println("List of product IDs in the collection :\n" + collectionManager.getIds());
                }
            } catch (NumberFormatException exception) {
                throw new InvalidIdException("Invalid ID!!!");
            }
        } else throw new ArgumentException("No argument!!!");
        return success;
    }

    /**
     * It's a method that updates product information with a specified ID
     *
     * @param argument Is a string that shows the product ID that is going to be updated
     * @param product  It shows a new product that is going to be replaced by a product that has a specified ID(update)
     * @return If the product information is successfully updated with the specified ID,the method returns true
     */
    public boolean execute(String argument, Product product) {
        product.setId(collectionManager.generateId());
        if (product.validate()) {
            if (collectionManager.getCollection().isEmpty()) {
                throw new CollectionEmptyException("collection is empty , there is no products to replace");
            }
            boolean success = false;
            if (!argument.equals("")) {
                try {
                    long id = Long.parseLong(argument);
                    if (id <= 0) throw new InvalidKeyException("id should be greater than 0 ");
                    if (collectionManager.getIds().contains(id)) {
                        if (collectionManager.update(id, product)) {
                            System.out.println("\n\t\t\t\t\t\t\t\tThe product with ID \"" + id + "\" has been updated");
                            success = true;
                        }
                    } else {
                        System.err.println("no product has been found with id \"" + id + "\"");
                        System.out.println("List of product IDs in the collection :\n" + collectionManager.getIds());
                    }
                } catch (NumberFormatException exception) {
                    throw new InvalidIdException("Invalid ID!!!");
                }
            } else throw new ArgumentException("No argument!!!");
            return success;
        } else throw new InvalidDataException();
    }

    /**
     * A method for providing descriptions about the command
     *
     * @return Returns a descriptions the {@code UpdateCommand}
     */
    @Override
    public String getDescription() {
        return "update the value of a collection element whose id is equal to the specified one.";
    }
}
