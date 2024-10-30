package com.example.peluqueria_3.Models;
import lombok.*;
@Setter
@Getter
@ToString
@Data
@AllArgsConstructor
public class Clientes {
    private int id_cliente;
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;
    private String observaciones;
    private boolean ley_datos;
}


