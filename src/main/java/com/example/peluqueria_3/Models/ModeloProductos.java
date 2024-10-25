package com.example.peluqueria_3.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ModeloProductos extends DataBase{

    public void crearProducto(String nombre, String marca, String descripcion, float precio, int stock, String codigo_barras){
        DataBase db = new DataBase();
        String query = "INSERT INTO productos (nombre, marca, descripcion, precio, stock, codigo_barras) VALUES (?, ?, ?, ?, ? ,?)";
        try{
            Connection conexion = db.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);

            stmt.setString(1, nombre);
            stmt.setString(2, marca);
            stmt.setString(3, descripcion);
            stmt.setFloat(4, precio);
            stmt.setInt(5, stock);
            stmt.setString(6, codigo_barras);

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Productos obtenerProducto(int idProducto){
        DataBase db = new DataBase();
        String query = "SELECT * FROM productos WHERE id_producto = ?";

        Productos producto = null;
        try{
            Connection conexion = db.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);

            stmt.setInt(1, idProducto);

            ResultSet rs = stmt.executeQuery();   //devuelve las filas de la query

            if (rs.next()) {
                int id_producto = rs.getInt("id_producto");
                String nombre = rs.getString("nombre");
                String marca = rs.getString("marca");
                String descripcion = rs.getString("descripcion");
                float precio = rs.getFloat("precio");
                int stock = rs.getInt("stock");
                String codigo_barras = rs.getString("codigo_barras");

                producto = new Productos(id_producto, nombre, marca, descripcion, precio, stock, codigo_barras);
            }

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return producto;
    }

    public void editarProducto(String nombre, String marca, String descripcion, float precio, int stock, String codigo_barras){
        DataBase db = new DataBase();
        String query = "UPDATE productos SET nombre = ?, marca = ?, descripcion, precio = ?, stock = ?, codigo_barras = ?";

        try{
            Connection conexion = db.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);

            stmt.setString(1, nombre);
            stmt.setString(2, marca);
            stmt.setString(3, descripcion);
            stmt.setFloat(4, precio);
            stmt.setInt(5, stock);
            stmt.setString(6, codigo_barras);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void eliminarProducto(int idProducto){
        DataBase db = new DataBase();
        String query = "DELETE FROM productos WHERE id_producto = ?";

        try{
            Connection conexion = db.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);
            stmt.setInt(1, idProducto);

            ResultSet rs = stmt.executeQuery();

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int obtenerStockProducto(int idProducto){
        DataBase db = new DataBase();
        String query = "SELECT stock FROM productos WHERE id_producto = ?";
        int stock = -1;
        try{
            Connection conexion = db.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);

            stmt.setInt(1, idProducto);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                stock = rs.getInt("stock");
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return stock;
    }

}
