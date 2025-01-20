package com.example.peluqueria_3.Models;

import lombok.*;

import java.sql.Date;

@Setter
@Getter
@ToString
@AllArgsConstructor

public class Cobros {
    private int id_cliente;
    private int id_servicio;
    private int  id_producto;
    private String id_empleado;

    private Date fecha_cobro;
    private float bizum;
    private float tarjeta;
    private float efectivo;
}
