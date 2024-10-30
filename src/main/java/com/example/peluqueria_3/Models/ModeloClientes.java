package com.example.peluqueria_3.Models;


import lombok.Data;


import java.sql.*;
import java.util.ArrayList;


public class ModeloClientes extends DataBase{


    private int id_Cliente;


    public ArrayList<Clientes> cargarClientes() {
        DataBase db = new DataBase();
        ArrayList<Clientes> array = new ArrayList<>();
        String query = "SELECT * FROM clientes";


        try {
            Connection conn = db.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id_cliente = rs.getInt("id_cliente");
                String nombre =rs.getString("nombre");
                String apeliido = rs.getString("apellido");
                String correo = rs.getString("correo");
                String telefono = rs.getString("telefono");
                String observaciones= rs.getString("observaciones");
                Boolean ley_datos = rs.getBoolean("ley_datos");


                Clientes cliente = new Clientes(id_cliente, nombre, apeliido, correo, telefono, observaciones, ley_datos);
                array.add(cliente);
            }




        } catch (SQLException e) {
            e.printStackTrace();
        }
        return array;
    }






    public void crearCliente(String nombre, String apellido, String correo, String telefono, String observaciones, boolean leyDatos) {
        String query = "INSERT INTO clientes (nombre, apellido, telefono, correo, observaciones, ley_datos) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conexion = getConnection();
             PreparedStatement stmt = conexion.prepareStatement(query)) {


            stmt.setString(1, nombre);
            stmt.setString(2, apellido);
            stmt.setString(3, telefono);
            stmt.setString(4, correo);
            stmt.setString(5, observaciones);
            stmt.setBoolean(6, leyDatos);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }




    public void eliminarCliente(int idCliente){
        DataBase db = new DataBase();
        String query = "DELETE FROM clientes WHERE tel = ?";


        try{
            Connection conexion = db.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);


            stmt.setInt(1, idCliente);


        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public Clientes obtenerCliente(int idCliente){
        DataBase db = new DataBase();
        String query = "SELECT * FROM clientes WHERE id_cliente = ?";


        Clientes cliente = null;
        try{
            Connection conexion = db.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);


            stmt.setInt(1, idCliente);


            ResultSet rs = stmt.executeQuery();   //devuelve las filas de la query


            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String tel = rs.getString("tel");
                String correo = rs.getString("correo");
                String observaciones = rs.getString("observaciones");
                Boolean ley_datos = rs.getBoolean("ley_datos"); // Verifica que el nombre sea correcto en la base de datos




                cliente = new Clientes(idCliente, nombre, apellido, tel, correo, observaciones, ley_datos);
            }


        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return cliente;
    }


    public void editarCliente(int idCliente, String nombre, String apellido, String tel, String correo, String observaciones, boolean ley_Datos) {
        String query = "UPDATE clientes SET nombre = ?, apellido = ?, telefono = ?, correo = ?, observaciones = ?, ley_datos = ? WHERE id_cliente = ?";
        try (Connection conexion = getConnection();
             PreparedStatement stmt = conexion.prepareStatement(query)) {


            stmt.setString(1, nombre);
            stmt.setString(2, apellido);
            stmt.setString(3, tel);
            stmt.setString(4, correo);
            stmt.setString(5, observaciones);
            stmt.setBoolean(6, ley_Datos);
            stmt.setInt(7, idCliente);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}
