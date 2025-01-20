package com.example.peluqueria_3.Models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

public class ModeloCobros extends DataBase{

    public void insertarCobro(int id_cliente, int id_servicio, String id_empleado, int id_producto, Date fecha, float bizum, float efectivo, float tarjeta){
        DataBase db = new DataBase();
        String query = "INSERT INTO cobros (fk_id_cliente, fk_id_servicio, fk_id_trabajador, fk_id_producto, fecha_cobro, bizum, tarjeta, efectivo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";


        try{
            Connection conexion = db.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);

            stmt.setInt(1, id_cliente);
            if(id_servicio == 0){
                stmt.setNull(2, java.sql.Types.INTEGER);
            }
            else{
                stmt.setInt(2, id_servicio);
            }
            stmt.setString(3, id_empleado);
            if(id_producto == 0){
                stmt.setNull(4, java.sql.Types.INTEGER);
            }
            else {
                stmt.setInt(4, id_producto);
            }
            stmt.setDate(5, fecha);
            stmt.setFloat(6, bizum);
            stmt.setFloat(7, tarjeta);
            stmt.setFloat(8, efectivo);

            stmt.executeUpdate();
            conexion.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
