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
    // FXML elements
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
    @FXML TextArea observaciones;

    @FXML TextField codigoBarras;
    @FXML Button buscarPorCodigoBarras;

    // Models
    ModeloProductos modeloProductos = new ModeloProductos();
    ModeloServicios modeloServicios = new ModeloServicios();
    ModeloEmpleados modeloEmpleados = new ModeloEmpleados();
    ModeloCobros modeloCobros = new ModeloCobros();

    // Arrays for form management
    private ArrayList<TextField> arrayBizumServicios = new ArrayList<>();
    private ArrayList<TextField> arrayEfectivoServicios = new ArrayList<>();
    private ArrayList<TextField> arrayTarjetaServicios = new ArrayList<>();
    private ArrayList<TextField> arrayBizumProductos = new ArrayList<>();
    private ArrayList<TextField> arrayEfectivoProductos = new ArrayList<>();
    private ArrayList<TextField> arrayTarjetaProductos = new ArrayList<>();
    private ArrayList<ComboBox<Servicios>> arrayServicios = new ArrayList<>();
    private ArrayList<ComboBox<Productos>> arrayProductos = new ArrayList<>();
    private ArrayList<ComboBox<Empleados>> arrayEmpleadosServicios = new ArrayList<>();
    private ArrayList<ComboBox<Empleados>> arrayEmpleadosProductos = new ArrayList<>();
    private ArrayList<Spinner<Integer>> arrayCantidad = new ArrayList<>();

    int stock;

    // Essential functions first
    private void actualizarTotales() {
        float totalBizum = 0;
        float totalEfectivo = 0;
        float totalTarjeta = 0;

        // Sumar totales de servicios
        for (int i = 0; i < arrayBizumServicios.size(); i++) {
            totalBizum += Float.parseFloat(arrayBizumServicios.get(i).getText());
            totalEfectivo += Float.parseFloat(arrayEfectivoServicios.get(i).getText());
            totalTarjeta += Float.parseFloat(arrayTarjetaServicios.get(i).getText());
        }

        // Sumar totales de productos
        for (int i = 0; i < arrayBizumProductos.size(); i++) {
            totalBizum += Float.parseFloat(arrayBizumProductos.get(i).getText());
            totalEfectivo += Float.parseFloat(arrayEfectivoProductos.get(i).getText());
            totalTarjeta += Float.parseFloat(arrayTarjetaProductos.get(i).getText());
        }

        bizum.setText(String.format("%.2f€", totalBizum));
        efectivo.setText(String.format("%.2f€", totalEfectivo));
        tarjeta.setText(String.format("%.2f€", totalTarjeta));
        total.setText(String.format("%.2f€", totalBizum + totalEfectivo + totalTarjeta));
    }

    private void configurarTextField(TextField textField) {
        textField.setText("0");
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*\\.?\\d*")) {
                textField.setText(oldValue);
            } else {
                actualizarTotales();
            }
        });
    }

    private <T> void setupSearchableComboBox(ComboBox<T> comboBox, ObservableList<T> items, StringConverter<T> converter) {
        comboBox.setEditable(true);
        comboBox.setItems(items);
        comboBox.setConverter(converter);

        FilteredList<T> filteredItems = new FilteredList<>(items, p -> true);
        final T[] lastValidValue = (T[]) new Object[1];

        comboBox.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            T selected = comboBox.getSelectionModel().getSelectedItem();

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

        comboBox.getEditor().focusedProperty().addListener((obs, wasFocused, isFocused) -> {
            if (!isFocused) {
                T selectedItem = comboBox.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    comboBox.getEditor().setText(converter.toString(selectedItem));
                    lastValidValue[0] = selectedItem;
                } else if (lastValidValue[0] != null) {
                    comboBox.setValue(lastValidValue[0]);
                    comboBox.getEditor().setText(converter.toString(lastValidValue[0]));
                }
            }
        });

        comboBox.setItems(filteredItems);
    }

    @FXML
    public void initialize() {
        // Initialize labels
        bizum.setText("0.00€");
        efectivo.setText("0.00€");
        tarjeta.setText("0.00€");
        total.setText("0.00€");

        ArrayList<Productos> productos = modeloProductos.mostrarProductos();
        ArrayList<Servicios> servicios = modeloServicios.mostrarServicios();
        ArrayList<Empleados> empleadosServicios = modeloEmpleados.mostrarEmpleados();
        ArrayList<Empleados> empleadosProductos = modeloEmpleados.mostrarEmpleados();

        ObservableList<Productos> obsProductos = FXCollections.observableArrayList(productos);
        ObservableList<Servicios> obsServicios = FXCollections.observableArrayList(servicios);
        ObservableList<Empleados> obsEmpleadosServicios = FXCollections.observableArrayList(empleadosServicios);
        ObservableList<Empleados> obsEmpleadosProductos = FXCollections.observableArrayList(empleadosProductos);

        StringConverter<Productos> productosConverter = new StringConverter<>() {
            @Override
            public String toString(Productos producto) {
                return producto != null ? producto.getNombre() : "";
            }

            @Override
            public Productos fromString(String string) {
                return null;
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

        buscarPorCodigoBarras.setOnAction(actionEvent -> {
            String codigoBarrasText = codigoBarras.getText().trim();
            if (codigoBarrasText.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Por favor, ingrese un código de barras");
                alert.showAndWait();
                return;
            }

            Productos productoEncontrado = modeloProductos.buscarPorCodigoBarras(codigoBarrasText);

            if (productoEncontrado == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("No se encontró ningún producto con ese código de barras");
                alert.showAndWait();
                return;
            }

            buscarPorCodigoBarrasHandler();
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
            panel.getChildren().clear();
            actualizarTotales();
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
            TextField textoTarjeta = new TextField();
            TextField textoEfectivo = new TextField();

            textoBizum.getStyleClass().add("dinero");
            textoTarjeta.getStyleClass().add("dinero");
            textoEfectivo.getStyleClass().add("dinero");

            configurarTextField(textoBizum);
            configurarTextField(textoTarjeta);
            configurarTextField(textoEfectivo);

            Button deleteButton = new Button("X");
            deleteButton.getStyleClass().add("delete-button");
            deleteButton.setOnAction(e -> {
                panel.getChildren().remove(fila_servicio);
                arrayServicios.remove(casilla_servicios);
                arrayEmpleadosServicios.remove(casilla_empleados);
                arrayBizumServicios.remove(textoBizum);
                arrayEfectivoServicios.remove(textoEfectivo);
                arrayTarjetaServicios.remove(textoTarjeta);
                actualizarTotales();
            });

            casilla_servicios.setOnAction(event -> {
                if (casilla_servicios.getValue() != null) {
                    textoEfectivo.setText(String.valueOf(modeloCobros.detectarPrecioServicio(casilla_servicios.getValue().getId_servicio())));
                    actualizarTotales();
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

            textoEfectivo.setText(String.valueOf(modeloCobros.detectarPrecioServicio(casilla_servicios.getValue().getId_servicio())));

            fila_servicio.getChildren().addAll(labelServicio, casilla_servicios, casilla_empleados,
                    textoEfectivo, textoBizum, textoTarjeta, deleteButton);

            panel.getChildren().add(fila_servicio);
            actualizarTotales();
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

            setupSearchableComboBox(casilla_productos, obsProductos, productosConverter);
            setupSearchableComboBox(casilla_empleados, obsEmpleadosProductos, empleadosConverter);

            TextField textoBizum = new TextField();
            TextField textoTarjeta = new TextField();
            TextField textoEfectivo = new TextField();

            textoBizum.getStyleClass().add("dinero");
            textoTarjeta.getStyleClass().add("dinero");
            textoEfectivo.getStyleClass().add("dinero");

            configurarTextField(textoBizum);
            configurarTextField(textoTarjeta);
            configurarTextField(textoEfectivo);

            Spinner<Integer> spinner = new Spinner<>();
            spinner.setMaxWidth(60);

            // Primero asignamos valores a los ComboBox
            if (!productos.isEmpty()) {
                casilla_productos.setValue(productos.getFirst());

                // Una vez que tenemos el producto, configuramos el spinner
                stock = modeloCobros.detectarStock(casilla_productos.getValue().getId_producto());
                if (stock > 0) {
                    spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, stock, 1));
                } else {
                    // Manejar el caso cuando el stock es 0 o negativo
                    spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 0, 0));
                }
                textoEfectivo.setText(String.valueOf(modeloCobros.detectarPrecioProducto(casilla_productos.getValue().getId_producto())));
            }

            if (!empleadosProductos.isEmpty()) {
                casilla_empleados.setValue(empleadosProductos.getFirst());
            }

            Button deleteButton = new Button("X");
            deleteButton.getStyleClass().add("delete-button");
            deleteButton.setOnAction(e -> {
                panel.getChildren().remove(fila_producto);
                arrayProductos.remove(casilla_productos);
                arrayEmpleadosProductos.remove(casilla_empleados);
                arrayBizumProductos.remove(textoBizum);
                arrayEfectivoProductos.remove(textoEfectivo);
                arrayTarjetaProductos.remove(textoTarjeta);
                arrayCantidad.remove(spinner);
                actualizarTotales();
            });

            // Configurar los listeners después de tener valores válidos
            casilla_productos.setOnAction(event -> {
                if (casilla_productos.getValue() != null) {
                    stock = modeloCobros.detectarStock(casilla_productos.getValue().getId_producto());
                    spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, stock, 1));
                    float precio = modeloCobros.detectarPrecioProducto(casilla_productos.getValue().getId_producto());
                    textoEfectivo.setText(String.valueOf(precio * spinner.getValue()));
                    actualizarTotales();
                }
            });

            spinner.valueProperty().addListener((observable, oldValue, newValue) -> {
                if (casilla_productos.getValue() != null) {
                    float precio = modeloCobros.detectarPrecioProducto(casilla_productos.getValue().getId_producto());
                    textoEfectivo.setText(String.valueOf(newValue * precio));
                    actualizarTotales();
                }
            });

            // Agregar a los arrays de control
            arrayProductos.add(casilla_productos);
            arrayEmpleadosProductos.add(casilla_empleados);
            arrayBizumProductos.add(textoBizum);
            arrayEfectivoProductos.add(textoEfectivo);
            arrayTarjetaProductos.add(textoTarjeta);
            arrayCantidad.add(spinner);

            // Construir la fila
            fila_producto.getChildren().addAll(
                    labelProducto,
                    casilla_productos,
                    casilla_empleados,
                    textoEfectivo,
                    textoBizum,
                    textoTarjeta,
                    spinner,
                    deleteButton
            );

            // Agregar la fila al panel
            panel.getChildren().add(fila_producto);
        });

    }



    @FXML
    public void buscarPorCodigoBarrasHandler() {
        String codigoBarrasText = codigoBarras.getText().trim();
        if (codigoBarrasText.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Por favor, ingrese un código de barras");
            alert.showAndWait();
            return;
        }

        // Asumiendo que necesitamos crear un método en ModeloProductos para buscar por código de barras
        Productos productoEncontrado = modeloProductos.buscarPorCodigoBarras(codigoBarrasText);

        if (productoEncontrado == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("No se encontró ningún producto con ese código de barras");
            alert.showAndWait();
            return;
        }

        // Crear nueva fila de producto (similar a agregar_producto)
        HBox fila_producto = new HBox();
        fila_producto.getStyleClass().add("fila");

        Label labelProducto = new Label("Producto para: " + ClientesController.clientesSeleccionado.getNombre());
        labelProducto.getStyleClass().add("labelText");

        ComboBox<Productos> casilla_productos = new ComboBox<>();
        casilla_productos.getStyleClass().add("combox");
        ComboBox<Empleados> casilla_empleados = new ComboBox<>();
        casilla_empleados.getStyleClass().add("combox");

        // Configurar ComboBoxes con los mismos convertidores
        ArrayList<Empleados> empleadosProductos = modeloEmpleados.mostrarEmpleados();
        ObservableList<Empleados> obsEmpleadosProductos = FXCollections.observableArrayList(empleadosProductos);
        ArrayList<Productos> productos = modeloProductos.mostrarProductos();
        ObservableList<Productos> obsProductos = FXCollections.observableArrayList(productos);

        StringConverter<Productos> productosConverter = new StringConverter<>() {
            @Override
            public String toString(Productos producto) {
                return producto != null ? producto.getNombre() : "";
            }

            @Override
            public Productos fromString(String string) {
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

        setupSearchableComboBox(casilla_productos, obsProductos, productosConverter);
        setupSearchableComboBox(casilla_empleados, obsEmpleadosProductos, empleadosConverter);

        // Configurar campos de texto para pagos
        TextField textoBizum = new TextField();
        TextField textoTarjeta = new TextField();
        TextField textoEfectivo = new TextField();

        textoBizum.getStyleClass().add("dinero");
        textoTarjeta.getStyleClass().add("dinero");
        textoEfectivo.getStyleClass().add("dinero");

        configurarTextField(textoBizum);
        configurarTextField(textoTarjeta);
        configurarTextField(textoEfectivo);

        // Configurar Spinner para cantidad
        Spinner<Integer> spinner = new Spinner<>();
        stock = modeloCobros.detectarStock(productoEncontrado.getId_producto());
        spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, stock, 1));
        spinner.setMaxWidth(60);

        // Botón eliminar
        Button deleteButton = new Button("X");
        deleteButton.getStyleClass().add("delete-button");
        deleteButton.setOnAction(e -> {
            panel.getChildren().remove(fila_producto);
            arrayProductos.remove(casilla_productos);
            arrayEmpleadosProductos.remove(casilla_empleados);
            arrayBizumProductos.remove(textoBizum);
            arrayEfectivoProductos.remove(textoEfectivo);
            arrayTarjetaProductos.remove(textoTarjeta);
            arrayCantidad.remove(spinner);
            actualizarTotales();
        });

        // Configurar listeners
        casilla_productos.setOnAction(event -> {
            if (casilla_productos.getValue() != null) {
                stock = modeloCobros.detectarStock(casilla_productos.getValue().getId_producto());
                spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, stock, 1));
                textoEfectivo.setText(String.valueOf(modeloCobros.detectarPrecioProducto(casilla_productos.getValue().getId_producto())));
                actualizarTotales();
            }
        });

        spinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            textoEfectivo.setText(String.valueOf(newValue * modeloCobros.detectarPrecioProducto(casilla_productos.getValue().getId_producto())));
            actualizarTotales();
        });

        // Establecer el producto encontrado como valor seleccionado
        casilla_productos.setValue(productoEncontrado);

        // Establecer empleado por defecto
        if (!empleadosProductos.isEmpty()) {
            casilla_empleados.setValue(empleadosProductos.getFirst());
        }

        // Agregar a los arrays de control
        arrayProductos.add(casilla_productos);
        arrayEmpleadosProductos.add(casilla_empleados);
        arrayBizumProductos.add(textoBizum);
        arrayEfectivoProductos.add(textoEfectivo);
        arrayTarjetaProductos.add(textoTarjeta);
        arrayCantidad.add(spinner);

        // Establecer precio inicial
        textoEfectivo.setText(String.valueOf(modeloCobros.detectarPrecioProducto(productoEncontrado.getId_producto())));

        // Agregar elementos a la fila
        fila_producto.getChildren().addAll(
                labelProducto,
                casilla_productos,
                casilla_empleados,
                textoEfectivo,
                textoBizum,
                textoTarjeta,
                spinner,
                deleteButton
        );

        // Agregar la fila al panel
        panel.getChildren().add(fila_producto);

        // Limpiar el campo de código de barras
        codigoBarras.clear();
    }

    public void pagoCompleto(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("El pago se hizo correctamente");
        alert.showAndWait();
    }


}