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

public class NoteSql implements DaoNote {

    private final Database database;

    public NoteSql(Database database) {
        this.database = database;
    }

    @Override
    public Note create(LocalDate date, int lenght, String content, User user) throws SQLException {

        try (Connection con = database.getConnection(); PreparedStatement stmnt = con.prepareStatement("INSERT INTO Note (date, min, content, user) VALUES (?,?,?,?)")) {
            stmnt.setDate(1, Date.valueOf(date));
            stmnt.setInt(2, lenght);
            stmnt.setString(3, content);
            stmnt.setInt(4, user.getId());

            stmnt.executeUpdate();

        }

        return getUserWithDate(user, date);

    }

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
                note = new Note(rs.getDate("date").toLocalDate(), rs.getInt("min"), rs.getString("content"), user, rs.getInt("id"));
            }
        }

        return note;

    }

    @Override
    public List<Note> getAll(User user) throws SQLException {
        List<Note> list = new ArrayList<>();
        int userId = user.getId();

        try (Connection con = database.getConnection(); PreparedStatement stmnt = con.prepareStatement("SELECT * FROM Note WHERE user = ? ORDER BY date DESC")) {
            stmnt.setInt(1, userId);
            try (ResultSet rs = stmnt.executeQuery()) {
                while (rs.next()) {
                    Note n = new Note(rs.getDate("date").toLocalDate(), rs.getInt("min"), rs.getString("content"), user, rs.getInt("id"));
                    list.add(n);
                }
            }
        }
        return list;
    }

    @Override
    public int totalTimeWasted(User user) throws SQLException {
        int userId = user.getId();
        int tulos = 0;

        try (Connection con = database.getConnection(); PreparedStatement stmnt = con.prepareStatement("SELECT SUM(min) FROM Note WHERE user = ?")) {
            stmnt.setInt(1, userId);

            try (ResultSet rs = stmnt.executeQuery()) {
                while (rs.next()) {
                    tulos = rs.getInt(1);
                }
            }
        }

        return tulos;
    }

    @Override
    public boolean deleteNote(LocalDate date, User user) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
