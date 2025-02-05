package com.example.peluqueria_3.Models;

import com.example.peluqueria_3.Controllers.DatosGlobales;
import com.example.peluqueria_3.Controllers.EmpleadosController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

public class ModeloEmpleados extends DataBase{

    public String obtenerEmpleado(String usuario){
        DataBase db = new DataBase();
        String query  = "SELECT DNI FROM trabajadores WHERE usuario = ?";
        String dniUsuario = "";

        try {
            Connection conexion = db.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);
            stmt.setString(1, usuario);

            // Ejecutar la consulta
            ResultSet rs = stmt.executeQuery();

            // Procesar el resultado
            if (rs.next()) {
                dniUsuario = rs.getString("DNI");
            }

            // Cerrar recursos
            rs.close();
            stmt.close();
            conexion.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("error obtener empleado");
        }

        return dniUsuario;
    }


    public boolean empleadoUsuarioExiste(String usuario){
        DataBase db = new DataBase();
        String query  = "SELECT usuario FROM trabajadores WHERE DNI = ?";
        boolean usuarioEmpleadoExiste = false;

        try {
            Connection conexion = db.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);
            stmt.setString(1, usuario);

            ResultSet rs = stmt.getResultSet();

            if(rs.next()){
                usuarioEmpleadoExiste = true;
            }
            conexion.close();

        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("error empleado existe");
        }
        return usuarioEmpleadoExiste;
    }

    public Empleados validarEmpleado(String user, String contrasenya) {

        DataBase db = new DataBase();
        String query = "SELECT * FROM trabajadores WHERE usuario = ? AND password = ?";
        Empleados empleado = null;
        try {
            Connection conexion = db.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);

            stmt.setString(1, user);
            stmt.setString(2, contrasenya);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String id = rs.getString("DNI");
                String usuario = rs.getString("usuario");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String correo = rs.getString("correo");
                String password = rs.getString("password");
                String telefono = rs.getString("tel");
                String direccion = rs.getString("direccion");


                Float comision_ventas = rs.getFloat("comision_ventas");
                Float comision_servicios = rs.getFloat("comision_servicios");
                Float lim_comision_servicios = rs.getFloat("limite_comision_servicios");

                String rol = rs.getString("rol");
                String estado = rs.getString("estado");

                String img = rs.getString("img");

                empleado = new Empleados(id, usuario, nombre, apellido, correo, password, telefono, direccion,  comision_ventas, comision_servicios, lim_comision_servicios, rol, estado, img);
            }
            conexion.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return empleado;
    }

    public Empleados validarAdministrador(String user, String contrasenya) {

        DataBase db = new DataBase();
        String query = "SELECT * FROM trabajadores WHERE usuario = ? AND password = ? AND rol = 'administrador'";
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


                Float comision_ventas = rs.getFloat("comision_ventas");
                Float comision_servicios = rs.getFloat("comision_servicios");
                Float lim_comision_servicios = rs.getFloat("limite_comision_servicios");

                String rol = rs.getString("rol");
                String estado = rs.getString("estado");

                String img = rs.getString("img");

                empleado = new Empleados(id, usuario, nombre, apellido, correo, password, telefono, direccion,  comision_ventas, comision_servicios, lim_comision_servicios, rol, estado, img);
            }
            conexion.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return empleado;
    }

    public boolean empleadoExiste(String DNI){
        DataBase db = new DataBase();
        String query = "SELECT * FROM trabajadores WHERE DNI = ?";
        boolean duplicado = false;
        try{
            Connection conexion = db.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);
            stmt.setString(1, DNI);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                duplicado = true;
            }
            conexion.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return duplicado;
    }

    public void crearEmpleado(String DNI,  String usuario, String nombre, String apellido, String correo, String password, String tel, String direccion,  float comision_ventas, float comision_servicios, float limite_comision_servicios, String rol, String estado){
        DataBase db = new DataBase();
        String query = "INSERT INTO trabajadores (DNI, usuario, nombre, apellido, correo, password, tel, direccion, comision_ventas, comision_servicios, limite_comision_servicios, rol, estado) VALUES (?, ?, ?, ?, ? ,?, ?, ? ,? ,? ,? ,? ,?)";
        try{
            Connection conexion = db.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);

            stmt.setString(1, DNI);
            stmt.setString(2, usuario);
            stmt.setString(3, nombre);
            stmt.setString(4, apellido);
            stmt.setString(5, correo);
            stmt.setString(6, password);
            stmt.setString(7, tel);
            stmt.setString(8, direccion);
            stmt.setFloat(9, comision_ventas);
            stmt.setFloat(10, comision_servicios);
            stmt.setFloat(11, limite_comision_servicios);
            stmt.setString(12, rol);
            stmt.setString(13, estado);

            stmt.executeUpdate();
            conexion.close();

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Empleados> mostrarEmpleados(){

        ArrayList<Empleados> empleados = new ArrayList<Empleados>();
        DataBase db = new DataBase();
        String query = "SELECT * FROM trabajadores";

        try{
            Connection conexion = db.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);

            ResultSet rs = stmt.executeQuery();   //devuelve las filas de la query

            while (rs.next()){  //mientras existan registros
                String id = rs.getString("DNI");
                String usuario = rs.getString("usuario");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String correo = rs.getString("correo");
                String password = rs.getString("password");
                String telefono = rs.getString("tel");
                String direccion = rs.getString("direccion");


                Float comision_ventas = rs.getFloat("comision_ventas");
                Float comision_servicios = rs.getFloat("comision_servicios");
                Float lim_comision_servicios = rs.getFloat("limite_comision_servicios");

                String rol = rs.getString("rol");
                String estado = rs.getString("estado");

                String img = rs.getString("img");

                Empleados empleado = new Empleados(id, usuario, nombre, apellido, correo, password, telefono, direccion, comision_ventas, comision_servicios, lim_comision_servicios, rol, estado, img);
                empleados.add(empleado);
            }
            conexion.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return empleados;
    }

    public int eliminarEmpleado(String DNI){
        int filas = -1;
        DataBase db = new DataBase();
        String query = "DELETE FROM trabajadores WHERE DNI = ?";
        try{
            Connection conexion = db.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);

            stmt.setString(1, DNI);
            filas = stmt.executeUpdate();
            conexion.close();

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return filas;
    }

    public void editarEmpleado(String DNI, String usuario, String nombre, String apellido, String correo, String password, String tel, String direccion, float comision_ventas, float comision_servicios, float limite_comision_servicios, String rol, String estado){
        DataBase db = new DataBase();
        String query = "UPDATE trabajadores SET usuario = ?, nombre = ?, apellido = ?, correo = ?, password = ?, tel = ?, direccion = ?, comision_ventas = ?, comision_servicios = ?, limite_comision_servicios = ?, rol = ?, estado = ? WHERE DNI = ?";
        try{
            Connection conexion = db.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);

            stmt.setString(1, usuario);
            stmt.setString(2, nombre);
            stmt.setString(3, apellido);
            stmt.setString(4, correo);
            stmt.setString(5, password);
            stmt.setString(6, tel);
            stmt.setString(7, direccion);
            stmt.setFloat(8, comision_ventas);
            stmt.setFloat(9, comision_servicios);
            stmt.setFloat(10, limite_comision_servicios);
            stmt.setString(11, rol);
            stmt.setString(12, estado);
            stmt.setString(13, DNI);

            stmt.executeUpdate();
            conexion.close();

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Float> obtenerSumasCobros(String DNI, int mes, int anio) {
        ArrayList<Float> sumas = new ArrayList<>();
        DataBase db = new DataBase();


        String query = "SELECT SUM(bizum) AS suma_bizum, SUM(tarjeta) AS suma_tarjeta, SUM(efectivo) AS suma_efectivo " +
                "FROM cobros WHERE fk_id_trabajador = ? AND MONTH(fecha_cobro) = ? AND YEAR(fecha_cobro) = ?";

        try {
            Connection conexion = db.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);


            stmt.setString(1, DNI);
            stmt.setInt(2, mes);
            stmt.setInt(3, anio);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Float sumaBizum = rs.getFloat("suma_bizum");
                Float sumaTarjeta = rs.getFloat("suma_tarjeta");
                Float sumaEfectivo = rs.getFloat("suma_efectivo");

                sumas.add(sumaBizum);
                sumas.add(sumaTarjeta);
                sumas.add(sumaEfectivo);
            }
            conexion.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return sumas;
    }


    public int contarServicios(String dni, int mes, int anio) {
        int count = 0;
        DataBase db = new DataBase();
        String query = "SELECT COUNT(*) AS total FROM cobros WHERE fk_id_trabajador = ? AND tipo  = ? AND MONTH(fecha_cobro) = ? AND YEAR(fecha_cobro) = ?";

        try {
            Connection conexion = db.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);

            stmt.setString(1, dni);
            stmt.setString(2, "servicio");
            stmt.setInt(3, mes);
            stmt.setInt(4, anio);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt("total");
            }
            conexion.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("contar productos");
        }

        return count;
    }

    public int contarProductos(String dni, int mes, int anio) {
        int count = 0;
        DataBase db = new DataBase();
        String query = "SELECT COUNT(*) AS total FROM cobros WHERE fk_id_trabajador = ? AND tipo  = ? AND MONTH(fecha_cobro) = ? AND YEAR(fecha_cobro) = ?";

        try {
            Connection conexion = db.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);

            stmt.setString(1, dni);
            stmt.setString(2, "producto");
            stmt.setInt(3, mes);
            stmt.setInt(4, anio);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt("total");
            }
            conexion.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("contar productos");
        }

        return count;
    }

    public ArrayList<Integer> obtenerAnios() {
        ArrayList<Integer> aniosList = new ArrayList<>();
        DataBase db = new DataBase();

        // Consulta SQL para obtener años distintos
        String query = "SELECT DISTINCT YEAR(fecha_cobro) AS anio FROM cobros ORDER BY anio";

        try {
            Connection conexion = db.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                aniosList.add(rs.getInt("anio"));
            }

            conexion.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("error obtener años");
        }

        return aniosList;
    }



    /*
    public void cambiarEstadoEmpleado(String estado, String DNI){
        DataBase db = new DataBase();
        String query = "UPDATE trabajadores SET estado = ? WHERE DNI = ?";
        try{
            Connection conexion = db.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);

            stmt.setString(1, estado);
            stmt.setString(2, DNI);

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void cambiarRolEmpleado(String rol, String DNI){
        DataBase db = new DataBase();
        String query = "UPDATE trabajadores SET rol = ? WHERE DNI = ?";
        try{
            Connection conexion = db.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);

            stmt.setString(1, rol);
            stmt.setString(2, DNI);

            stmt.executeUpdate();


        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
*/
}
