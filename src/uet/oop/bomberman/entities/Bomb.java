package uet.oop.bomberman.entities;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends Entity{
    private AnimationTimer bombAnimation;

    public Bomb(int x, int y, Image img) {
        super(x, y, img);
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

    public void bombStart() {

        bombAnimation=createBombAnimationTimer();
//        bombAnimation=createBombAnimationTimer();
//        root.getChildren().add(blackBomb.getImageView());
//        //root.getChildren().add(bomb.getImageView());
        bombAnimation.start();

    }

    public AnimationTimer createBombAnimationTimer() {
        return new AnimationTimer() {
            boolean isFrame1 = true;
            long  lastTime = 0;
            int count=0;
            @Override
            public void handle(long now) {
                if (now - lastTime > 500000000) {
                    setBombImageFrame(isFrame1);
                    isFrame1 = !isFrame1;
                    lastTime = now;
                    count++;
                }
                render();
            }

        };

    }
    private void setBombImageFrame( boolean isFrame1) {
        if (isFrame1) {
            img=Sprite.bomb_1.getFxImage();
        } else
        {
            img=Sprite.bomb_2.getFxImage();
        }
    }
}
