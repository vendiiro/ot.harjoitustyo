/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import paivakirja.domain.User;
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
public class UserTest {
    
  @Test
    public void equalWhenSameUsername() {
        User u1 = new User("big rammy", "bigR", 1);
        User u2 = u1;
        assertTrue(u1.equals(u2));
    }
    
    @Test
    public void nonEqualWhenDifferentId() {
        User u1 = new User("test", "Teest", 3);
        User u2 = new User("test", "Teest", 4);
        assertFalse(u1.equals(u2));
    } 
    
    @Test
    public void nonEqualWhenDifferentType() {
        User u = new User("test", "Hest", 5);
        Object o = new Object();
        assertFalse(u.equals(o));
    }     
}

