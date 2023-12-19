package org.oms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionManager {
    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/orders";
    private static final String username = "root";
    private static final String password = "Pranavroot123";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(jdbcUrl, username, password);
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as needed
            throw new RuntimeException("Error getting database connection", e);
        }
    }
}
