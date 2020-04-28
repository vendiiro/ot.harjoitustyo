package paivakirja.dao;

import paivakirja.domain.Note;
import paivakirja.domain.User;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * Rajapinnan toteuttava luokka vastaa käyttäjän muistiinpanojen käsittelystä ja
 * tietojen tallentamisesta.
 */
public interface DaoNote {

    /**
     * Metodi luo muistiinpanon, joka vastaa parametreina saatuja tietoja.
     *
     * @param date Muistiinpanon pvm
     * @param length Kauanko treeniin on kulunut aikaa
     * @param content Muistiinpanoon liittyvä tekstisisältö
     * @param user Muistiinpanoon liittyvä käyttäjä
     *
     * @return Luotu muistiinpano
     *
     * @throws SQLException virhe tietokannassa
     */
    Note create(LocalDate date, int length, String content, User user) throws SQLException;

    /**
     * Metodi palauttaa kaikki nykyisen käyttäjän muistiinpanot.
     *
     * @param user Käyttäjä, johon muistiinpanot liittyvät
     * @return lista muistiinpanoja tai tyhjä lista jos muistiinpanoja ei ole
     *
     * @throws SQLException virhe tietokannassa
     */
    List<Note> getAll(User user) throws SQLException;

    /**
     * Metodi kertoo kuinka paljon käyttäjä on yhteensä kuluttaa aikaa
     * treeneihin yhteensä.
     *
     * @param user Käyttäjä
     * @return treeniin kulutettu aika yhteensä tai 0 jos ei ole yhtää tullut
     * treenattua
     *
     * @throws SQLException virhe tietokannassa
     */
    int totalTimeWasted(User user) throws SQLException;

    /**
     * Metodi poistaa muistiinpanon halutulla päivämäärältä.
     *
     * @param date Käyttäjän antama pvm
     * @param user Käyttäjä
     *
     * @return true mikäli muistiinpanon poistaminen onnistui
     *
     * @throws SQLException virhe tietokannassa
     */
    boolean deleteNote(LocalDate date, User user) throws SQLException;

}
