package com.example.peluqueria_3.Controllers;

import com.example.peluqueria_3.Models.Empleados;
import javafx.stage.Stage;

public class DatosGlobales {
    private static Empleados empleadoActual;
    private static Stage mystage;

    // Getter para obtener el empleado actual
    public static Empleados getEmpleadoActual() {
        return empleadoActual;
    }

    // Setter para asignar el empleado actual
    public static void setEmpleadoActual(Empleados empleado) {
        empleadoActual = empleado;
    }

    public static Stage getMystage() {
        return mystage;
    }

    public static void setMystage(Stage mystage) {
        DatosGlobales.mystage = mystage;
    }
}


