/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import paivakirja.dao.DaoNote;
import paivakirja.dao.DaoUser;
import paivakirja.domain.NoteService;


/**
 *
 * @author iiro
 */
public class NoteServiceUserTest {
    
    DaoNote daoNote;
    
    DaoUser daoUser;
    
    NoteService noteService;
    
     @Before
    public void setup() {
        this.noteService = new NoteService(daoNote, daoUser);
    }
    
    
    
}
