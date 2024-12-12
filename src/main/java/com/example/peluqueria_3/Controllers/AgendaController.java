package com.example.peluqueria_3.Controllers;

import com.example.peluqueria_3.Models.Agenda;
import com.example.peluqueria_3.Models.Empleados;
import com.example.peluqueria_3.Models.ModeloAgenda;
import com.example.peluqueria_3.Models.ModeloEmpleados;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextArea;
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
            "08:00", "08:30", "09:00", "09:30", "10:00", "10:30",
            "11:00", "11:30", "12:00", "12:30", "13:00", "13:30",
            "14:00", "14:30", "15:00", "15:30", "16:00", "16:30",
            "17:00", "17:30", "18:00", "18:30", "19:00", "19:30",
            "20:00"
    };
    Map<String,TextArea> mapInputs = new HashMap<>();
    Map<String, String> mapComparacion = new HashMap<>();

    ModeloEmpleados empleados = new ModeloEmpleados();
    ArrayList<Empleados> arrayEmpleados = empleados.mostrarEmpleados();


    ModeloAgenda modeloAgenda = new ModeloAgenda();


    public void initialize() {
        if(date != null) {
            date.setValue(LocalDate.now());
            columnaHoras();
            columnaEmpleados();

            date.valueProperty().addListener((observable, oldValue, newValue) -> {  //genera los campos cada vez que cambia la fecha
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
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setContentText("");
            alerta.showAndWait();
        });
    }


    public void insercion(String clave, TextArea textField){
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
            label.setMinWidth(30);
            vbox.getChildren().add(label);
        }
        box.getChildren().add(vbox);
    }

    public void columnaEmpleados(){
        String fecha = date.getValue().toString();
        ArrayList<String> existeCita = modeloAgenda.arrayCitas(fecha);

        HashMap<String, Agenda> arrayAgenda = modeloAgenda.obtenerCitas();
        // Iterar los empleados para crear las columnas
        for(Empleados empleado: arrayEmpleados){
            VBox vboxEmpleados = new VBox();

            vboxEmpleados.setId(empleado.getId_empleado());
            Label label2 = new Label(empleado.getUsuario());
            label2.setPrefHeight(200);
            vboxEmpleados.getChildren().add(label2);

            for(int i = 0; i<horas.length; i++){
                TextArea textoplano = new TextArea();

                double c = arrayEmpleados.toArray().length;
                textoplano.setMaxWidth((box.getMaxWidth())/c);

                // Añadir IDs a los TextFileds

                String trabajador = empleado.getId_empleado();
                String id = fecha + "_" + horas[i] + "_" + trabajador;
                textoplano.setId(id);

                if(existeCita.contains(id)){   //verifica si existe alguna cita en el input
                    textoplano.setText(arrayAgenda.get(id).getTextoPlano());
                }


                //comparar 2 maps por sus claves
                mapComparacion.put(textoplano.getId(), textoplano.getText());  //ambos son parecidos para compararlos al guardar
                Map<String, String> mapComparacion2 = mapComparacion;
                mapInputs.put(textoplano.getId(), textoplano);  //mapInputs guarda el textfield como tal, si su texto cambia, el del objeto guardado en el map tambien

                textoplano.focusedProperty().addListener((observable, old_value, new_value) ->{
                    if(!observable.getValue()){
                        String nuevoValor = textoplano.getText();
                        String antiguoValor = mapComparacion2.get(textoplano.getId());
                        System.out.println("----------------------------------");
                        System.out.println("El nuevo valor es: " + nuevoValor);
                        System.out.println("El valor inicial es: " + antiguoValor);

                        if (!nuevoValor.equals(antiguoValor) && antiguoValor.isEmpty()){
                            System.out.println("hago un insert");
                            insercion(textoplano.getId(), textoplano);
                            mapComparacion2.put(textoplano.getId(), nuevoValor);
                        } else if (!nuevoValor.equals(antiguoValor) && nuevoValor.isEmpty()) {
                            System.out.println("Hago un delete");
                            modeloAgenda.deleteCitas(textoplano.getId());
                            mapComparacion2.put(textoplano.getId(), nuevoValor);

                        } else if (!nuevoValor.equals(antiguoValor) && !nuevoValor.isEmpty()) {
                            System.out.println("Hago un update");
                            modeloAgenda.modificarCita(textoplano.getId(), nuevoValor);
                            mapComparacion2.put(textoplano.getId(), nuevoValor);
                        }
                        else {
                            System.out.println("No hago nada");
                        }
                        System.out.println("----------------------------------");
                    }
                });
                // Añadir los TextAreas a la columna de cada empleado
                vboxEmpleados.getChildren().add(textoplano);
            }
            box.getChildren().add(vboxEmpleados);
        }
    }


}

