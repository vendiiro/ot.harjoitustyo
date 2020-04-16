/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;
import paivakirja.dao.DaoNote;
import paivakirja.dao.DaoUser;
import paivakirja.domain.Note;
import paivakirja.domain.NoteService;
import paivakirja.domain.User;

/**
 *
 * @author iiro
 */
@RunWith(MockitoJUnitRunner.class)
public class NoteServiceNoteTest {
    
    @Mock
    DaoNote daoNote;
    
    @Mock
    DaoUser daoUser;
    
    NoteService noteService;
    User user;
    
    @Before
    public void setup() throws SQLException {
        this.noteService = new NoteService(daoNote, daoUser);
        this.user = new User("Cynthia Cyclist", "cycy", 1);
        
        when(daoUser.getUsingUsername("cycy")).thenReturn(user);
        noteService.login("cycy");
    }
       
    @Test
    public void creatingNewNoteForCurrentUserWorks() throws Exception {      
        LocalDate date = LocalDate.now();
        Note note = new Note(date, 22, "foo", user, 1);
        
        when(daoNote.create(date, 22, "foo", user)).thenReturn(note);
        
        assertEquals(true, noteService.createNote(date, 22, "foo"));
    }
    
    @Test
    public void gettingKmCountForCurrentUserWorks() throws SQLException {
        when(daoNote.totalTimeWasted(user)).thenReturn(12);
        
        assertEquals(12, noteService.totalTimeWasted());
        
    }
    
    @Test 
    public void gettingListofNotesForCurrentUserWorksWhenThereAreNotes() throws SQLException {
        Note note = new Note(LocalDate.now(), 22, "foo", user, 1);
        List<Note> list = new ArrayList<>();
        list.add(note);

        when(daoNote.getAll(user)).thenReturn(list);
        
        assertEquals(list, noteService.getAll());
        
    }
   
    
    @Test
    public void gettingListOfNotesForCurrentUserWorksWhenThereAreNoNotes() throws SQLException {
        List<Note> list = new ArrayList<>();
        
        when(daoNote.getAll(user)).thenReturn(list);
        
        assertEquals(list, noteService.getAll());    
    }
    
    
    @Test 
    public void noteIsDeletedCorrectlyWhenCurrentUserHasACorrespondingNote() throws SQLException {
        List<Note> list = new ArrayList<>();
        LocalDate date = LocalDate.now();
        Note note = new Note(date, 22, "foo", user, 1);
        list.add(note);
        
        when(daoNote.getAll(user)).thenReturn(list);
        
        assertTrue(noteService.deleteNote(date));
    }
    
    @Test
    public void tryingToDeleteANoteReturnsFalseWhenCurrentUserHasNoCorrespondingNote() throws SQLException {
        List<Note> list = new ArrayList<>();
        LocalDate date = LocalDate.now();
        
        when(daoNote.getAll(user)).thenReturn(list);
        
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
