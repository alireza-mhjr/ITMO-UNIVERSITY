package main;

import json.CollectionDeserialize;
import managers.CollectionManager;
import managers.CommandManager;
import managers.FileManager;


public class Main {
    private static final String FILE = System.getenv("file");
    public static void main(String[] args) {
        System.out.println( "\r\n _____                   _                         __  __                                         \r\n|  _  |                 | |                       |  \\/  |                                        \r\n| (_) | _ __   ___    __| | _   _    __  _______  | \\  / |  __ _  _ __    __ _   __ _   ___  _ __ \r\n|  __/ | '__| / _ \\  / _  || | | | / __||__   __| | |\\/| | / _` || '_ \\  / _` | / _` | / _ \\| '__|\r\n| |    | |   | (_) || (_) || \\_/ || |__    | |    | |  | || (_| || | | || (_| || (_| ||  __/| |   \r\n|_|    |_|    \\___/  \\___/  \\___/  \\___|   |_|    |_|  |_| \\__,_||_| |_| \\__,_| \\__, | \\___||_|\r\n                                                                                 __/ |            \r\n                                                                                |___/             \r\n\t\t\t\t\t\t\t\t\t by Mohajer Alireza\r\n");
        FileManager fileManager = new FileManager(FILE);
        CollectionManager collectionManager = new CollectionManager();
        CommandManager commandManager = new CommandManager(fileManager,collectionManager);
        CollectionDeserialize collectionDeserialize = new CollectionDeserialize(collectionManager);
        System.out.println(collectionDeserialize.deserialize(fileManager.read())?"Collection successfully deserialized )":"unable to deserialize collection");
        System.out.println(collectionManager.collectionInfo());
        commandManager.runCommand();

    }
}




