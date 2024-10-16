module com.example.peluqueria_3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    // require java.sql;


    opens com.example.peluqueria_3 to javafx.fxml;
    exports com.example.peluqueria_3;
    exports com.example.peluqueria_3.Controllers;
    opens com.example.peluqueria_3.Controllers to javafx.fxml;
}