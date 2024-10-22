package com.example.peluqueria_3.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class mainController {
    private String id_empleado;
    private String usuario;
    private String nombre;
    private String apellido;
    private String email;
    private String contrasenya;
    private String telefono;
    private String direccion;
    private String rol;

    private Float comision_ventas;
    private Float comision_servicios;
    private Float limite_comision;


    public mainController(String id_empleado, String usuario, String nombre, String apellido, String email, String contrasenya, String telefono, String direccion, String rol, Float comision_ventas, Float comision_servicios, Float limite_comision) {
        this.id_empleado = id_empleado;
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contrasenya = contrasenya;
        this.telefono = telefono;
        this.direccion = direccion;
        this.rol = rol;
        this.comision_ventas = comision_ventas;
        this.comision_servicios = comision_servicios;
        this.limite_comision = limite_comision;
    }
}

