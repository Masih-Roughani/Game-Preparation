package org.example.first;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class FirstPage {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}