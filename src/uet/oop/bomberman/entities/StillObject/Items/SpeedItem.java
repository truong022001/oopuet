package uet.oop.bomberman.entities.StillObject.Items;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Character.Bomber;

public class SpeedItem extends Items {
    public SpeedItem(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

    }

    @Override
    public void powerUp(Bomber bomber) {
        bomber.powerUp("speedPowerUp");
    }
}
