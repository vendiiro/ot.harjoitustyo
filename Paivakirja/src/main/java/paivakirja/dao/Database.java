package paivakirja.dao;

import java.sql.*;

public class Database {

    private final String database;

    public Database(String adress) throws ClassNotFoundException {
        this.database = adress;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(database);
    }

    public void createTable() throws SQLException {
        String userTables = "CREATE TABLE IF NOT EXISTS User (\n"
                + "id integer PRIMARY KEY,\n"
                + "name text NOT NULL,\n"
                + "username text NOT NULL\n"
                + ");";

        String noteTable = "CREATE TABLE IF NOT EXISTS Note (\n"
                + "id integer PRIMARY KEY,\n"
                + "date date NOT NULL,\n"
                + "min integer NOT NULL,\n"
                + "content text NOT NULL,\n"
                + "user integer NOT NULL,\n"
                + "FOREIGN KEY(user) REFERENCES User(id)\n"
                + ");";

        try (Connection con = getConnection()) {
            Statement stmnt = con.createStatement();
            stmnt.execute(userTables);
            stmnt.execute(noteTable);

            stmnt.close();
            con.close();
        }
    }
}
