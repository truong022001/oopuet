package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

//Class chướng ngại vật
public abstract class Obstacle extends Entity {
    public Obstacle(int x, int y, Image img) {
        super(x, y, img);
    }
}
