package com.example.peluqueria_3.Models;

import lombok.*;

import java.sql.Date;

@Setter
@Getter
@ToString
@AllArgsConstructor

public class Cobros {
    private String nombre_cliente;
    private String nombre_servicio;
    private String  nombre_producto;
    private String nombre_empleado;

    private Date fecha_cobro;
    private float bizum;
    private float tarjeta;
    private float efectivo;
}
