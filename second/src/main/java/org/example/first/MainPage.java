package org.example.first;

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
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
import javafx.scene.transform.Translate;
import javafx.util.Duration;
import lombok.Synchronized;
import model.Hero;

import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.jar.Manifest;

public class MainPage implements Initializable {
    private ArrayList<Hero> heroes = new ArrayList<>();
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
        new Thread(() -> {
            for (Hero hero : heroes) {
                new Thread(() -> {
                    Platform.runLater(hero);
                    transition(firstBuilding, hero);
                }).start();
            }
            sleepThread(6000);
            setValueFalse();
            sleepThread(5000);
            new Thread(() -> {
                for (Hero hero : heroes) {
                    transition(secondBuilding, hero);
                }
            }).start();
            sleepThread(6000);
            setValueFalse();
            sleepThread(5000);
        }).start();
    }

    public void setValueFalse(){
        new Thread(() -> {
            for (Hero hero : heroes) {
                Platform.runLater(() -> {
                    hero.setValue(false);
                });
            }
        }).start();
    }

    public void sleepThread(int duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public void transition(ImageView building, Hero hero) {
        Platform.runLater(() -> {
            hero.setValue(true);
            TranslateTransition transition = new TranslateTransition(Duration.millis(6000));
            transition.setNode(hero.getWarriorImageView());
            transition.setToX(building.getLayoutX() + 65 - hero.getWarriorImageView().getLayoutX());
            transition.setToY(building.getLayoutY() + 65 - hero.getWarriorImageView().getLayoutY());
            transition.playFromStart();
        });
    }

    @FXML
    void battleGroundClicked(MouseEvent event) {
        if (counter == 10) {
            showLimitAlert();
        } else if (characterIcon.getEffect() != null) {
            heroes.add(new Hero());
            ImageView imageView = heroes.getLast().getWarriorImageView();
            imageView.setFitWidth(25);
            imageView.setFitHeight(37);
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


