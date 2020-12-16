package uet.oop.bomberman.entities.Bomb;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Flame;
import uet.oop.bomberman.graphics.Sprite;

import java.util.List;

public abstract class Bomb extends Entity {
    protected static AnimationTimer bombAnimation;
    protected Flame flame, flameUp, flameDown, flameLeft, flameRight;
    protected Rectangle bombCollisionShape;
    protected List<Entity> bricksInBomb;

    public Bomb(int x, int y, Image img) {
        super(x, y, img);
        creatFlame();
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
        return new AnimationTimer() {
            boolean isFrame1 = true;
            long lastTime = 0;
            int count = 6;
            @Override
            public void handle(long now) {
                if (now - lastTime > 300000000) {
                    setBombImageFrame(isFrame1);
                    if (count <= 2) {
                        if (count == 2) {
                            addFlameOnGroup();
                        }
                        flameRender();
                        setFlameImageFrame(isFrame1);
                    }
                    isFrame1 = !isFrame1;
                    lastTime = now;
                    count--;
                }
                if (count <= -2) {
                    removeFlameFromGroup();
                    this.stop();
                }
                render();
            }
        };
    }

    public void setBombImageFrame(boolean isFrame1) {
        if (isFrame1) {
            img = Sprite.bomb_1.getFxImage();
        } else {
            img = Sprite.bomb_2.getFxImage();
        }
    }

    public abstract void flameRender();

    public abstract void setFlameImageFrame(boolean isFrame1);

    public abstract void creatFlame();

    public abstract void addFlameOnGroup();

    public abstract void removeFlameFromGroup();
}
