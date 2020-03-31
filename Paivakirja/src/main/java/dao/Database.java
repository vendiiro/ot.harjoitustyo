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

public class Database {

    private final String database;
    
    public Database(String adress) throws ClassNotFoundException {
        this.database = adress;
    }
    
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(database);
    }
    
    public void creatingTables() throws SQLException {
        String userTables = "CREATE TABLE IF NOT EXISTS User (\n"
            +   "id integer PRIMARY KEY,\n"
            +   "name text NOT NULL,\n"
            +   "username text NOT NULL\n"       
            +   ");";
        
        String noteTable = "CREATE TABLE IF NOT EXISTS Note (\n"
            +   "id integer PRIMARY KEY,\n" 
            +   "date date NOT NULL,\n"
            +   "min integer NOT NULL,\n"
            +   "content text NOT NULL,\n"
            +   "user integer NOT NULL,\n" 
            +   ");";  
        
        try (Connection con = getConnection()) {
            Statement stmnt = con.createStatement();
            stmnt.execute(userTables);
            stmnt.execute(noteTable);
            
            stmnt.close();
        }
    }    
}
