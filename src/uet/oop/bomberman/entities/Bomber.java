package uet.oop.bomberman.entities;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends Entity {
    private int velocity = 3;
    private AnimationTimer leftBomberAT;
    private AnimationTimer rightBomberAT;
    private AnimationTimer upBomberAT;
    private AnimationTimer downBomerAT;
    private boolean visible;

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
    }

    @Override
    public void render(GraphicsContext gc) {
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        ImageView iv = new ImageView(img);
        Image base = iv.snapshot(params, null);
        gc.drawImage(base, x, y);
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

    public void keyPressed(KeyEvent e) {
        KeyCode keyCode = e.getCode();
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
                break;
            case LEFT:
                this.leftBomberAT.stop();
                break;
            case UP:
                this.upBomberAT.stop();
                break;
            case DOWN:
                this.downBomerAT.stop();
                break;
        }
    }

    public AnimationTimer createAnimationTimer(String direction, GraphicsContext gc) {
        return new AnimationTimer() {
            public void handle(long now) {
                switch (direction) {
                    case "left":
                        setX(getX() - velocity);
                        render(gc);
                        break;
                    case "right":
                        setX(getX() + velocity);
                        render(gc);
                        break;
                    case "up":
                        setY(getY() - velocity);
                        render(gc);
                        break;
                    case "down":
                        setY(getY() + velocity);
                        render(gc);
                        break;
                }
            }
        };
    }
}
