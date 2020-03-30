package domain;

import dao.NoteDao;
import dao.UserDao;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Sovelluslogiikasta vastaava luokka. 
 */
public class NoteService {
    private NoteDao noteDao;
    private UserDao userDao;
    private User currentUser;
    
    /**
     * Konstruktori.
     * @param noteDao Käyttäjän muistiinpanojen käsittely
     * @param userDao  Käyttäjän tietojen käsittely
     */
    public NoteService(NoteDao noteDao, UserDao userDao) {
        this.noteDao = noteDao;
        this.userDao = userDao;
    }
    
    
    /**
     * Metodi poistaa muistiinpanon nykyisen käyttäjän antamalta
     * päivämäärältä.
     * 
     * @param date Nykyisen käyttäjän antama päivämäärä
     * @return palautetaan true jos muistiinpanon poistaminen onnistui,
     * tai palautetaan false jos annetulla päivämäärällä ei ole muistiinpanoa
     * 
     * @throws SQLException virhe tietokannassa
     */
    public boolean deleteNote(LocalDate date) throws SQLException  {
        List<Note> list = noteDao.getAll(currentUser);
        boolean result = false;
        for (Note note : list) {
            if (note.getDate().equals(date)) {
                result = true;
            }
        }
        if (result == true) {
            this.noteDao.deleteNote(date, currentUser);
        } 
        return result;
    }
    

    /**
     * Metodi palauttaa nykyisen käyttäjän kokonaiskilometrit.
     * 
     * @return pyöräillyt kilometrit yhteensä, tai 0 jos ei kilometreja
     * 
     * @throws SQLException virhe tietokannassa
     */
    
    public int kmTotal() throws SQLException {
        return noteDao.kmTotal(currentUser);
    }
    
    /**
     * Metodi palauttaa nykyisen käyttäjän kaikki muistiinpanot.
     * 
     * @return lista muistiinpanoista, tai tyhjä lista jos muistiinpanoja ei ole
     * 
     * @throws SQLException virhe tietokannassa
     */
    public List<Note> getAll() throws SQLException {
        return noteDao.getAll(currentUser);          
    }
    
    /**
     * Metodi luo uuden muistiinpanon nykyiselle käyttäjälle.
     * 
     * @param date Muistiinpanon päivämäärä
     * @param km Kuinka monta kilometria muistiinpanoon liittyy
     * @param content Teksti, joka halutaan liittää osaksi muistiinpanoa
     * 
     * @return true jos muistiinpanon luominen onnistuu
     * 
     * @throws SQLException virhe tietokannassa
     */
    public boolean createNote(LocalDate date, int km, String content) throws SQLException {       
        noteDao.create(date, km, content, currentUser);  
        return true;
    }
    
    /**
     * Metodin avulla käyttäjä, jolla on jo käyttäjätunnus, kirjautuu sisään.
     * 
     * @param username Käyttäjänimi
     * 
     * @return true jos sisäänkirjautuminen onnistuu, false jos käyttäjänimeä
     * ei ole olemassa
     * 
     * @throws SQLException virhe tietokannassa
     */
    public boolean login(String username) throws SQLException {
        User user = userDao.findByUsername(username);
        if (user == null) {
            return false;
        }
        currentUser = user;
        return true;
    }
    
    /**
     * Metodi kirjaa nykyisen käyttäjän ulos ohjelmasta.
     */
    public void logout() {
        currentUser = null;
    }
    
    /**
     * Metodi luo uuden käyttäjän.
     * 
     * @param name Käyttäjän nimi
     * @param username Käyttäjän käyttäjänimi
     * 
     * @return true jos käyttäjän luominen onnistuu, false jos ei onnistu 
     * 
     * @throws SQLException virhe tietokannassa
     */
    public boolean createUser(String name, String username) throws SQLException {
        if (userDao.findByUsername(username) != null) {
            return false;
        }
        userDao.create(name, username);
        return true;
    }

    /**
     * Metodi palauttaa nykyisen käyttäjän.
     * 
     * @return nykyinen käyttäjä 
     */
    public User getLoggedUser() {
        return currentUser;
    }
    
    /**
     * Metodi kertoo onko käyttäjä kirjautuneena sisään.
     * 
     * @return false jos käyttäjä on sisäänkirjautuneena, false jos ei
     */
    public boolean isUserLoggedIn() {
        if (currentUser == null) {
            return false;
        } 
        return true;
    }
        
}
