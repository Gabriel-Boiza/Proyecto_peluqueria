package com.example.peluqueria_3.Controllers;

import com.example.peluqueria_3.Models.Empleados;

public class DatosGlobales {
    private static Empleados empleadoActual;

    // Getter para obtener el empleado actual
    public static Empleados getEmpleadoActual() {
        return empleadoActual;
    }

    // Setter para asignar el empleado actual
    public static void setEmpleadoActual(Empleados empleado) {
        empleadoActual = empleado;
    }
}


