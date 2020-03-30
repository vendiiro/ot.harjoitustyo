package domain;


import dao.NoteDao;
import dao.UserDao;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class NoteServiceNoteTest {
    
    @Mock
    NoteDao noteDao;
    
    @Mock
    UserDao userDao;
    
    NoteService noteService;
    User user;
    
    @Before
    public void setup() throws SQLException {
        this.noteService = new NoteService(noteDao, userDao);
        this.user = new User("Cynthia Cyclist", "cycy", 1);
        
        when(userDao.findByUsername("cycy")).thenReturn(user);
        noteService.login("cycy");
    }
       
    @Test
    public void creatingNewNoteForCurrentUserWorks() throws Exception {      
        LocalDate date = LocalDate.now();
        Note note = new Note(date, 22, "foo", user, 1);
        
        when(noteDao.create(date, 22, "foo", user)).thenReturn(note);
        
        assertEquals(true, noteService.createNote(date, 22, "foo"));
    }
    
    @Test
    public void gettingKmCountForCurrentUserWorks() throws SQLException {
        when(noteDao.kmTotal(user)).thenReturn(12);
        
        assertEquals(12, noteService.kmTotal());
        
    }
    
    @Test 
    public void gettingListofNotesForCurrentUserWorksWhenThereAreNotes() throws SQLException {
        Note note = new Note(LocalDate.now(), 22, "foo", user, 1);
        List<Note> list = new ArrayList<>();
        list.add(note);

        when(noteDao.getAll(user)).thenReturn(list);
        
        assertEquals(list, noteService.getAll());
        
    }
   
    
    @Test
    public void gettingListOfNotesForCurrentUserWorksWhenThereAreNoNotes() throws SQLException {
        List<Note> list = new ArrayList<>();
        
        when(noteDao.getAll(user)).thenReturn(list);
        
        assertEquals(list, noteService.getAll());    
    }
    
    
    @Test 
    public void noteIsDeletedCorrectlyWhenCurrentUserHasACorrespondingNote() throws SQLException {
        List<Note> list = new ArrayList<>();
        LocalDate date = LocalDate.now();
        Note note = new Note(date, 22, "foo", user, 1);
        list.add(note);
        
        when(noteDao.getAll(user)).thenReturn(list);
        
        assertTrue(noteService.deleteNote(date));
    }
    
    @Test
    public void tryingToDeleteANoteReturnsFalseWhenCurrentUserHasNoCorrespondingNote() throws SQLException {
        List<Note> list = new ArrayList<>();
        LocalDate date = LocalDate.now();
        
        when(noteDao.getAll(user)).thenReturn(list);
        
        assertFalse(noteService.deleteNote(date));
    }
    
    @Test
    public void checkingIfUserIsLoggedInWorksWhenUserIsLoggedIn() throws SQLException {
        assertTrue(noteService.isUserLoggedIn());
    }
    
    @Test
    public void checkingIfUserIsLoggedInWorksWhenUserIsNotLoggedIn() {
        noteService.logout();
        
        assertFalse(noteService.isUserLoggedIn());
    }
       
}