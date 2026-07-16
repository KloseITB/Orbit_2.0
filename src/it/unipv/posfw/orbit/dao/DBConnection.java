package it.unipv.posfw.orbit.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class for managing the SQLite database connection.
 * Provides a single point of access to the database driver.
 */

public class DBConnection {
    private static final String URL = "jdbc:sqlite:res/database/orbit_database.db"; // database directory
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