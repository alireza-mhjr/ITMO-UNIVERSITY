package commands;

import exceptions.ArgumentException;
import managers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A command to execute the script
 */
public class ExecuteScriptCommand implements Command {
    /**
     * An object of class {@code CommandManager} to access the method runCommand() to execute commands within the file
     */
    private final CommandManager commandManager;
    /**
     * An object of class {@code CollectionManager} to access the methods and the collection(HashTable<Integer,Product>)
     */
    private final CollectionManager collectionManager;

    /**
     * Constructor {@code ExecuteScriptCommand} with the specified object of {@code CollectionManager}
     * and specified object of {@code CommandManager}
     *
     * @param collectionManager Specified object of {@code CollectionManager}
     * @param commandManager    Specified object of {@code CommandManager}
     */
    public ExecuteScriptCommand(CommandManager commandManager, CollectionManager collectionManager) {
        this.commandManager = commandManager;
        this.collectionManager = collectionManager;
    }

    /**
     * @param argument Is a string containing the argument(ID Product, Key of Product in the collection, string
     *                 for filtering names and Part Number field, address of file) for executing the command.
     * @return that indicates whether the script has been successfully executed
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (collectionManager.getCollection() == null) {
                System.out.println("This command doesn't work right now");
                return false;
            }
            argument = argument.trim();
            if (checkRecursion(Path.of(argument), new ArrayDeque<>())) {
                System.out.println("While parsing the script, recursion was detected. Eliminate it before execution.");
                return false;
            }
        } catch (IOException e) {
            System.err.println("Invalid path !!!");
        }
        if (!argument.equals("")) {
            FileManager fileManager = new FileManager(argument);
            commandManager.runCommand(fileManager.read());
            System.out.println("\n\t\t\t\t\t\t\t\tThe execution of the script file \"" + fileManager.getFileName(argument) + "\" is finished");
        } else throw new ArgumentException("No path found !!!");
        return true;
    }

    /**
     * A method that checks Recursion path
     *
     * @param path  The path to the file that is currently executing
     * @param stack Stack to save the paths that are in the file
     * @return A boolean indicating whether there is a recursion
     * @throws IOException If an I/O error occurs reading from the file or a malformed or unmappable byte sequence is read
     */
    private boolean checkRecursion(Path path, ArrayDeque<Path> stack) throws IOException {
        if (stack.contains(path)) return true;
        stack.addLast(path);
        String str = Files.readString(path);
        Pattern pattern = Pattern.compile("execute_script .*");
        Matcher patternMatcher = pattern.matcher(str);
        while (patternMatcher.find()) {
            Path newPath = Path.of(patternMatcher.group().split(" ", 2)[1]);
            if (checkRecursion(newPath, stack)) return true;
        }
        stack.removeLast();
        return false;
    }

    /**
     * A method for providing descriptions about the executeScriptCommand
     *
     * @return Returns a String that descriptions the {@code executeScriptCommand}
     */
    @Override
    public String getDescription() {
        return "read and execute the script from the specified file. The script contains commands in the same form in which they are entered by the user in interactive mode.";
    }
}
