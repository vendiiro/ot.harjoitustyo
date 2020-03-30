package domain;

import dao.NoteDao;
import dao.UserDao;
import java.sql.SQLException;
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
public class NoteServiceUserTest {
    
    @Mock
    NoteDao noteDao;
    
    @Mock
    UserDao userDao;
    
    NoteService noteService;
    
    @Before
    public void setup() {
        this.noteService = new NoteService(noteDao, userDao);
    }
    
    
    @Test
    public void creatingNewUserWithNewUsernameWorks() throws SQLException {
        
        when(userDao.findByUsername("username")).thenReturn(null);
        
        assertEquals(true, noteService.createUser("name", "username"));
        
    }
    
    
    @Test
    public void creatingNewUserWithTakenUsernameReturnsFalse() throws SQLException {  
        User user = new User("name", "username", 123);
        when(userDao.findByUsername("username")).thenReturn(user);
        
        noteService.createUser("name", "username");
        assertEquals(false, noteService.createUser("name", "username"));
    }
    
    @Test
    public void loggingOutWorks() throws SQLException {
        
        User user = new User("name", "username", 123);
        when(userDao.findByUsername("username")).thenReturn(user);
        
        noteService.login("username");
        noteService.logout();
        
        assertEquals(noteService.getLoggedUser(), null);
    }
    
    @Test
    public void loginWithExistingUsernameWorks() throws SQLException {
        
        User user = new User("name", "username", 123);
        when(userDao.findByUsername("username")).thenReturn(user);
        
        assertTrue(noteService.login("username"));        
        assertEquals(user, noteService.getLoggedUser());    
    }
    
    
    @Test 
    public void loginWithNonExistingUsernameDoesNotWork() throws SQLException {
        
        when(userDao.findByUsername("abcd")).thenReturn(null);
        
        assertFalse(noteService.login("abcd"));
    }


    
}