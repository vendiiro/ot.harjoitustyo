package paivakirja.dao;

import java.sql.*;

public class Database {

    private final String database;

    /**
     * Konstruktori.
     *
     * @param adress tietokannan osoite
     * @throws ClassNotFoundException virhe tietokannassa
     */
    public Database(String adress) throws ClassNotFoundException {
        this.database = adress;
    }

    /**
     * Metodi luo yhteyden tietokantaan.
     *
     * @return yhteys tietokantaan
     * 
     * @throws SQLException virhe tietokannassa
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(database);
    }

    /**
     * Metodi luo tietokantaan tietokantataulut.
     *
     * @throws SQLException virhe tietokannassa
     */
    public void creatingTables() throws SQLException {
        String userTables = "CREATE TABLE IF NOT EXISTS User (\n" + "id integer PRIMARY KEY,\n"
                + "name text NOT NULL,\n" + "username text NOT NULL\n" + ");";

        String noteTable = "CREATE TABLE IF NOT EXISTS Note (\n"
                + "id integer PRIMARY KEY,\n" + "date date NOT NULL,\n" + "min integer NOT NULL,\n"
                + "content text NOT NULL,\n" + "user integer NOT NULL,\n"
                + "FOREIGN KEY(user) REFERENCES User(id)\n" + ");";

        try (Connection con = getConnection()) {
            Statement stmnt = con.createStatement();
            stmnt.execute(userTables);
            stmnt.execute(noteTable);

            stmnt.close();
            con.close();
        }
    }
}
