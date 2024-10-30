package com.example.peluqueria_3.Controllers;


import com.example.peluqueria_3.Models.Clientes;
import com.example.peluqueria_3.Models.ModeloClientes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class ClientesController {


    @FXML
    private TableView<Clientes> clientesTable;
    @FXML
    private TableColumn<Clientes, Integer> colId_Cliente;
    @FXML
    private TableColumn<Clientes, String> colNombre;
    @FXML
    private TableColumn<Clientes, String> colApellido;
    @FXML
    private TableColumn<Clientes, String> colCorreo;
    @FXML
    private TableColumn<Clientes, String> colTelefono;
    @FXML
    private TableColumn<Clientes, String> colObservaciones;
    @FXML
    private TableColumn<Clientes, Boolean> colLeyDatos;


    @FXML
    private TextField txtNombre, txtApellido, txtCorreo, txtTelefono, txtObservaciones;


    private ObservableList<Clientes> clientesList = FXCollections.observableArrayList();
    private ModeloClientes modeloClientes = new ModeloClientes();


    @FXML
    public void initialize() {
        // Configuración de columnas para que muestren los datos correctos de Clientes
        colId_Cliente.setCellValueFactory(new PropertyValueFactory<>("id_Cliente"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colObservaciones.setCellValueFactory(new PropertyValueFactory<>("observaciones"));
        colLeyDatos.setCellValueFactory(new PropertyValueFactory<>("ley_datos"));


        cargarClientes();  // Cargar los datos al inicializar
    }


    // Método para cargar clientes desde la base de datos
    private void cargarClientes() {
        clientesList.clear();
        clientesList.addAll(modeloClientes.cargarClientes());
        clientesTable.setItems(clientesList);
    }


    // Método para agregar un cliente
    @FXML
    private void handleAgregarCliente() {
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String correo = txtCorreo.getText();
        String telefono = txtTelefono.getText();
        String observaciones = txtObservaciones.getText();
        boolean leyDatos = true; // Puedes cambiar esto según la lógica de la aplicación


        modeloClientes.crearCliente(nombre, apellido, correo, telefono, observaciones, leyDatos);
        cargarClientes(); // Actualiza la tabla después de agregar
    }


    // Método para editar un cliente
    @FXML
    private void handleEditarCliente() {
        Clientes clienteSeleccionado = clientesTable.getSelectionModel().getSelectedItem();
        if (clienteSeleccionado != null) {
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            String correo = txtCorreo.getText();
            String telefono = txtTelefono.getText();
            String observaciones = txtObservaciones.getText();
            boolean leyDatos = true; // Ajustar según sea necesario


            modeloClientes.editarCliente(clienteSeleccionado.getId_cliente(), nombre, apellido, telefono, correo, observaciones, leyDatos);
            cargarClientes(); // Actualiza la tabla después de editar
        }
    }


    // Método para eliminar un cliente
    @FXML
    private void handleEliminarCliente() {
        Clientes clienteSeleccionado = clientesTable.getSelectionModel().getSelectedItem();
        if (clienteSeleccionado != null) {
            modeloClientes.eliminarCliente(clienteSeleccionado.getId_cliente());
            cargarClientes(); // Actualiza la tabla después de eliminar
        }
    }
}
