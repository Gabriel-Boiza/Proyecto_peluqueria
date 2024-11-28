package com.example.peluqueria_3.Models;

import java.time.LocalDate;
import lombok.*;
@Setter
@Getter
@ToString

public class Citas {

    private String hora;
    private String textoPlano;
    private LocalDate fecha;

    public Citas(String hora, String textoPlano, LocalDate fecha){
        this.hora = hora;
        this.textoPlano = "";
        this.fecha = fecha;
    }
    public Citas(String textoPlano){

        this.textoPlano = "";

    }

}
