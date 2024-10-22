package com.example.peluqueria_3.Controllers;

import com.example.peluqueria_3.Models.Empleados;
import com.example.peluqueria_3.Models.ModeloEmpleados;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class EmpleadosController {

    @FXML private Button BotonLogin;
    @FXML private PasswordField input_contrasenya;
    @FXML private TextField input_usuario;
    @FXML private Label nombre_usuario; // Label donde mostrar el nombre de usuario

    private ModeloEmpleados modelo = new ModeloEmpleados();

    @FXML
    public void ValidarUser(){
        try{
            ModeloEmpleados empleado = new ModeloEmpleados();
            Empleados empleadoRegistrado = empleado.validarEmpleado(input_usuario.getText(), input_contrasenya.getText());

            if (empleadoRegistrado != null) {

                LoadStage main = new LoadStage("/com/example/peluqueria_3/Vistas/agenda.fxml"); // Cargar la vista "agenda"

                Stage myStage = (Stage) BotonLogin.getScene().getWindow();  // Recoge la ventana en la que se encuentra el botón
                myStage.close();

                System.out.println(empleadoRegistrado.getId_empleado());
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
