/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author iiro
 */
import java.sql.*;

/** 
 * Luokka luo tietokannan sovellukselle ja tallentaa sen parametrina annettuun osoitteeseen.
 */ 
public class Database {

    private String databaseAddress;
    
    /**
     * Konstruktori.
     * @param databaseAddress Tietokannan osoite
     * @throws ClassNotFoundException virhe tietokannassa
     */
    public Database(String databaseAddress) throws ClassNotFoundException {
        this.databaseAddress = databaseAddress;
    }
  
    /**
     * Metodi luo yhteyden tietokantaan.
     * 
     * @return yhteys tietokantaan
     * 
     * @throws SQLException virhe tietokannassa
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseAddress);
    }
    
    /**
     * Metodi luo tietokantaan tietokantataulut.
     * 
     * @throws SQLException virhe tietokannassa
     */
    public void createTables() throws SQLException {
        String userTable = "CREATE TABLE IF NOT EXISTS User (\n"
            +   "id integer PRIMARY KEY,\n"
            +   "name text NOT NULL,\n"
            +   "username text NOT NULL\n"       
            +   ");";
        
        String noteTable = "CREATE TABLE IF NOT EXISTS Note (\n"
            +   "id integer PRIMARY KEY,\n" 
            +   "date date NOT NULL,\n"
            +   "km integer NOT NULL,\n"
            +   "content text NOT NULL,\n"
            +   "user integer NOT NULL,\n" 
            +    "FOREIGN KEY(user) REFERENCES User(id)\n"    
            +   ");";  
        
        Connection con = getConnection();
        Statement stmt = con.createStatement();         
        stmt.execute(userTable);
        stmt.execute(noteTable);
        
        stmt.close();
        con.close();
    }
}    

