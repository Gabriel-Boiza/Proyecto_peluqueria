package com.example.peluqueria_3.Controllers;

import com.example.peluqueria_3.Models.Empleados;
import com.example.peluqueria_3.Models.ModeloEmpleados;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;

public class EmpleadosController {

    ModeloEmpleados modelo = new ModeloEmpleados();

    //Elementos vista login
    @FXML private Button BotonLogin;
    @FXML private PasswordField input_contrasenya;
    @FXML private TextField input_usuario;

    //Elementos vista CRUD empleados

    @FXML private TableView<Empleados> empleadosTabla;
    @FXML private TableColumn<Empleados, String> ID;
    @FXML private TableColumn<Empleados, String> usuario;
    @FXML private TableColumn<Empleados, String> nombre;
    @FXML private TableColumn<Empleados, String> apellido;
    @FXML private TableColumn<Empleados, String> email;
    @FXML private TableColumn<Empleados, String> telefono;
    @FXML private TableColumn<Empleados, String> direccion;
    @FXML private TableColumn<Empleados, String> cventas;
    @FXML private TableColumn<Empleados, String> cservicios;
    @FXML private TableColumn<Empleados, String> l_cservicios;

    private ObservableList<Empleados> empleadosObervable;

    @FXML private TextField campo_id;
    @FXML private TextField campo_usuario;
    @FXML private TextField campo_nombre;
    @FXML private TextField campo_apellido;
    @FXML private TextField campo_correo;
    @FXML private TextField campo_telefono;
    @FXML private TextField campo_direccion;
    @FXML private TextField campo_cservicios;
    @FXML private TextField campo_cventas;
    @FXML private  TextField campo_l_cservicios;

    @FXML private PasswordField campo_contrasenya;
    @FXML private ChoiceBox campo_rol;
    @FXML private ChoiceBox campo_estado;

    @FXML private Button boton_volver;
    @FXML private Button boton_crear;
    @FXML private Button boton_limpiar;
    @FXML private Button boton_modificar;
    @FXML private Button boton_eliminar;

    Empleados empleadoSeleccionado;



    // METODOS

