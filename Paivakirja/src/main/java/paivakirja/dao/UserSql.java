/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paivakirja.dao;

import paivakirja.domain.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author iiro
 */
public class UserSql implements DaoUser {

    private Database database;

    public UserSql(Database database) {
        this.database = database;
    }

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
