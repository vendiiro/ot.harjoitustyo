package paivakirja.dao;

import paivakirja.domain.User;
import java.sql.SQLException;

public interface DaoUser {

    User create(String name, String username) throws SQLException;

    User getUsingUsername(String username) throws SQLException;

}
