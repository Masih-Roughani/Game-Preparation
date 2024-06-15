package model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class Hero implements Runnable {
    private double speed;
    private String name;
    private ArrayList<String> imageAddresses = new ArrayList<>();
    private double destructionPower;

    public Hero() {
        this.speed = 50;
        this.name = "Warrior";
        this.destructionPower = 100;
        imageAddresses.add("/css/character.png");
    }

    @Override
    public void run() {
        try {
            wait();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
