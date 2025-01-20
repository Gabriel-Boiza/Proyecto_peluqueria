package com.example.peluqueria_3.Controllers;

import com.example.peluqueria_3.Models.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.Date;
import java.time.LocalDate;
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

    ArrayList<ChoiceBox<Servicios>> arrayServicios = new ArrayList<>();
    ArrayList<ChoiceBox<Productos>> arrayProductos = new ArrayList<>();

    ArrayList<ChoiceBox<Empleados>> arrayEmpleadosServicios = new ArrayList<>();
    ArrayList<ChoiceBox<Empleados>> arrayEmpleadosProductos = new ArrayList<>();

    ArrayList<TextField> arrayBizumServicios = new ArrayList<>();
    ArrayList<TextField> arrayEfectivoServicios = new ArrayList<>();
    ArrayList<TextField> arrayTarjetaServicios = new ArrayList<>();

    ArrayList<TextField> arrayBizumProductos = new ArrayList<>();
    ArrayList<TextField> arrayEfectivoProductos = new ArrayList<>();
    ArrayList<TextField> arrayTarjetaProductos = new ArrayList<>();




    @FXML
    public void initialize(){
        ArrayList<Productos> productos = modeloProductos.mostrarProductos();
        ArrayList<Servicios> servicios = modeloServicios.mostrarServicios();

        ArrayList<Empleados> empleadosServicios = modeloEmpleados.mostrarEmpleados();
        ArrayList<Empleados> empleadosProductos = modeloEmpleados.mostrarEmpleados();


        agregar_servicio.setOnAction(actionEvent -> {
            HBox fila_servicio = new HBox();

            ChoiceBox<Servicios> casilla_servicios = new ChoiceBox<>();
            ChoiceBox<Empleados> casilla_empleados = new ChoiceBox<>();

            TextField textoBizum = new TextField();
            TextField textoTarjeta = new TextField();
            TextField textoEfectivo = new TextField();

            textoBizum.setText("0");
            textoTarjeta.setText("0");
            textoEfectivo.setText("0");

            casilla_servicios.getItems().addAll(servicios);
            casilla_empleados.getItems().addAll(empleadosServicios);

            if (!servicios.isEmpty()) {
                casilla_servicios.setValue(servicios.get(0)); // Primer servicio como valor por defecto
            }

            if (!empleadosServicios.isEmpty()) {
                casilla_empleados.setValue(empleadosServicios.get(0)); // Primer empleado como valor por defecto
            }

            arrayServicios.add(casilla_servicios);
            arrayEmpleadosServicios.add(casilla_empleados);

            arrayBizumServicios.add(textoBizum);
            arrayEfectivoServicios.add(textoEfectivo);
            arrayTarjetaServicios.add(textoTarjeta);


            fila_servicio.getChildren().add(new Label("Servicio para: " + ClientesController.clientesSeleccionado.getNombre()));
            fila_servicio.getChildren().add(casilla_servicios);
            fila_servicio.getChildren().add(casilla_empleados);
            fila_servicio.getChildren().addAll(textoEfectivo, textoBizum, textoTarjeta);

            panel.getChildren().add(fila_servicio);


        });

        agregar_producto.setOnAction(actionEvent -> {
            HBox fila_producto = new HBox();

            ChoiceBox<Productos> casilla_productos = new ChoiceBox<>();
            ChoiceBox<Empleados> casilla_empleados = new ChoiceBox<>();

            TextField textoBizum = new TextField();
            TextField textoTarjeta = new TextField();
            TextField textoEfectivo = new TextField();

            textoBizum.setText("0");
            textoTarjeta.setText("0");
            textoEfectivo.setText("0");

            casilla_productos.getItems().addAll(productos);
            casilla_empleados.getItems().addAll(empleadosProductos);

            if (!productos.isEmpty()) {
                casilla_productos.setValue(productos.getFirst()); // Primer producto como valor por defecto
            }

            if (!empleadosProductos.isEmpty()) {
                casilla_empleados.setValue(empleadosProductos.getFirst()); // Primer empleado como valor por defecto
            }

            arrayProductos.add(casilla_productos);
            arrayEmpleadosProductos.add(casilla_empleados);

            arrayBizumProductos.add(textoBizum);
            arrayEfectivoProductos.add(textoEfectivo);
            arrayTarjetaProductos.add(textoTarjeta);

            fila_producto.getChildren().add(new Label("Producto para: " + ClientesController.clientesSeleccionado.getNombre()));
            fila_producto.getChildren().add(casilla_productos);
            fila_producto.getChildren().add(casilla_empleados);
            fila_producto.getChildren().addAll(textoEfectivo, textoBizum, textoTarjeta);


            panel.getChildren().add(fila_producto);
        });

        pagar.setOnAction(actionEvent -> {
            int cont = 0;
            for(ChoiceBox<Servicios> servicio : arrayServicios){
                String id_empleado = empleadosServicios.get(cont).getId_empleado();
                System.out.println(id_empleado + " : " + servicio.getValue().getId_servicio() + " precio: " + arrayTarjetaServicios.get(cont).getText());
                cont = cont +1;
            }
            System.out.println("PRODUCTOS ______________");
            cont = 0;
            for(ChoiceBox<Productos> producto : arrayProductos){
                String id_empleado = empleadosProductos.get(cont).getId_empleado();
                System.out.println(id_empleado + " : " + producto.getValue().getId_producto() + " precio: " + arrayTarjetaProductos.get(cont).getText());
                cont = cont +1;
            }
        });
    }


}
