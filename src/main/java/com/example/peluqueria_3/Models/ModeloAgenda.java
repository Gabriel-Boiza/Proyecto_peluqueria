package com.example.peluqueria_3.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public class ModeloAgenda extends DataBase{



    public void insertarCita(String id_agenda, String id_empleado, String fecha, String hora, String descripcion){
        DataBase db = new DataBase();
        String query = "INSERT INTO agenda (id_agenda, fk_id_trabajador, fecha, hora, descripcion) VALUES (?, ?, ?, ?, ?)";

        try{
            Connection conexion = db.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);

            stmt.setString(1, id_agenda);
            stmt.setString(2, id_empleado);
            stmt.setString(3, fecha);
            stmt.setString(4, hora);
            stmt.setString(5, descripcion);

            stmt.executeUpdate();
            conexion.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void modificarCita(String id_cita, String descripcion){
        DataBase db = new DataBase();
        String query = "UPDATE agenda SET descripcion = ? WHERE id_agenda = ?";

        try{
            Connection conexion = db.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);

            stmt.setString(1, descripcion);
            stmt.setString(2, id_cita);

            stmt.executeUpdate();
            conexion.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public boolean comprobarCita(String id_cita){
        DataBase db = new DataBase();
        String query = "SELECT id_agenda FROM agenda WHERE id_agenda = ?";
        boolean existe = false;
        try{
            Connection conexion = db.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);

            stmt.setString(1, id_cita);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                existe = true;
            }

            conexion.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        return existe;
    }

    public HashMap<String, Agenda> obtenerCitas(){
        DataBase db = new DataBase();
        String query = "SELECT * FROM agenda";
        HashMap<String, Agenda> datosCitas = new HashMap<>();

        try{
            Connection conexion = db.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){

                String id_cita = rs.getString("id_agenda");
                String fecha = rs.getString("fecha");
                String hora = rs.getString("hora");
                String descripcion = rs.getString("descripcion");
                String id_empleado = rs.getString("fk_id_trabajador");

                Agenda agenda = new Agenda(id_cita, fecha, hora, descripcion, id_empleado);

                datosCitas.put(id_cita, agenda);
            }
            conexion.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        return datosCitas;
    }

    public void deleteCitas(String id_cita){
        DataBase db = new DataBase();
        String query = "DELETE FROM agenda WHERE id_agenda = ?";

        try{
            Connection conexion = db.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);

            stmt.setString(1, id_cita);

            stmt.execute();

            conexion.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
