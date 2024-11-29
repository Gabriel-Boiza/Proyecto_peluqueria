package com.example.peluqueria_3.Controllers;

import com.example.peluqueria_3.Models.Agenda;
import com.example.peluqueria_3.Models.Empleados;
import com.example.peluqueria_3.Models.ModeloAgenda;
import com.example.peluqueria_3.Models.ModeloEmpleados;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AgendaController{
    @FXML  DatePicker date;
    @FXML HBox box;
    @FXML Button guardarAgenda;

    final String[] horas = {
            "08:00", "09:30", "10:00", "10:30", "11:00", "11:30",
            "12:00", "12:30", "13:00", "13:30", "14:00", "14:30",
            "15:00", "15:30", "16:00", "16:30", "17:00", "17:30",
            "18:00", "18:30", "19:00", "20:30"
    };
    Map<String,TextField> mapInputs = new HashMap<>();
    Map<String, String> mapComparacion = new HashMap<>();

    ModeloEmpleados empleados = new ModeloEmpleados();
    ArrayList<Empleados> arrayEmpleados = empleados.mostrarEmpleados();

    ModeloAgenda modeloAgenda = new ModeloAgenda();


    public void initialize() {
        if(date != null) {
            date.setValue(LocalDate.now());
            columnaHoras();
            columnaEmpleados();

            date.valueProperty().addListener((observable, oldValue, newValue) -> {  //genera los campos cada ves que cambia la fecha
                if(newValue != null){
                    mapInputs.clear();
                    String idDate = date.getValue().toString();
                    box.getChildren().clear();
                    columnaHoras();
                    columnaEmpleados();
                }
            });
        }
        guardarAgenda.setOnAction(e->{

            mapInputs.forEach((clave, textField) -> {
                if(!mapComparacion.get(clave).equals(textField.getText())){ //Comprueban los valores que hayan cambiado
                    if (!modeloAgenda.comprobarCita(clave)){
                        insercion(clave, textField);
                    }
                    else if(modeloAgenda.comprobarCita(clave) && !textField.getText().isEmpty()){
                        modeloAgenda.modificarCita(clave, textField.getText());
                    }
                    else{
                        modeloAgenda.deleteCitas(clave);
                    }
                }
            });
        });
    }


    public void insercion(String clave, TextField textField){
        String componentes[] = clave.split("_");

        String fecha = componentes[0];
        String hora = componentes[1];
        String id_empleado = componentes[2];
        String descripcion = textField.getText();

        modeloAgenda.insertarCita(clave, id_empleado, fecha, hora, descripcion);
    }

    public void columnaHoras(){
        VBox vbox = new VBox();
        vbox.setId("vHoras");

        Label labelHora1 = new Label("");
        vbox.getChildren().add(labelHora1);

        for(String hora: horas){
            Label label = new Label(hora);
            label.setPrefHeight(200);
            vbox.getChildren().add(label);
        }
        box.getChildren().add(vbox);
    }

    public void columnaEmpleados(){

        HashMap<String, Agenda> arrayAgenda = modeloAgenda.obtenerCitas();
        // Iterar los empleados para crear las columnas
        for(Empleados empleado: arrayEmpleados){
            VBox vboxEmpleados = new VBox();

            vboxEmpleados.setId(empleado.getId_empleado());
            Label label2 = new Label(empleado.getUsuario());
            label2.setPrefHeight(200);
            vboxEmpleados.getChildren().add(label2);

            for(int i = 0; i<horas.length; i++){
                TextField textoplano = new TextField();
                textoplano.setPrefHeight(200); // No funciona

                // Añadir IDs a los TextFileds
                String fecha = date.getValue().toString();
                String trabajador = empleado.getId_empleado();
                String id = fecha + "_" + horas[i] + "_" + trabajador;
                textoplano.setId(id);

                if(modeloAgenda.comprobarCita(id)){
                    textoplano.setText(arrayAgenda.get(id).getTextoPlano());
                }

                mapComparacion.put(textoplano.getId(), textoplano.getText());  //ambos son parecidos para compararlos al guardar
                mapInputs.put(textoplano.getId(), textoplano);

                // Añadir los TextFields a la columna de cada empleado
                vboxEmpleados.getChildren().add(textoplano);
            }
            box.getChildren().add(vboxEmpleados);
        }
    }


}

