package domain;

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
import paivakirja.domain.User;

/**
 *
 * @author iiro
 */
public class UserTest {
    private User usr;
    
    @Before
    public void setUp() {
        usr = new User("matti", "masa", 1);
    }
    
    
  @Test
    public void equalWhenSameUsername() {
        User u1 = new User("matti", "masa", 1);
        assertTrue(usr.equals(u1));
    }
    
    @Test
    public void nonEqualWhenDifferentId() {
        User u1 = new User("test", "Teest", 2);
        assertFalse(usr.equals(u1));
    } 
    
    @Test
    public void nonEqualWhenDifferentType() {
        User u = new User("test", "Hest", 5);
        Object o = new Object();
        assertFalse(u.equals(o));
    }     
}

