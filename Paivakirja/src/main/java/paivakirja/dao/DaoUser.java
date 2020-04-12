package paivakirja.dao;

import paivakirja.domain.User;
import java.sql.SQLException;

public interface DaoUser {

    /**
     *
     * @param name
     * @param username
     * @return
     * @throws SQLException
     */
    User create(String name, String username) throws SQLException;

    /**
     *
     * @param username
     * @return
     * @throws SQLException
     */
    User getUsingUsername(String username) throws SQLException;

}
