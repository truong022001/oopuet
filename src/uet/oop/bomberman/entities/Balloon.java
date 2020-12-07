package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class Balloon extends Entity{
    private double velocity = 5;

    public Balloon(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
    }
}
