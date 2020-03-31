/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Note {
    private LocalDate date;
    private int length;
    private String content;
    private User user;
    private int id;
 
    public Note(LocalDate date, int length, String content, User user, int id) {
        
        this.date = date;
        this.length = length;
        this.content = content;
        this.user = user;
        this.id = id;
    }  
    
    public LocalDate getDate() {
        return this.date;
    }
   
  
    public int getMinutes() {
        return this.length;
    }

    
    public String getContent() {
        return this.content;
    }
   
    
    public User getUser() {
        return this.user;
    }
    
     public int getId() {
        return this.id;
    }
    
    @Override
    public String toString() {
        DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        return "Date: " + this.date.format(DTF) + "\n" + "Length of the training session: " + 
                this.length + "\n" + "Your notes from this session: " + this.content;
    }
}
