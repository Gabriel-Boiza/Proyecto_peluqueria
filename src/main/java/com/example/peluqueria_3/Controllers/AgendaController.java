package com.example.peluqueria_3.Controllers;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.view.CalendarView;

import com.calendarfx.view.page.DayPage;
import com.example.peluqueria_3.Models.Empleados;
import com.example.peluqueria_3.Models.ModeloEmpleados;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class AgendaController{
    @FXML  DatePicker date;
    @FXML  Pane panelHoras;
    @FXML  TableView tabla;

    String[] horas = {
            "09:00", "09:30", "10:00", "10:30", "11:00", "11:30",
            "12:00", "12:30", "13:00", "13:30", "14:00", "14:30",
            "15:00", "15:30", "16:00", "16:30", "17:00", "17:30",
            "18:00", "18:30", "19:00", "19:30"
    };

    ModeloEmpleados empleados = new ModeloEmpleados();
    public void initialize() {
        if(date != null) {
            date.setValue(LocalDate.now());
            ArrayList<Empleados> arrayEmpleados = empleados.mostrarEmpleados();

            TableColumn<LocalTime, String> columnaHoras = new TableColumn<>();
            columnaHoras.setText("Horas");

            LocalTime inicio = LocalTime.of(8, 30);
            LocalTime fin = LocalTime.of(19, 30);

            while(fin.isAfter(inicio)){
                inicio = inicio.plusMinutes(30);
                //setOnEditCommit
                //event.rowvalue
            }


            for(Empleados empleado: arrayEmpleados){
                TableColumn<Empleados, String> columna = new TableColumn<>();
                columna.setText(empleado.getUsuario());

                tabla.getColumns().add(columna);
            }
        }
    }
}