    public void ValidarUser(){
        try{
            ModeloEmpleados modeloEmpleado = new ModeloEmpleados();
            Empleados empleadoRegistrado = modeloEmpleado.validarEmpleado(input_usuario.getText(), input_contrasenya.getText());

            if (empleadoRegistrado != null) {
                DatosGlobales.setEmpleadoActual(empleadoRegistrado);

                LoadStage loadStage = new LoadStage("/com/example/peluqueria_3/Vistas/agenda.fxml", "Agenda");

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    public void mostrarUsuarios(){
        ModeloEmpleados modelo = new ModeloEmpleados();
        ArrayList<Empleados> empleados = modelo.mostrarEmpleados();
        empleadosObervable = FXCollections.observableArrayList(empleados);

        empleadosTabla.setItems(empleadosObervable);
        ID.setCellValueFactory(new PropertyValueFactory<Empleados, String>("Id_empleado"));
        usuario.setCellValueFactory(new PropertyValueFactory<Empleados, String>("usuario"));
        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        apellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        email.setCellValueFactory(new PropertyValueFactory<>("correo"));
        telefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        direccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        cventas.setCellValueFactory(new PropertyValueFactory<>("comision_ventas"));
        cservicios.setCellValueFactory(new PropertyValueFactory<>("comision_servicios"));
        l_cservicios.setCellValueFactory(new PropertyValueFactory<>("limite_comision"));


    }

    public void rellenarInputs(Empleados empleadoSeleccionado){
        campo_id.setText(empleadoSeleccionado.getId_empleado());
        campo_usuario.setText(empleadoSeleccionado.getUsuario());
        campo_nombre.setText(empleadoSeleccionado.getNombre());
        campo_apellido.setText(empleadoSeleccionado.getApellido());
        campo_correo.setText(empleadoSeleccionado.getCorreo());
        campo_telefono.setText(empleadoSeleccionado.getTelefono());
        campo_direccion.setText(empleadoSeleccionado.getDireccion());
        campo_cservicios.setText(empleadoSeleccionado.getComision_servicios().toString() + "%");
        campo_cventas.setText(empleadoSeleccionado.getComision_ventas().toString() + "%");
        campo_l_cservicios.setText(empleadoSeleccionado.getLimite_comision().toString()+ "%");

        campo_contrasenya.setText(empleadoSeleccionado.getContrasenya());
        campo_rol.setValue(empleadoSeleccionado.getRol());
        campo_estado.setValue(empleadoSeleccionado.getEstado());

    }

    public void limpiarInputs(){
        campo_id.setText("");
        campo_usuario.setText("");
        campo_nombre.setText("");
        campo_apellido.setText("");
        campo_correo.setText("");
        campo_telefono.setText("");
        campo_direccion.setText("");
        campo_cservicios.setText("");
        campo_cventas.setText("");
        campo_l_cservicios.setText("");

        campo_contrasenya.setText("");
        campo_rol.setValue(null);
        campo_estado.setValue(null);

        empleadoSeleccionado = null;
    }



    @FXML
    public void initialize(){
        //Inicializa la tabla
        if (empleadosTabla !=null){
            mostrarUsuarios();
            campo_rol.getItems().addAll("administrador", "empleado", "invitado");
            campo_estado.getItems().addAll("Activo", "Inactivo");

            // Establece el listener para actualizar empleadoSeleccionado al cambiar la selección
            empleadosTabla.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    empleadoSeleccionado = newValue; // Actualiza el empleadoSeleccionado aquí
                    rellenarInputs(empleadoSeleccionado); // Rellena los inputs
                }
            });

            //Botones

            boton_crear.setOnAction(event -> {
                if (empleadoSeleccionado == null){
                    try{
                        if(!modelo.empleadoExiste(campo_id.getText())){
                            Float cventas = Float.parseFloat(campo_cventas.getText().substring(0, campo_cventas.getText().length() - 1));
                            Float cservicios = Float.parseFloat(campo_cservicios.getText().substring(0, campo_cservicios.getText().length() - 1));
                            Float lcservicios = Float.parseFloat(campo_l_cservicios.getText().substring(0, campo_l_cservicios.getText().length() - 1));
                            modelo.crearEmpleado(campo_id.getText(), campo_usuario.getText(), campo_nombre.getText(), campo_apellido.getText(), campo_correo.getText(), campo_contrasenya.getText(), campo_telefono.getText(), campo_direccion.getText(), cventas, cservicios, lcservicios, campo_rol.getValue().toString(), campo_estado.getValue().toString());
                            mostrarUsuarios();
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setContentText("Usuario registrado correctamente");
                            alert.showAndWait();
                        }
                        else{
                            Alert alerta = new Alert(Alert.AlertType.ERROR);
                            alerta.setContentText("Usuario ya existente");
                            alerta.showAndWait();
                        }
                    }
                    catch (Exception e){
                        Alert alert = new Alert(Alert.AlertType.WARNING );
                        alert.setContentText(e.getMessage());
                        alert.showAndWait();
                    }
                }

            });
            boton_limpiar.setOnAction(event -> {
                limpiarInputs();
            });

            boton_modificar.setOnAction(event ->{
                if(empleadoSeleccionado != null){
                    Float cventas = Float.parseFloat(campo_cventas.getText().substring(0, campo_cventas.getText().length() - 1));
                    Float cservicios = Float.parseFloat(campo_cservicios.getText().substring(0, campo_cservicios.getText().length() - 1));
                    Float lcservicios = Float.parseFloat(campo_l_cservicios.getText().substring(0, campo_l_cservicios.getText().length() - 1));

                    modelo.editarEmpleado(campo_id.getText(), campo_usuario.getText(), campo_nombre.getText(), campo_apellido.getText(), campo_correo.getText(), campo_contrasenya.getText(), campo_telefono.getText(), campo_direccion.getText(), cventas, cservicios, lcservicios, campo_rol.getValue().toString(), campo_estado.getValue().toString());
                    mostrarUsuarios();
                }
            });
            boton_eliminar.setOnAction(event -> {

                if(empleadoSeleccionado != null){
                    modelo.eliminarEmpleado(empleadoSeleccionado.getId_empleado());
                    empleadosObervable.remove(empleadoSeleccionado);
                }

            });

            boton_volver.setOnAction(event ->{
                LoadStage load = new LoadStage("/com/example/peluqueria_3/Vistas/agenda.fxml", "Agenda");
            });
        }

    }








}
