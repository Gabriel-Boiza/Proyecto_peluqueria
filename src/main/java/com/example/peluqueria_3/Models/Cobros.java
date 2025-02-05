package com.example.peluqueria_3.Models;

import lombok.*;

import java.sql.Date;

@Setter
@Getter
@ToString


public class Cobros {
    private String nombre_cliente;
    private String nombre_servicio;
    private String  nombre_producto;
    private String nombre_empleado;

    private int id_cliente;
    private int id_servicio;
    private int id_producto;
    private String id_empleado;

    private Date fecha_cobro;
    private float bizum;
    private float tarjeta;
    private float efectivo;

    public String observaciones;

    //Constructor para mostrar datos

    public Cobros(String nombre_cliente, String nombre_servicio, String nombre_producto, String nombre_empleado, Date fecha_cobro, float bizum, float tarjeta, float efectivo, String observaciones) {
        this.nombre_cliente = nombre_cliente;
        this.nombre_servicio = nombre_servicio;
        this.nombre_producto = nombre_producto;
        this.nombre_empleado = nombre_empleado;
        this.fecha_cobro = fecha_cobro;
        this.bizum = bizum;
        this.tarjeta = tarjeta;
        this.efectivo = efectivo;
        this.observaciones = observaciones;
    }

    //Constructor para insertar datos

    public Cobros(int id_cliente, int id_servicio, int id_producto, String id_empleado, Date fecha_cobro, float bizum, float tarjeta, float efectivo) {
        this.id_cliente = id_cliente;
        this.id_servicio = id_servicio;
        this.id_producto = id_producto;
        this.id_empleado = id_empleado;
        this.fecha_cobro = fecha_cobro;
        this.bizum = bizum;
        this.tarjeta = tarjeta;
        this.efectivo = efectivo;
    }
}
