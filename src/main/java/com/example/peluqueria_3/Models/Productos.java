package com.example.peluqueria_3.Models;

import lombok.*;
@Setter
@Getter
@ToString
@AllArgsConstructor

public class Productos {
    private int idProducto;
    private String nombre;
    private String marca;
    private String descripcion;
    private float precio;
    private int stock;
    private String codigoBarras;

}
