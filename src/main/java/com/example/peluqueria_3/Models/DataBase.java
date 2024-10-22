package com.example.peluqueria_3.Models;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
    private final String URL = "jdbc:mysql://u4xmlspbjyd111rs:iaeRku37k6Ftnaoc7Es8@bugffmlodzmfiksquriy-mysql.services.clever-cloud.com:3306/bugffmlodzmfiksquriy";
    private final String USER = "u4xmlspbjyd111rs";
    private final String PASSWORD = "iaeRku37k6Ftnaoc7Es8";

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
