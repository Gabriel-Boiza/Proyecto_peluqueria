package com.example.peluqueria_3.Controllers;

import com.example.peluqueria_3.Models.Productos;
import com.example.peluqueria_3.Models.ModeloProductos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class ProductosController {

    ModeloProductos modelo = new ModeloProductos();

    //Elementos vista CRUD productos

    @FXML private TableView<Productos> productosTabla;
    @FXML private TableColumn<Productos, Integer> ID;
    @FXML private TableColumn<Productos, String> nombre;
    @FXML private TableColumn<Productos, String> marca;
    @FXML private TableColumn<Productos, String> descripcion;
    @FXML private TableColumn<Productos, Float> precio;
    @FXML private TableColumn<Productos, Integer> stock;
    @FXML private TableColumn<Productos, String> codigo_barras;

    private ObservableList<Productos> productosObervable;

    @FXML private TextField campo_nombre;
    @FXML private TextField campo_marca;
    @FXML private TextField campo_descripcion;
    @FXML private TextField campo_precio;
    @FXML private TextField campo_stock;
    @FXML private TextField campo_codigo_barras;

    @FXML private Button boton_volver;
    @FXML private Button boton_crear;
    @FXML private Button boton_limpiar;
    @FXML private Button boton_modificar;
    @FXML private Button boton_eliminar;

    // Boton nav
    @FXML private Button agenda;

    // Boton salir
    @FXML private Button salir;

    Productos productosSeleccionado;



    // METODOS

    public void mostrarProductos(){
        ModeloProductos modelo = new ModeloProductos();
        ArrayList<Productos> productos = modelo.mostrarProductos();
        productosObervable = FXCollections.observableArrayList(productos);

        productosTabla.setItems(productosObervable);
        ID.setCellValueFactory(new PropertyValueFactory<>("id_producto"));
        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        marca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        precio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        codigo_barras.setCellValueFactory(new PropertyValueFactory<>("codigo_barras"));
    }

    public void rellenarInputs(Productos productoSeleccionado){
        campo_nombre.setText(productoSeleccionado.getNombre());
        campo_marca.setText(productoSeleccionado.getMarca());
        campo_descripcion.setText(productoSeleccionado.getDescripcion());
        campo_precio.setText(productoSeleccionado.getPrecio().toString());
        campo_stock.setText(productoSeleccionado.getStock().toString());
        campo_codigo_barras.setText(productoSeleccionado.getCodigo_barras());

    }

    public void limpiarInputs(){
        campo_nombre.setText("");
        campo_marca.setText("");
        campo_descripcion.setText("");
        campo_precio.setText("");
        campo_stock.setText("");
        campo_codigo_barras.setText("");

        productosSeleccionado = null;
    }



    @FXML
    public void initialize(){
        //Inicializa la tabla
        if (productosTabla != null){
            mostrarProductos();

            // Establece el listener para actualizar empleadoSeleccionado al cambiar la selección
            productosTabla.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    productosSeleccionado = newValue; // Actualiza el empleadoSeleccionado aquí
                    rellenarInputs(productosSeleccionado); // Rellena los inputs
                }
            });

            //Botones

            boton_crear.setOnAction(event -> {

                try{
                    float precio = Float.parseFloat(campo_precio.getText());
                    int stock = Integer.parseInt(campo_stock.getText());

                    modelo.crearProducto(campo_nombre.getText(), campo_marca.getText(), campo_descripcion.getText(), precio, stock, campo_codigo_barras.getText());
                    mostrarProductos();
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

                    alert.setContentText("Producto registrado correctamente");
                    alert.showAndWait();
                }
                catch (Exception e){
                    Alert alert = new Alert(Alert.AlertType.WARNING );
                    alert.showAndWait();
                }
            });
            boton_limpiar.setOnAction(event -> {
                limpiarInputs();
            });

            boton_modificar.setOnAction(event ->{
                if(productosSeleccionado != null){
                    int id = productosSeleccionado.getId_producto();
                    float precio = Float.parseFloat(campo_precio.getText());
                    int stock = Integer.parseInt(campo_stock.getText());

                    modelo.editarProducto(id, campo_nombre.getText(), campo_marca.getText(), campo_descripcion.getText(), precio, stock, campo_codigo_barras.getText());
                    mostrarProductos();
                }
            });
            boton_eliminar.setOnAction(event -> {

                if(productosSeleccionado != null){
                    modelo.eliminarProducto(productosSeleccionado.getId_producto());
                    productosObervable.remove(productosSeleccionado);
                }

            });

            boton_volver.setOnAction(event ->{
                LoadStage load = new LoadStage("/com/example/peluqueria_3/Vistas/personal.fxml", "Agenda");
            });

            agenda.setOnAction(event ->{
                LoadStage load = new LoadStage("/com/example/peluqueria_3/Vistas/personal.fxml", "Agenda");
            });

            salir.setOnAction(event ->{
                LoadStage load = new LoadStage("/com/example/peluqueria_3/Vistas/login.fxml", "Agenda");
            });
        }

    }
}
