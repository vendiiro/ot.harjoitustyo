package domain;

import domain.User;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;

/**
 * Luokka edustaa päiväkohtaista muistiinpanoa.  
 */
public class Note {
    
    private LocalDate date;
    private int km;
    private String content;
    private User user;
    private int id;
 
    /**
     * Konstruktori.
     * 
     * @param date Päivämäärä, jolle muistiinpano on luotu
     * @param km Kuinka monta kilometria muistiinpanoon liittyy
     * @param content Teksti, joka halutaan liittää osaksi muistiinpanoa
     * @param user Käyttäjä, johon muistiinpano liittyy
     * @param id Muistiinpanon tunnus
     */
    public Note(LocalDate date, int km, String content, User user, int id) {
        
        this.date = date;
        this.km = km;
        this.content = content;
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
