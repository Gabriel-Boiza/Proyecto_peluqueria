package com.example.peluqueria_3.Controllers;

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
    public void ValidarUser() throws IOException {
        boolean llave = modelo.validarEmpleado(input_usuario.getText(), input_contrasenya.getText());

        if (llave) {
            // Cargar la vista "agenda"
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/peluqueria_3/Vistas/agenda.fxml")); // Recoge la vista
            Parent root = loader.load(); // Carga la vista

            // Obtener el Label de nombre_usuario en la vista "agenda"
            Label nombreUsuarioEnAgenda = (Label) root.lookup("#nombre_usuario"); //busca por el id

            // Establecer el nombre de usuario en el Label de la vista "agenda"
            if (nombreUsuarioEnAgenda != null) {
                nombreUsuarioEnAgenda.setText(input_usuario.getText());
            }

            // Cambiar la escena al nuevo escenario
            Scene scene = new Scene(root); // Añade la vista a la escena
            Stage stage = new Stage();
            stage.setScene(scene); // Añade la escena al escenario (stage)
            stage.show();

            Stage myStage = (Stage) BotonLogin.getScene().getWindow();  // Recoge la ventana en la que se encuentra el botón
            myStage.close();
        }
    }
}
