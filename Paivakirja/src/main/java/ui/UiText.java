
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
        inst.put("1", "1: login");
        inst.put("2", "2: register as a new user");
        inst.put("3", "3: add a new note of your training session");
        inst.put("4", "4: total minutes used while training");
        inst.put("5", "5: list all notes about your training");
        inst.put("6", "6: delete note by date");
        inst.put("x", "x: logout and close the program");
        return inst;
    }

    public void start() {
        System.out.println("**Why r u not working on tira**");
        printInstructions();
       
    }

    private void printInstructions() {
        System.out.println("Choose one of the following commands:");
        for (Map.Entry<String, String> entry : commands.entrySet()) {
            System.out.println(entry.getValue());
        }
    }
    

}


