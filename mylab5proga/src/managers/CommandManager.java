package managers;

import commands.*;
import exceptions.ArgumentException;
import exceptions.CollectionEmptyException;
import exceptions.CommandException;
import exceptions.InvalidDataException;
import models.*;

import java.io.IOException;
import java.util.*;

public class CommandManager {
    private Map<String, Command> commandMap;
    private Scanner consoleIn;
    private HistoryCommand historyCommand;
    private InputDataManager inputDataManager;

    public CommandManager(FileManager fileManager, CollectionManager collectionManager) {
        commandMap = new HashMap<>();
        historyCommand = new HistoryCommand();
        consoleIn = new Scanner(System.in);
        commandMap.put("insert", new InsertCommand(collectionManager));
        commandMap.put("clear", new ClearCommand(collectionManager));
        commandMap.put("exit", new ExitCommand());
        commandMap.put("info", new InfoCommand(collectionManager));
        commandMap.put("history", historyCommand);
        commandMap.put("remove_key", new RemoveKeyCommand(collectionManager));
        commandMap.put("replace_if_lowe", new ReplaceIfLoweCommand(collectionManager));
        commandMap.put("save", new SaveCommand(fileManager, collectionManager));
        commandMap.put("max_by_unit_of_measure", new MaxByUnitOfMeasureCommand(collectionManager));
        commandMap.put("update", new UpdateCommand(collectionManager));
        commandMap.put("remove_lower_key", new RemoveLowerKeyCommand(collectionManager));
        commandMap.put("filter_starts_with_name", new FilterStartsWithNameCommand(collectionManager));
        commandMap.put("filter_contains_partNumber", new FilterContainsPartNumberCommand(collectionManager));
        commandMap.put("execute_script", new ExecuteScriptCommand(this, collectionManager));
        commandMap.put("help", new HelpCommand(this));
        commandMap.put("show", new ShowCommand(collectionManager));
        inputDataManager = new InputDataManager(collectionManager);
    }

    public Map<String, Command> getCommandMap() {
        return commandMap;
    }

    private ArrayList<String> readCommand() {
        System.out.println("Please , Enter command (\"help\" to list of commands ) : ");
        String input = consoleIn.nextLine();
        return splitter(input);
    }

    public void runCommand() {
        while (true) {
            ArrayList<String> commandAndArg = readCommand();
            try {
                if (commandMap.containsKey(commandAndArg.get(0))) {
                    try {
                        if (commandMap.get(commandAndArg.get(0)).execute(commandAndArg.get(1)))
                            historyCommand.addToHistory(commandAndArg.get(0));
                    } catch (ArgumentException | CollectionEmptyException exception) {
                        System.err.println(exception.getMessage());
                    } catch (IOException e) {
                        System.out.println("alksjfa");
                    }
                } else throw new CommandException("no such command!!!!");
            } catch (CommandException exception) {
                System.err.println(exception.getMessage());
            } finally {
                System.out.println();
            }
        }
    }

    public void runCommand(String fileContents) {
        if(fileContents.equals("")){
            System.err.println("File is empty ");
            return ;
        }
        String[] lines = fileContents.trim().split("\r\n");
        for (int index = 0; index < lines.length; index++) {
            if (lines[index].equals("")) continue;
            ArrayList<String> commandAndArg = splitter(lines[index]);
            Command command = commandMap.get(commandAndArg.get(0));
            String arg = commandAndArg.get(1);
            System.out.print("Command > "+commandAndArg.get(0));
            System.out.println(arg.equals("") ? arg : " : " + arg);
            if (commandMap.containsKey(commandAndArg.get(0))) {
                try {
                    if (command == commandMap.get("insert")) {
                        System.out.println("The \"insert\" command is in progress ... ");
                        Product product = inputDataManager.getNonUserProduct(new ArrayList<String>(Arrays.asList(Arrays.copyOfRange(lines, ++index, lines.length))));
                        index = index + inputDataManager.getIndex();
                        InsertCommand insert = (InsertCommand) command;
                        if (insert.execute(arg, product))
                            historyCommand.addToHistory(commandAndArg.get(0));
                    } else if (command == commandMap.get("replace_if_lowe")) {
                        System.out.println("The \"replace_if_lowe\" command is in progress ... ");
                        Product product = inputDataManager.getNonUserProduct(new ArrayList<String>(Arrays.asList(Arrays.copyOfRange(lines, ++index, lines.length))));
                        index = index + inputDataManager.getIndex();
                        ReplaceIfLoweCommand replaceIfLowe = (ReplaceIfLoweCommand) command;
                        if (replaceIfLowe.execute(arg, product))
                            historyCommand.addToHistory(commandAndArg.get(0));
                    } else if (command == commandMap.get("update")) {
                        System.out.println("The \"update\" command is in progress ... ");
                        Product product = inputDataManager.getNonUserProduct(new ArrayList<String>(Arrays.asList(Arrays.copyOfRange(lines, ++index, lines.length))));
                        index = index + inputDataManager.getIndex();
                        UpdateCommand update = (UpdateCommand) command;
                        if (update.execute(arg, product))
                            historyCommand.addToHistory(commandAndArg.get(0));
                    } else if (command.execute(arg))
                        historyCommand.addToHistory(commandAndArg.get(0));
                } catch (ArgumentException | CollectionEmptyException | InvalidDataException exception) {
                    System.err.println(exception.getMessage());
                    break;
                } catch (IOException e) {
                    System.err.println("Invalid path!!");
                }
            } else System.err.println("No such command !!!");;
            System.out.println();
        }
    }

    private ArrayList<String> splitter(String line) {
        ArrayList<String> commandAndArg = new ArrayList<>(Arrays.asList(line.trim().split(" ", 2)));
        if (commandAndArg.size() < 2) commandAndArg.add("");
        return commandAndArg;
    }
}