/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.User;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class SqlUserDaoTest {
    
    Database database;
    UserDao userDao; 
    
    User user;
    
    @Before
    public void setup() throws Exception {
        database = new Database("jdbc:sqlite:test-tietokanta.db");
        database.createTables();
        
        this.userDao = new SqlUserDao(database);
        this.user = this.userDao.create("Cynthia Cyclist", "cycy");      
    }
    
    @After
    public void cleanup() throws IOException {
        Files.deleteIfExists(Paths.get("test-tietokanta.db"));
    }
    
    @Test
    public void existingUserIsFound() throws SQLException{
        User user = userDao.findByUsername("cycy");
        
        assertEquals("Cynthia Cyclist", user.getName());
        assertEquals("cycy", user.getUsername());   
    }
    
    @Test
    public void nonExistingUserIsFound() throws SQLException{
        User user = userDao.findByUsername("batman");
        
        assertEquals(null, user);
    }
    
    @Test 
    public void newUserIsFound() throws SQLException {
        userDao.create("Jill Homer", "jill");
        
        User user = userDao.findByUsername("jill");
        
        assertEquals("Jill Homer", user.getName());
        assertEquals("jill", user.getUsername());
    }
    
}