package paivakirja.dao;

import paivakirja.domain.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Luokka vastaa käyttäjän luomisesta ja hänen tietojensa etsimisetä SQL-
 * tietokannasta.
 */
public class UserSql implements DaoUser {

    private Database database;

    /**
     * Konstruktori.
     *
     * @param database tietokanta
     */
    public UserSql(Database database) {
        this.database = database;
    }

    /**
     * Metodi luo käyttäjän, joka vastaa parametreina saatuja tietoja.
     *
     * @param name Käyttäjän oma nimi
     * @param username Käyttäjän käyttäjänimi
     *
     * @return Luotu käyttäjä
     *
     * @throws SQLException virhe tietokannassa
     */
    @Override
    public User create(String name, String username) throws SQLException {
        Connection conn = database.getConnection();

        PreparedStatement stmnt = conn.prepareStatement("INSERT INTO User (name, username) VALUES (?,?)");

        stmnt.setString(1, name);
        stmnt.setString(2, username);

        stmnt.executeUpdate();

        stmnt.close();
        conn.close();

        return getUsingUsername(username);
    }

    /**
     * Metodi etsii annettua käyttäjänimeä vastaavan käyttäjän.
     *
     * @param username Käyttäjänimi
     *
     * @return parametrina annettuun käyttäjänimeen liittyvä käyttäjä, tai null
     * jos käyttäjänimen on vienyt toinen käyttäjä
     *
     * @throws SQLException virhe tietokannassa
     */
    @Override
    public User getUsingUsername(String username) throws SQLException {
        Connection conn = database.getConnection();

        PreparedStatement stmnt = conn.prepareStatement("SELECT * FROM User WHERE username = ?");

        stmnt.setString(1, username);

        ResultSet rs = stmnt.executeQuery();

        boolean hasOne = rs.next();
        if (!hasOne) {
            rs.close();
            stmnt.close();
            conn.close();
            return null;
        }

        User usr = new User(rs.getString("name"), rs.getString("username"), rs.getInt("id"));

        rs.close();
        stmnt.close();
        conn.close();

        return usr;
    }

}
