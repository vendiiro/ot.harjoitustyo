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

    /**
     * Konstruktori.
     *
     * @param daoNote Käyttäjän muistiinpanojen käsittely
     * @param daoUser Käyttäjän tietojen käsittely
     */
    public NoteService(DaoNote daoNote, DaoUser daoUser) {
        this.daoNote = daoNote;
        this.daoUser = daoUser;
    }

    /**
     * Metodi poistaa käyttäjän luoman muistiinpanon, mikäli antamalla pvm:llä
     * löytyy muistiinpano.
     *
     * @param date käyttäjän antama pvm
     * @return palauttaa true jos muistiinpanon poistaminen onnistu ja false jos
     * antamalla pvm:llä ei löydy muistiinpanoa
     *
     * @throws SQLException virhe tietokannassa
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
     * @return kakki nykyisen käyttäjän muistiinpanot listana ja tyhjä lista
     * mikäli muistiinpanoja ei ole
     * 
     * @throws SQLException virhe tietokannassa
     */
    public List<Note> getAll() throws SQLException {
        return daoNote.getAll(existingUser);
    }

    /**
     * Metodi palauttaa käyttäjän treeneihin kuluttaman ajan summan.
     *
     * @return summa käyttäjän kuluttamasta ajasta treeneihin, mikäli ei ole
     * tullut treenattua palautetaan 0
     * 
     * @throws SQLException virhe tietokannassa
     */
    public int totalTimeWasted() throws SQLException {
        return daoNote.totalTimeWasted(existingUser);
    }

    /**
     * Metodi luo uuden muistiinpanon sisäänkirjautuneelle käyttäjälle.
     *
     * @param date muistiinpanon pvm
     * @param min treeneihin kulutettu aika
     * @param content muistiinpanon tekstisisältö
     * @return true jos muistiinpannon luominen onnstuu
     * 
     * @throws SQLException virhe tietokannassa
     */
    public boolean createNote(LocalDate date, int min, String content) throws SQLException {
        daoNote.create(date, min, content, existingUser);
        return true;
    }

    /**
     * Metodi kirjaa kättäjän sisään järjestelmään mikäli käyttäjällä on tunnus
     * järjestelmässä.
     *
     * @param username sisäänkirjutujan käyttäjätunnus
     * @return true jos sisäänkrjautuminen onnistuu ja false jos käyttäjän
     * käyttäjätunnuksella ei löydy käyttäjää järjestelmästä
     * 
     * @throws SQLException virhe tietokannassa
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
     * Metodi hakee järjestelmästä sisään kirjautuneen käyttäjän.
     *
     * @return palauttaa nykysen sisäänkirjautuneen käyttäjän
     */
    public User getLoggedUser() {
        return existingUser;
    }

    /**
     * Metodi kertoo onko käyttäjä kirjautuneena järjestelmään.
     *
     * @return palauttaa true mikäli käyttäjä on kirjautneena sisään ja false
     * jos ei
     */
    public boolean isUserLoggedIn() {
        return existingUser != null;
    }

    /**
     * Metodi kirjaa nykyisen käyttäjän ulos järjestelmästä.
     *
     */
    public void logout() {
        existingUser = null;
    }

    /**
     * Metodi luo uuden käyttäjän järjestelmään.
     *
     * @param name käyttäjän oma nimi
     * @param username käyttäjänimi
     * @return true jos uuden käyttäjän luonti onnistuu
     * 
     * @throws SQLException virhe tietokannassa
     */
    public boolean createUser(String name, String username) throws SQLException {
        if (daoUser.getUsingUsername(username) != null) {
            return false;
        }
        daoUser.create(name, username);
        return true;
    }

}
