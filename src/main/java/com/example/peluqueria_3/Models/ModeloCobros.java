package com.example.peluqueria_3.Models;

import java.sql.*;
import java.util.ArrayList;

public class ModeloCobros extends DataBase{

    public void insertarCobro(int id_cliente, int id_servicio, String id_empleado, int id_producto, Date fecha, float bizum, float efectivo, float tarjeta, String tipo, int cantidad){
        DataBase db = new DataBase();
        String query = "INSERT INTO cobros (fk_id_cliente, fk_id_servicio, fk_id_trabajador, fk_id_producto, fecha_cobro, bizum, tarjeta, efectivo, tipo, cantidad) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String queryStock = "UPDATE productos set stock = stock - ? WHERE id_producto = ?";

        try{
            Connection conexion = db.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);
            PreparedStatement stmtStock = conexion.prepareStatement(queryStock);

            stmt.setInt(1, id_cliente);
            if(id_servicio == 0){
                stmt.setNull(2, Types.INTEGER);
            }
            else{
                stmt.setInt(2, id_servicio);
            }
            stmt.setString(3, id_empleado);
            if(id_producto == 0){
                stmt.setNull(4, Types.INTEGER);
            }
            else {
                stmt.setInt(4, id_producto);
            }
            stmt.setDate(5, fecha);
            stmt.setFloat(6, bizum);
            stmt.setFloat(7, tarjeta);
            stmt.setFloat(8, efectivo);
            stmt.setString(9, tipo);
            stmt.setInt(10, cantidad);

            stmtStock.setInt(1, cantidad);
            stmtStock.setInt(2, id_producto);

            stmt.executeUpdate();
            stmtStock.executeUpdate();

            conexion.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public int detectarStock(int id_producto){
        int stock = 1;
        DataBase db = new DataBase();
        String query = "SELECT * FROM productos p WHERE p.id_producto = ?";

        try {
            Connection conexion = db.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);

            stmt.setInt(1, id_producto);

            ResultSet rs = stmt.executeQuery();   // Devuelve las filas de la query

            while (rs.next()) {  // Mientras existan registros
                stock = rs.getInt("stock");
            }
            conexion.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return stock;
    }

    public float detectarPrecioProducto(int id_producto){
        float precio = 0;
        DataBase db = new DataBase();
        String query = "SELECT * FROM productos p WHERE p.id_producto = ?";

        try {
            Connection conexion = db.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);

            stmt.setInt(1, id_producto);

            ResultSet rs = stmt.executeQuery();   // Devuelve las filas de la query

            while (rs.next()) {  // Mientras existan registros
                precio = rs.getFloat("precio");
            }
            conexion.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return precio;
    }

    public float detectarPrecioServicio(int id_servicio){
        float precio = 0;
        DataBase db = new DataBase();
        String query = "SELECT * FROM servicios s WHERE s.id_servicio = ?";

        try {
            Connection conexion = db.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);

            stmt.setInt(1, id_servicio);

            ResultSet rs = stmt.executeQuery();   // Devuelve las filas de la query

            while (rs.next()) {  // Mientras existan registros
                precio = rs.getFloat("precio");
            }
            conexion.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return precio;
    }

}
