package uet.oop.bomberman.entities.Character;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Balloon extends Enemy {

    public Balloon(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void setImageFrame(boolean isFrame1, String direction) {
        switch (direction) {
            case "left":
            case "up":
                if (isFrame1) {
                    img = Sprite.balloom_left2.getFxImage();
                } else {
                    this.img = Sprite.balloom_left3.getFxImage();
                }
                break;
            case "right":
            case "down":
                if (isFrame1) {
                    img = Sprite.balloom_right2.getFxImage();
                } else {
                    this.img = Sprite.balloom_right3.getFxImage();
                }
                break;
        }
    }
}