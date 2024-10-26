module com.example.peluqueria_3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires static lombok;

    // Abre el paquete Models a javafx.base para acceder a las propiedades de las clases
    opens com.example.peluqueria_3.Models to javafx.base;

    // Abrir otros paquetes necesarios
    opens com.example.peluqueria_3 to javafx.fxml;
    opens com.example.peluqueria_3.Controllers to javafx.fxml;

    exports com.example.peluqueria_3;
    exports com.example.peluqueria_3.Controllers;
}