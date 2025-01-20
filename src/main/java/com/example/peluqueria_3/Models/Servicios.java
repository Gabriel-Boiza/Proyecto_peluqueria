package com.example.peluqueria_3.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter

@AllArgsConstructor

public class Servicios {
    private Integer id_servicio;
    private String nombre;
    private String descripcion;
    private Float precio;

    public String toString() {
        return nombre; // Solo el nombre será mostrado en el ChoiceBox
    }
}
