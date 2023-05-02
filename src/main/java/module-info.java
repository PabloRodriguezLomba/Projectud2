module com.example.proyectoud1pablorl {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires java.sql;

    exports com.example.proyectoud1pablorl;
    opens com.example.proyectoud1pablorl to javafx.fxml;

    exports com.example.proyectoud1pablorl.Object;
    opens com.example.proyectoud1pablorl.Object to javafx.fxml;


}