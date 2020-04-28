package paivakirja.dao;

import paivakirja.domain.User;
import java.sql.SQLException;

/**
 * Rajapinnan toteuttava luokka vastaa käyttäjän luomisesta ja käyttäjän tietojen etsimisestä.
 */

public interface DaoUser {

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

    User create(String name, String username) throws SQLException;

    /**
     * Metodi etsii annettua käyttäjänimeä vastaavan käyttäjän.
     * 
     * @param username Käyttäjänimi
     * 
     * @return parametrina annettuun käyttäjänimeen liittyvä käyttäjä,
     * tai null jos käyttäjänimen on vienyt toinen käyttäjä
     * 
     * @throws SQLException virhe tietokannassa
     */
    User getUsingUsername(String username) throws SQLException;

}
