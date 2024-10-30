package com.example.peluqueria_3.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ModeloServicios extends  DataBase{

    public void crearServicio(int idServicio, String nombre, String descripcion, String fecha, String hora, float precio){
        DataBase db = new DataBase();
        String query = "INSERT INTO servicios (id_servicio, nombre, descripcion, fecha, hora, precio) VALUES (?, ?, ?, ?, ? ,?)";
        try{
            Connection conexion = db.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);

            stmt.setInt(1, idServicio);
            stmt.setString(2, nombre);
            stmt.setString(3, descripcion);
            stmt.setString(4, fecha);
            stmt.setString(5, hora);
            stmt.setFloat(6, precio);

            stmt.executeUpdate();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void eliminarServicio(int idServicio){
        DataBase db = new DataBase();
        String query = "DELETE FROM servicios WHERE id_servicio = ?";
        try{
            Connection conexion = db.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);

            stmt.setInt(1, idServicio);
            stmt.executeUpdate();

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void editarServicio(int idServicio, String nombre, String descripcion, String fecha, String hora, float precio){
        DataBase db = new DataBase();
        String query = "UPDATE servicios SET nombre = ?, descripcion = ?, fecha = ?, hora = ?, precio = ? WHERE id_servicio = ?";

        try{
            Connection conexion = db.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);

            stmt.setString(1, nombre);
            stmt.setString(2, descripcion);
            stmt.setString(3, fecha);
            stmt.setString(4, hora);
            stmt.setFloat(5, precio);
            stmt.setInt(6, idServicio);

            stmt.executeUpdate();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public Servicios obtenerServicio(int idServicio){
        DataBase db = new DataBase();
        String query = "SELECT * FROM servicios WHERE id_servicio = ?";

        Servicios servicio = null;
        try{
            Connection conexion = db.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);

            stmt.setInt(1, idServicio);

            ResultSet rs = stmt.executeQuery();   //devuelve las filas de la query

            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                String fecha = rs.getString("fecha");
                String hora = rs.getString("hora");
                float precio = rs.getFloat("precio");

                servicio = new Servicios(idServicio, nombre, descripcion, fecha, hora, precio);
            }

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return servicio;
    }

    public ArrayList<Servicios> mostrarServicios(){

        ArrayList<Servicios> servicios = new ArrayList<>();
        DataBase db = new DataBase();
        String query = "SELECT * FROM servicios";

        try{
            Connection conexion = db.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);

            ResultSet rs = stmt.executeQuery();   //devuelve las filas de la query

            while (rs.next()){  //mientras existan registros
                int id = rs.getInt("id_servicio");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                String fecha = rs.getString("fecha");
                String hora = rs.getString("hora");
                Float precio = rs.getFloat("precio");

                Servicios servicio = new Servicios(id, nombre, descripcion, fecha, hora, precio);
                servicios.add(servicio);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return servicios;
    }
}
