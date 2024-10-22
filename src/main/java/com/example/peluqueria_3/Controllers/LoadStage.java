package com.example.peluqueria_3.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoadStage {

    LoadStage(String fxmlFilePath){
        try {
            // Cargar el archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFilePath));
            Parent root = loader.load();

            // Crear un nuevo Stage
            Stage stage = new Stage();
            stage.setTitle("Nueva Ventana");

            // Configurar la escena
            Scene scene = new Scene(root);
            stage.setScene(scene);

            // Mostrar la nueva ventana
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

