package commands;

import exceptions.ArgumentException;
import exceptions.InvalidDataException;
import exceptions.InvalidKeyException;
import managers.CollectionManager;
import managers.InputDataManager;
import models.Product;

/**
 * A command to add a new product in the collection with the specified key
 */
public class InsertCommand implements Command {
    /**
     * An object of class {@code CollectionManager} to access the methods insert() and
     * generateId() and the collection(HashTable<Integer,Product>)
     */
    private final CollectionManager collectionManager;
    /**
     * An object of class {@code InputDataManager} to get new product information from the user
     */
    private final InputDataManager inputDataManager;

    /**
     * Constructor {@code InsertCommand} with the specified object of {@code CollectionManager}
     * and initializes object of class InputDataManager
     *
     * @param collectionManager Specified object of {@code CollectionManager}
     */
    public InsertCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
        this.inputDataManager = new InputDataManager(collectionManager);
    }

    /**
     * This is a method used to insert a product whose information is entered by the <b>user</b>
     *
     * @param argument It's a string that shows the key in which the new product is going to be inserted in the collection
     * @return If the product has been successfully inserted ,the method returns a Boolean value true
     */
    @Override
    public boolean execute(String argument) {
        boolean success = false;
        if (!argument.equals("")) {
            try {
                int key = Integer.parseInt(argument);
                Product product = inputDataManager.readUserProduct();
                product.setId(collectionManager.generateId());
                System.out.println("Id of PRODUCT : " + product.getId());
                if (collectionManager.insert(key, product)) {
                    System.out.println("\n\t\t\t\t\t\t\t\tThe inputted product has been added to the collection");
                    success = true;
                }
            } catch (NumberFormatException exception) {
                throw new InvalidKeyException("Invalid \"KEY\" , The key must be Integer!!!");
            }
        } else throw new ArgumentException("No argument!!!");
        return success;
    }

    /**
     * This method inserts the given product in the given key in the collection
     *
     * @param argument A string that represent the key of the collection in which the given product is to be inserted
     * @param product  An object of Product class that is inserted in the specified key
     * @return If the product has been successfully inserted ,the method returns a Boolean value true
     */
    public boolean execute(String argument, Product product) {
        if (product.validate()) {
            boolean success = false;
            if (!argument.equals("")) {
                try {
                    int key = Integer.parseInt(argument);
                    if (collectionManager.insert(key, product)) {
                        System.out.println("\n\t\t\t\t\t\t\t\tThe inputted product has been added to the collection");
                        success = true;
                    }
                } catch (NumberFormatException exception) {
                    throw new InvalidKeyException("Invalid \"KEY\" , The key must be Integer!!!");
                }
            } else throw new ArgumentException("No argument!!!");
            return success;
        } else throw new InvalidDataException();
    }

    /**
     * A method for providing descriptions about the command
     *
     * @return Returns a descriptions the {@code InsertCommand}
     */
    @Override
    public String getDescription() {
        return "add a new element with the specified key.";
    }
}

