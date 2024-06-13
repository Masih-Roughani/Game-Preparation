module org.example.first {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.first to javafx.fxml;
    exports org.example.first;
}