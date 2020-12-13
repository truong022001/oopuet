package uet.oop.bomberman.entities;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.graphics.Sprite;

public class Balloon extends Entity {
    private int velocity = 3;
    private String direction;
    private AnimationTimer realBalloon;
    private int velocityX;
    private int velocityY;
    private CheckTouchWall checkTouchWall=new CheckTouchWall();
    private boolean alive = true;
    private Rectangle bomberCollisionShape;

    public Balloon(int x, int y, Image img) {
        super(x, y, img);
        bomberCollisionShape= new Rectangle(x,y,14,22);
    }

    public void setCheckTouchWall(CheckTouchWall checkTouchWall) {
        this.checkTouchWall = checkTouchWall;
    }

    @Override
    public void update() {
        realBalloon=createBalloonAnimationTimer();
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
        realBalloon.start();
    }
        public void move(){
            this.setX(getX() + velocityX);
            this.setY(getY() + velocityY);
            render();
        }
        public AnimationTimer createBalloonAnimationTimer() {
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
        public Rectangle getCollishionShape() {
            bomberCollisionShape.setX(x + 4);
            bomberCollisionShape.setY(y + 2);
            return bomberCollisionShape;
        }
}