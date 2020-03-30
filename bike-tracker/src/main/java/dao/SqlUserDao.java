package dao;

import domain.User;
import java.sql.*;

/**
 * Luokka vastaa käyttäjän luomisesta ja käyttäjän tietojen etsimisestä 
 * SQL-tietokannasta.
 */
public class SqlUserDao implements UserDao {

    private Database database;
    
    /**
     * Konstruktori.
     * @param database tietokanta 
     */
    public SqlUserDao(Database database) {
        this.database = database;
    }
  
     /**
     * Metodi luo uuden käyttäjän.
     * 
     * @param name Käyttäjän nimi
     * @param username Käyttäjän käyttäjänimi
     * 
     * @return Luotu käyttäjä
     * @throws SQLException virhe tietokannassa
     */
    @Override    
    public User create(String name, String username) throws SQLException {
        Connection conn = database.getConnection();  
        
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO User (name, username) VALUES (?,?)");

        stmt.setString(1, name);
        stmt.setString(2, username);

        stmt.executeUpdate();

        stmt.close();
        conn.close();

        return findByUsername(username);
    }

    /**
     * Metodi etsii annettua käyttäjänimeä vastaavan käyttäjän tietokannasta.
     * 
     * @param username Käyttäjänimi
     * 
     * @return parametrina annettuun käyttäjänimeen liittyvä käyttäjä,
     * tai null jos käyttäjänimi on vielä vapaa
     * 
     * @throws SQLException virhe tietokannassa
     */
    @Override
    public User findByUsername(String username) throws SQLException {
        Connection conn = database.getConnection();
            
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM User WHERE username = ?");

        stmt.setString(1, username);

        ResultSet rs = stmt.executeQuery();
        
        boolean hasOne = rs.next();
        if (!hasOne) {
            rs.close();
            stmt.close();
            conn.close();
            return null;
        }
        
        User u = new User(rs.getString("name"), rs.getString("username"), rs.getInt("id"));
        
        rs.close();
        stmt.close();
        conn.close();

        return u;
    }
}