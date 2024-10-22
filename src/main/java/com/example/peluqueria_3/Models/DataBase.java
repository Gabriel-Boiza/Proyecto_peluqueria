package com.example.peluqueria_3.Models;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase extends config{
    private final String URL = url;
    private final String USER = user;
    private final String PASSWORD = password;

    public Connection getConnection() {  //devuelve la conexion a la bbdd
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos.");
            e.getMessage();
        }
        return connection;
    }
}
