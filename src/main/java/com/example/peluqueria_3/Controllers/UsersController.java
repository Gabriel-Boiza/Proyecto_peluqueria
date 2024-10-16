package com.example.peluqueria_3.Controllers;

import com.example.peluqueria_3.Models.DataBase;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class UsersController {

    @FXML private Button BotonLogin;
    @FXML public DataBase modelo = new DataBase();

    @FXML
    public void ValidarUser() throws IOException{

        modelo.getConnection();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/peluqueria_3/Vistas/agenda.fxml")); //recoge la vista
        Parent root = loader.load(); //carga la vista

        Scene scene = new Scene(root); //añade la vista a la escena
        Stage stage = new Stage();

        stage.setScene(scene); //añade la escena al escenario (stage)
        stage.show();

        Stage myStage = (Stage) BotonLogin.getScene().getWindow();  //recoge la ventana en la que se encuentra el boton cambiar
        myStage.close();
    }

}