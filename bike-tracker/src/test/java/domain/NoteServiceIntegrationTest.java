package domain;

import dao.Database;
import dao.NoteDao;
import dao.SqlNoteDao;
import dao.SqlUserDao;
import dao.UserDao;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDate;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class NoteServiceIntegrationTest {

    NoteService noteService;
    
    @Before
    public void setup() throws Exception {
        Database database = new Database("jdbc:sqlite:test-tietokanta2.db");
        database.createTables();
        
        UserDao userDao = new SqlUserDao(database); 
        NoteDao noteDao = new SqlNoteDao(database);
        NoteService noteService = new NoteService(noteDao, userDao);
        
        this.noteService = noteService;
    }    
    
    @After
    public void cleanup() throws IOException {
        Files.deleteIfExists(Paths.get("test-tietokanta2.db"));
    }
    
    private void loginUser() throws SQLException {
        noteService.createUser("Helena Testaaja", "hellu");
        noteService.login("hellu");        
    }
    
    @Test
    public void creatingNewUserWithNewUsernameWorks() throws SQLException {
        assertTrue(noteService.createUser("Tiina Testaaja", "tipi")); 
    }
    
    @Test
    public void creatingNewUserWithTakenUsernameReturnsFalse() throws SQLException {
        noteService.createUser("Teppo Testaaja", "tepa");
        assertFalse(noteService.createUser("Teppo Testaaja", "tepa"));
    }
    
    @Test
    public void loggingOutWorks() throws SQLException {
        loginUser();
        noteService.logout();
        
        assertEquals(null, noteService.getLoggedUser());
    }
    
    @Test
    public void loginWithExistingUsernameWorks() throws SQLException {
       noteService.createUser("Bertta Jokinen", "bertta");
       
       assertTrue(noteService.login("bertta"));
    }
    
    @Test 
    public void loginWithNonExistingUsernameDoesNotWork() throws SQLException {
        assertFalse(noteService.login("spiderman"));
    }
    
    @Test
    public void creatingNewNoteForCurrentUserWorks() throws Exception {
        loginUser();
        
        assertTrue(noteService.createNote(LocalDate.now(), 15, "mountain biking"));
    }
    
    @Test
    public void gettingKmCountForCurrentUserWorks() throws SQLException {
        loginUser();
        noteService.createNote(LocalDate.now(), 18, "mountain biking");

        assertEquals(18, noteService.kmTotal());
    }
    
    @Test 
    public void gettingListofNotesForCurrentUserWorksWhenThereAreNotes() throws SQLException {
        loginUser();
        noteService.createNote(LocalDate.now(), 18, "mountain biking");
        
        assertEquals(1, noteService.getAll().size());
        
    }
    
    @Test
    public void gettingListOfNotesForCurrentUserWorksWhenThereAreNoNotes() throws SQLException {
        loginUser();
        
        assertEquals(0, noteService.getAll().size());
    }
    
    @Test 
    public void noteIsDeletedCorrectlyWhenCurrentUserHasACorrespondingNote() throws SQLException {
        loginUser();
        noteService.createNote(LocalDate.now(), 18, "mountain biking");
        
        assertTrue(noteService.deleteNote(LocalDate.now()));
    }
    
    @Test
    public void tryingToDeleteANoteReturnsFalseWhenCurrentUserHasNoCorrespondingNote() throws SQLException {
        loginUser();
        
        assertFalse(noteService.deleteNote(LocalDate.now()));
    }
    
    @Test
    public void checkingIfUserIsLoggedInWorksWhenUserIsLoggedIn() throws SQLException {
        loginUser();
        
        assertTrue(noteService.isUserLoggedIn());
    }
    
    @Test
    public void checkingIfUserIsLoggedInWorksWhenUserIsNotLoggedIn() {
        assertFalse(noteService.isUserLoggedIn());
    }
}
