package com.example.peluqueria_3.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Empleados extends DataBase {
    private String id_empleado;
    private String nombre;
    private String email;
    private String telefono;
    private String contrasenya;
    private Float comision_ventas;
    private Float comision_servicios;
    private Float limite_comision;
    private boolean es_administrador;



    public Empleados(String id_empleado, String nombre, String email, String telefono, String contrasenya, Float comision_ventas, Float comision_servicios, Float limite_comision, boolean es_administrador) {
        this.id_empleado = id_empleado;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.contrasenya = contrasenya;
        this.comision_ventas = comision_ventas;
        this.comision_servicios = comision_servicios;
        this.limite_comision = limite_comision;
        this.es_administrador = es_administrador;
    }

    public String getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(String id_empleado) {
        this.id_empleado = id_empleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public Float getComision_ventas() {
        return comision_ventas;
    }

    public void setComision_ventas(Float comision_ventas) {
        this.comision_ventas = comision_ventas;
    }

    public Float getComision_servicios() {
        return comision_servicios;
    }

    public void setComision_servicios(Float comision_servicios) {
        this.comision_servicios = comision_servicios;
    }

    public Float getLimite_comision() {
        return limite_comision;
    }

    public void setLimite_comision(Float limite_comision) {
        this.limite_comision = limite_comision;
    }

    public boolean isEs_administrador() {
        return es_administrador;
    }

    public void setEs_administrador(boolean es_administrador) {
        this.es_administrador = es_administrador;
    }


}
