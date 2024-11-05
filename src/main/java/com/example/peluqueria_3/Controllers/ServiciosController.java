package com.example.peluqueria_3.Controllers;

import com.example.peluqueria_3.Models.Servicios;
import com.example.peluqueria_3.Models.ModeloServicios;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ServiciosController {

    ModeloServicios modelo = new ModeloServicios();

    //Elementos vista CRUD servicios

    @FXML private TableView<Servicios> serviciosTabla;
    @FXML private TableColumn<Servicios, Integer> ID;
    @FXML private TableColumn<Servicios, String> nombre;
    @FXML private TableColumn<Servicios, String> descripcion;
    @FXML private TableColumn<Servicios, String> fecha;
    @FXML private TableColumn<Servicios, String> hora;
    @FXML private TableColumn<Servicios, Float> precio;

    private ObservableList<Servicios> serviciosObervable;

    @FXML private TextField campo_nombre;
    @FXML private TextField campo_descripcion;
    @FXML private TextField campo_fecha;
    @FXML private TextField campo_hora;
    @FXML private TextField campo_precio;

    @FXML private Button boton_volver;
    @FXML private Button boton_crear;
    @FXML private Button boton_limpiar;
    @FXML private Button boton_modificar;
    @FXML private Button boton_eliminar;

    // Boton
    @FXML private Button agenda;
    @FXML private Button salir;
    Servicios servicioSeleccionado;



    // METODOS

    public void mostrarServicios(){
        ModeloServicios modelo = new ModeloServicios();
        ArrayList<Servicios> servicios = modelo.mostrarServicios();
        serviciosObervable = FXCollections.observableArrayList(servicios);

        serviciosTabla.setItems(serviciosObervable);
        ID.setCellValueFactory(new PropertyValueFactory<>("id_servicio"));
        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        fecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        hora.setCellValueFactory(new PropertyValueFactory<>("hora"));
        precio.setCellValueFactory(new PropertyValueFactory<>("precio"));
    }

    public void rellenarInputs(Servicios servicioSeleccionado){
        campo_nombre.setText(servicioSeleccionado.getNombre());
        campo_descripcion.setText(servicioSeleccionado.getDescripcion());
        campo_fecha.setText(servicioSeleccionado.getFecha());
        campo_hora.setText(servicioSeleccionado.getHora());
        campo_precio.setText(servicioSeleccionado.getPrecio().toString());

    }

    public void limpiarInputs(){
        campo_nombre.setText("");
        campo_descripcion.setText("");
        campo_fecha.setText("");
        campo_hora.setText("");
        campo_precio.setText("");

        servicioSeleccionado = null;
    }



    @FXML
    public void initialize(){
        //Inicializa la tabla
        if (serviciosTabla != null){
            mostrarServicios();


            // Establece el listener para actualizar empleadoSeleccionado al cambiar la selección
            serviciosTabla.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    servicioSeleccionado = newValue; // Actualiza el empleadoSeleccionado aquí
                    rellenarInputs(servicioSeleccionado); // Rellena los inputs
                }
            });


            //Botones

            boton_crear.setOnAction(event -> {

                try{
                    float precio = Float.parseFloat(campo_precio.getText());

                    modelo.crearServicio(campo_nombre.getText(), campo_descripcion.getText(), campo_fecha.getText(), campo_hora.getText(), precio);
                    mostrarServicios();
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

                    alert.setContentText("Servicio registrado correctamente");
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
                if(servicioSeleccionado != null){
                    int id = servicioSeleccionado.getId_servicio();
                    float precio = Float.parseFloat(campo_precio.getText());

                    modelo.editarServicio(id, campo_nombre.getText(), campo_descripcion.getText(), campo_fecha.getText(), campo_hora.getText(), precio);
                    mostrarServicios();
                }
            });
            boton_eliminar.setOnAction(event -> {

                if(servicioSeleccionado != null){
                    modelo.eliminarServicio(servicioSeleccionado.getId_servicio());
                    serviciosObervable.remove(servicioSeleccionado);
                }

            });
            boton_volver.setOnAction(event ->{
                LoadStage load = new LoadStage("/com/example/peluqueria_3/Vistas/agenda.fxml", "Agenda");
            });

            agenda.setOnAction(event ->{
                LoadStage load = new LoadStage("/com/example/peluqueria_3/Vistas/agenda.fxml", "Agenda");
            });

            salir.setOnAction(event ->{
                LoadStage load = new LoadStage("/com/example/peluqueria_3/Vistas/login.fxml", "Agenda");
            });
        }

    }
}
