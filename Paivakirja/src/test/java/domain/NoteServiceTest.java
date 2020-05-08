package domain;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDate;
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
import paivakirja.domain.NoteService;

/**
 *
 * @author iiro
 */
public class NoteServiceTest {

    NoteService ns;

    @Before
    public void setup() throws Exception {
        Database database = new Database("jdbc:sqlite:test-feikkitietokanta.db");
        database.creatingTables();

        DaoUser userDao = new UserSql(database);
        DaoNote noteDao = new NoteSql(database);
        NoteService nst = new NoteService(noteDao, userDao);

        this.ns = nst;
    }

    public void loginUser() throws SQLException {
        ns.createUser("Testi Tiesto", "testi");
        ns.login("testi");
    }

    @Test
    public void loggingOutWorks() throws SQLException {
        loginUser();
        ns.logout();

        assertEquals(null, ns.getLoggedUser());
    }

    @Test
    public void creatingNewUserWithUniqueUsernameWorks() throws SQLException {
        assertTrue(ns.createUser("Tira Master", "tima"));
    }

    @Test
    public void creatingNewUserWithNonuniqueUsernameReturnsFalse() throws SQLException {
        ns.createUser("Testi Taisto", "testi");
        assertFalse(ns.createUser("Testi Tuisku", "testi"));
    }

    @Test
    public void loginWithExistingUsernameWorks() throws SQLException {
        ns.createUser("Martin Garrix", "maga");

        assertTrue(ns.login("maga"));
    }

    @Test
    public void loginWithNonexistingUsernameDoesNotWork() throws SQLException {
        assertFalse(ns.login("feik"));
    }

    @Test
    public void checkingIsUserLoggedInWhenUserIsLoggedInWorks() throws SQLException {
        loginUser();

        assertTrue(ns.isUserLoggedIn());
    }

    @Test
    public void checkingIsUserLoggedInWorksWhenUserNotLoggedIn() {
        assertFalse(ns.isUserLoggedIn());
    }

    @Test
    public void creatingNewNoteForTheUserWorks() throws Exception {
        loginUser();

        assertTrue(ns.createNote(LocalDate.now(), 60, "ulkosali treeni"));
    }

    @Test
    public void gettingTrainingLenghtForTheUserWorks() throws SQLException {
        loginUser();
        ns.createNote(LocalDate.now(), 90, "juoksulenkki ja kahvittelut");

        assertEquals(90, ns.totalTimeWasted());
    }

    @Test
    public void gettingListOfNotesForUserWorksWhenThereAreNotes() throws SQLException {
        loginUser();
        ns.createNote(LocalDate.now(), 100, "saliiiiiiiiiiiiiiiiiiiiiii");

        assertEquals(1, ns.getAll().size());

    }

    @Test
    public void gettingListOfNotesForCurrentUserWorksWhenThereAreNoNotes() throws SQLException {
        loginUser();

        assertEquals(0, ns.getAll().size());
    }

    @Test
    public void noteIsDeletedCorrectlyWhenUserHasTheNote() throws SQLException {
        loginUser();
        ns.createNote(LocalDate.now(), 100, "saliiiiiiiiiiiiiiiiii");

        assertTrue(ns.deleteNote(LocalDate.now()));
    }

    @Test
    public void tryingToDeleteNoteFalseWhenThereIsNotNotes() throws SQLException {
        loginUser();

        assertFalse(ns.deleteNote(LocalDate.now()));
    }

    @After
    public void tearDown() throws IOException {
        Files.deleteIfExists(Paths.get("test-feikkitietokanta.db"));
    }
}
