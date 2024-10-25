package com.example.peluqueria_3.Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DataBase extends config {
    private final String URL = url;
    private final String USER = user;
    private final String PASSWORD = password;

    public Connection getConnection() {
        Connection connection = null;
        try {
            // Load the JDBC driver (replace with your driver class name)
            Class.forName("com.mysql.cj.jdbc.Driver");  // Replace for your database driver
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("Error: JDBC Driver not found in classpath.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error connecting to database:");
            e.printStackTrace();
        }
        return connection;
    }
}