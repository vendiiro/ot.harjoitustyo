/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paivakirja.dao;

import paivakirja.domain.User;
import java.sql.SQLException;

/**
 *
 * @author iiro
 */
public interface DaoUser {
    
    User create(String name, String username) throws SQLException; 
     
    User getUsingUsername(String username) throws SQLException;
    
}
