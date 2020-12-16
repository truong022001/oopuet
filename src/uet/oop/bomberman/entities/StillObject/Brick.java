package uet.oop.bomberman.entities.StillObject;

import javafx.scene.image.Image;
import uet.oop.bomberman.Collision.CheckTouchWall;

public class Brick extends Obstacle {
    private CheckTouchWall checkTouchWall;

    public Brick(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

    }

    public void setCheckTouchWall(CheckTouchWall checkTouchWall) {
        this.checkTouchWall = checkTouchWall;
    }
}
