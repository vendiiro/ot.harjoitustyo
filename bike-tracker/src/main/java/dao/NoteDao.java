
package dao;

import domain.Note;
import domain.User;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * Rajapinnan toteuttava luokka vastaa käyttäjän muistiinpanojen käsittelystä ja 
 * tietojen tallentamisesta. 
 */
public interface NoteDao {
    
    /**
     * Metodi luo muistiinpanon, joka vastaa parametreina saatuja tietoja.
     * 
     * @param date Muistiinpanon päivämäärä
     * @param km Kuinka monta kilometria muistiinpanoon liittyy
     * @param content Teksti, joka halutaan liittää osaksi muistiinpanoa
     * @param user Käyttäjä, johon muistiinpano liittyy
     * 
     * @return Luotu muistiinpano
     * 
     * @throws SQLException virhe tietokannassa
     */
    Note create(LocalDate date, int km, String content, User user) throws SQLException;
    
    /**
     * Metodi palauttaa kaikki käyttäjään liittyvät muistiinpanot.
     * 
     * @param user Käyttäjä, johon muistiinpanot liittyvät
     * @return lista muistiinpanoja tai tyhjä lista jos muistiinpanoja ei ole
     * 
     * @throws SQLException virhe tietokannassa
     */
    List<Note> getAll(User user) throws SQLException;
    
    /**
     * Metodi kertoo kuinka paljon käyttäjä on yhteensä pyöräillyt.
     * 
     * @param user Käyttäjä
     * @return pyöräillyt kilometrit yhteensä, tai 0 jos ei kilometreja
     * 
     * @throws SQLException virhe tietokannassa
     */
    int kmTotal(User user) throws SQLException;
    
    /**
     * Metodi poistaa muistiinpanon tietyltä päivämäärältä.
     * 
     * @param date Käyttäjän antama päivämäärä
     * @param user Käyttäjä
     * 
     * @return true jos poistaminen onnistui
     * 
     * @throws SQLException virhe tietokannassa
     */
    boolean deleteNote(LocalDate date, User user) throws SQLException;
    
}