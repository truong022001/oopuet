package uet.oop.bomberman.entities.StillObject.Items;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Character.Bomber;

public class BombItem extends Items {
    public BombItem(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

    }

    @Override
    public void powerUp(Bomber bomber) {
        bomber.powerUp("bombPowerUp");
    }
}
