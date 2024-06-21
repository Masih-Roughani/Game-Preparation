package model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Objects;

@Getter
@Setter
public class Hero implements Runnable {
    private double speed;
    private String name;
    private ArrayList<String> imageAddresses = new ArrayList<>();
    private double destructionPower;
    private ImageView warriorImageView;
    private boolean value = true;

    public Hero() {
        this.speed = 50;
        this.name = "Warrior";
        this.destructionPower = 100;
        imageAddresses.add("/css/warrior1.png");
        imageAddresses.add("/css/warrior2.png");
        String warriorPath = Objects.requireNonNull(Objects.requireNonNull(getClass().getResource("/css/warrior1.png")).toExternalForm());
        warriorImageView = new ImageView(new Image(warriorPath));
    }

    public void run() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            if (value) {
                String currentImageUrl = warriorImageView.getImage().getUrl();
                String newImageUrl;
                if (currentImageUrl.endsWith("warrior1.png")) {
                    newImageUrl = Objects.requireNonNull(getClass().getResource(imageAddresses.get(1))).toExternalForm();
                } else {
                    newImageUrl = Objects.requireNonNull(getClass().getResource(imageAddresses.getFirst())).toExternalForm();
                }
                warriorImageView.setImage(new Image(newImageUrl));
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}
