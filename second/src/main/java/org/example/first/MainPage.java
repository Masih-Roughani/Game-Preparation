package org.example.first;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.effect.Bloom;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Polyline;
import model.Hero;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainPage implements Initializable {
    private ArrayList<Hero> heroes = new ArrayList<>();
    private final String buildingPath = "/css/warrior1.png";
    private int counter = 0;

    @FXML
    private ImageView firstBuilding;

    @FXML
    private Label startButton;

    @FXML
    private ImageView secondBuilding;

    @FXML
    private Polyline battleGround;

    @FXML
    private ImageView characterIcon;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    void startButtonClicked(MouseEvent event) {
        for (Hero hero : heroes) {
            new Thread(() -> {
                Platform.runLater(hero::run);
            }).start();
            new Thread(() -> {

            }).start();
        }
    }

    @FXML
    void battleGroundClicked(MouseEvent event) {
        if (counter == 10) {
            showLimitAlert();
        } else if (characterIcon.getEffect() != null) {
            heroes.add(new Hero());
            ;
            ImageView imageView = heroes.getLast().getWarriorImageView();
            imageView.setFitWidth(45);
            imageView.setFitHeight(65);
            imageView.setLayoutX(event.getSceneX());
            imageView.setLayoutY(event.getSceneY());
            anchorPane.getChildren().add(imageView);
            counter++;
        } else {
            showForceAlert();
        }
    }

    public void showLimitAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("There might be a problem!");
        alert.setTitle("Deployment Error!");
        alert.setContentText("You have deployed the maximum number of soldiers!");
        alert.showAndWait();
    }

    public void showForceAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("There might be a problem!");
        alert.setTitle("Deployment Error!");
        alert.setContentText("Please check if you have chosen the force that you want to use during the battle!");
        alert.showAndWait();
    }

    @FXML
    void characterClicked(MouseEvent event) {
        if (characterIcon.getEffect() == null) {
            characterIcon.setEffect(new Bloom());
        } else {
            characterIcon.setEffect(null);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        battleGround.setOpacity(0);
    }
}


