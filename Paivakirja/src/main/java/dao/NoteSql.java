/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Note;
import domain.User;
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
    
        private Database database;
        
         public NoteSql(Database database) {
        this.database = database;
    }
        
        @Override
    public Note create(LocalDate date, int min, String content, User user) throws SQLException {
        
        Connection conn = database.getConnection();
            
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Note (date, min, content, user) VALUES (?,?,?,?)");
        stmt.setDate(1, Date.valueOf(date));
        stmt.setInt(2, min);
        stmt.setString(3, content);           
        stmt.setInt(4, user.getId()); 

        stmt.executeUpdate();

        stmt.close();
        conn.close();

        return findByUsernameAndDate(user, date);            
    }
    
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

    @Override
    public List<Note> getAll(User user) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int totalTime(User user) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteNote(LocalDate date, User user) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
