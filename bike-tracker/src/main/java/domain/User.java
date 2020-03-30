package domain;

/**
 * Luokka edustaa käyttäjää.
 */
public class User {
    private String name;
    private String username;
    private int id;

    /**
     * Konstruktori.
     * 
     * @param name Käyttäjän nimi
     * @param username Käyttäjän käyttäjänimi
     * @param id Käyttäjän tunnus
     */
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