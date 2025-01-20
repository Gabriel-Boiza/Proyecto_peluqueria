package com.example.peluqueria_3.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ModeloServicios extends  DataBase{

    public void crearServicio(String nombre, String descripcion, float precio){
        DataBase db = new DataBase();
        String query = "INSERT INTO servicios (nombre, descripcion, precio) VALUES (?, ?, ?)";
        try{
            Connection conexion = db.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);


            stmt.setString(1, nombre);
            stmt.setString(2, descripcion);
            stmt.setFloat(3, precio);

            stmt.executeUpdate();
            conexion.close();
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
            conexion.close();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void editarServicio(int idServicio, String nombre, String descripcion,float precio){
        DataBase db = new DataBase();
        String query = "UPDATE servicios SET nombre = ?, descripcion = ?, precio = ? WHERE id_servicio = ?";

        try{
            Connection conexion = db.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);

            stmt.setString(1, nombre);
            stmt.setString(2, descripcion);
            stmt.setFloat(3, precio);
            stmt.setInt(4, idServicio);

            stmt.executeUpdate();
            conexion.close();
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
                float precio = rs.getFloat("precio");

                servicio = new Servicios(idServicio, nombre, descripcion, precio);
            }
            conexion.close();

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
                Float precio = rs.getFloat("precio");

                Servicios servicio = new Servicios(id, nombre, descripcion, precio);
                servicios.add(servicio);
            }
            conexion.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return servicios;
    }
}
