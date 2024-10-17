package com.example.peluqueria_3.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ModeloEmpleados extends DataBase{

    public boolean validarEmpleado(String user, String contrasenya) {

        DataBase db = new DataBase();
        String query = "SELECT * FROM empleados WHERE nombre = ? AND contrasenya = ?";
        boolean empleado = false;
        try {
            Connection conexion = db.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);

            stmt.setString(1, user);
            stmt.setString(2, contrasenya);

            ResultSet rs = stmt.executeQuery();   //devuelve las filas de la query

            if (rs.next()) {
                empleado = true;  //si existe algun valor devuelve true
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return empleado;
    }
}
