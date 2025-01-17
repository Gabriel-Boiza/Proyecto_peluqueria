package com.example.peluqueria_3.Controllers;

import com.example.peluqueria_3.Models.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;


public class CobrosController {
    @FXML Button volver;
    @FXML Button agregar_servicio;
    @FXML Button agregar_producto;
    @FXML Button pagar;

    @FXML Label bizum;
    @FXML Label tarjeta;
    @FXML Label efectivo;
    @FXML Label total;

    @FXML VBox panel;

    ModeloProductos modeloProductos = new ModeloProductos();
    ModeloServicios modeloServicios = new ModeloServicios();
    ModeloEmpleados modeloEmpleados = new ModeloEmpleados();




    @FXML
    public void initialize(){
        ArrayList<Productos> productos = modeloProductos.mostrarProductos();
        ArrayList<Servicios> servicios = modeloServicios.mostrarServicios();
        ArrayList<Empleados> empleados = modeloEmpleados.mostrarEmpleados();

        agregar_servicio.setOnAction(actionEvent -> {
            HBox fila_producto = new HBox();

            ChoiceBox<Servicios> casilla_servicios = new ChoiceBox<>();
            ChoiceBox<Empleados> casilla_empleados = new ChoiceBox<>();

            casilla_servicios.getItems().addAll(servicios);
            casilla_empleados.getItems().addAll(empleados);


            fila_producto.getChildren().add(new Label("Servicio para: " + ClientesController.clientesSeleccionado.getNombre()));
            fila_producto.getChildren().add(casilla_servicios);
            fila_producto.getChildren().add(casilla_empleados);



            panel.getChildren().add(fila_producto);
        });
        agregar_producto.setOnAction(actionEvent -> {
            HBox fila_producto = new HBox();

            ChoiceBox<Productos> casilla_productos = new ChoiceBox<>();
            ChoiceBox<Empleados> casilla_empleados = new ChoiceBox<>();

            casilla_productos.getItems().addAll(productos);
            casilla_empleados.getItems().addAll(empleados);

            fila_producto.getChildren().add(new Label("Producto para: " + ClientesController.clientesSeleccionado.getNombre()));
            fila_producto.getChildren().add(casilla_productos);
            fila_producto.getChildren().add(casilla_empleados);

            panel.getChildren().add(fila_producto);
        });
    }
}
