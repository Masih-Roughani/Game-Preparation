package model;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    public Hero() {
        this.speed = 50;
        this.name = "Warrior";
        this.destructionPower = 100;
        imageAddresses.add("/css/warrior1.png");
        imageAddresses.add("/css/warrior2.png");
        String warriorPath = Objects.requireNonNull(Objects.requireNonNull(getClass().getResource("/css/warrior1.png")).toExternalForm());
        warriorImageView = new ImageView(new Image(warriorPath));
    }

    @Override
    public void run() {
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    if (warriorImageView.getImage().getUrl().endsWith("warrior1.png")) {
                        Platform.runLater(() -> {
                            warriorImageView.setImage(new Image(Objects.requireNonNull(getClass().getResource(imageAddresses.get(1))).toExternalForm()));
                        });
                    } else {
                        Platform.runLater(() -> {
                            warriorImageView.setImage(new Image(Objects.requireNonNull(getClass().getResource(imageAddresses.getFirst())).toExternalForm()));
                        });
                    }
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }).start();
    }
}
