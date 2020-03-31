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
     private DaoNote noteDao;
    private DaoUser userDao;
    private User currentUser;
    
    
    public NoteService(DaoNote noteDao, DaoUser userDao) {
        this.noteDao = noteDao;
        this.userDao = userDao;
    }
    
    
    public boolean deleteNote(LocalDate date) throws SQLException  {
        List<Note> list = noteDao.getAll(currentUser);
        boolean result = false;
        for (Note note : list) {
            if (note.getDate().equals(date)) {
                result = true;
            }
        }
        if (result == true) {
            this.noteDao.deleteNote(date, currentUser);
        } 
        return result;
    }
    

    public List<Note> getAll() throws SQLException {
        return noteDao.getAll(currentUser);          
    }
    
   
    public boolean createNote(LocalDate date, int km, String content) throws SQLException {       
        noteDao.create(date, km, content, currentUser);  
        return true;
    }
    
   
}

