package iu;


import dao.Database;
import dao.NoteDao;
import dao.SqlNoteDao; 
import dao.SqlUserDao; 
import dao.UserDao;
import domain.Note;
import domain.NoteService;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.TreeMap;

public class TextUi {

    private Scanner scanner;
    private NoteService noteService;
    private Map<String, String> commands;
    
    public TextUi() throws Exception {
        this.commands = createCommands();
    }
    
    private TreeMap<String, String> createCommands() {
        TreeMap commands = new TreeMap<>();
        commands.put("1", "1: login");
        commands.put("2", "2: register as a new user");
        commands.put("3", "3: add a new cycling note");
        commands.put("4", "4: total kilometer count");
        commands.put("5", "5: list all cycling notes");
        commands.put("6", "6: delete note by date");
        commands.put("x", "x: logout and close the program");
        return commands;
    }
    
    public void start() {
        System.out.println("***Kilometer tracker for cycling***");
        printInstructions();
        try {
            while (true) {
                System.out.println();
                System.out.println("Command: "); 
                String command = scanner.nextLine();
                System.out.println("");
                if (!commands.keySet().contains(command)) {
                    System.out.println("Command was not recognized.");
                    printInstructions();
                }
                if (command.equals("x")) {
                    logout();
                    break;
                } else if (command.equals("1")) {
                    login();
                } else if (command.equals("2")) {
                    createUser();
                } else if (command.equals("3")) {
                    createNote();
                } else if (command.equals("4")) {
                    kmTotal();
                } else if (command.equals("5")) {
                    listAllNotes();         
                } else if (command.equals("6")) {
                    deleteNote();
                } 
            }
        } catch (SQLException e) {
            System.out.println("Oops, something went wrong. The program is closed. Please try again later!");
        }
    }

    private void deleteNote() throws SQLException {
        if (noteService.isUserLoggedIn() == false) {
            System.out.println("You need to be logged in to delete a note!");
            return;
        }
        System.out.println("Which day's note would you like to remove?");
        
        LocalDate localDate = null; 
        while (localDate == null) {
            System.out.println("Date (dd/mm/yyyy): ");
            String stringDate = scanner.nextLine();
        
            localDate = formatStringDateToLocalDate(stringDate);
        }
        boolean result = noteService.deleteNote(localDate);
        if (result == false) {
            System.out.println("You don't have a cycling note with this date.");
        } else {
            System.out.println("The note has been deleted.");
        }
    }
    
    private void kmTotal() throws SQLException {
        if (noteService.isUserLoggedIn() == false) {
            System.out.println("You need to be logged in to check your kilometer count!");
            return;
        }
        int total = noteService.kmTotal();
        System.out.println("Your total kilometer count: " + total);
    }
    
    private void listAllNotes() throws SQLException{
        if (noteService.isUserLoggedIn() == false) {
            System.out.println("You need to be logged in to list all notes!");
            return;
        }
        
        List<Note> notes = noteService.getAll();
        if (notes.size() == 0) {
            System.out.println("You have no cycling notes yet.");
        } else {
            for (Note n : notes){
                System.out.println(n.toString());
                System.out.println("***");
            }
        }
    }
    
    private void createNote() throws SQLException {   
        if (noteService.isUserLoggedIn() == false) {
            System.out.println("You need to be logged in to create a new note!");
            return;
        }
        LocalDate localDate = null;
        while (localDate == null) {
            System.out.println("Date (dd/mm/yyyy): "); 
            String stringDate = scanner.nextLine();        
            localDate = formatStringDateToLocalDate(stringDate);
            localDate = validateDateInputDateNotInTheFuture(localDate);  
            localDate = validateDateInputDateIsUnique(localDate);
        }       
        Integer km = null;
        while (km == null) {
            System.out.println("Kilometers: ");
            String stringKm = scanner.nextLine();
            km = validateKmInput(stringKm);
        }        
        String content = null;
        while (content == null) {
            System.out.println("Your notes about the day: ");
            content = scanner.nextLine();
            content = validateNoteContentInput(content);
        }
        
        noteService.createNote(localDate, km, content);
    }
    
    private String validateNoteContentInput(String content) {
        String trimmedContent = content.trim();
        if (trimmedContent.length() < 1 || trimmedContent.length() > 200) {
                System.out.println("Invalid, note has to be within 1 and 200 characters.");
                trimmedContent = null;
            }
        return trimmedContent;
    }
    
    private LocalDate validateDateInputDateIsUnique(LocalDate date) throws SQLException {
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
    
    private LocalDate validateDateInputDateNotInTheFuture(LocalDate date) {
        if (date == null) {
            return date;
        }
        if (date.compareTo(LocalDate.now()) > 0) {
            date = null;
            System.out.println("Select the current date or a date from the past.");  
        } 
        return date;
    }
    
    
    private Integer validateKmInput(String stringKm) {
        try {
            int km = Integer.parseInt(stringKm);
            
            if (km < 1 || km > 500) {
                System.out.println("Invalid, daily kilometers need to be within 1 and 500.");
                return null;
            }
            
            return km;
        } catch (NumberFormatException e) {
            System.out.println("Invalid format for kilometers! Use numbers.");
        }
        return null;
    }
    
    
    private LocalDate formatStringDateToLocalDate(String stringDate) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate localDate = LocalDate.parse(stringDate, formatter);
            return localDate;
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format!");
        }   
        return null;
    }
    
    private void printInstructions() {
        System.out.println("Choose one of the following commands:");
        for (Map.Entry<String, String> entry : commands.entrySet()) {
            System.out.println(entry.getValue());
        }    
    }
    
    private void logout() {
        if (noteService.isUserLoggedIn() == true) {
            noteService.logout();
            System.out.println("You have been logged out.");
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
        String username = scanner.nextLine();
        
        if (noteService.login(username) == false) {
            System.out.println("No such username. You need to register as a new user first!");
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
            name = scanner.nextLine();
            name = validateNameAndUsername(name);
        }
        String username = null;
        while (username == null) {
            System.out.println("Username: "); 
            username = scanner.nextLine();
            username = validateNameAndUsername(username);
        }
                
        
        if (noteService.createUser(name, username) == false) {
            System.out.println("The username is already taken. Select a unique username!");
        } else {
            System.out.println("You have now been registered. Select login (1) to continue.");
        }
    }
    
    public String validateNameAndUsername(String input) {
        String trimmedInput = input.trim();
        if (trimmedInput.length() == 0 || trimmedInput.length() < 2|| trimmedInput.length() > 30) {
            System.out.println("Name and username must be between 2 and 30 characters in length!");
            trimmedInput = null;
        }
        return trimmedInput;
    }
    
    public static void main(String[] args) throws Exception {
        TextUi ui = new TextUi();
        ui.init();  
    }
    
    private void init() throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("config.properties"));
        
        String databaseAddress = properties.getProperty("databaseAddress");
        Database db = new Database(databaseAddress);
        db.createTables();
        UserDao userDao = new SqlUserDao(db); 
        NoteDao noteDao = new SqlNoteDao(db);
        NoteService noteService = new NoteService(noteDao, userDao);
        
        Scanner scanner = new Scanner(System.in);
                
        this.scanner = scanner;
        this.noteService = noteService;
        start();    
    }
}