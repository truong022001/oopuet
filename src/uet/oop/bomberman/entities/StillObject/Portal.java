package uet.oop.bomberman.entities.StillObject;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Portal extends Obstacle {
    public Portal(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

    }

    @Override
    public Rectangle getCollisonShape() {
        collisionShape = new Rectangle(x * 32 + 2, y * 32 + 2, 28, 28);
        return collisionShape;
    }
}
