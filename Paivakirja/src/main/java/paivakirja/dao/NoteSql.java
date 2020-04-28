package paivakirja.dao;

import paivakirja.domain.Note;
import paivakirja.domain.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Luokka vastaa käyttäjän muistiinpanojen tietojen luomisesta, käsittelystä ja
 * tallentamisesta SQL-tietokantaan.
 */
public class NoteSql implements DaoNote {

    private final Database database;

    /**
     * Konstruktori.
     *
     * @param database tietokanta
     */
    public NoteSql(Database database) {
        this.database = database;
    }

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
    @Override
    public Note create(LocalDate date, int length, String content, User user) throws SQLException {

        try (Connection con = database.getConnection(); PreparedStatement stmnt = con.prepareStatement("INSERT INTO Note (date, length, content, user) VALUES (?,?,?,?)")) {
            stmnt.setDate(1, Date.valueOf(date));
            stmnt.setInt(2, length);
            stmnt.setString(3, content);
            stmnt.setInt(4, user.getId());

            stmnt.executeUpdate();

        }

        return getUserWithDate(user, date);

    }

    /**
     * Metodi hakee tietokannasta tietokannan luoman tunnisteen (id)
     * muistiinpanolle ja palauttaa näitä tietoja vastaavan muistiinpanon
     * käyttäjälle.
     *
     * @param user Käyttäjä, joka on luonut muistiinpanon
     * @param date Pvm, jolloin henkilö on treenannut
     *
     * @return Käyttäjän luoma muistiinpano, tai null jos muistiinpanoa ei löydy
     *
     * @throws SQLException virhe tietokannassa
     */
    public Note getUserWithDate(User user, LocalDate date) throws SQLException {
        String username = user.getUsername();

        Note note;
        try (Connection conn = database.getConnection(); PreparedStatement stmnt = conn.prepareStatement("SELECT * FROM Note, User WHERE User.username = ? AND Note.date = ?")) {
            stmnt.setString(1, username);
            stmnt.setDate(2, Date.valueOf(date));
            try (ResultSet rs = stmnt.executeQuery()) {
                boolean hasOne = rs.next();
                if (!hasOne) {
                    rs.close();
                    stmnt.close();
                    conn.close();
                    return null;
                }
                note = new Note(rs.getDate("date").toLocalDate(), rs.getInt("length"), rs.getString("content"), user, rs.getInt("id"));
            }
        }

        return note;

    }

    /**
     * Metodi palauttaa kaikki nykyisen käyttäjän muistiinpanot.
     *
     * @param user Käyttäjä, johon muistiinpanot liittyvät
     * @return lista muistiinpanoja tai tyhjä lista jos muistiinpanoja ei ole
     *
     * @throws SQLException virhe tietokannassa
     */
    @Override
    public List<Note> getAll(User user) throws SQLException {
        List<Note> list = new ArrayList<>();
        int userId = user.getId();

        try (Connection con = database.getConnection(); PreparedStatement stmnt = con.prepareStatement("SELECT * FROM Note WHERE user = ? ORDER BY date DESC")) {
            stmnt.setInt(1, userId);
            try (ResultSet rs = stmnt.executeQuery()) {
                while (rs.next()) {
                    Note n = new Note(rs.getDate("date").toLocalDate(), rs.getInt("length"), rs.getString("content"), user, rs.getInt("id"));
                    list.add(n);
                }
            }
        }
        return list;
    }

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
    @Override
    public int totalTimeWasted(User user) throws SQLException {
        int userId = user.getId();
        int tulos = 0;

        try (Connection con = database.getConnection(); PreparedStatement stmnt = con.prepareStatement("SELECT SUM(length) FROM Note WHERE user = ?")) {
            stmnt.setInt(1, userId);

            try (ResultSet rs = stmnt.executeQuery()) {
                while (rs.next()) {
                    tulos = rs.getInt(1);
                }
            }
        }

        return tulos;
    }

    /**
     * Metodi poistaa muistiinpanon halutulla päivämäärältä.
     *
     * @param date Käyttäjän antama pvm
     * @param u Käyttäjä
     *
     * @return true mikäli muistiinpanon poistaminen onnistui
     *
     * @throws SQLException virhe tietokannassa
     */
    @Override
    public boolean deleteNote(LocalDate date, User u) throws SQLException {
        int userId = u.getId();

        Date sqlDate = Date.valueOf(date);

        Connection con = database.getConnection();

        PreparedStatement stmnt = con.prepareStatement("DELETE FROM Note WHERE user = ? AND date = ?");
        stmnt.setInt(1, userId);
        stmnt.setDate(2, sqlDate);

        stmnt.executeUpdate();

        stmnt.close();
        con.close();
        return true;
    }

}
