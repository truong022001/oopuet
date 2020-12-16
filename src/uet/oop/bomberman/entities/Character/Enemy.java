package uet.oop.bomberman.entities.Character;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Enemy extends Character {
    protected String direction;
    protected AnimationTimer realEnemy;
    protected boolean alive = true;

    public Enemy(int x, int y, Image img) {
        super(x, y, img);
        collisionShape = new Rectangle(x, y,28,28);
        velocity = 1;
    }

    @Override
    public void update() {
        realEnemy = createEnemyAnimationTimer();
        moveStart();
    }

    @Override
    public void die() {
        BombermanGame.getEntities().remove(getEnemy());
        BombermanGame.getCharacterTouch().getEnemys().remove(getEnemy());
        realEnemy.stop();
        velocity = 0;
        BombermanGame.setNumberOfEnemy(BombermanGame.getNumberOfEnemy() - 1);
        setDieAnimation();
        new AnimationTimer() {
            long beginTime = System.currentTimeMillis();
            @Override
            public void handle(long now) {
                if (System.currentTimeMillis() - beginTime >= 500) {
                    BombermanGame.getRoot().getChildren().remove(getImageView());
                    stop();
                }
            }
        }.start();
    }

    public void moveStart() {
        direction = randomDirection();
        realEnemy.start();
    }

    public void move(){
        this.setX(getX() + velocityX);
        this.setY(getY() + velocityY);
        render();
    }

    public AnimationTimer createEnemyAnimationTimer() {
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
                if (checkTouchWall.Touch(getEnemy())) {
                    velocityX = 0;
                    velocityY = 0;
                    direction = randomDirection();
                }
                if (characterTouch.touchEnemy(getEnemy())) {
                    velocityX = 0;
                    velocityY = 0;
                    direction = randomDirection();
                }
                if (now - lastTime > 200000000) {
                    setImageFrame(isFrame1, direction);
                    isFrame1 = !isFrame1;
                    lastTime = now;
                }
                move();
            }
        };
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

    @Override
    public Rectangle getCollishionShape() {
        collisionShape.setX(x + 2 + velocityX);
        collisionShape.setY(y + 2 + velocityY);
        return collisionShape;
    }

    public Enemy getEnemy() {
        return this;
    }

    public abstract void setImageFrame(boolean isFrame1, String direction);

    public abstract void setDieAnimation();
}
