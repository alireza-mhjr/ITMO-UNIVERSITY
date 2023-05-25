package commands;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import exceptions.ArgumentException;
import exceptions.SerializeException;
import json.ZonedDateTimeSerializer;
import managers.CollectionManager;
import managers.FileManager;
import models.Product;

import java.lang.reflect.Type;
import java.time.ZonedDateTime;
import java.util.Hashtable;

/**
 * It is the command that stores the product information of the collection in the specified JSON file
 */
public class SaveCommand implements Command {
    /**
     * It's an object of the class {@code FileManager} so that through the write method of this
     * class we can store product information in the file
     */
    private final FileManager fileManager;
    /**
     * An object of class {@code CollectionManager} to access the collection(HashTable<Integer,Product>)
     */
    private final CollectionManager collectionManager;

    /**
     * Constructor {@code InsertCommand} with the specified object of {@code CollectionManager}
     *
     * @param collectionManager Specified object of {@code CollectionManager}
     * @param fileManager       Specified object of {@code FileManager}
     */
    public SaveCommand(FileManager fileManager, CollectionManager collectionManager) {
        this.fileManager = fileManager;
        this.collectionManager = collectionManager;
    }

    /**
     * It's a method that saves the information of the products in the collection in the file with the help of the Gson-2.8.2 library
     *
     * @param argument It is a string used to check the correctness of the inserted command(For this command the argument must be empty)
     * @return If the products information is successfully saved in the file, the method returns true
     */
    @Override
    public boolean execute(String argument) {
        boolean success = false;
        if (!argument.equals("")) throw new ArgumentException("this command can't have argument");
        Type collectionType = new TypeToken<Hashtable<Integer, Product>>() {
        }.getType();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeSerializer())
                .setPrettyPrinting()
                .create();
        if (fileManager.write(gson.toJson(collectionManager.getCollection(), collectionType))) {
            System.out.println("\n\t\t\t\t\t\t\t\tThe collection was successfully written in the file");
            success = true;
        } else throw new SerializeException("can't serialize collection ");
        return success;
    }

    /**
     * A method for providing descriptions about the command
     *
     * @return Returns a descriptions the {@code SaveCommand}
     */
    @Override
    public String getDescription() {
        return "save the collection to a file.";
    }
}
