package com.agri.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Read all connection info from Environment Variables
            String dbUrl = System.getenv("DB_URL");
            String dbUser = System.getenv("DB_USER");
            String dbPassword = System.getenv("DB_PASSWORD");

            // Register the PostgreSQL driver
            Class.forName("org.postgresql.Driver");

            // Create the connection using the server-provided variables
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.err.println("Error connecting to the database: " + e.getMessage());
        }
        return connection;
    }
}