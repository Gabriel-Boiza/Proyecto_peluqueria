package com.example.peluqueria_3.Models;

import lombok.*;
@Setter
@Getter

@AllArgsConstructor

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
    private String img;

    @Override
    public String toString() {
        return nombre; // Solo el nombre será mostrado en el ChoiceBox
    }

}
