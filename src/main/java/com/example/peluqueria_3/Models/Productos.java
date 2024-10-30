package com.example.peluqueria_3.Models;

import lombok.*;
@Setter
@Getter
@ToString
@AllArgsConstructor

public class Productos {
    private Integer id_producto;
    private String nombre;
    private String marca;
    private String descripcion;
    private Float precio;
    private Integer stock;
    private String codigo_barras;

}
