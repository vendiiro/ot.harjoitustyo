/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author iiro
 */
public class User {
    private String name;
    private String username;
    private int id;

    
    public User(String name, String username, int id) {
        this.name = name;
        this.username = username;
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }    
    
    public int getId() {
        return this.id;
    }   
}
