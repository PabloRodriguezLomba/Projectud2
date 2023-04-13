module com.example.proyectoud1pablorl {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.proyectoud1pablorl to javafx.fxml;
    exports com.example.proyectoud1pablorl;
}