package com.example.peluqueria_3.Controllers;

import com.example.peluqueria_3.Models.Empleados;
import com.example.peluqueria_3.Models.ModeloEmpleados;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EmpleadosController {

    @FXML private Button BotonLogin;
    @FXML private PasswordField input_contrasenya;
    @FXML private TextField input_usuario;
    @FXML private Label nombre_usuario;

    private ModeloEmpleados modelo = new ModeloEmpleados();

    @FXML
    public void ValidarUser(){
        try{
            ModeloEmpleados modeloEmpleado = new ModeloEmpleados();
            Empleados empleadoRegistrado = modeloEmpleado.validarEmpleado(input_usuario.getText(), input_contrasenya.getText());

            if (empleadoRegistrado != null) {
                DatosGlobales.setEmpleadoActual(empleadoRegistrado);
                // Cargar la vista "agenda"
                LoadStage main = new LoadStage("/com/example/peluqueria_3/Vistas/agenda.fxml", "Agenda");
                // Cerrar la ventana de login
                Stage myStage = (Stage) BotonLogin.getScene().getWindow();
                myStage.close();

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}

