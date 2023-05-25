package commands;

import exceptions.ArgumentException;
import exceptions.CollectionEmptyException;
import managers.CollectionManager;
import models.Product;

/**
 * A command that prints Product information with the maximum UnitOfMeasure field value
 */
public class MaxByUnitOfMeasureCommand implements Command {
    /**
     * An object of class {@code CollectionManager} to access the method max_by_unit_of_measure()
     */
    private final CollectionManager collectionManager;

    /**
     * Constructor {@code MaxByUnitOfMeasureCommand} with the specified object of {@code CollectionManager}
     *
     * @param collectionManager Specified object of {@code CollectionManager}
     */
    public MaxByUnitOfMeasureCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * A method prints Product information with the maximum UnitOfMeasure field value
     *
     * @param argument It is a string used to check the correctness of the inserted command(For this command the argument must be empty)
     * @return a boolean variable that indicates whether the command has been successfully executed
     */
    @Override
    public boolean execute(String argument) {
        boolean success = false;
        if (!argument.equals("")) throw new ArgumentException("this command can't have argument");
        if (collectionManager.getCollection().isEmpty()) throw new CollectionEmptyException("collection is empty");
        Product productWithMaxUnitOfMeasure = collectionManager.max_by_unit_of_measure();
        if (productWithMaxUnitOfMeasure != null) {
            System.out.println("\n\t\t\t\t\t\t\t\tThe product below has maximum UnitOfMeasure value : \n" + productWithMaxUnitOfMeasure);
            success = true;
        } else System.err.println("The unit of measure field value for all products in collection is null.");
        return success;
    }

    /**
     * A method for providing descriptions about the command
     *
     * @return Returns a descriptions the {@code MaxByUnitOfMeasureCommand}
     */
    @Override
    public String getDescription() {
        return "output any object from the collection whose UnitOfMeasure field value is the maximum.";
    }
}
