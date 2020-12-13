package uet.oop.bomberman.entities;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import static javafx.scene.input.KeyCode.*;

public class Oneal extends Entity {
    private int velocity = 3;
    private String direction;
    private AnimationTimer leftOneal;
    private AnimationTimer rightOneal;
    private AnimationTimer upOneal;
    private AnimationTimer downOneal;
    private int velocityX;
    private int velocityY;
    private CheckTouchWall checkTouchWall;
    private boolean alive = true;
    private Rectangle bomberCollisionShape;
    public Oneal(int x, int y, Image img) {
        super(x, y, img);
        bomberCollisionShape = new Rectangle(x, y, 14, 22);
    }

    @Override
    public void setX(int x) {
        super.setX(x);
    }

    @Override
    public void setY(int y) {
        super.setY(y);
    }

    @Override
    public int getX() {
        return super.getX();
    }

    @Override
    public int getY() {
        return super.getY();
    }

    public void setCheckTouchWall(CheckTouchWall checkTouchWall) {
        this.checkTouchWall = checkTouchWall;
    }

    @Override
    public void update() {
        leftOneal=createAnimationTimer("left");
        rightOneal=createAnimationTimer("right");
        upOneal=createAnimationTimer("up");
        downOneal=createAnimationTimer("down");
        moveStart();
    }

    @Override
    public void render() {
        imageView.setImage(img);
        imageView.setX(getX());
        imageView.setY(getY());
    }

    public void moveStart() {
        direction = randomDirection();
        switch (direction) {
            case "left":
                leftOneal.start();
                break;
            case "right":
                rightOneal.start();
                break;
            case "up":
                upOneal.start();
                break;
            case "down":
                downOneal.start();
                break;
        }

    }
    public void move(){
        this.setX(getX() + velocityX);
        this.setY(getY() + velocityY);
        render();
    }
    public AnimationTimer createAnimationTimer(String dir) {
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
                    direction=randomDirection();
                }
                if (now - lastTime > 200000000) {
                    setImageFrame(isFrame1);
                    isFrame1 = !isFrame1;
                    lastTime = now;
                }
                move();
            }
        };
    }
    private void setImageFrame(boolean isFrame1) {
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
    private String randomDirection() {
        int r = 0 + (int) (Math.random() * ((3 - 0) + 1));
        if (r == 0) {
            return "up";
        } else if (r == 1) {
            return "down";
        } else if (r == 2) {
            return "left";
        } else {
            return "right";
        }
    }

    public AnimationTimer getDownOneal() {
        return downOneal;
    }

    public void setDownOneal(AnimationTimer downOneal) {
        this.downOneal = downOneal;
    }

    public void setLeftOneal(AnimationTimer leftOneal) {
        this.leftOneal = leftOneal;
    }

    public void setRightOneal(AnimationTimer rightOneal) {
        this.rightOneal = rightOneal;
    }

    public void setUpOneal(AnimationTimer upOneal) {
        this.upOneal = upOneal;
    }

    public Rectangle getCollishionShape() {
        bomberCollisionShape.setX(x + 4);
        bomberCollisionShape.setY(y + 4);
        return bomberCollisionShape;
    }
}


