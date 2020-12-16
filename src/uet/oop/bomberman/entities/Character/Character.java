package uet.oop.bomberman.entities.Character;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.Collision.CharacterTouch;
import uet.oop.bomberman.Collision.CheckTouchWall;
import uet.oop.bomberman.entities.Entity;

public abstract class Character extends Entity {
    protected int velocity;
    protected int velocityX;
    protected int velocityY;
    protected Rectangle collisionShape;
    protected CheckTouchWall checkTouchWall;
    protected CharacterTouch characterTouch;
    protected boolean dead;

    public Character(int x, int y, Image img) {
        super(x, y, img);
        checkTouchWall = new CheckTouchWall();
        characterTouch = new CharacterTouch();
        dead = false;
    }

    @Override
    public void render() {
        imageView.setImage(img);
        imageView.setX(getX());
        imageView.setY(getY());
    }

    public abstract void die();

    public void setCheckTouchWall(CheckTouchWall checkTouchWall) {
        this.checkTouchWall = checkTouchWall;
    }

    public void setCharacterTouch(CharacterTouch characterTouch) {
        this.characterTouch = characterTouch;
    }

    public abstract Rectangle getCollishionShape();
}
