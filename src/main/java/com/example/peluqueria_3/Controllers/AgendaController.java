package com.example.peluqueria_3.Controllers;

import com.example.peluqueria_3.Models.Agenda;
import com.example.peluqueria_3.Models.Empleados;
import com.example.peluqueria_3.Models.ModeloAgenda;
import com.example.peluqueria_3.Models.ModeloEmpleados;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import javax.swing.plaf.basic.BasicButtonUI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AgendaController{
    @FXML  DatePicker date;
    @FXML HBox box;
    @FXML ImageView left;
    @FXML ImageView right;

    @FXML Button btnAdmin;
    @FXML Button btnClientes;
    @FXML Button btnLoginTrabajador;

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

            date.valueProperty().addListener((observable, oldValue, newValue) -> {  //genera los campos cada ves que cambia la fecha
                if(newValue != null){
                    mapInputs.clear();
                    String idDate = date.getValue().toString();
                    box.getChildren().clear();
                    columnaHoras();
                    columnaEmpleados();
                }
            });

            left.setOnMouseClicked(event ->{
                LocalDate diaSeleccionado = date.getValue();
                date.setValue(diaSeleccionado.minusDays(1));
            });

            right.setOnMouseClicked(event ->{
                LocalDate diaSeleccionado = date.getValue();
                date.setValue(diaSeleccionado.plusDays(1));
            });

            btnClientes.setOnMouseClicked(event ->{
                LoadStage load = new LoadStage("/com/example/peluqueria_3/Vistas/clientes.fxml", "Clientes");
            });

            btnLoginTrabajador.setOnAction( event ->{
                LoadStage load = new LoadStage("/com/example/peluqueria_3/Vistas/loginTrabajador.fxml", "Login Trabajadores");
            });

            btnAdmin.setOnAction(event->{
                LoadStage load = new LoadStage("/com/example/peluqueria_3/Vistas/loginAdmin.fxml", "Login Administración");
            });
        }

    }

    public void insercion(String clave, TextArea textField){
        String componentes[] = clave.split("_");

        String fecha = componentes[0];
        String hora = componentes[1];
        String id_empleado = componentes[2];
        String descripcion = textField.getText();

        modeloAgenda.insertarCita(clave, id_empleado, fecha, hora, descripcion);
    }

    public void columnaHoras() {
        VBox vbox = new VBox();
        vbox.setId("vHoras");
        vbox.setMinWidth(50);
        vbox.setAlignment(Pos.CENTER);

        Label labelHora1 = new Label("");
        labelHora1.setMinHeight(50);
        vbox.getChildren().add(labelHora1);

        for (String hora : horas) {
            Label label = new Label(hora);
            label.setPrefHeight(200);
            label.setMinWidth(30);
            label.getStyleClass().add("labelHoras");
            label.setMinHeight(100);
            Font fontHora = Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 15);
            label.setFont(fontHora);
            label.setTextAlignment(TextAlignment.CENTER);
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
            vboxEmpleados.setAlignment(Pos.CENTER);
            vboxEmpleados.setId(empleado.getId_empleado());


            Label label2 = new Label(empleado.getUsuario());
            label2.setText(empleado.getUsuario());
            label2.setMinHeight(50);
            Font font = Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 18);
            label2.setFont(font);

          /*  VBox vBoxImgLabel = new VBox(5);
            vBoxImgLabel.getChildren().addAll(imageView, label2);*/


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
                    textoplano.getStyleClass().add("rojo");
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
                            textoplano.getStyleClass().add("rojo");
                            mapComparacion2.put(textoplano.getId(), nuevoValor);
                        } else if (!nuevoValor.equals(antiguoValor) && nuevoValor.isEmpty()) {
                            System.out.println("Hago un delete");
                            modeloAgenda.deleteCitas(textoplano.getId());
                            textoplano.getStyleClass().remove("rojo");
                            mapComparacion2.put(textoplano.getId(), nuevoValor);

                        } else if (!nuevoValor.equals(antiguoValor) && !nuevoValor.isEmpty()) {
                            System.out.println("Hago un update");
                            modeloAgenda.modificarCita(textoplano.getId(), nuevoValor);
                            textoplano.getStyleClass().add("rojo");
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

