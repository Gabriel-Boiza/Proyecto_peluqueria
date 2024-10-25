package com.example.peluqueria_3.Controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AgendaController {

    //Aqui irá la agenda, demomento solo se aplicarán los botones para acceder a los CRUDS
    @FXML private Button botonEmpleados;
    @FXML private Button cerrarSesion;


    @FXML
    public void initialize(){


        if (DatosGlobales.getEmpleadoActual().getRol().equals("administrador")){
            botonEmpleados.setVisible(true);
        }
        else{
            botonEmpleados.setVisible(false);
        }
        // ACCIONES DE LOS BOTONES
        botonEmpleados.setOnAction(event -> {
            LoadStage load = new LoadStage("/com/example/peluqueria_3/Vistas/empleados.fxml", "Empleados");
        });
        cerrarSesion.setOnAction(event-> {
            LoadStage load = new LoadStage("/com/example/peluqueria_3/Vistas/login.fxml", "login");
        });
    }





}
