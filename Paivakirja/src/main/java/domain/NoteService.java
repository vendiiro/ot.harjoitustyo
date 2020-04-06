/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import dao.DaoNote;
import dao.DaoUser;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author iiro
 */
public class NoteService {

    private DaoNote daoNote;
    private DaoUser daoUser;
    private User existingUser;

    public NoteService(DaoNote daoNote, DaoUser daoUser) {
        this.daoNote = daoNote;
        this.daoUser = daoUser;
    }

    public boolean deleteNote(LocalDate date) throws SQLException {
        List<Note> list = daoNote.getAll(existingUser);
        boolean result = false;
        for (Note note : list) {
            if (note.getDate().equals(date)) {
                result = true;
            }
        }
        if (result == true) {
            this.daoNote.deleteNote(date, existingUser);
        }
        return result;
    }

    public List<Note> getAll() throws SQLException {
        return daoNote.getAll(existingUser);
    }

    public boolean createNote(LocalDate date, int min, String content) throws SQLException {
        daoNote.create(date, min, content, existingUser);
        return true;
    }
    
    public boolean login(String username) throws SQLException {
        User user = daoUser.getUsingUsername(username);
        if (user == null) {
            return false;
        }
        existingUser = user;
        return true;
    }

    public boolean isUserLoggedIn() {
        if (existingUser == null) {
            return false;
        }
        return true;
    }
    
    public boolean createUser(String name, String username) throws SQLException {
        if (daoUser.getUsingUsername(username) != null) {
            return false;
        }
        daoUser.create(name, username);
        return true;
    }

}
