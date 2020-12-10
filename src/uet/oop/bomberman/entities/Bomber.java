package uet.oop.bomberman.entities;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends Entity {
    private int velocity = 2;
    private AnimationTimer leftBomberAT;
    private AnimationTimer rightBomberAT;
    private AnimationTimer upBomberAT;
    private AnimationTimer downBomerAT;
    private boolean visible = true;

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.clearRect(0, 0, Sprite.SCALED_SIZE * BombermanGame.getWIDTH(),
                Sprite.SCALED_SIZE * BombermanGame.getHEIGHT());
        gc.drawImage(img, x, y);
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
        render(BombermanGame.gcCharacter);
    }

    public AnimationTimer createAnimationTimer(String direction, GraphicsContext gc) {
        return new AnimationTimer() {
            boolean isFrame1 = true;
            long lastTime = 0;
            public void handle(long now) {
                switch (direction) {
                    case "left":
                        setX(getX() - velocity);
                        break;
                    case "right":
                        setX(getX() + velocity);
                        break;
                    case "up":
                        setY(getY() - velocity);
                        break;
                    case "down":
                        setY(getY() + velocity);
                        break;
                }
                if (now - lastTime > 200000000) {
                    setImageFrame(direction, isFrame1);
                    isFrame1 = !isFrame1;
                    lastTime = now;
                }
                render(gc);
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

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
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

}
