package com.example.peluqueria_3.Controllers;

import com.example.peluqueria_3.Models.Cobros;
import com.example.peluqueria_3.Models.ModeloClientes;
import com.example.peluqueria_3.Models.Clientes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class ClientesController {

    ModeloClientes modelo = new ModeloClientes();

    //Elementos vista CRUD clientes

    @FXML TableView<Clientes> clientesTabla;
    @FXML TableColumn<Clientes, Integer> ID;
    @FXML TableColumn<Clientes, String> nombre;
    @FXML TableColumn<Clientes, String> apellido;
    @FXML TableColumn<Clientes, String> telefono;
    @FXML TableColumn<Clientes, String> correo;
    @FXML TableColumn<Clientes, String> observaciones;
    @FXML TableColumn<Clientes, Boolean> ley_datos;

    private ObservableList<Clientes> clientesObervable;

    @FXML TextField campo_nombre;
    @FXML TextField campo_apellido;
    @FXML TextField campo_telefono;
    @FXML TextField campo_correo;
    @FXML TextField campo_observaciones;
    @FXML ChoiceBox campo_ley_datos;

    @FXML Button ficha_cliente;
    @FXML Button boton_volver;
    @FXML Button boton_crear;
    @FXML Button boton_limpiar;
    @FXML Button boton_modificar;
    @FXML Button boton_eliminar;

    @FXML Button ver_servicios;
    @FXML  Button ver_productos;

    //Datos pagina ficha
    @FXML Label nombre_ficha;
    @FXML Button nueva_sesion;
    @FXML Button guardar;
    @FXML Button volverFichaCliente;
    @FXML VBox sesiones;
    @FXML Label usuario_ficha;
    @FXML TextArea observacionesCliente;

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
                LoadStage load = new LoadStage("/com/example/peluqueria_3/Vistas/agenda.fxml", "Agenda");
            });

            if (ficha_cliente != null){
                ficha_cliente.setOnAction(actionEvent -> {
                    if(clientesSeleccionado != null){
                        LoadStage load = new LoadStage("/com/example/peluqueria_3/Vistas/fichaCliente.fxml", "Ficha Cliente");
                    }
                    else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Selecciona un cliente");
                        alert.showAndWait();
                    }
                });
            }
        }



        if(nombre_ficha != null){

            usuario_ficha.setText("Ficha de " + clientesSeleccionado.getNombre());
            ArrayList<Cobros> cobrosCliente = modelo.datosFichaClienteServicios(clientesSeleccionado.getId_cliente());
            ArrayList<Cobros> cobrosClienteProductos = modelo.datosFichaClienteProductos(clientesSeleccionado.getId_cliente());

            observacionesCliente.setText(clientesSeleccionado.getObservaciones());

            ver_servicios.setOnAction(actionEvent -> {
                sesiones.getChildren().clear();

                HBox cabecera = new HBox();
                cabecera.getStyleClass().add("fila");
                Label servicio = new Label("Servicio");
                servicio.getStyleClass().add("nombreServicio");
                Label empleado = new Label("Empleado");
                empleado.getStyleClass().add("nombreEmpleado");

                Label bizumlabel = new Label("Bizum");
                bizumlabel.getStyleClass().add("dinero");
                Label efectivolabel = new Label("Efectivo");
                efectivolabel.getStyleClass().add("dinero");

                Label tarjetalabel = new Label("Tarjeta");
                tarjetalabel.getStyleClass().add("dinero");

                cabecera.getChildren().addAll(servicio, empleado, bizumlabel, efectivolabel, tarjetalabel);

                sesiones.getChildren().add(cabecera);

                for(Cobros cobro: cobrosCliente){
                    HBox hbox = new HBox();
                    hbox.getStyleClass().add("fila");

                    String bizum = String.valueOf(cobro.getBizum());
                    String efectivo = String.valueOf(cobro.getEfectivo());
                    String tarjeta = String.valueOf(cobro.getTarjeta());

                    Label nombreServicio = new Label(cobro.getNombre_servicio());
                    nombreServicio.getStyleClass().add("nombreServicio");

                    Label nombreEmpleado = new Label(cobro.getNombre_empleado());
                    nombreEmpleado.getStyleClass().add("nombreEmpleado");

                    Label bizumlbl = new Label(bizum);
                    bizumlbl.getStyleClass().add("dinero");

                    Label efectivolbl = new Label(efectivo);
                    efectivolbl.getStyleClass().add("dinero");

                    Label tarjetalbl = new Label(tarjeta);
                    tarjetalbl.getStyleClass().add("dinero");

                    Label labelObs = new Label(cobro.getObservaciones());
                    labelObs.getStyleClass().add("nombreServicio");

                    hbox.getChildren().add(nombreServicio);
                    hbox.getChildren().add(nombreEmpleado);

                    hbox.getChildren().add(bizumlbl);
                    hbox.getChildren().add(efectivolbl);
                    hbox.getChildren().add(tarjetalbl);
                    hbox.getChildren().add(labelObs);

                    sesiones.getChildren().add(hbox);
                }
            });
            ver_productos.setOnAction(actionEvent -> {
                sesiones.getChildren().clear();

                HBox cabecera = new HBox();
                cabecera.getStyleClass().add("fila");
                Label servicio = new Label("Servicio");
                servicio.getStyleClass().add("nombreServicio");
                Label empleado = new Label("Empleado");
                empleado.getStyleClass().add("nombreEmpleado");

                Label bizumlabel = new Label("Bizum");
                bizumlabel.getStyleClass().add("dinero");
                Label efectivolabel = new Label("Efectivo");
                efectivolabel.getStyleClass().add("dinero");

                Label tarjetalabel = new Label("Tarjeta");
                tarjetalabel.getStyleClass().add("dinero");

                cabecera.getChildren().addAll(servicio, empleado, bizumlabel, efectivolabel, tarjetalabel);

                sesiones.getChildren().add(cabecera);

                for(Cobros cobro: cobrosClienteProductos){
                    HBox hbox = new HBox();
                    hbox.getStyleClass().add("fila");

                    System.out.println(cobro);

                    String bizum = String.valueOf(cobro.getBizum());
                    String efectivo = String.valueOf(cobro.getEfectivo());
                    String tarjeta = String.valueOf(cobro.getTarjeta());

                    Label nombreproducto = new Label(cobro.getNombre_producto());
                    nombreproducto.getStyleClass().add("nombreServicio");

                    Label nombreEmpleado = new Label(cobro.getNombre_empleado());
                    nombreEmpleado.getStyleClass().add("nombreEmpleado");

                    Label bizumlbl = new Label(bizum);
                    bizumlbl.getStyleClass().add("dinero");

                    Label efectivolbl = new Label(efectivo);
                    efectivolbl.getStyleClass().add("dinero");

                    Label tarjetalbl = new Label(tarjeta);
                    tarjetalbl.getStyleClass().add("dinero");

                    Label labelObs = new Label(cobro.getObservaciones());
                    labelObs.getStyleClass().add("nombreServicio");

                    hbox.getChildren().add(nombreproducto);
                    hbox.getChildren().add(nombreEmpleado);

                    hbox.getChildren().add(bizumlbl);
                    hbox.getChildren().add(efectivolbl);
                    hbox.getChildren().add(tarjetalbl);
                    hbox.getChildren().add(labelObs);

                    sesiones.getChildren().add(hbox);
                }
            });

            nueva_sesion.setOnAction(event->{
                LoadStage load = new LoadStage("/com/example/peluqueria_3/Vistas/cobro.fxml", "Cobro");
            });

            observacionesCliente.focusedProperty().addListener((observable, oldValue, newValue)  -> {
                if (!newValue) { // Si newValue es false, significa que perdió el foco
                    System.out.println("Texto actual: " + observacionesCliente.getText());
                    modelo.insertObservaciones(clientesSeleccionado.getId_cliente(), observacionesCliente.getText());
                }
            });

            if (volverFichaCliente != null){
                volverFichaCliente.setOnAction(event ->{
                    LoadStage load = new LoadStage("/com/example/peluqueria_3/Vistas/clientes.fxml", "Clientes");
                });
            }
        }

    }
}
