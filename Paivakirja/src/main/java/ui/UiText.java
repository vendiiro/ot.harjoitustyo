package ui;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class UiText {

    private Scanner reader;
    private Map<String, String> commands;

    public UiText() throws Exception {
        this.commands = createInstructions();
    }

    private TreeMap<String, String> createInstructions() {
        TreeMap inst = new TreeMap<>();
        System.out.println("Choose one of the following commands:");

        inst.put("1", "1: create new user");
        inst.put("2", "2: login with your username");
        inst.put("3", "3: add a new note of your training session");
        inst.put("4", "4: total minutes wasted while training");
        inst.put("5", "5: list all notes about your training");
        inst.put("6", "6: delete note by date");
        inst.put("x", "x: logout and close the program and work on tira");
        return inst;
    }

    public void start() {
        System.out.println("**Why r u not working on tira**");
        printInstructions();
        while (true) {
            System.out.println();
            System.out.println("Command: ");
            String command = reader.nextLine();
            System.out.println("");

    }
    }
     private void printInstructions() {
        System.out.println("Choose one of the listed commands:");
        for (Map.Entry<String, String> entry : commands.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

}
