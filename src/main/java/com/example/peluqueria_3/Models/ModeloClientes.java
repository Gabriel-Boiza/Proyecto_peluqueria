package com.example.peluqueria_3.Models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ModeloClientes extends DataBase{

    public void crearCliente(String nombre, String apellido, String tel, String correo, String observaciones, boolean ley_datos){
        DataBase db = new DataBase();
        String query = "INSERT INTO clientes (nombre, apellido, tel, correo, observaciones, ley_datos) VALUES (?, ?, ?, ?, ? ,?)";
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
            conexion.close();

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
            conexion.close();
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
            conexion.close();

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
            conexion.close();
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
            conexion.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return clientes;
    }

    public ArrayList<Cobros> datosFichaCliente(int id_cliente) {

        ArrayList<Cobros> arrayCobros = new ArrayList<Cobros>();
        DataBase db = new DataBase();
        String query = "SELECT * FROM cobros co " +
                "INNER JOIN clientes c ON co.fk_id_cliente = c.id_cliente " +
                "INNER JOIN servicios s ON co.fk_id_servicio = s.id_servicio " +
                "INNER JOIN productos p ON co.fk_id_producto = p.id_producto " +
                "INNER JOIN trabajadores t ON co.fk_id_trabajador = t.DNI " +
                "WHERE co.fk_id_cliente = ?";

        try {
            Connection conexion = db.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);

            stmt.setInt(1, id_cliente);

            ResultSet rs = stmt.executeQuery();   // Devuelve las filas de la query

            while (rs.next()) {  // Mientras existan registros

                String nombre = rs.getString("c.nombre");
                String servicio = rs.getString("s.nombre");
                String producto = rs.getString("p.nombre");
                String trabajador = rs.getString("t.nombre");
                Date fecha = rs.getDate("co.fecha_cobro");
                float bizum = rs.getFloat("co.bizum");
                float efectivo = rs.getFloat("co.efectivo");
                float tarjeta = rs.getFloat("co.tarjeta");

                Cobros cobro = new Cobros(nombre, servicio, producto, trabajador, fecha, bizum, tarjeta, efectivo);
                arrayCobros.add(cobro);
            }
            conexion.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return arrayCobros;
    }

}
