module org.example.first {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;


    opens org.example.first to javafx.fxml;
    exports org.example.first;
}