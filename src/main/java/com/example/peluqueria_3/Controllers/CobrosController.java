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
    @FXML Button limpiar;

    @FXML Label bizum;
    @FXML Label tarjeta;
    @FXML Label efectivo;
    @FXML Label total;

    @FXML VBox panel;

    ModeloProductos modeloProductos = new ModeloProductos();
    ModeloServicios modeloServicios = new ModeloServicios();
    ModeloEmpleados modeloEmpleados = new ModeloEmpleados();
    ModeloCobros modeloCobros = new ModeloCobros();

    int stock;

    @FXML TextArea observaciones;


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

        ArrayList<Spinner<Integer>> arrayCantidad = new ArrayList<>();

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

        limpiar.setOnAction(actionEvent -> {
            arrayServicios.clear();
            arrayProductos.clear();
            arrayEmpleadosServicios.clear();
            arrayEmpleadosProductos.clear();
            arrayBizumServicios.clear();
            arrayEfectivoServicios.clear();
            arrayTarjetaServicios.clear();
            arrayBizumProductos.clear();
            arrayEfectivoProductos.clear();
            arrayTarjetaProductos.clear();
            arrayCantidad.clear();

            // Clear VBox
            panel.getChildren().clear();
        });

        agregar_servicio.setOnAction(actionEvent -> {
            HBox fila_servicio = new HBox();
            fila_servicio.setSpacing(15);
            fila_servicio.getStyleClass().add("fila");

            Label labelServicio = new Label("Servicio para: " + ClientesController.clientesSeleccionado.getNombre());
            labelServicio.getStyleClass().add("labelText");

            ComboBox<Servicios> casilla_servicios = new ComboBox<>();
            casilla_servicios.getStyleClass().add("combox");
            ComboBox<Empleados> casilla_empleados = new ComboBox<>();
            casilla_empleados.getStyleClass().add("combox");

            setupSearchableComboBox(casilla_servicios, obsServicios, serviciosConverter);
            setupSearchableComboBox(casilla_empleados, obsEmpleadosServicios, empleadosConverter);

            TextField textoBizum = new TextField();
            textoBizum.getStyleClass().add("dinero");

            TextField textoTarjeta = new TextField();
            textoTarjeta.getStyleClass().add("dinero");

            TextField textoEfectivo = new TextField();
            textoEfectivo.getStyleClass().add("dinero");

            textoBizum.setText("0");
            textoTarjeta.setText("0");


            casilla_servicios.setOnAction(event -> {
                if (casilla_servicios.getValue() != null) {
                    textoEfectivo.setText(String.valueOf(modeloCobros.detectarPrecioServicio(casilla_servicios.getValue().getId_servicio())));
                    System.out.println(String.valueOf(modeloCobros.detectarPrecioServicio(casilla_servicios.getValue().getId_servicio())));
                }
            });

            if (!servicios.isEmpty()) {
                casilla_servicios.setValue(servicios.getFirst());
            }

            if (!empleadosServicios.isEmpty()) {
                casilla_empleados.setValue(empleadosServicios.getFirst());
            }

            arrayServicios.add(casilla_servicios);
            arrayEmpleadosServicios.add(casilla_empleados);

            arrayBizumServicios.add(textoBizum);
            arrayEfectivoServicios.add(textoEfectivo);
            arrayTarjetaServicios.add(textoTarjeta);

            textoEfectivo.setText(String.valueOf(modeloCobros.detectarPrecioServicio(arrayServicios.getFirst().getValue().getId_servicio())));

            fila_servicio.getChildren().add(labelServicio);
            fila_servicio.getChildren().add(casilla_servicios);
            fila_servicio.getChildren().add(casilla_empleados);
            fila_servicio.getChildren().addAll(textoEfectivo, textoBizum, textoTarjeta);

            panel.getChildren().add(fila_servicio);
        });

        agregar_producto.setOnAction(actionEvent -> {
            HBox fila_producto = new HBox();
            fila_producto.getStyleClass().add("fila");

            Label labelProducto = new Label("Producto para: " + ClientesController.clientesSeleccionado.getNombre());
            labelProducto.getStyleClass().add("labelText");

            ComboBox<Productos> casilla_productos = new ComboBox<>();
            casilla_productos.getStyleClass().add("combox");

            ComboBox<Empleados> casilla_empleados = new ComboBox<>();
            casilla_empleados.getStyleClass().add("combox");

            // Setup searchable ComboBoxes
            setupSearchableComboBox(casilla_productos, obsProductos, productosConverter);
            setupSearchableComboBox(casilla_empleados, obsEmpleadosProductos, empleadosConverter);

            TextField textoBizum = new TextField();
            textoBizum.getStyleClass().add("dinero");
            TextField textoTarjeta = new TextField();
            textoTarjeta.getStyleClass().add("dinero");
            TextField textoEfectivo = new TextField();
            textoEfectivo.getStyleClass().add("dinero");

            textoBizum.setText("0");
            textoTarjeta.setText("0");


            Spinner<Integer> spinner = new Spinner<>();

            stock = modeloCobros.detectarStock(casilla_productos.getItems().getFirst().getId_producto());
            spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, stock, 1));
            spinner.setMaxWidth(60);

            casilla_productos.setOnAction(event ->{
                if (casilla_productos.getValue() != null) {
                    stock = modeloCobros.detectarStock(casilla_productos.getValue().getId_producto());
                    spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, stock, 1));

                    textoEfectivo.setText(String.valueOf(modeloCobros.detectarPrecioProducto(casilla_productos.getValue().getId_producto())));
                }
            });

            spinner.valueProperty().addListener((observable, oldValue, newValue) -> {
                // Update price label with new value
                textoEfectivo.setText(String.valueOf(newValue * modeloCobros.detectarPrecioProducto(casilla_productos.getValue().getId_producto())));
            });

            arrayCantidad.add(spinner);

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

            textoEfectivo.setText(String.valueOf(modeloCobros.detectarPrecioServicio(arrayProductos.getFirst().getValue().getId_producto())));

            fila_producto.getChildren().add(labelProducto);
            fila_producto.getChildren().add(casilla_productos);
            fila_producto.getChildren().add(casilla_empleados);
            fila_producto.getChildren().addAll(textoEfectivo, textoBizum, textoTarjeta);
            fila_producto.getChildren().add(spinner);

            panel.getChildren().add(fila_producto);
        });

        pagar.setOnAction(actionEvent -> {
            System.out.println("SERVICIOS ______________");
            int cont = 0;
            for(ComboBox<Servicios> servicio : arrayServicios){
                String id_empleado = arrayEmpleadosServicios.get(cont).getValue().getId_empleado();
                System.out.println(id_empleado + " : " + servicio.getValue().getId_servicio() + " precio: " + arrayTarjetaServicios.get(cont).getText());
                float bizum = Float.parseFloat(arrayBizumServicios.get(cont).getText());
                float efectivo = Float.parseFloat(arrayEfectivoServicios.get(cont).getText());
                float tarjeta = Float.parseFloat(arrayTarjetaServicios.get(cont).getText());

                modeloCobros.insertarCobro(ClientesController.clientesSeleccionado.getId_cliente(), servicio.getValue().getId_servicio(), id_empleado, 0, new Date(System.currentTimeMillis()), bizum, efectivo, tarjeta, "servicio", 1, observaciones.getText());
                cont = cont +1;
            }
            System.out.println("PRODUCTOS ______________");
            cont = 0;
            for(ComboBox<Productos> producto : arrayProductos){
                String id_empleado = arrayEmpleadosProductos.get(cont).getValue().getId_empleado();
                int cantidad = arrayCantidad.get(cont).getValue();
                System.out.println(cantidad);
                System.out.println(id_empleado + " : " + producto.getValue().getId_producto() + " precio: " + arrayTarjetaProductos.get(cont).getText());
                float bizum = Float.parseFloat(arrayBizumProductos.get(cont).getText());
                float efectivo = Float.parseFloat(arrayEfectivoProductos.get(cont).getText());
                float tarjeta = Float.parseFloat(arrayTarjetaProductos.get(cont).getText());
                modeloCobros.insertarCobro(ClientesController.clientesSeleccionado.getId_cliente(), 0, id_empleado, producto.getValue().getId_producto(), new Date(System.currentTimeMillis()), bizum, efectivo, tarjeta, "producto",cantidad, observaciones.getText());
                cont = cont +1;
            }

            pagoCompleto();
            arrayServicios.clear();
            arrayProductos.clear();
            arrayEmpleadosServicios.clear();
            arrayEmpleadosProductos.clear();
            arrayBizumServicios.clear();
            arrayEfectivoServicios.clear();
            arrayTarjetaServicios.clear();
            arrayBizumProductos.clear();
            arrayEfectivoProductos.clear();
            arrayTarjetaProductos.clear();
            arrayCantidad.clear();

            // Clear VBox
            panel.getChildren().clear();

        });
    }

    public void pagoCompleto(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("El pago se hizo correctamente");
        alert.showAndWait();
    }

}