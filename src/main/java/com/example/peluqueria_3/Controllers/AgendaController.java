package com.example.peluqueria_3.Controllers;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.view.CalendarView;

import com.calendarfx.view.page.DayPage;
import com.example.peluqueria_3.Models.Citas;
import com.example.peluqueria_3.Models.Empleados;
import com.example.peluqueria_3.Models.ModeloEmpleados;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AgendaController{
    @FXML  DatePicker date;
    @FXML  Pane panelHoras;
    @FXML  TableView tabla;
    @FXML HBox box;
    @FXML Button guardarAgenda;

    final String[] horas = {
            "09:00", "09:30", "10:00", "10:30", "11:00", "11:30",
            "12:00", "12:30", "13:00", "13:30", "14:00", "14:30",
            "15:00", "15:30", "16:00", "16:30", "17:00", "17:30",
            "18:00", "18:30", "19:00", "19:30"
    };
    Map<String,TextField> mapInputs = new HashMap<>();

    ModeloEmpleados empleados = new ModeloEmpleados();
    ArrayList<Empleados> arrayEmpleados = empleados.mostrarEmpleados();


    public void initialize() {
        if(date != null) {
            date.setValue(LocalDate.now());
            columnaHoras();
            columnaEmpleados();

            date.valueProperty().addListener((observable, oldValue, newValue) -> {
                if(newValue != null){
                    mapInputs.clear();
                    String idDate = date.getValue().toString();
                    box.getChildren().clear();
                    columnaHoras();
                    columnaEmpleados();
                    System.out.println(mapInputs);
                }
            });
        }
        guardarAgenda.setOnAction(e->{
            mapInputs.forEach((clave, textField) -> {
                if(!textField.getText().isEmpty()){
                    System.out.println("Clave: " + clave + ", Valor: " + textField.getText());
                }
            });
        });
    }

    public void columnaHoras(){
        VBox vbox = new VBox();
        vbox.setId("vHoras");

        Label labelHora1 = new Label("");
        vbox.getChildren().add(labelHora1);

        for(String hora: horas){
            Label label = new Label(hora);
            label.setPrefHeight(100);
            vbox.getChildren().add(label);
        }
        box.getChildren().add(vbox);
    }

    public void columnaEmpleados(){
        // Iterar los empleados para crear las columnas
        for(Empleados empleado: arrayEmpleados){
            VBox vboxEmpleados = new VBox();

            vboxEmpleados.setId(empleado.getId_empleado());
            Label label2 = new Label(empleado.getUsuario());
            vboxEmpleados.getChildren().add(label2);

            for(int i = 0; i<horas.length; i++){
                TextField textoplano = new TextField();

                // Añadir IDs a los TextFileds
                String fecha = date.getValue().toString();
                String trabajador = empleado.getId_empleado();
                textoplano.setId(fecha + "_" + horas[i] + "_" + trabajador);

                mapInputs.put(textoplano.getId(), textoplano);

                // Añadir los TextFields a la columna de cada empleado
                vboxEmpleados.getChildren().add(textoplano);
            }
            box.getChildren().add(vboxEmpleados);
        }
    }


}

