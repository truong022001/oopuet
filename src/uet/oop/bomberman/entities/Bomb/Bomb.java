package uet.oop.bomberman.entities.Bomb;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Character.Character;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Flame;
import uet.oop.bomberman.entities.StillObject.Brick;
import uet.oop.bomberman.entities.StillObject.Obstacle;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public abstract class Bomb extends Entity {
    protected static AnimationTimer bombAnimation;
    protected Flame flame, flameUp, flameDown, flameLeft, flameRight;
    protected Rectangle bombCollisionShape;
    protected List<Entity> bricksInBomb;
    protected List<Entity> brickExploded = new ArrayList<>();
    protected List<Flame> flameAddInGroup = new ArrayList<>();
    protected List<Obstacle> canStopFlame;

    public Bomb(int x, int y, Image img) {
        super(x, y, img);
        bombCollisionShape = new Rectangle(x, y, 24, 24);
        creatFlame();
        bricksInBomb = BombermanGame.getBricks();
        canStopFlame = BombermanGame.getCheckTouchWall().getObstacles();
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
            int count = 8;
            @Override
            public void handle(long now) {
                if (now - lastTime > 250000000) {
                    setBombImageFrame(isFrame1);
                    if (count <= 4) {
                        if (count == 2) {
                            addFlameOnGroup();
                            checkBrick();
                            checkCharacter();
                        }
                        flameRender();
                        setFlameImageFrame(isFrame1);
                        for (Entity i:brickExploded) {
                            setBrickExplodedFrame((Brick) i, isFrame1);
                            i.render();
                        }
                    }
                    isFrame1 = !isFrame1;
                    lastTime = now;
                    count--;
                }
                if (count <= 0) {
                    removeFlameFromGroup();
                    for (Entity i:brickExploded) {
                        BombermanGame.getRoot().getChildren().remove(i.getImageView());
                        BombermanGame.getBricks().remove(i);
                        BombermanGame.getStillObjects().remove(i);
                        BombermanGame.getCheckTouchWall().getObstacles().remove(i);
                        Rectangle emptyRectangle = new Rectangle(0,0,0,0);
                        ((Brick) i).setCollisonShape(emptyRectangle);
                        i.render();
                    }
                    this.stop();
                }
                render();
            }
        };
    }

    public abstract void checkBrick();

    public void setBrickExplodedFrame(Brick brick,boolean isFrame1) {
        if (isFrame1) {
            brick.setImage(Sprite.brick_exploded1.getFxImage());
        } else {
            brick.setImage(Sprite.brick_exploded2.getFxImage());
        }
    }

    public static boolean isTouched(Rectangle collisionShape1, Rectangle collisionShape2) {
        if (collisionShape1.getBoundsInParent().intersects(collisionShape2.getBoundsInParent())) {
            return true;
        } else return false;
    }

    public void setBombImageFrame(boolean isFrame1) {
        if (isFrame1) {
            img = Sprite.bomb_1.getFxImage();
        } else {
            img = Sprite.bomb_2.getFxImage();
        }
    }

    public void checkCharacter() {
        List<Entity> character = BombermanGame.getEntities();
        for (int i = 0; i < character.size(); i++) {
            for (int j = 0; j < flameAddInGroup.size(); j++) {
                if (character.get(i) instanceof Character) {
                    if (isTouched(((Character) character.get(i)).getCollishionShape(),
                            flameAddInGroup.get(j).getCollishionShape())) {
                        ((Character) character.get(i)).die();
                    }
                }
            }
        }
    }

    public abstract void flameRender();

    public abstract void setFlameImageFrame(boolean isFrame1);

    public abstract void creatFlame();

    public abstract void addFlameOnGroup();

    public abstract void removeFlameFromGroup();

    public Rectangle getBombCollisionShape() {
        return bombCollisionShape;
    }
}
