    package com.example.peluqueria_3.Controllers;

    import com.example.peluqueria_3.Models.Empleados;
    import com.example.peluqueria_3.Models.ModeloEmpleados;
    import javafx.fxml.FXML;
    import javafx.fxml.FXMLLoader;
    import javafx.scene.control.Button;
    import javafx.scene.control.PasswordField;
    import javafx.scene.control.TextField;
    import javafx.stage.Stage;

    public class EmpleadosController {

        @FXML private Button BotonLogin;
        @FXML private PasswordField input_contrasenya;
        @FXML private TextField input_usuario;



        private ModeloEmpleados modelo = new ModeloEmpleados();

        @FXML
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


    }

