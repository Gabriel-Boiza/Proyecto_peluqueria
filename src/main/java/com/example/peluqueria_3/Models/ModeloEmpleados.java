package com.example.peluqueria_3.Models;

import com.example.peluqueria_3.Controllers.EmpleadosController;
import  com.example.peluqueria_3.Controllers.mainController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ModeloEmpleados extends DataBase{

    public Empleados validarEmpleado(String user, String contrasenya) {

        DataBase db = new DataBase();
        String query = "SELECT * FROM trabajadores WHERE usuario = ? AND password = ?";
        Empleados empleado = null;
        try {
            Connection conexion = db.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);

            stmt.setString(1, user);
            stmt.setString(2, contrasenya);

            ResultSet rs = stmt.executeQuery();   //devuelve las filas de la query

            if (rs.next()) {
                String id = rs.getString("DNI");
                String usuario = rs.getString("usuario");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String correo = rs.getString("correo");
                String password = rs.getString("password");
                String telefono = rs.getString("tel");
                String direccion = rs.getString("direccion");
                String rol = rs.getString("rol");
                String estado = rs.getString("estado");

                Float comision_ventas = rs.getFloat("comision_ventas");
                Float comision_servicios = rs.getFloat("comision_servicios");
                Float lim_comision_servicios = rs.getFloat("limite_comision_servicios");

                empleado = new Empleados(id, usuario, nombre, apellido, correo, password, telefono, direccion,rol, estado, comision_ventas, comision_servicios, lim_comision_servicios);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return empleado;
    }
}
