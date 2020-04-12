package paivakirja.dao;

import paivakirja.domain.Note;
import paivakirja.domain.User;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * Rajapinnta luokka
 */
public interface DaoNote {

    /**
     * Metodi luo muistiinpanon, joka vastaa parametreina saatuja tietoja.
     *
     * @param date
     * @param min
     * @param content
     * @param user
     * @return
     * @throws java.sql.SQLException
     */
    Note create(LocalDate date, int min, String content, User user) throws SQLException;

    /**
     * Metodi palauttaa kaikki käyttäjän tekemät muistiinpanot.
     *
     * @param user
     * @return
     * @throws java.sql.SQLException
     */
    List<Note> getAll(User user) throws SQLException;

    /**
     * Metodi kertoo summan käyttäjän treeneihin kuluneesta ajasta.
     *
     * @param user
     * @return
     * @throws java.sql.SQLException
     */
    int totalTimeWasted(User user) throws SQLException;

    /**
     * Metodi poistaa muistiinpanon halutulta pvm.
     *
     * @param date
     * @param user
     * @return
     * @throws java.sql.SQLException
     */

    boolean deleteNote(LocalDate date, User user) throws SQLException;

}
