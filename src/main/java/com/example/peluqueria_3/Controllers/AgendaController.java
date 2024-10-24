package com.example.peluqueria_3.Controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AgendaController {
    @FXML private Button boton;


    @FXML
    public void initialize(){
        boton.setText(DatosGlobales.getEmpleadoActual().getNombre());

        boton.setOnAction(event -> {
            LoadStage load = new LoadStage("/com/example/peluqueria_3/Vistas/login.fxml", "login");
        });
    }





}
