package com.example.peluqueria_3.Models;

public class Clientes {
    private int id_cliente;
    private String nombre;
    private String apellido;
    private String tel;
    private String correo;
    private String observaciones;
    private boolean ley_datos;

    public Clientes(int id_cliente, String nombre, String apellido, String tel, String correo, String observaciones, boolean ley_datos) {
        this.id_cliente = id_cliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tel = tel;
        this.correo = correo;
        this.observaciones = observaciones;
        this.ley_datos = ley_datos;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public boolean isLey_datos() {
        return ley_datos;
    }

    public void setLey_datos(boolean ley_datos) {
        this.ley_datos = ley_datos;
    }

    @Override
    public String toString() {
        return "Clientes{" +
                "id_cliente=" + id_cliente +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", tel='" + tel + '\'' +
                ", correo='" + correo + '\'' +
                ", observaciones='" + observaciones + '\'' +
                ", ley_datos=" + ley_datos +
                '}';
    }
}
