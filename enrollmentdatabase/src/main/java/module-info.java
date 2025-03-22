module com.example.enrollmentdatabase {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.enrollmentdatabase to javafx.fxml;
    exports com.example.enrollmentdatabase;
    exports com.example.enrollmentdatabase.model;
    opens com.example.enrollmentdatabase.model to javafx.fxml;
}