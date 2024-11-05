package com.example.peluqueria_3.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;

public class LoadStage {
    LoadStage(String ventana, String nombre_ventana){
        try {
            // Cargar el archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ventana));
            Parent root = loader.load();

            // Crear un nuevo Stage
            Stage stage = DatosGlobales.getMystage();
            stage.setTitle(nombre_ventana);

            Image icon = new Image(getClass().getResourceAsStream("/img/logoIcon.png")); // Cambia la ruta a tu ícono
            stage.getIcons().add(icon);

            // Configurar la escena
            Scene scene = new Scene(root);
            stage.setScene(scene);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

