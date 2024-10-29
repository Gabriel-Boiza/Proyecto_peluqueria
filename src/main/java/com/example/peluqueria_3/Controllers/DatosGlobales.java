package com.example.peluqueria_3.Controllers;

import com.example.peluqueria_3.Models.Empleados;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

public class DatosGlobales {
    // Setter para asignar el empleado actual
    // Getter para obtener el empleado actual
    @Setter
    @Getter
    private static Empleados empleadoActual;
    @Getter
    private static Stage mystage;

    public static void setMystage(Stage mystage) {
        DatosGlobales.mystage = mystage;
    }
}


