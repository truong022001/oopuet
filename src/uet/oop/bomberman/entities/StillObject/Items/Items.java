package uet.oop.bomberman.entities.StillObject.Items;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.entities.Character.Bomber;
import uet.oop.bomberman.entities.StillObject.Obstacle;

public abstract class Items extends Obstacle {
    public Items(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public Rectangle getCollisonShape() {
        return new Rectangle(x * 32 + 1, y * 32 + 1, 30, 30);
    }

    public abstract void powerUp(Bomber bomber);
}
