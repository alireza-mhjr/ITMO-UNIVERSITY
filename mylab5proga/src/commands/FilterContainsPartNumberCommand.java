package commands;

import exceptions.ArgumentException;
import exceptions.CollectionEmptyException;
import managers.CollectionManager;
import models.Product;
import java.util.ArrayList;
/**
 * A command to output Products whose partNumber field value contains the specified substring
 */
public class FilterContainsPartNumberCommand implements Command{
    /**
     * An object of class {@code CollectionManager} to access the method filter_contains_part_number and the collection(HashTable<Integer,Product>)
     */
    private final CollectionManager collectionManager;
    /**
     * Constructor {@code ClearCommand} with the specified object of {@code CollectionManager}
     *
     * @param collectionManager Specified object of {@code CollectionManager}
     */
    public FilterContainsPartNumberCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }

    /**
     * Print information ao all Products where the partNumber field value contains the specified substring
     * @param argument Is a string containing the string for filtering Part Number field to executing the command.
     * @return The command has been successfully executed or not
     */
    @Override
    public boolean execute(String argument) {
        boolean success = false;
        if(collectionManager.getCollection().isEmpty()) throw new CollectionEmptyException("collection is empty");
        if(!argument.equals("")) {
            ArrayList<Product> products = collectionManager.filter_contains_part_number(argument);
            if (!products.isEmpty()) {
                System.out.println("\n\t\t\t\t\t\t\t\tpartNumber field the following product(s) have the String \"" +argument +"\" :");
                products.forEach(product->{
                    System.out.println("-".repeat(70)+"\n"+product);
                });
                success = true;
            } else System.err.println("no product found which it's partNumber field value contains \"" + argument+"\"");
        }else throw new ArgumentException("No argument!!!");
        return success;
    }

    /**
     * A method for providing descriptions about the command
     *
     * @return Returns a String that descriptions the {@code FilterContainsPartNumberCommand}
     */
    @Override
    public String getDescription() {
        return "output elements whose partNumber field value contains the specified substring.";
    }
}
