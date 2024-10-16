package com.example.peluqueria_3.Models;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
    private  String URL = "jdbc:mysql://localhost:3306/peluqueria";
    private  String USER = "root";
    private  String PASSWORD = "";

    public Connection getConnection() {  //devuelve la conexion a la bbdd
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos.");
            e.printStackTrace();
        }
        return connection;
    }
}
