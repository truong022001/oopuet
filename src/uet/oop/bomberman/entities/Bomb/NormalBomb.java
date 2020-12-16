package uet.oop.bomberman.entities.Bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Flame;
import uet.oop.bomberman.entities.StillObject.Brick;
import uet.oop.bomberman.entities.StillObject.Obstacle;
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
        flameAddInGroup.add(flame);
        boolean checkFlameUpBeTouch = false;
        boolean checkFlameDownBeTouch = false;
        boolean checkFlameRightBeTouch = false;
        boolean checkFlameLeftBeTouch = false;
        for (Obstacle i:canStopFlame) {
            if (isTouched(flameUp.getCollishionShape(), i.getCollisonShape())) {
                checkFlameUpBeTouch = true;
            }
            if (isTouched(flameDown.getCollishionShape(), i.getCollisonShape())) {
                checkFlameDownBeTouch = true;
            }
            if (isTouched(flameLeft.getCollishionShape(), i.getCollisonShape())) {
                checkFlameLeftBeTouch = true;
            }
            if (isTouched(flameRight.getCollishionShape(), i.getCollisonShape())) {
                checkFlameRightBeTouch = true;
            }
        }
        if (!checkFlameUpBeTouch) {
            BombermanGame.getRoot().getChildren().add(flameUp.getImageView());
            flameAddInGroup.add(flameUp);
        }
        if (!checkFlameDownBeTouch) {
            BombermanGame.getRoot().getChildren().add(flameDown.getImageView());
            flameAddInGroup.add(flameDown);
        }
        if (!checkFlameLeftBeTouch) {
            BombermanGame.getRoot().getChildren().add(flameLeft.getImageView());
            flameAddInGroup.add(flameLeft);
        }
        if (!checkFlameRightBeTouch) {
            BombermanGame.getRoot().getChildren().add(flameRight.getImageView());
            flameAddInGroup.add(flameRight);
        }
    }

    @Override
    public void removeFlameFromGroup() {
        for (Flame fl:flameAddInGroup) {
            BombermanGame.getRoot().getChildren().remove(fl.getImageView());
        }
        flameAddInGroup.clear();
    }

    @Override
    public void checkBrick() {
        for (Entity i:bricksInBomb) {
            if (isTouched(flameUp.getCollishionShape(), ((Brick) i).getCollisonShape())
                    || isTouched(flameDown.getCollishionShape(), ((Brick) i).getCollisonShape())
                    || isTouched(flameLeft.getCollishionShape(), ((Brick) i).getCollisonShape())
                    || isTouched(flameRight.getCollishionShape(), ((Brick) i).getCollisonShape())) {
                brickExploded.add(i);
            }
        }
    }
}
