package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Entity {
    protected int x;
    protected int y;
    protected Image img;
    protected ImageView imageView;

    public Entity( int x, int y, Image img) {
        this.x = x;
        this.y = y;
        this.img = img;
        this.imageView = new ImageView(img);
    }

    public void render() {
        imageView.setImage(img);
        imageView.setX(getX() * Sprite.SCALED_SIZE);
        imageView.setY(getY() * Sprite.SCALED_SIZE);
    }

    public abstract void update();


    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ImageView getImageView() {
        return imageView;
    }
}
