package paivakirja.ui;

import java.io.FileInputStream;
import paivakirja.domain.NoteService;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.TreeMap;
import paivakirja.dao.DaoNote;
import paivakirja.dao.DaoUser;
import paivakirja.dao.Database;
import paivakirja.dao.NoteSql;
import paivakirja.dao.UserSql;
import paivakirja.domain.Note;

public class UiText {

    private Scanner reader;
    private Map<String, String> commands;
    private NoteService noteService;

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
        try {
            OUTER:
            while (true) {
                System.out.println();
                System.out.print("Command: ");
                String command = reader.nextLine();
                System.out.println("");
                if (!commands.keySet().contains(command)) {
                    System.out.println("Command was not recognized.");
                    printInstructions();
                }
                switch (command) {
                    case "x":
                        logout();
                        break OUTER;
                    case "1":
                        createUser();
                        break;
                    case "2":
                        login();
                        break;
                    case "3":
                        createNote();
                        break;
                    case "4":
                        totalTimeWasted();
                        break;
                    case "5":
                        System.out.println("get all notes");
                        break;
                    case "6":
                        System.out.println("delete one of your notes");
                        break;
                    default:
                        break;
                }
            }
        } catch (SQLException e) {
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
        if (trimnames.length() == 0 || trimnames.length() < 2 || trimnames.length() > 30) {
            System.out.println("Name and username must be between 2 and 30 characters in length!");
            trimnames = null;
        }
        return trimnames;
    }

    private void logout() {
        if (noteService.isUserLoggedIn() == true) {
            noteService.logout();
            System.out.println("U R now logged out of the app and should work on tira");
        } else {
            System.out.println("The program has been closed.");
        }
    }

    private void login() throws SQLException {
        if (noteService.isUserLoggedIn() == true) {
            System.out.println("Username '" + noteService.getLoggedUser().getUsername() + "' is already logged in.");
            return;
        }
        System.out.println("Username: ");
        String username = reader.nextLine();

        if (noteService.login(username) == false) {
            System.out.println("No such username found. You need to create a new user first!");
        }

    }

    private void createNote() throws SQLException {
        if (noteService.isUserLoggedIn() == false) {
            System.out.println("You need to be logged in to create a new note!");
            return;
        }
        LocalDate date = null;
        while (date == null) {
            System.out.print("Date (dd/mm/yyyy): ");
            String stringDate = reader.nextLine();
            date = stringToDate(stringDate);
            date = trainingInTheFuture(date);
            date = notesHaveDifferentDate(date);
        }
        Integer length = null;
        while (length == null) {
            System.out.print("Training length: ");
            String stringMins = reader.nextLine();
            length = overTraining(stringMins);
        }
        String content = null;
        while (content == null) {
            System.out.println("Notes about your training session: ");
            content = reader.nextLine();
            content = noteContentLenght(content);
        }

        noteService.createNote(date, length, content);
    }

    private String noteContentLenght(String input) {
        String trimInput = input.trim();
        if (trimInput.length() < 10 || trimInput.length() > 500) {
            System.out.println("Invalid, note has to be within 10 and 500 characters.");
            trimInput = null;
        }
        return trimInput;
    }

    private LocalDate notesHaveDifferentDate(LocalDate date) throws SQLException {
        LocalDate result = date;
        List<Note> list = noteService.getAll();
        for (Note note : list) {
            if (note.getDate().equals(date)) {
                result = null;
                System.out.println("You already have a note with this date.");
            }
        }
        return result;
    }

    private LocalDate trainingInTheFuture(LocalDate date) {
        if (date == null) {
            return date;
        }
        if (date.compareTo(LocalDate.now()) > 0) {
            date = null;
            System.out.println("You cant't train in the future!");
        }
        return date;
    }

    private Integer overTraining(String stringMins) {
        try {
            int mins = Integer.parseInt(stringMins);

            if (mins < 10 || mins > 240) {
                System.out.println("Invalid, daily training activity needs to be within 10 minutes and 240.");
                return null;
            }

            return mins;
        } catch (NumberFormatException e) {
            System.out.println("Invalid format for training length! Use numbers.");
        }
        return null;
    }

    private LocalDate stringToDate(String stringDate) {
        try {
            DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = LocalDate.parse(stringDate, DTF);
            return date;
        } catch (DateTimeParseException e) {
            System.out.println("You wrote the date wrong!");
        }
        return null;
    }

    private void totalTimeWasted() throws SQLException {
        if (noteService.isUserLoggedIn() == false) {
            System.out.println("You need to be logged in first to see how much time U waste!");
            return;
        }
        int allMinutes = noteService.totalTimeWasted() % 60;
        int allHours = noteService.totalTimeWasted() / 60;

        
        System.out.println("U have used " + allHours + " hours and " + allMinutes + " minutes exercising.");
    }

    public static void main(String[] args) throws Exception {
        UiText ui = new UiText();
        ui.init();
    }

    private void init() throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("config.properties"));

        String adress = properties.getProperty("Address");
        Database db = new Database(adress);
        db.creatingTables();
        DaoUser userDao = new UserSql(db);
        DaoNote noteDao = new NoteSql(db);
        NoteService nc = new NoteService(noteDao, userDao);

        Scanner sanner = new Scanner(System.in);

        this.reader = sanner;
        this.noteService = nc;
        start();
    }
}
