/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author iiro
 */
public class Notes {
    private LocalDate date;
    private int km;
    private String content;
    private User user;
    private int id;
 
    /**
     * Konstruktori.
     * 
     * @param date pvm, jolle muistiinpano on luotu
     * @param trainingLenght Kuinka monta kilometria muistiinpanoon liittyy
     * @param notes Teksti, joka halutaan liittää osaksi muistiinpanoa
     * @param user Käyttäjä, johon muistiinpano liittyy
     * @param id Muistiinpanon tunnus
     */
    public Notes(LocalDate date, int trainingLenght, String notes, User user, int id) {
        
        this.date = date;
        this.km = trainingLenght;
        this.content = notes;
        this.user = user;
        this.id = id;
    }
    
    public int getId() {
        return this.id;
    }
      

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public LocalDate getDate() {
        return this.date;
    }
   
    
    public void setKm(int km) {
        this.km = km;
    }
    
    public int getKm() {
        return this.km;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public String getContent() {
        return this.content;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public User getUser() {
        return this.user;
    }
    
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        return "Date: " + this.date.format(formatter) + "\n" + "Kilometers: " + 
                this.km + "\n" + "Your notes: " + this.content;
    }
}
