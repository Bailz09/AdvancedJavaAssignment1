module com.example.advancedjavaassignment1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.advancedjavaassignment1 to javafx.fxml;
    exports com.example.advancedjavaassignment1;
}