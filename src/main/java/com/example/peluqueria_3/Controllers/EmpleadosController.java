package com.example.peluqueria_3.Controllers;

import com.example.peluqueria_3.Models.Empleados;
import com.example.peluqueria_3.Models.ModeloEmpleados;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.w3c.dom.ls.LSOutput;

import javax.swing.text.html.ImageView;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class EmpleadosController {

    ModeloEmpleados modelo = new ModeloEmpleados();

    //Elementos vista login

    @FXML private PasswordField input_contrasenya;

    //Elementos vista CRUD empleados
    @FXML private Label welcome;

    @FXML private TableView<Empleados> empleadosTabla;
    @FXML private TableColumn<Empleados, String> ID;
    @FXML private TableColumn<Empleados, String> usuario;
    @FXML private TableColumn<Empleados, String> nombre;
    @FXML private TableColumn<Empleados, String> apellido;
    @FXML private TableColumn<Empleados, String> email;
    @FXML private TableColumn<Empleados, String> telefono;
    @FXML private TableColumn<Empleados, String> direccion;
    @FXML private TableColumn<Empleados, String> cventas;
    @FXML private TableColumn<Empleados, String> cservicios;
    @FXML private TableColumn<Empleados, String> l_cservicios;

    private ObservableList<Empleados> empleadosObervable;

    @FXML private TextField campo_id;
    @FXML private TextField campo_usuario;
    @FXML private TextField campo_nombre;
    @FXML private TextField campo_apellido;
    @FXML private TextField campo_correo;
    @FXML private TextField campo_telefono;
    @FXML private TextField campo_direccion;
    @FXML private TextField campo_cservicios;
    @FXML private TextField campo_cventas;
    @FXML private TextField campo_l_cservicios;

    @FXML private TextField buscador;

    @FXML private PasswordField campo_contrasenya;
    @FXML private ChoiceBox campo_rol;
    @FXML private ChoiceBox campo_estado;

    @FXML private Button boton_volver;
    @FXML private Button boton_crear;
    @FXML private Button boton_limpiar;
    @FXML private Button boton_modificar;
    @FXML private Button boton_eliminar;

    // Botones Nav
    @FXML private Button agenda;

    // Boton salir
    @FXML private Button salir;

    // Login Trabajadores
    @FXML Button volveragenda;
    @FXML ComboBox<String> listaUsuarios;
    @FXML PasswordField passwordTrabajador;
    @FXML Button entrarTrabajador;

    // Facturación Trabajador
    @FXML ComboBox<String> meses;
    @FXML ComboBox<String> ano;

    @FXML Label facturacionTrabajador;
    @FXML BarChart chartPane;
    @FXML Label totalDinero;
    @FXML Label totalProductos;
    @FXML Label totalServicios;
    @FXML Button volver_ficha_estadisticas;

    // Login Administradores
    @FXML ComboBox<String> listaAdministradores;
    @FXML PasswordField passwordAdministrador;
    @FXML Button entrarAdministrador;

    // Administración
    @FXML Label panelAdmin;
    @FXML Button adminTrabajadores;
    @FXML Button adminServicios;
    @FXML Button adminProductos;
    @FXML Button adminFacturacion;

    @FXML ComboBox<String> empleados;


    Empleados empleadoSeleccionado;

    // METODOS

    public void ValidarUser(){
        try{
            String username = "Administrador";
            ModeloEmpleados modeloEmpleado = new ModeloEmpleados();
            Empleados empleadoRegistrado = modeloEmpleado.validarEmpleado(username, input_contrasenya.getText());

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
        empleadosObervable = FXCollections.observableArrayList(empleados);

        empleadosTabla.setItems(empleadosObervable);
        ID.setCellValueFactory(new PropertyValueFactory<Empleados, String>("Id_empleado"));
        usuario.setCellValueFactory(new PropertyValueFactory<Empleados, String>("usuario"));
        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        apellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        email.setCellValueFactory(new PropertyValueFactory<>("correo"));
        telefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        direccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        cventas.setCellValueFactory(new PropertyValueFactory<>("comision_ventas"));
        cservicios.setCellValueFactory(new PropertyValueFactory<>("comision_servicios"));
        l_cservicios.setCellValueFactory(new PropertyValueFactory<>("limite_comision"));


    }

    public void rellenarInputs(Empleados empleadoSeleccionado){
        campo_id.setText(empleadoSeleccionado.getId_empleado());
        campo_usuario.setText(empleadoSeleccionado.getUsuario());
        campo_nombre.setText(empleadoSeleccionado.getNombre());
        campo_apellido.setText(empleadoSeleccionado.getApellido());
        campo_correo.setText(empleadoSeleccionado.getCorreo());
        campo_telefono.setText(empleadoSeleccionado.getTelefono());
        campo_direccion.setText(empleadoSeleccionado.getDireccion());
        campo_cservicios.setText(empleadoSeleccionado.getComision_servicios().toString() + "%");
        campo_cventas.setText(empleadoSeleccionado.getComision_ventas().toString() + "%");
        campo_l_cservicios.setText(empleadoSeleccionado.getLimite_comision().toString()+ "%");

        campo_contrasenya.setText(empleadoSeleccionado.getContrasenya());
        campo_rol.setValue(empleadoSeleccionado.getRol());
        campo_estado.setValue(empleadoSeleccionado.getEstado());

    }

    public void limpiarInputs(){
        campo_id.setText("");
        campo_usuario.setText("");
        campo_nombre.setText("");
        campo_apellido.setText("");
        campo_correo.setText("");
        campo_telefono.setText("");
        campo_direccion.setText("");
        campo_cservicios.setText("");
        campo_cventas.setText("");
        campo_l_cservicios.setText("");

        campo_contrasenya.setText("");
        campo_rol.setValue(null);
        campo_estado.setValue(null);

        empleadoSeleccionado = null;
    }

    public void filtroBuscador(TextField buscador, TableView<Empleados> tabla){
        FilteredList<Empleados> filtro = new FilteredList<>(empleadosObervable,p -> true);
        buscador.textProperty().addListener((observable, oldValue, newValue) ->{
            filtro.setPredicate(item ->{
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Filtrar por nombre o categoría
                String lowerCaseFilter = newValue.toLowerCase();
                return item.getNombre().toLowerCase().contains(lowerCaseFilter);
            });
        });
        SortedList<Empleados> sortedData = new SortedList<>(filtro);
        sortedData.comparatorProperty().bind(tabla.comparatorProperty());
        tabla.setItems(sortedData);
    }

    public void validarTrabajador(){
        try{
            String usuario = listaUsuarios.getValue();
            String password = passwordTrabajador.getText();

            if (!usuario.equals("Selecciona un Trabajador")){
                ModeloEmpleados empleado = new ModeloEmpleados();
                Empleados empleadoLogeado = empleado.validarEmpleado(usuario, password);

                if(empleadoLogeado != null){
                    DatosGlobales.setEmpleadoActual(empleadoLogeado);
                    LoadStage load = new LoadStage("/com/example/peluqueria_3/Vistas/fichaTrabajador.fxml", "Ficha Trabajador");
                }else{
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setTitle("Error");
                    alerta.setHeaderText("Contraseña incorrecta.");
                    alerta.showAndWait();
                }
            } else{
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Error");
                alerta.setHeaderText("Debes de sleccionar un Trabajador");
                alerta.showAndWait();
            }

        }catch (Exception e){
            System.out.println("Error al validar trabajador: " + e.getMessage());
        }
    }

    public void validarAdministrador(){
        try{
            String usuario = listaAdministradores.getValue();
            String password = passwordAdministrador.getText();

            if (!usuario.equals("Selecciona un Administrador")){
                ModeloEmpleados empleado = new ModeloEmpleados();
                Empleados empleadoLogeado = empleado.validarAdministrador(usuario, password);

                if(empleadoLogeado != null){
                    DatosGlobales.setEmpleadoActual(empleadoLogeado);
                    LoadStage load = new LoadStage("/com/example/peluqueria_3/Vistas/administracion.fxml", "Panel Administrador");
                }else{
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setTitle("Error");
                    alerta.setHeaderText("Contraseña incorrecta.");
                    alerta.showAndWait();
                }
            } else{
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Error");
                alerta.setHeaderText("Debes de sleccionar un Administrador");
                alerta.showAndWait();
            }

        }catch (Exception e){
            System.out.println("Error al validar trabajador: " + e.getMessage());
        }
    }

    public void  setFacturacionTrabajador(String dni, int mes, int anio){
        facturacionTrabajador.setText(DatosGlobales.getEmpleadoActual().getUsuario());

        int totalProd = modelo.contarProductos(dni, mes, anio);
        int totalServ = modelo.contarServicios(dni, mes, anio);

        int valorMax = totalServ + totalProd;


        NumberAxis yAxis = (NumberAxis) chartPane.getYAxis();
        yAxis.setLabel("Valores");
        yAxis.setTickUnit(10);
        yAxis.setLowerBound(0);
        yAxis.setUpperBound(valorMax * 10);
        yAxis.setAutoRanging(false);
        yAxis.setForceZeroInRange(true);

        CategoryAxis xAxis = (CategoryAxis) chartPane.getXAxis();
        xAxis.setLabel("Categorías");

        chartPane.setTitle("Comparación de Totales");

        XYChart.Series<String, Number> dataSeries = new XYChart.Series<>();
        dataSeries.setName("Estadisticas");

        dataSeries.getData().add(new XYChart.Data<>("Total Productos", totalProd));
        dataSeries.getData().add(new XYChart.Data<>("Total Servicios", totalServ));

        chartPane.getData().clear();
        chartPane.getData().add(dataSeries);

        totalProductos.setText(String.valueOf(totalProd));
        totalServicios.setText(String.valueOf(totalServ));

        ArrayList<Float> valores =  modelo.obtenerSumasCobros(DatosGlobales.getEmpleadoActual().getId_empleado(), mes, anio);
        float sum = 0.00f;
        for (Float valor : valores) {
            System.out.println(valor);
            sum += valor;
        }
        totalDinero.setText(String.valueOf(sum) + "€");
    }

    @FXML
    public void initialize(){
        //Inicializa la tabla
        if (empleadosTabla !=null){
            mostrarUsuarios();
            filtroBuscador(buscador, empleadosTabla);
            campo_rol.getItems().addAll("administrador", "empleado", "invitado");
            campo_estado.getItems().addAll("Activo", "Inactivo");

            // Establece el listener para actualizar empleadoSeleccionado al cambiar la selección
            empleadosTabla.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    empleadoSeleccionado = newValue; // Actualiza el empleadoSeleccionado aquí
                    rellenarInputs(empleadoSeleccionado); // Rellena los inputs
                }
            });

            //Botones
            boton_crear.setOnAction(event -> {
                if (empleadoSeleccionado == null){
                    try{
                        if(!modelo.empleadoExiste(campo_id.getText())){
                            Float cventas = Float.parseFloat(campo_cventas.getText().substring(0, campo_cventas.getText().length() - 1));
                            Float cservicios = Float.parseFloat(campo_cservicios.getText().substring(0, campo_cservicios.getText().length() - 1));
                            Float lcservicios = Float.parseFloat(campo_l_cservicios.getText().substring(0, campo_l_cservicios.getText().length() - 1));
                            modelo.crearEmpleado(campo_id.getText(), campo_usuario.getText(), campo_nombre.getText(), campo_apellido.getText(), campo_correo.getText(), campo_contrasenya.getText(), campo_telefono.getText(), campo_direccion.getText(), cventas, cservicios, lcservicios, campo_rol.getValue().toString(), campo_estado.getValue().toString());
                            mostrarUsuarios();
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setContentText("Usuario registrado correctamente");
                            alert.showAndWait();
                        }
                        else{
                            Alert alerta = new Alert(Alert.AlertType.ERROR);
                            alerta.setContentText("Usuario ya existente");
                            alerta.showAndWait();
                        }
                    }
                    catch (Exception e){
                        Alert alert = new Alert(Alert.AlertType.WARNING );
                        alert.setContentText(e.getMessage());
                        alert.showAndWait();
                    }
                }

            });
            boton_limpiar.setOnAction(event -> {
                limpiarInputs();
            });

            boton_modificar.setOnAction(event ->{
                if(empleadoSeleccionado != null){
                    Float cventas = Float.parseFloat(campo_cventas.getText().substring(0, campo_cventas.getText().length() - 1));
                    Float cservicios = Float.parseFloat(campo_cservicios.getText().substring(0, campo_cservicios.getText().length() - 1));
                    Float lcservicios = Float.parseFloat(campo_l_cservicios.getText().substring(0, campo_l_cservicios.getText().length() - 1));

                    modelo.editarEmpleado(campo_id.getText(), campo_usuario.getText(), campo_nombre.getText(), campo_apellido.getText(), campo_correo.getText(), campo_contrasenya.getText(), campo_telefono.getText(), campo_direccion.getText(), cventas, cservicios, lcservicios, campo_rol.getValue().toString(), campo_estado.getValue().toString());
                    mostrarUsuarios();
                }
            });
            boton_eliminar.setOnAction(event -> {
                if(empleadoSeleccionado != null){
                    modelo.eliminarEmpleado(empleadoSeleccionado.getId_empleado());
                    empleadosObervable.remove(empleadoSeleccionado);
                }

            });

            boton_volver.setOnAction(event ->{
                LoadStage load = new LoadStage("/com/example/peluqueria_3/Vistas/personal.fxml", "Agenda");
            });
            agenda.setOnAction(event ->{
                LoadStage load = new LoadStage("/com/example/peluqueria_3/Vistas/personal.fxml", "Agenda");
            });

            salir.setOnAction(event ->{
                LoadStage load = new LoadStage("/com/example/peluqueria_3/Vistas/login.fxml", "Agenda");
            });
        }

        if(listaUsuarios != null){
            volveragenda.setOnAction(event->{
                LoadStage load = new LoadStage("/com/example/peluqueria_3/Vistas/agenda.fxml", "Agenda");
            });

            try{
                ModeloEmpleados modelo = new ModeloEmpleados();
                ArrayList<Empleados> empleados = modelo.mostrarEmpleados();
                listaUsuarios.setValue("Selecciona un Trabajador");

                for (Empleados empleado : empleados) {
                    if(!empleado.getUsuario().equals("Administrador") && empleado.getEstado().equals("Activo") && empleado.getRol().equals("empleado")){
                        listaUsuarios.getItems().add(empleado.getUsuario());
                    }

                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        if (listaAdministradores != null){
            volveragenda.setOnAction(event->{
                LoadStage load = new LoadStage("/com/example/peluqueria_3/Vistas/agenda.fxml", "Agenda");
            });

            try{
                ModeloEmpleados modelo = new ModeloEmpleados();
                ArrayList<Empleados> empleados = modelo.mostrarEmpleados();
                listaAdministradores.setValue("Selecciona un Administrador");

                for (Empleados empleado : empleados) {
                    if(!empleado.getUsuario().equals("Administrador") && empleado.getRol().equals("administrador") && empleado.getEstado().equals("Activo")){
                        listaAdministradores.getItems().add(empleado.getUsuario());
                    }

                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        if (panelAdmin != null){

            adminTrabajadores.setOnAction(event ->{
                LoadStage load = new LoadStage("/com/example/peluqueria_3/Vistas/empleados.fxml", "Trabajadores");
            });

            adminProductos.setOnAction(event->{
                LoadStage load = new LoadStage("/com/example/peluqueria_3/Vistas/productos.fxml", "Productos");
            });

            adminServicios.setOnAction(event->{
                LoadStage load = new LoadStage("/com/example/peluqueria_3/Vistas/servicios.fxml", "Servicios");
            });

            adminFacturacion.setOnAction(event->{
                LoadStage load = new LoadStage("/com/example/peluqueria_3/Vistas/fichaTrabajador.fxml", "Facturación");
            });
        }

        if (meses != null){
            String primerDni = DatosGlobales.getEmpleadoActual().getId_empleado();
            Map<String, Integer> mesesMap = new LinkedHashMap<>();
            mesesMap.put("Enero", 1);
            mesesMap.put("Febrero", 2);
            mesesMap.put("Marzo", 3);
            mesesMap.put("Abril", 4);
            mesesMap.put("Mayo", 5);
            mesesMap.put("Junio", 6);
            mesesMap.put("Julio", 7);
            mesesMap.put("Agosto", 8);
            mesesMap.put("Septiembre", 9);
            mesesMap.put("Octubre", 10);
            mesesMap.put("Noviembre", 11);
            mesesMap.put("Diciembre", 12);

            for (String mes : mesesMap.keySet()) {
                meses.getItems().add(mes);
            }

            LocalDate hoy = LocalDate.now();
            int mesActualNumero = hoy.getMonthValue();
            int anoActual = hoy.getYear();
            String mesActualNombre = null;

            for (Map.Entry<String, Integer> entry : mesesMap.entrySet()) {
                if (entry.getValue() == mesActualNumero) {
                    mesActualNombre = entry.getKey();
                }
            }

            if (mesActualNombre != null) {
                meses.setValue(mesActualNombre);
            }

            ArrayList<Integer> anios = modelo.obtenerAnios();


            for (Integer anio : anios) {
                ano.getItems().add(String.valueOf(anio));
            }

            if (!anios.isEmpty()) {
                ano.setValue(String.valueOf(anios.get(0)));
            }

            if (empleados != null){
                if(DatosGlobales.getEmpleadoActual().getRol().equals("administrador")){
                    empleados.setVisible(true);

                    ModeloEmpleados modelo = new ModeloEmpleados();
                    ArrayList<Empleados> empleadosArray = modelo.mostrarEmpleados();
                    empleados.setValue("Selecciona un Trabajador");

                    for (Empleados empleado : empleadosArray) {
                        if(!empleado.getUsuario().equals("Administrador") && empleado.getEstado().equals("Activo")){
                            empleados.getItems().add(empleado.getUsuario());
                        }

                    }
                    primerDni = empleados.getValue();
                }else{
                    empleados.setVisible(false);
                }
            }


            setFacturacionTrabajador(primerDni, mesActualNumero, anoActual);

            meses.valueProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null && ano.getValue() != null) {
                    String dni;
                    if (DatosGlobales.getEmpleadoActual().getRol().equals("administrador")){
                        dni = modelo.obtenerEmpleado(empleados.getValue());
                    }else{
                        dni = DatosGlobales.getEmpleadoActual().getId_empleado();
                    }

                    int mesSeleccionado = mesesMap.get(newValue);
                    int anioSeleccionado = Integer.valueOf(ano.getValue());

                    setFacturacionTrabajador(dni, mesSeleccionado, anioSeleccionado);
                    System.out.println("Mes seleccionado: " + newValue + " - Número: " + mesSeleccionado);
                    System.out.println("Año seleccionado: " + anioSeleccionado);
                }
            });

            ano.valueProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null && meses.getValue() != null) {
                    String dni;
                    if (DatosGlobales.getEmpleadoActual().getRol().equals("administrador")){
                        dni = modelo.obtenerEmpleado(empleados.getValue());
                    }else{
                        dni = DatosGlobales.getEmpleadoActual().getId_empleado();
                    }

                    int mesSeleccionado = mesesMap.get(meses.getValue());
                    int anioSeleccionado = Integer.valueOf(newValue);

                    setFacturacionTrabajador(dni, mesSeleccionado, anioSeleccionado);
                    System.out.println("Mes seleccionado: " + meses.getValue() + " - Número: " + mesSeleccionado);
                    System.out.println("Año seleccionado: " + anioSeleccionado);
                }
            });

            empleados.valueProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null && empleados.getValue() != null) {

                    String dni;
                    if (DatosGlobales.getEmpleadoActual().getRol().equals("administrador")){
                        ModeloEmpleados modelo = new ModeloEmpleados();
                        dni = modelo.obtenerEmpleado(empleados.getValue());
                    }else{
                        dni = DatosGlobales.getEmpleadoActual().getId_empleado();
                    }

                    int mesSeleccionado = mesesMap.get(meses.getValue());
                    int anioSeleccionado = Integer.valueOf(ano.getValue());

                    setFacturacionTrabajador(dni, mesSeleccionado, anioSeleccionado);
                    System.out.println("Mes seleccionado: " + meses.getValue() + " - Número: " + mesSeleccionado);
                    System.out.println("Año seleccionado: " + anioSeleccionado);
                }
            });

            volver_ficha_estadisticas.setOnAction(actionEvent -> {
                LoadStage loadStage = new LoadStage("/com/example/peluqueria_3/Vistas/loginTrabajador.fxml", "Agenda");
            });
        }
    }
}
