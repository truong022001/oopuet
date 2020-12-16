package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Flame extends Entity{
    private Rectangle flameCollisionShape;

    public Flame(int x, int y, Image img) {
        super(x, y, img);
        flameCollisionShape = new Rectangle(x, y, 20, 20);
    }

    @Override
    public void update() {

    }
    @Override
    public void render() {
        imageView.setImage(img);
        imageView.setX(getX());
        imageView.setY(getY());
    }

    public Rectangle getCollishionShape() {
        flameCollisionShape.setX(x + 4);
        flameCollisionShape.setY(y + 4);
        return flameCollisionShape;
    }
}
