package uet.oop.bomberman.entities;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends Entity {
    private static AnimationTimer bombAnimation;
    private Flame flame,flameUp,flameDown,flameLeft,flameRight;
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
        bombAnimation = createBombAnimationTimer();
        bombAnimation.start();
    }

    public AnimationTimer createBombAnimationTimer() {
        flame=new Flame(x,y,Sprite.bomb_exploded.getFxImage());
        flameDown=new Flame(x,y+Sprite.SCALED_SIZE,Sprite.explosion_vertical_down_last.getFxImage());
        flameUp=new Flame(x,y-Sprite.SCALED_SIZE,Sprite.explosion_vertical_top_last.getFxImage());
        flameLeft=new Flame(x-Sprite.SCALED_SIZE,y,Sprite.explosion_horizontal_left_last.getFxImage());
        flameRight=new Flame(x+Sprite.SCALED_SIZE,y,Sprite.explosion_horizontal_right_last.getFxImage());
        BombermanGame.getRoot().getChildren().add(flame.getImageView());
        BombermanGame.getRoot().getChildren().add(flameUp.getImageView());
        BombermanGame.getRoot().getChildren().add(flameDown.getImageView());
        BombermanGame.getRoot().getChildren().add(flameLeft.getImageView());
        BombermanGame.getRoot().getChildren().add(flameRight.getImageView());
        return new AnimationTimer() {
            boolean isFrame1 = true;
            long lastTime = 0;
            int count = 6;
            @Override
            public void handle(long now) {
                if (now - lastTime > 500000000) {
                    setBombImageFrame(isFrame1);
                    if (count<=2) {
                        setFlameImageFrame(isFrame1);
                    }
                    isFrame1 = !isFrame1;
                    lastTime = now;
                    count--;
                }
                if (count <= 2) {
                    BombermanGame.getRoot().getChildren().remove(getImageView());
                    flame.render();
                    flameUp.render();
                    flameDown.render();
                    flameLeft.render();
                    flameRight.render();
                }
                if (count<=-1) {
                    BombermanGame.getRoot().getChildren().remove(flame.getImageView());
                    BombermanGame.getRoot().getChildren().remove(flameUp.getImageView());
                    BombermanGame.getRoot().getChildren().remove(flameDown.getImageView());
                    BombermanGame.getRoot().getChildren().remove(flameLeft.getImageView());
                    BombermanGame.getRoot().getChildren().remove(flameRight.getImageView());
                    this.stop();
                }
                render();
            }
        };
    }

    private void setBombImageFrame(boolean isFrame1) {
        if (isFrame1) {
            img = Sprite.bomb_1.getFxImage();
        } else {
            img = Sprite.bomb_2.getFxImage();
        }
    }
    private void setFlameImageFrame(boolean isFrame1) {
        if (isFrame1) {
            flame.img=Sprite.bomb_exploded1.getFxImage();
            flameUp.img=Sprite.explosion_vertical_top_last1.getFxImage();
            flameDown.img=Sprite.explosion_vertical_down_last1.getFxImage();
            flameRight.img=Sprite.explosion_horizontal_right_last1.getFxImage();
            flameLeft.img=Sprite.explosion_horizontal_left_last1.getFxImage();
        } else
        {
            flame.img=Sprite.bomb_exploded2.getFxImage();
            flameUp.img=Sprite.explosion_vertical_top_last2.getFxImage();
            flameDown.img=Sprite.explosion_vertical_down_last2.getFxImage();
            flameRight.img=Sprite.explosion_horizontal_right_last2.getFxImage();
            flameLeft.img=Sprite.explosion_horizontal_left_last2.getFxImage();
        }
    }
}
