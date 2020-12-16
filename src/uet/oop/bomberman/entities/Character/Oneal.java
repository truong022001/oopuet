package uet.oop.bomberman.entities.Character;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Oneal extends Enemy {
    private AnimationTimer randomVelocityTimer;

   public Oneal(int x, int y, Image img) {
        super(x, y, img);
        randomVelocityTimer = creatRandomVelocityTimer();
        randomVelocityTimer.start();
    }

    @Override
    public void setImageFrame(boolean isFrame1, String direction) {
        switch (direction) {
            case "left":
            case "up":
                if (isFrame1) {
                    img = Sprite.oneal_left3.getFxImage();
                } else {
                    this.img = Sprite.oneal_left2.getFxImage();
                }
                break;
            case "right":
            case "down":
                if (isFrame1) {
                    img = Sprite.oneal_right2.getFxImage();
                } else {
                    this.img = Sprite.oneal_right3.getFxImage();
                }
                break;
        }
    }

    public AnimationTimer creatRandomVelocityTimer() {
       return new AnimationTimer() {
           long lastTime = System.currentTimeMillis();
           double timeRandomVelocity = Math.random() * (4000 - 1000) + 1000;
           int velocityRandom;
           @Override
           public void handle(long now) {
                if (System.currentTimeMillis() - lastTime >= timeRandomVelocity) {
                    velocityRandom = (int) Math.round(Math.random() * (2 - 1) + 1);
                    velocity = velocityRandom;
                    lastTime = System.currentTimeMillis();
                    timeRandomVelocity = Math.random() * (4000 - 1000 + 1) + 1000;
                }
           }
       };
    }

    @Override
    public void setDieAnimation() {
        setImage(Sprite.oneal_dead.getFxImage());
        render();
    }
}


