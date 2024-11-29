package com.example.peluqueria_3.Models;

import java.time.LocalDate;
import lombok.*;
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Agenda {

    private String id;
    private String fecha;
    private String hora;
    private String textoPlano;
    private String id_empleado;

}
//borrar registro cuando donde antes habia texto, ya no hay
//añadir texto cuando donde antes si habia texto, hay
//modificar texto cuando donde antes habia texto, ahora hay uno DIFERENTE