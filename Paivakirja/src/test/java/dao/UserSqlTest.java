
package dao;

import paivakirja.dao.DaoUser;
import paivakirja.dao.Database;
import paivakirja.dao.UserSql;
import paivakirja.domain.User;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author iiro
 */
public class UserSqlTest {
    
    Database database;
    DaoUser daoUser; 
    
    User user;
    
    @Before
    public void setup() throws Exception {
        database = new Database("jdbc:sqlite:test-tietokanta.db");
        database.creatingTables();
        
        this.daoUser = new UserSql(database) ;
        this.user = this.daoUser.create("Big Rammy", "bigR");      
    }
    
    @After
    public void cleanup() throws IOException {
        Files.deleteIfExists(Paths.get("test-tietokanta.db"));
    }
    
    @Test
    public void existingUserIsFound() throws SQLException{
        User usr = daoUser.getUsingUsername("bigR");
        
        assertEquals("Big Rammy", usr.getName());
        assertEquals("bigR", usr.getUsername());   
    }
    
    @Test
    public void nonExistingUserIsFound() throws SQLException{
        User usr = daoUser.getUsingUsername("salisiima");
        
        assertEquals(null, usr);
    }
    
    @Test 
    public void newUserIsFound() throws SQLException {
        daoUser.create("Chris Bumstaid", "cbum");
        
        User usr = daoUser.getUsingUsername("cbum");
        
        assertEquals("Chris Bumstaid", usr.getName());
        assertEquals("cbum", usr.getUsername());
    }
    
}