package org.example.first;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import model.Hero;

import java.net.URL;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainPage implements Initializable {

    @FXML
    private Polyline battleGround;

    @FXML
    private ImageView characterIcon;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    void battleGroundClicked(MouseEvent event) {
        String path = Objects.requireNonNull(getClass().getResource("/css/character.png")).toExternalForm();
        ImageView imageView = new ImageView(new Image(path));
        imageView.setFitWidth(25);
        imageView.setFitHeight(25);
        imageView.setLayoutX(event.getSceneX());
        imageView.setLayoutY(event.getSceneY());
        anchorPane.getChildren().add(imageView);
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


