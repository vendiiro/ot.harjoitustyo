/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paivakirja.dao;

import paivakirja.domain.Note;
import paivakirja.domain.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author iiro
 */
public class NoteSql implements DaoNote{
    
        private final Database database;
        
         public NoteSql(Database database) {
        this.database = database;
    }
        
        @Override
    public Note create(LocalDate date, int lenght, String content, User user) throws SQLException {
        
        Connection conn = database.getConnection();
            
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Note (date, min, content, user) VALUES (?,?,?,?)");
        stmt.setDate(1, Date.valueOf(date));
        stmt.setInt(2, lenght);
        stmt.setString(3, content);           
        stmt.setInt(4, user.getId()); 

        stmt.executeUpdate();

        stmt.close();
        conn.close();

        return getUserWithDate(user, date);            
    
    }
    
    public Note getUserWithDate(User user, LocalDate date) throws SQLException {
  String username = user.getUsername();        
        
        Connection conn = database.getConnection();
            
        PreparedStatement stmnt = conn.prepareStatement("SELECT * FROM Note, User WHERE User.username = ? AND Note.date = ?");
        stmnt.setString(1, username);
        stmnt.setDate(2, Date.valueOf(date));

        ResultSet rs = stmnt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            rs.close();
            stmnt.close();
            conn.close();
            return null;
        }

        Note note = new Note(rs.getDate("date").toLocalDate(), rs.getInt("min"), rs.getString("content"), user, rs.getInt("id"));

        rs.close();
        stmnt.close();
        conn.close();

        return note;        
               
    }

    @Override
    public List<Note> getAll(User user) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int totalTimeWasted(User user) throws SQLException {
int userId = user.getId();
        int tulos = 0;
        
        Connection conn = database.getConnection();
            
        PreparedStatement stmt = conn.prepareStatement("SELECT SUM(min) FROM Note WHERE user = ?");
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

    @Override
    public boolean deleteNote(LocalDate date, User user) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
