package com.example.peluqueria_3.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ModeloClientes extends DataBase{

    public void crearCliente(String nombre, String apellido, String tel, String correo, String observaciones, boolean ley_datos){
        DataBase db = new DataBase();
        String query = "INSERT INTO clientes (nombre, apellido, correo, observaciones, ley_datos) VALUES (?, ?, ?, ?, ? ,?)";
        try{
            Connection conexion = db.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);

            stmt.setString(1, nombre);
            stmt.setString(2, apellido);
            stmt.setString(3, tel);
            stmt.setString(4, correo);
            stmt.setString(5, observaciones);
            stmt.setBoolean(6, ley_datos);

            stmt.executeUpdate();

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void eliminarCliente(int idCliente){
        DataBase db = new DataBase();
        String query = "DELETE FROM clientes WHERE id_cliente = ?";

        try{
            Connection conexion = db.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);

            stmt.setInt(1, idCliente);

            stmt.executeUpdate();
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
                boolean ley_datos = rs.getBoolean("ley_datos");

                cliente = new Clientes(idCliente, nombre, apellido, tel, correo, observaciones, ley_datos);
            }

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return cliente;
    }

    public void editarCliente(int idCliente, String nombre, String apellido, String tel, String correo, String observaciones, boolean ley_datos){
        DataBase db = new DataBase();
        String query = "UPDATE clientes SET nombre = ?, apellido = ?, tel = ?, correo = ?, observaciones = ?, ley_datos = ? WHERE id_cliente = ?";

        try{
            Connection conexion = db.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);

            stmt.setString(1, nombre);
            stmt.setString(2, apellido);
            stmt.setString(3, tel);
            stmt.setString(4, correo);
            stmt.setString(5, observaciones);
            stmt.setBoolean(6, ley_datos);
            stmt.setInt(7, idCliente);

            stmt.executeUpdate();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Clientes> mostrarClientes(){

        ArrayList<Clientes> clientes = new ArrayList<Clientes>();
        DataBase db = new DataBase();
        String query = "SELECT * FROM clientes";

        try{
            Connection conexion = db.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);

            ResultSet rs = stmt.executeQuery();   //devuelve las filas de la query

            while (rs.next()){  //mientras existan registros
                Integer id = rs.getInt("id_cliente");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String tel = rs.getString("tel");
                String correo = rs.getString("correo");
                String observaciones = rs.getString("observaciones");
                Boolean ley_datos = rs.getBoolean("ley_datos");

                Clientes cliente = new Clientes(id, nombre, apellido, tel, correo, observaciones, ley_datos);
                clientes.add(cliente);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return clientes;
    }
}
