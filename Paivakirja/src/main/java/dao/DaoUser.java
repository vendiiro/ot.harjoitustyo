/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.User;

/**
 *
 * @author iiro
 */
public interface DaoUser {
    
    User create(String name, String username); 
    
    
    User findByUsername(String username);
    
}
