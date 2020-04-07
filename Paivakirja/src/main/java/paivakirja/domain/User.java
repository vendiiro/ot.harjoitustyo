/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paivakirja.domain;

import java.util.Objects;

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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.name);
        hash = 11 * hash + Objects.hashCode(this.username);
        hash = 11 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return true;
    }

    
    
   }
