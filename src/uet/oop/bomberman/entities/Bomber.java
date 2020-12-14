package uet.oop.bomberman.entities;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends Entity {
    private int velocity = 2;
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
        bomberCollisionShape = new Rectangle(x, y, 16, 24);
    }

    public void setCheckTouchWall(CheckTouchWall checkTouchWall) {
        this.checkTouchWall = checkTouchWall;
    }

    @Override
    public void update() {
        leftBomberAT = createAnimationTimer("left");
        rightBomberAT = createAnimationTimer("right");
        upBomberAT = createAnimationTimer("up");
        downBomerAT = createAnimationTimer("down");
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
                if (checkTouchWall.Touch(getCollishionShape())) {
                    System.out.println("Touch wall");
                    velocityX = 0;
                    velocityY = 0;
                }
                if (now - lastTime > 150000000) {
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

    public Rectangle getCollishionShape() {
        bomberCollisionShape.setX(x + 2 + velocityX);
        bomberCollisionShape.setY(y + 6 + velocityY);
        return bomberCollisionShape;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public int getVelocity() {
        return velocity;
    }
}
