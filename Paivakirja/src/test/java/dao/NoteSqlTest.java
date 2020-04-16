/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import paivakirja.dao.DaoNote;
import paivakirja.dao.DaoUser;
import paivakirja.dao.Database;
import paivakirja.dao.NoteSql;
import paivakirja.dao.UserSql;
import paivakirja.domain.Note;
import paivakirja.domain.User;

/**
 *
 * @author iiro
 */
public class NoteSqlTest {

    Database database;
    DaoNote noteDao;
    DaoUser userDao;
    User user;

    @Before
    public void setup() throws Exception {
        database = new Database("jdbc:sqlite:test-tietokanta.db");
        database.creatingTables();

        this.userDao = new UserSql(database);
        this.user = this.userDao.create("Cynthia Cyclist", "cycy");
        this.noteDao = new NoteSql(database);
    }

    @Test
    public void noteIsCreatedWithoutError() throws Exception {
        Note result = this.noteDao.create(LocalDate.now(), 1, "foo", user);

        assertNotNull(result);
    }
    @Test
    public void notesAreReadCorrectlyFromDatabase() throws Exception {
        this.noteDao.create(LocalDate.now(), 1, "foo", user);
        
        List<Note> list = noteDao.getAll(this.user);
        
        assertEquals(1, list.size());
    }

    @After
    public void cleanup() throws IOException {
        Files.deleteIfExists(Paths.get("test-tietokanta.db"));
    }
}
