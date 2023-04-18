module com.example.proyectoud1pablorl {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;


    opens com.example.proyectoud1pablorl to javafx.fxml;
    exports com.example.proyectoud1pablorl;
}