package com.example.peluqueria_3.Controllers;

import com.example.peluqueria_3.Models.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.util.StringConverter;

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
    ModeloCobros modeloCobros = new ModeloCobros();

    ArrayList<ComboBox<Servicios>> arrayServicios = new ArrayList<>();
    ArrayList<ComboBox<Productos>> arrayProductos = new ArrayList<>();

    ArrayList<ComboBox<Empleados>> arrayEmpleadosServicios = new ArrayList<>();
    ArrayList<ComboBox<Empleados>> arrayEmpleadosProductos = new ArrayList<>();

    ArrayList<TextField> arrayBizumServicios = new ArrayList<>();
    ArrayList<TextField> arrayEfectivoServicios = new ArrayList<>();
    ArrayList<TextField> arrayTarjetaServicios = new ArrayList<>();

    ArrayList<TextField> arrayBizumProductos = new ArrayList<>();
    ArrayList<TextField> arrayEfectivoProductos = new ArrayList<>();
    ArrayList<TextField> arrayTarjetaProductos = new ArrayList<>();

    private <T> void setupSearchableComboBox(ComboBox<T> comboBox, ObservableList<T> items, StringConverter<T> converter) {
        comboBox.setEditable(true);
        comboBox.setItems(items);
        comboBox.setConverter(converter);

        // Create a filtered list that will contain all items matching the search text
        FilteredList<T> filteredItems = new FilteredList<>(items, p -> true);

        // Variable para mantener el último valor válido
        final T[] lastValidValue = (T[]) new Object[1];

        // Add a listener to the combobox editor to filter items based on input
        comboBox.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            T selected = comboBox.getSelectionModel().getSelectedItem();

            // Guardar el valor seleccionado si es válido
            if (selected != null) {
                lastValidValue[0] = selected;
            }

            if (selected == null || !converter.toString(selected).equals(newValue)) {
                filteredItems.setPredicate(item -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    String itemString = converter.toString(item).toLowerCase();
                    return itemString.contains(lowerCaseFilter);
                });
                if (!comboBox.isShowing()) {
                    comboBox.show();
                }
            }
        });

        // Manejar la pérdida de foco
        comboBox.getEditor().focusedProperty().addListener((obs, wasFocused, isFocused) -> {
            if (!isFocused) {  // Cuando pierde el foco
                T selectedItem = comboBox.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    // Si hay un item seleccionado, actualizar el texto
                    comboBox.getEditor().setText(converter.toString(selectedItem));
                    lastValidValue[0] = selectedItem;
                } else if (lastValidValue[0] != null) {
                    // Si no hay selección pero tenemos un valor válido anterior, restaurarlo
                    comboBox.setValue(lastValidValue[0]);
                    comboBox.getEditor().setText(converter.toString(lastValidValue[0]));
                }
            }
        });

        // Manejar la selección de items
        comboBox.setOnAction(event -> {
            T selectedItem = comboBox.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                lastValidValue[0] = selectedItem;
                comboBox.getEditor().setText(converter.toString(selectedItem));
            }
        });

        comboBox.setItems(filteredItems);
    }

    @FXML
    public void initialize() {


        ArrayList<Productos> productos = modeloProductos.mostrarProductos();
        ArrayList<Servicios> servicios = modeloServicios.mostrarServicios();
        ArrayList<Empleados> empleadosServicios = modeloEmpleados.mostrarEmpleados();
        ArrayList<Empleados> empleadosProductos = modeloEmpleados.mostrarEmpleados();

        // Create ObservableLists
        ObservableList<Productos> obsProductos = FXCollections.observableArrayList(productos);
        ObservableList<Servicios> obsServicios = FXCollections.observableArrayList(servicios);
        ObservableList<Empleados> obsEmpleadosServicios = FXCollections.observableArrayList(empleadosServicios);
        ObservableList<Empleados> obsEmpleadosProductos = FXCollections.observableArrayList(empleadosProductos);

        // Create converters for each type
        StringConverter<Productos> productosConverter = new StringConverter<>() {
            @Override
            public String toString(Productos producto) {
                return producto != null ? producto.getNombre() : "";
            }

            @Override
            public Productos fromString(String string) {
                return null; // No necesitamos convertir de String a Producto
            }
        };

        StringConverter<Servicios> serviciosConverter = new StringConverter<>() {
            @Override
            public String toString(Servicios servicio) {
                return servicio != null ? servicio.getNombre() : "";
            }

            @Override
            public Servicios fromString(String string) {
                return null;
            }
        };

        StringConverter<Empleados> empleadosConverter = new StringConverter<>() {
            @Override
            public String toString(Empleados empleado) {
                return empleado != null ? empleado.getNombre() : "";
            }

            @Override
            public Empleados fromString(String string) {
                return null;
            }
        };

        volver.setOnAction(actionEvent -> {
            LoadStage load = new LoadStage("/com/example/peluqueria_3/Vistas/fichaCliente.fxml", "Ficha Cliente");
        });

        agregar_servicio.setOnAction(actionEvent -> {
            HBox fila_servicio = new HBox();

            ComboBox<Servicios> casilla_servicios = new ComboBox<>();
            ComboBox<Empleados> casilla_empleados = new ComboBox<>();

            // Setup searchable ComboBoxes
            setupSearchableComboBox(casilla_servicios, obsServicios, serviciosConverter);
            setupSearchableComboBox(casilla_empleados, obsEmpleadosServicios, empleadosConverter);

            TextField textoBizum = new TextField();
            TextField textoTarjeta = new TextField();
            TextField textoEfectivo = new TextField();

            textoBizum.setText("0");
            textoTarjeta.setText("0");
            textoEfectivo.setText("0");

            if (!servicios.isEmpty()) {
                casilla_servicios.setValue(servicios.get(0));
            }

            if (!empleadosServicios.isEmpty()) {
                casilla_empleados.setValue(empleadosServicios.get(0));
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

            ComboBox<Productos> casilla_productos = new ComboBox<>();
            ComboBox<Empleados> casilla_empleados = new ComboBox<>();

            // Setup searchable ComboBoxes
            setupSearchableComboBox(casilla_productos, obsProductos, productosConverter);
            setupSearchableComboBox(casilla_empleados, obsEmpleadosProductos, empleadosConverter);

            TextField textoBizum = new TextField();
            TextField textoTarjeta = new TextField();
            TextField textoEfectivo = new TextField();

            textoBizum.setText("0");
            textoTarjeta.setText("0");
            textoEfectivo.setText("0");

            if (!productos.isEmpty()) {
                casilla_productos.setValue(productos.getFirst());
            }

            if (!empleadosProductos.isEmpty()) {
                casilla_empleados.setValue(empleadosProductos.getFirst());
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
            System.out.println("SERVICIOS ______________");
            int cont = 0;
            for(ComboBox<Servicios> servicio : arrayServicios){
                String id_empleado = empleadosServicios.get(cont).getId_empleado();
                System.out.println(id_empleado + " : " + servicio.getValue().getId_servicio() + " precio: " + arrayTarjetaServicios.get(cont).getText());
                float bizum = Float.parseFloat(arrayBizumServicios.get(cont).getText());
                float efectivo = Float.parseFloat(arrayEfectivoServicios.get(cont).getText());
                float tarjeta = Float.parseFloat(arrayTarjetaServicios.get(cont).getText());

                modeloCobros.insertarCobro(ClientesController.clientesSeleccionado.getId_cliente(), servicio.getValue().getId_servicio(), id_empleado, 0, new Date(System.currentTimeMillis()), bizum, efectivo, tarjeta);
                cont = cont +1;
            }
            System.out.println("PRODUCTOS ______________");
            cont = 0;
            for(ComboBox<Productos> producto : arrayProductos){
                String id_empleado = empleadosProductos.get(cont).getId_empleado();
                System.out.println(id_empleado + " : " + producto.getValue().getId_producto() + " precio: " + arrayTarjetaProductos.get(cont).getText());
                float bizum = Float.parseFloat(arrayBizumProductos.get(cont).getText());
                float efectivo = Float.parseFloat(arrayEfectivoProductos.get(cont).getText());
                float tarjeta = Float.parseFloat(arrayTarjetaProductos.get(cont).getText());
                modeloCobros.insertarCobro(ClientesController.clientesSeleccionado.getId_cliente(), 0, id_empleado, producto.getValue().getId_producto(), new Date(System.currentTimeMillis()), bizum, efectivo, tarjeta);
                cont = cont +1;
            }
        });
    }

}