package com.example.peluqueria_3;

import com.calendarfx.util.CalendarFX;
import com.example.peluqueria_3.Controllers.DatosGlobales;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Vistas/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 700);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
        DatosGlobales.setMystage(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}