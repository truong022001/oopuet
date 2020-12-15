package uet.oop.bomberman.entities.Bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Flame;
import uet.oop.bomberman.graphics.Sprite;

public class NormalBomb extends Bomb {
    public NormalBomb(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void flameRender(){
        flame.render();
        flameUp.render();
        flameDown.render();
        flameLeft.render();
        flameRight.render();
    }

    @Override
    public void setFlameImageFrame(boolean isFrame1) {
        if (isFrame1) {
            flame.setImage(Sprite.bomb_exploded1.getFxImage());
            flameUp.setImage(Sprite.explosion_vertical_top_last1.getFxImage());
            flameDown.setImage(Sprite.explosion_vertical_down_last1.getFxImage());
            flameRight.setImage(Sprite.explosion_horizontal_right_last1.getFxImage());
            flameLeft.setImage(Sprite.explosion_horizontal_left_last1.getFxImage());
        } else {
            flame.setImage(Sprite.bomb_exploded2.getFxImage());
            flameUp.setImage(Sprite.explosion_vertical_top_last2.getFxImage());
            flameDown.setImage(Sprite.explosion_vertical_down_last2.getFxImage());
            flameRight.setImage(Sprite.explosion_horizontal_right_last2.getFxImage());
            flameLeft.setImage(Sprite.explosion_horizontal_left_last2.getFxImage());
        }
    }

    @Override
    public void creatFlame() {
        flame = new Flame(x, y, Sprite.bomb_exploded.getFxImage());
        flameDown = new Flame(x, y + Sprite.SCALED_SIZE, Sprite.explosion_vertical_down_last.getFxImage());
        flameUp = new Flame(x, y - Sprite.SCALED_SIZE, Sprite.explosion_vertical_top_last.getFxImage());
        flameLeft = new Flame(x - Sprite.SCALED_SIZE, y, Sprite.explosion_horizontal_left_last.getFxImage());
        flameRight = new Flame(x + Sprite.SCALED_SIZE, y, Sprite.explosion_horizontal_right_last.getFxImage());
    }

    @Override
    public void addFlameOnGroup() {
        BombermanGame.getRoot().getChildren().remove(getImageView());
        BombermanGame.getRoot().getChildren().add(flame.getImageView());
        BombermanGame.getRoot().getChildren().add(flameUp.getImageView());
        BombermanGame.getRoot().getChildren().add(flameDown.getImageView());
        BombermanGame.getRoot().getChildren().add(flameLeft.getImageView());
        BombermanGame.getRoot().getChildren().add(flameRight.getImageView());
    }

    @Override
    public void removeFlameFromGroup() {
        BombermanGame.getRoot().getChildren().remove(flame.getImageView());
        BombermanGame.getRoot().getChildren().remove(flameUp.getImageView());
        BombermanGame.getRoot().getChildren().remove(flameDown.getImageView());
        BombermanGame.getRoot().getChildren().remove(flameLeft.getImageView());
        BombermanGame.getRoot().getChildren().remove(flameRight.getImageView());
    }
}
