package ui;

import domain.NoteService;
import java.sql.SQLException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class UiText {

    private Scanner reader;
    private Map<String, String> commands;
    private NoteService noteService;

    public UiText(Scanner reader) throws Exception {
        this.reader = reader;
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
        try {
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
                    createUser();
                } else if (command.equals("2")) {
                    System.out.println("login");
                } else if (command.equals("3")) {
                    System.out.println("create note");
                } else if (command.equals("4")) {
                    System.out.println("time wasted while training");
                } else if (command.equals("5")) {
                    System.out.println("get all notes");
                } else if (command.equals("6")) {
                    System.out.println("delete your one of your notes");
                }
            }
        } catch (Exception e) {
            System.out.println("Well that didn't go as planned. Sorry but the program is closed. While we work on that U work on tira!!!!");

        }
    }

    private void printInstructions() {
        System.out.println("Choose one of the listed commands:");
        for (Map.Entry<String, String> entry : commands.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

    private void createUser() throws SQLException {
        if (noteService.isUserLoggedIn() == true) {
            System.out.println("You are already logged in. Logout first if you want to create a new account.");
            return;
        }
        String name = null;
        while (name == null) {
            System.out.println("Name: ");
            name = reader.nextLine();
            name = rulesOfNameAndUsername(name);
        }
        String username = null;
        while (username == null) {
            System.out.println("Username: ");
            username = reader.nextLine();
            username = rulesOfNameAndUsername(username);
        }

        if (noteService.createUser(name, username) == false) {
            System.out.println("The username is already taken. Select different username!");
        } else {
            System.out.println("You have succesfully created new user. Select two to login and continue.");
        }
    }
    public String rulesOfNameAndUsername(String input) {
        String trimnames = input.trim();
        if (trimnames.length() == 0 || trimnames.length() < 2|| trimnames.length() > 30) {
            System.out.println("Name and username must be between 2 and 30 characters in length!");
            trimnames = null;
        }
        return trimnames;
    }
    

}
