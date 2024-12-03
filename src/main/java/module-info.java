module org.example.assignment2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens org.example.assignment2 to javafx.fxml, com.google.gson;
    exports org.example.assignment2;
}