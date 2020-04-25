/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.SQLException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;
import paivakirja.dao.DaoNote;
import paivakirja.dao.DaoUser;
import paivakirja.domain.NoteService;
import paivakirja.domain.User;

/**
 *
 * @author iiro
 */
@RunWith(MockitoJUnitRunner.class)

public class NoteServiceUserTest {

    @Mock
    DaoNote daoNote;
    @Mock
    DaoUser daoUser;

    NoteService noteService;

    @Before
    public void setup() {
        this.noteService = new NoteService(daoNote, daoUser);
    }

    @Test
    public void creatingNewUserWithUniqueUsernameWorks() throws SQLException {

        when(daoUser.getUsingUsername("usr")).thenReturn(null);

        assertEquals(true, noteService.createUser("name", "usr"));

    }

    @Test
    public void creatingNewUserWithTakenUsernameDoesNotWork() throws SQLException {
        User user = new User("iiro", "ipe", 1);
        when(daoUser.getUsingUsername("ipe")).thenReturn(user);

        noteService.createUser("iiro", "ipe");
        assertEquals(false, noteService.createUser("iira", "ipe"));
    }

    @Test
    public void loginWithExistingUsernameWorks() throws SQLException {

        User user = new User("iiro", "ipe", 1);
        when(daoUser.getUsingUsername("ipe")).thenReturn(user);

        assertTrue(noteService.login("ipe"));
        assertEquals(user, noteService.getLoggedUser());
    }

    @Test
    public void loginWithNonExistingUsernameDoesNotWork() throws SQLException {

        when(daoUser.getUsingUsername("fake")).thenReturn(null);

        assertFalse(noteService.login("fake"));
    }

}
