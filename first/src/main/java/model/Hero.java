package model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Hero {
    private double speed;
    private String name;
    private ArrayList<String> imageAddresses;
    private double destructionPower;

    public Hero(double speed, String name, ArrayList<String> imageAddresses, double destructionPower) {
        this.speed = speed;
        this.name = name;
        this.imageAddresses = imageAddresses;
        this.destructionPower = destructionPower;
    }
}
