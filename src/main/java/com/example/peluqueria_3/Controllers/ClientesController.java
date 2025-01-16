package com.example.peluqueria_3.Controllers;

import com.example.peluqueria_3.Models.ModeloClientes;
import com.example.peluqueria_3.Models.Clientes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class ClientesController {

    ModeloClientes modelo = new ModeloClientes();

    //Elementos vista CRUD clientes

    @FXML private TableView<Clientes> clientesTabla;
    @FXML private TableColumn<Clientes, Integer> ID;
    @FXML private TableColumn<Clientes, String> nombre;
    @FXML private TableColumn<Clientes, String> apellido;
    @FXML private TableColumn<Clientes, String> telefono;
    @FXML private TableColumn<Clientes, String> correo;
    @FXML private TableColumn<Clientes, String> observaciones;
    @FXML private TableColumn<Clientes, Boolean> ley_datos;

    private ObservableList<Clientes> clientesObervable;

    @FXML private TextField campo_nombre;
    @FXML private TextField campo_apellido;
    @FXML private TextField campo_telefono;
    @FXML private TextField campo_correo;
    @FXML private TextField campo_observaciones;
    @FXML private ChoiceBox campo_ley_datos;

    @FXML private Button ficha_cliente;
    @FXML private Button boton_volver;
    @FXML private Button boton_crear;
    @FXML private Button boton_limpiar;
    @FXML private Button boton_modificar;
    @FXML private Button boton_eliminar;

    // Boton nav
    @FXML private Button agenda;

    // Boton Salir
    @FXML private Button salir;

    //Datos pagina ficha
    @FXML private Label nombre_ficha;
    @FXML private Button nueva_sesion;

    //dato general
    static Clientes clientesSeleccionado;

    // METODOS
    public void mostrarClientes(){
        ModeloClientes modelo = new ModeloClientes();
        ArrayList<Clientes> clientes = modelo.mostrarClientes();
        clientesObervable = FXCollections.observableArrayList(clientes);

        clientesTabla.setItems(clientesObervable);
        ID.setCellValueFactory(new PropertyValueFactory<>("id_cliente"));
        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        apellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        telefono.setCellValueFactory(new PropertyValueFactory<>("tel"));
        correo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        observaciones.setCellValueFactory(new PropertyValueFactory<>("observaciones"));
        ley_datos.setCellValueFactory(new PropertyValueFactory<>("ley_datos"));

        // Mostrar Si o No en la tabla
        ley_datos.setCellFactory(columna -> new TableCell<>(){
            @Override
            protected  void updateItem(Boolean item, boolean empty){
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : item ? "SI" : "NO");
            }
        });
    }

    public void rellenarInputs(Clientes clientesSeleccionado){
        String res;
        campo_nombre.setText(clientesSeleccionado.getNombre());
        campo_apellido.setText(clientesSeleccionado.getApellido());
        campo_telefono.setText(clientesSeleccionado.getTel());
        campo_correo.setText(clientesSeleccionado.getCorreo());
        campo_observaciones.setText(clientesSeleccionado.getObservaciones());

        if (clientesSeleccionado.getLey_datos()){
            res = "SI";
        }else{
            res = "NO";
        }
        campo_ley_datos.setValue(res);
    }

    public void limpiarInputs(){
        campo_nombre.setText("");
        campo_apellido.setText("");
        campo_telefono.setText("");
        campo_correo.setText("");
        campo_observaciones.setText("");
        campo_ley_datos.setValue("");

        clientesSeleccionado = null;
    }



    @FXML
    public void initialize(){
        //Inicializa la tabla
        if (clientesTabla != null){
            mostrarClientes();
            campo_ley_datos.getItems().addAll("SI", "NO");

            // Establece el listener para actualizar empleadoSeleccionado al cambiar la selección
            clientesTabla.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    clientesSeleccionado = newValue; // Actualiza el empleadoSeleccionado aquí
                    rellenarInputs(clientesSeleccionado); // Rellena los inputs
                }
            });

            //Botones

            boton_crear.setOnAction(event -> {

                try{
                    clientesSeleccionado = null;

                    boolean leyDatos = campo_ley_datos.getValue().equals("SI") ? true : false;

                    modelo.crearCliente(campo_nombre.getText(), campo_apellido.getText(), campo_telefono.getText(), campo_correo.getText(), campo_observaciones.getText(), leyDatos);
                    mostrarClientes();
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

                    alert.setContentText("Cliente registrado correctamente");
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
                if(clientesSeleccionado != null){
                    int id = clientesSeleccionado.getId_cliente();
                    boolean leyDatos = campo_ley_datos.getValue().equals("SI") ? true : false;

                    modelo.editarCliente(id, campo_nombre.getText(), campo_apellido.getText(), campo_telefono.getText(), campo_correo.getText(), campo_observaciones.getText(), leyDatos);
                    mostrarClientes();
                }
            });
            boton_eliminar.setOnAction(event -> {

                if(clientesSeleccionado != null){
                    modelo.eliminarCliente(clientesSeleccionado.getId_cliente());
                    clientesObervable.remove(clientesSeleccionado);
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

            ficha_cliente.setOnAction(actionEvent -> {
                LoadStage load = new LoadStage("/com/example/peluqueria_3/Vistas/fichaCliente.fxml", "Ficha Cliente");
            });
        }


        //
        if(nombre_ficha != null){
            System.out.println("hola");
            nueva_sesion.setOnAction(event->{
                LoadStage load = new LoadStage("/com/example/peluqueria_3/Vistas/cobro.fxml", "Agenda");
            });
        }

    }
}
