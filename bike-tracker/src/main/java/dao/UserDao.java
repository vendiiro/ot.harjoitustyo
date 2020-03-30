package dao;

import domain.User;
import java.sql.SQLException;
import java.util.List;

/**
 * Rajapinnan toteuttava luokka vastaa käyttäjän luomisesta ja käyttäjän tietojen etsimisestä.
 */
public interface UserDao {
    
    /**
     * Metodi luo käyttäjän, joka vastaa parametreina saatuja tietoja.
     * 
     * @param name Käyttäjän nimi
     * @param username Käyttäjän käyttäjänimi
     * 
     * @return Luotu käyttäjä 
     * 
     * @throws SQLException virhe tietokannassa
     */
    User create(String name, String username) throws SQLException; 
    
    /**
     * Metodi etsii annettua käyttäjänimeä vastaavan käyttäjän.
     * 
     * @param username Käyttäjänimi
     * 
     * @return parametrina annettuun käyttäjänimeen liittyvä käyttäjä,
     * tai null jos käyttäjänimi on vielä vapaa
     * 
     * @throws SQLException virhe tietokannassa
     */
    User findByUsername(String username) throws SQLException;
    
}