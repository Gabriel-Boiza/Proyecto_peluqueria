package com.example.peluqueria_3.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class PersonalController {

    @FXML private Text bienvenido;
    //Aqui irá la agenda, demomento solo se aplicarán los botones para acceder a los CRUDS
    @FXML private Button botonClientes;
    @FXML private Button botonProductos;
    @FXML private Button botonEmpleados;
    @FXML private Button botonServicios;
    @FXML private Button salir;





    @FXML private Button agenda;



    @FXML
    public void initialize(){

        if (DatosGlobales.getEmpleadoActual().getRol().equals("administrador")){
            botonEmpleados.setVisible(true);
        }
        else{
            botonEmpleados.setVisible(false);
        }
        // ACCIONES DE LOS BOTONES
        botonClientes.setOnAction(event -> {
            LoadStage load = new LoadStage("/com/example/peluqueria_3/Vistas/clientes.fxml", "Clientes");
        });

        botonProductos.setOnAction(event -> {
            LoadStage load = new LoadStage("/com/example/peluqueria_3/Vistas/productos.fxml", "Productos");
        });

        botonEmpleados.setOnAction(event -> {
            LoadStage load = new LoadStage("/com/example/peluqueria_3/Vistas/empleados.fxml", "Empleados");
        });

        botonServicios.setOnAction(event -> {
            LoadStage load = new LoadStage("/com/example/peluqueria_3/Vistas/servicios.fxml", "Servicios");
        });

        salir.setOnAction(event-> {
            LoadStage load = new LoadStage("/com/example/peluqueria_3/Vistas/login.fxml", "login");
        });

        agenda.setOnAction(event-> {
            LoadStage load = new LoadStage("/com/example/peluqueria_3/Vistas/personal.fxml", "login");
        });


    }





}