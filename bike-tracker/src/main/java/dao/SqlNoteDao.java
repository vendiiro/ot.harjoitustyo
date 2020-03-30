package dao;

import domain.Note;
import domain.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Luokka vastaa käyttäjän muistiinpanojen käsittelystä ja tietojen 
 * tallentamisesta SQL-tietokantaan.
 */
public class SqlNoteDao implements NoteDao {
    
    private Database database;
    
    /**
     * Konstruktori.
     * @param database tietokanta 
     */
    public SqlNoteDao(Database database) {
        this.database = database;
    }

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
    @Override
    public Note create(LocalDate date, int km, String content, User user) throws SQLException {
        
        Connection conn = database.getConnection();
            
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Note (date, km, content, user) VALUES (?,?,?,?)");
        stmt.setDate(1, Date.valueOf(date));
        stmt.setInt(2, km);
        stmt.setString(3, content);           
        stmt.setInt(4, user.getId()); 

        stmt.executeUpdate();

        stmt.close();
        conn.close();

        return findByUsernameAndDate(user, date);            
    }

    /**
     * Metodi hakee tietokannasta tietokannan luoman tunnisteen (id) 
     * muistiinpanolle ja palauttaa näitä tietoja vastaavan muistiinpanon.
     * 
     * @param user Käyttäjä, joka on luonut muistiinpanon
     * @param date Päivämäärä, jolle muistiinpano on luotu
     * 
     * @return Luotu muistiinpano, tai null jos muistiinpanoa ei löydy
     * 
     * @throws SQLException virhe tietokannassa
     */
    public Note findByUsernameAndDate(User user, LocalDate date) throws SQLException {
        String username = user.getUsername();        
        
        Connection conn = database.getConnection();
            
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Note, User WHERE User.username = ? AND Note.date = ?");
        stmt.setString(1, username);
        stmt.setDate(2, Date.valueOf(date));

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            rs.close();
            stmt.close();
            conn.close();
            return null;
        }

        Note n = new Note(rs.getDate("date").toLocalDate(), rs.getInt("km"), rs.getString("content"), user, rs.getInt("id"));

        rs.close();
        stmt.close();
        conn.close();

        return n;        
    }
    
    /**
     * Metodi palauttaa kaikki käyttäjään liittyvät muistiinpanot.
     * 
     * @param user Käyttäjä, johon muistiinpanot liittyvät
     * 
     * @return lista muistiinpanoista, tai tyhjä lista jos muistiinpanoja ei ole
     * 
     * @throws SQLException virhe tietokannassa
     */
    @Override
    public List<Note> getAll(User user) throws SQLException {
        List<Note> list = new ArrayList<>();              
        int userId = user.getId();
        
        Connection conn = database.getConnection();            
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Note WHERE user = ? ORDER BY date DESC");
        stmt.setInt(1, userId);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Note n = new Note(rs.getDate("date").toLocalDate(), 
                    rs.getInt("km"), rs.getString("content"), user, 
                    rs.getInt("id"));
            list.add(n);
        }       
        
        rs.close();
        stmt.close();
        conn.close();
        return list;
    }

    /**
    * Metodi kertoo kuinka paljon käyttäjä on yhteensä pyöräillyt.
    * 
    * @param user Käyttäjä
    * 
    * @return pyöräillyt kilometrit yhteensä, tai 0 jos ei kilometreja
    * 
    * @throws SQLException virhe tietokannassa
    */
    @Override
    public int kmTotal(User user) throws SQLException {
        int userId = user.getId();
        int tulos = 0;
        
        Connection conn = database.getConnection();
            
        PreparedStatement stmt = conn.prepareStatement("SELECT SUM(km) FROM Note WHERE user = ?");
        stmt.setInt(1, userId);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            tulos = rs.getInt(1);
        }

        rs.close();
        stmt.close();
        conn.close();
        
        return tulos;
    }

    /**
     * Metodi poistaa muistiinpanon tietyltä päivämäärältä.
     * 
     * @param date Käyttäjän antama päivämäärä
     * @param user Käyttäjä
     *
     * @return true jos poistaminen onnistui 
     * @throws SQLException virhe tietokannassa
     */
    @Override
    public boolean deleteNote(LocalDate date, User user) throws SQLException {
        int userId = user.getId();
       
        Date sqlDate = Date.valueOf(date);
        
        Connection conn = database.getConnection();
            
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM Note WHERE user = ? AND date = ?");
        stmt.setInt(1, userId);
        stmt.setDate(2, sqlDate);

        stmt.executeUpdate();

        stmt.close();
        conn.close();
        return true;
    }
    
}