package com.example.peluqueria_3.Models;

public class Empleados extends DataBase {
    private String id_empleado; //DNI
    private String usuario;
    private String nombre;
    private String apellido;
    private String correo;
    private String contrasenya;
    private String telefono;
    private String direccion;
    private Float comision_ventas;
    private Float comision_servicios;
    private Float limite_comision;
    private String rol;
    private String estado;

    public Empleados(String id_empleado, String usuario, String nombre, String apellido, String email, String contrasenya, String telefono, String direccion, Float comision_ventas, Float comision_servicios, Float limite_comision, String rol, String estado) {
        this.id_empleado = id_empleado;
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = email;
        this.contrasenya = contrasenya;
        this.telefono = telefono;
        this.direccion = direccion;
        this.comision_ventas = comision_ventas;
        this.comision_servicios = comision_servicios;
        this.limite_comision = limite_comision;
        this.rol = rol;
        this.estado = estado;
    }

    public String getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(String id_empleado) {
        this.id_empleado = id_empleado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Empleados{" +
                "id_empleado='" + id_empleado + '\'' +
                ", usuario='" + usuario + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", correo='" + correo + '\'' +
                ", contrasenya='" + contrasenya + '\'' +
                ", telefono='" + telefono + '\'' +
                ", direccion='" + direccion + '\'' +
                ", comision_ventas=" + comision_ventas +
                ", comision_servicios=" + comision_servicios +
                ", limite_comision=" + limite_comision +
                ", rol='" + rol + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
