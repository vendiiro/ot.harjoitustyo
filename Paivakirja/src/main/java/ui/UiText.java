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
        try{
         while (true) {
                System.out.println();
                System.out.println("Command: "); 
                String command = reader.nextLine();
                System.out.println("");
                if (!commands.keySet().contains(command)) {
                    System.out.println("Command was not recognized.");
                    printInstructions();
                }
                if (command.equals("x")) {
                    System.out.println("logout");
                    break;
                } else if (command.equals("1")) {
                    System.out.println("create user");
                } else if (command.equals("2")) {
                    System.out.println("login");
                } else if (command.equals("3")) {
                    System.out.println("create note");        
                } else if (command.equals("4")) {
                    System.out.println("time wasted while training");
                } else if (command.equals("5")) {
                    System.out.println("get all notes");  
                } else if (command.equals("6")) {
                    System.out.println("get all notes");
                } 
            }
        } catch (Exception e)  {
            System.out.println("Well that didn't go as planned. Sorry but the program is closed. While we work on that U work on tira!!!!");
        
    
    }
    }
     private void printInstructions() {
        System.out.println("Choose one of the listed commands:");
        for (Map.Entry<String, String> entry : commands.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

}
