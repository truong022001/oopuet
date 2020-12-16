package uet.oop.bomberman.entities.Character;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Collision.CheckItems;
import uet.oop.bomberman.entities.Bomb.Bomb;
import uet.oop.bomberman.entities.Bomb.NormalBomb;
import uet.oop.bomberman.entities.Bomb.PowerUpBomb;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.util.List;

public class Bomber extends Character {
    private long timePlaceBomb;
    private boolean isPowerUpBomb;
    private boolean isPowerUpSpeed;
    private boolean isPowerUpFlame;
    private Bomb bomb;
    private CheckItems checkItems;
    private AnimationTimer leftBomberAT;
    private AnimationTimer rightBomberAT;
    private AnimationTimer upBomberAT;
    private AnimationTimer downBomerAT;
    private AnimationTimer checkDead;
    private boolean isBombPlaced = false;

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
        velocity = 2;
        isPowerUpBomb = false;
        isPowerUpSpeed = false;
        isPowerUpFlame = false;
        timePlaceBomb = -2000;
        collisionShape = new Rectangle(x, y, 16, 24);
        leftBomberAT = createAnimationTimer("left");
        rightBomberAT = createAnimationTimer("right");
        upBomberAT = createAnimationTimer("up");
        downBomerAT = createAnimationTimer("down");
        checkDead = createCheckDeadAT();
        checkDead.start();
    }

    @Override
    public void update() {
    }

    public void move() {
        this.setX(getX() + velocityX);
        this.setY(getY() + velocityY);
        render();
    }

    public void keyPressed(KeyEvent e) {
        if (!dead) {
            switch (e.getCode()) {
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
                case SPACE:
                    if ((!isPowerUpBomb && System.currentTimeMillis() - timePlaceBomb >= 1800)
                            || (isPowerUpBomb && System.currentTimeMillis() - timePlaceBomb >= 500)) {
                        int xValue = (int) (Math.round(x * 1.0 / 32) * 32);
                        int yValue = (int) (Math.round(y * 1.0 / 32) * 32);
                        if (!isPowerUpBomb) {
                            bomb = new NormalBomb(xValue, yValue, Sprite.bomb.getFxImage());
                        } else {
                            bomb = new PowerUpBomb(xValue, yValue, Sprite.bomb.getFxImage());
                        }
                        timePlaceBomb = System.currentTimeMillis();
                        root.getChildren().add(bomb.getImageView());
                        bomb.bombStart();
                    }
                    break;
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        if (!dead) {
            switch (e.getCode()) {
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
                if (checkTouchWall.Touch(getBomber())) {
                    velocityX = 0;
                    velocityY = 0;
                }
                if (now - lastTime > 150000000) {
                    setImageFrame(direction, isFrame1);
                    isFrame1 = !isFrame1;
                    lastTime = now;
                }
                checkItems.Touch(getBomber());
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

    public void powerUp(String type) {
        switch (type) {
            case "speedPowerUp":
                isPowerUpSpeed = true;
                this.setVelocity(3);
                break;
            case "bombPowerUp":
                isPowerUpBomb = true;
                break;
            case "flamePowerUp":
                isPowerUpFlame = true;
                break;
            case "nothing":
                isPowerUpSpeed = false;
                isPowerUpFlame = false;
                isPowerUpBomb = false;
                this.setVelocity(2);
                break;
        }
    }

    public void die() {
        dead = true;
        setVelocity(0);
        leftBomberAT.stop();
        rightBomberAT.stop();
        upBomberAT.stop();
        downBomerAT.stop();
        checkDead.stop();
        new AnimationTimer() {
            long beginTime = System.currentTimeMillis();
            int count = 1;
            @Override
            public void handle(long now) {
                if (System.currentTimeMillis() - beginTime >= 400) {
                    count++;
                }
                if (count == 1) {
                    img = Sprite.player_dead1.getFxImage();
                } else if (count == 2) {
                    img = Sprite.player_dead2.getFxImage();
                } else if (count == 3) {
                    img = Sprite.player_dead3.getFxImage();
                } else if (count == 6){
                    playAgain();
                    stop();
                }
                render();
            }
        }.start();
    }

    public void playAgain() {
        setAgain();
        BombermanGame.clear();
        BombermanGame.loadLevel(1);
    }

    public void setAgain() {
        setVelocity(2);
        img = Sprite.player_right.getFxImage();
        isPowerUpBomb = false;
        isPowerUpSpeed = false;
        isPowerUpFlame = false;
        dead = false;
        checkDead.start();
    }

    public AnimationTimer createCheckDeadAT() {
        return new AnimationTimer() {
            @Override
            public void handle(long now) {
                characterTouch.touchEnemy(getBomber());
            }
        };
    }

    @Override
    public Rectangle getCollishionShape() {
        collisionShape.setX(x + 2 + velocityX);
        collisionShape.setY(y + 6 + velocityY);
        return collisionShape;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public void setCheckItems(List<Entity> stillObjects) {
        checkItems = new CheckItems();
        checkItems.createCheckItems(stillObjects);
    }

    public Bomber getBomber() {
        return this;
    }

    public CheckItems getCheckItems() {
        return checkItems;
    }
}
