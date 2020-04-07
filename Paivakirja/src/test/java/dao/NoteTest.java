package dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import domain.Note;
import domain.User;
import java.sql.SQLException;
import java.time.LocalDate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author iiro
 */
public class NoteTest {

    User user;
    LocalDate date;

    @Before
    public void setup() throws SQLException {
        this.user = new User("Sali Kissa", "saki", 1);
        this.date = LocalDate.now();

    }

    @Test
    public void equalWhenSameId() {

        Note t1 = new Note(date, 60, "moi", user, 1);
        Note t2 = new Note(date, 60, "moi", user, 1);
        assertTrue(t1.equals(t2));
    }

    @Test
    public void notEqualWhenDifferentId() {
        Note t1 = new Note(date, 60, "jes", user, 1);
        Note t2 = new Note(date, 60, "jes", user, 2);
        assertFalse(t1.equals(t2));
    }

    @Test
    public void nonEqualWhenDifferentType() {
        Note t = new Note(date, 60, "jes", user, 1);
        Object o = new Object();
        assertFalse(t.equals(o));
    }
}
