package uet.oop.bomberman.entities;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.graphics.Sprite;

import java.util.List;

public class Bomber extends Entity {
    private final int velocity = 2;
    private int velocityX;
    private int velocityY;
    private CheckTouchWall checkTouchWall;
    private AnimationTimer leftBomberAT;
    private AnimationTimer rightBomberAT;
    private AnimationTimer upBomberAT;
    private AnimationTimer downBomerAT;
    private Rectangle bomberCollisionShape;

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
        bomberCollisionShape = new Rectangle(x, y, 14, 22);
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

    public void move() {
        this.setX(getX() + velocityX);
        this.setY(getY() + velocityY);
        render();
    }

    public void keyPressed(KeyEvent e) {
        switch(e.getCode()) {
            case RIGHT:
                this.rightBomberAT.start();
                break;
            case LEFT:
                this.leftBomberAT.start();
                break;
            case UP:
                this.upBomberAT.start();
                break;
            case DOWN:
                this.downBomerAT.start();
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        switch(e.getCode()) {
            case RIGHT:
                this.rightBomberAT.stop();
                this.img = Sprite.player_right.getFxImage();
                break;
            case LEFT:
                this.leftBomberAT.stop();
                this.img = Sprite.player_left.getFxImage();
                break;
            case UP:
                this.upBomberAT.stop();
                this.img = Sprite.player_up.getFxImage();
                break;
            case DOWN:
                this.downBomerAT.stop();
                this.img = Sprite.player_down.getFxImage();
                break;
        }
        render();
    }

    public AnimationTimer createAnimationTimer(String direction) {
        return new AnimationTimer() {
            boolean isFrame1 = true;
            long lastTime = 0;
            public void handle(long now) {
                if (!checkTouchWall.Touch(getCollishionShape())) {
                    switch (direction) {
                        case "left":
                            velocityX = -velocity;
                            velocityY = 0;
                            break;
                        case "right":
                            velocityX = velocity;
                            velocityY = 0;
                            break;
                        case "up":
                            velocityX = 0;
                            velocityY = -velocity;
                            break;
                        case "down":
                            velocityX = 0;
                            velocityY = velocity;
                            break;
                    }
                } else {
                    System.out.println("Touch wall");
                    velocityX *= -1;
                    velocityY *= -1;
                }
                if (now - lastTime > 200000000) {
                    setImageFrame(direction, isFrame1);
                    isFrame1 = !isFrame1;
                    lastTime = now;
                }
                move();
            }
        };
    }

    private void setImageFrame(String direction, boolean isFrame1) {
        switch (direction) {
            case "left":
                if (isFrame1) {
                    img = Sprite.player_left_1.getFxImage();
                } else {
                    this.img = Sprite.player_left_2.getFxImage();
                }
                break;
            case "right":
                if (isFrame1) {
                    img = Sprite.player_right_1.getFxImage();
                } else {
                    this.img = Sprite.player_right_2.getFxImage();
                }
                break;
            case "up":
                if (isFrame1) {
                    img = Sprite.player_up_1.getFxImage();
                } else {
                    this.img = Sprite.player_up_2.getFxImage();
                }
                break;
            case "down":
                if (isFrame1) {
                    img = Sprite.player_down_1.getFxImage();
                } else {
                    this.img = Sprite.player_down_2.getFxImage();
                }
                break;
        }
    }

    public void createCheckTouchWall(List<Entity> stillObjects) {
        checkTouchWall = new CheckTouchWall();
        for (int i = 0; i < stillObjects.size(); i++) {
            Entity object = stillObjects.get(i);
            if (object instanceof Wall) {
                Wall obj = (Wall)object;
                checkTouchWall.addObstacle(obj);
            }
            if (object instanceof Brick) {
                Brick obj1 = (Brick)object;
                checkTouchWall.addObstacle(obj1);
            }
        }
    }

    public int getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }

    public int getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }

    public void setDownBomerAT(AnimationTimer downBomerAT) {
        this.downBomerAT = downBomerAT;
    }

    public void setUpBomberAT(AnimationTimer upBomberAT) {
        this.upBomberAT = upBomberAT;
    }

    public void setLeftBomberAT(AnimationTimer leftBomberAT) {
        this.leftBomberAT = leftBomberAT;
    }

    public void setRightBomberAT(AnimationTimer rightBomberAT) {
        this.rightBomberAT = rightBomberAT;
    }

    public Rectangle getCollishionShape() {
        bomberCollisionShape.setX(x + 4);
        bomberCollisionShape.setY(y + 2);
        return bomberCollisionShape;
    }

}
