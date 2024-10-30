package com.example.peluqueria_3.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor

public class Servicios {
    private Integer id_servicio;
    private String nombre;
    private String descripcion;
    private String fecha;
    private String hora;
    private Float precio;
}
