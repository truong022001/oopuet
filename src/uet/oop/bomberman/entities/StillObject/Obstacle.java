package uet.oop.bomberman.entities.StillObject;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.entities.Entity;

public abstract class Obstacle extends Entity {
    protected Rectangle collisionShape = new Rectangle(x * 32, y * 32, 32, 32);

    public Obstacle(int x, int y, Image img) {
        super(x, y, img);
    }

    public Rectangle getCollisonShape() {
        //return new Rectangle(x * 32, y * 32, 32, 32);
        return collisionShape;
    }

    public void setCollisonShape(Rectangle shape) {
        collisionShape = shape;
    }
}
