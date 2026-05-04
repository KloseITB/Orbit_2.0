package it.unipv.posfw.orbit.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Singleton class, establish and maintain connection to the database

public class DBConnection {
    private static final String URL = "jdbc:sqlite:orbit_database.db"; // database directory
    private static Connection connection = null;

    private DBConnection() {}

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection(URL);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Connection Error: " + e.getMessage());
        }
        return connection;
    }
}