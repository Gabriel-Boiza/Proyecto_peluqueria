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

        //Elementos vista login
        @FXML private Button BotonLogin;
        @FXML private PasswordField input_contrasenya;
        @FXML private TextField input_usuario;

        //Elementos vista CRUD empleados

        @FXML private TableView<Empleados> empleadosTabla;
        @FXML private TableColumn<Empleados, String> ID;
        @FXML private TableColumn<Empleados, String> nombre;
        @FXML private TableColumn<Empleados, String> apellido;
        @FXML private TableColumn<Empleados, String> email;
        @FXML private TableColumn<Empleados, String> telefono;
        @FXML private TableColumn<Empleados, String> direccion;
        private ObservableList<Empleados> empleadosObervable;

        private int indiceTabla;



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
            empleadosObervable = FXCollections.observableArrayList(
                    empleados
            );

            empleadosTabla.setItems(empleadosObervable);
            ID.setCellValueFactory(new PropertyValueFactory<Empleados, String>("Id_empleado"));
            nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            apellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
            email.setCellValueFactory(new PropertyValueFactory<>("email"));
            telefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
            direccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));


        }


        //ACCIONES BOTONES
        @FXML
        public void initialize(){
            if (empleadosTabla !=null){

                    mostrarUsuarios();

            }

        }








    }

