package paivakirja.domain;

import paivakirja.dao.DaoNote;
import paivakirja.dao.DaoUser;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * Sovelluslogiikasta vastaava luokka.
 */
public class NoteService {

    private DaoNote daoNote;
    private DaoUser daoUser;
    private User existingUser;

    public NoteService(DaoNote daoNote, DaoUser daoUser) {
        this.daoNote = daoNote;
        this.daoUser = daoUser;
    }

    /**
     * Metodi poistaa nykyisen käyttäjän muistiinpanon annetulla pvm.
     *
     * @param date Nykyisen käyttäjän antama päivämäärä
     * @return
     * @throws SQLException
     */
    public boolean deleteNote(LocalDate date) throws SQLException {
        List<Note> list = daoNote.getAll(existingUser);
        boolean result = false;
        for (Note note : list) {
            if (note.getDate().equals(date)) {
                result = true;
            }
        }
        if (result == true) {
            this.daoNote.deleteNote(date, existingUser);
        }
        return result;
    }

    /**
     * Metodi palauttaa nykyisen käyttäjän kaikki muistiinpanot.
     *
     * @return
     * @throws SQLException
     */
    public List<Note> getAll() throws SQLException {
        return daoNote.getAll(existingUser);
    }

    /**
     * Metodi palauttaa summan treeneihin kuluneesta ajasta.
     *
     * @return
     * @throws SQLException
     */
    public int totalTimeWasted() throws SQLException {
        return daoNote.totalTimeWasted(existingUser);
    }

    /**
     * Metodi luo uuden muistiinpanon nykyiselle käyttäjälle.
     *
     * @param date Muistiinpanon päivämäärä
     * @param min Kuinka monta kilometria muistiinpanoon liittyy
     * @param content Teksti, joka halutaan liittää osaksi muistiinpanoa
     * @return kjdsak
     * @throws SQLException
     */
    public boolean createNote(LocalDate date, int min, String content) throws SQLException {
        daoNote.create(date, min, content, existingUser);
        return true;
    }

    /**
     * Käyttäjä jolla on jo käyttäjätunnus, voi kirjautua sisään.
     *
     * @param username Käyttäjänimi
     * @return
     * @throws SQLException
     */
    public boolean login(String username) throws SQLException {
        User user = daoUser.getUsingUsername(username);
        if (user == null) {
            return false;
        }
        existingUser = user;
        return true;
    }

    /**
     * Metodi palauttaa nykyisen käyttäjän.
     *
     * @return
     */
    public User getLoggedUser() {
        return existingUser;
    }

    /**
     * Metodi kertoo onko käyttäjä kirjautuneena sisään.
     *
     * @return
     */
    public boolean isUserLoggedIn() {
        return existingUser != null;
    }

    /**
     * Metodi kirjaa ulos nykyisen käyttäjän.
     */
    public void logout() {
        existingUser = null;
    }

    /**
     * Metodi luo uuden käyttäjän.
     *
     * @param name Käyttäjän nimi
     * @param username Käyttäjän käyttäjänimi
     * @return
     * @throws SQLException
     */
    public boolean createUser(String name, String username) throws SQLException {
        if (daoUser.getUsingUsername(username) != null) {
            return false;
        }
        daoUser.create(name, username);
        return true;
    }

}
