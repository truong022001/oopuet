package uet.oop.bomberman.entities.Bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Flame;
import uet.oop.bomberman.entities.StillObject.Brick;
import uet.oop.bomberman.entities.StillObject.Obstacle;
import uet.oop.bomberman.graphics.Sprite;

public class PowerUpBomb extends Bomb {
    private Flame flamePowerUpUp, flamePowerUpDown, flamePowerUpLeft, flamePowerUpRight;

    public PowerUpBomb(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void flameRender() {
        flame.render();
        flameUp.render();
        flameDown.render();
        flameLeft.render();
        flameRight.render();
        flamePowerUpDown.render();
        flamePowerUpLeft.render();
        flamePowerUpRight.render();
        flamePowerUpUp.render();
    }

    @Override
    public void setFlameImageFrame(boolean isFrame1) {
        if (isFrame1) {
            flame.setImage(Sprite.bomb_exploded1.getFxImage());
            flameUp.setImage(Sprite.explosion_vertical1.getFxImage());
            flameDown.setImage(Sprite.explosion_vertical1.getFxImage());
            flameRight.setImage(Sprite.explosion_horizontal1.getFxImage());
            flameLeft.setImage(Sprite.explosion_horizontal1.getFxImage());
            flamePowerUpUp.setImage(Sprite.explosion_vertical_top_last1.getFxImage());
            flamePowerUpDown.setImage(Sprite.explosion_vertical_down_last1.getFxImage());
            flamePowerUpRight.setImage(Sprite.explosion_horizontal_right_last1.getFxImage());
            flamePowerUpLeft.setImage(Sprite.explosion_horizontal_left_last1.getFxImage());
        } else {
            flame.setImage(Sprite.bomb_exploded2.getFxImage());
            flameUp.setImage(Sprite.explosion_vertical2.getFxImage());
            flameDown.setImage(Sprite.explosion_vertical2.getFxImage());
            flameRight.setImage(Sprite.explosion_horizontal2.getFxImage());
            flameLeft.setImage(Sprite.explosion_horizontal2.getFxImage());
            flamePowerUpUp.setImage(Sprite.explosion_vertical_top_last2.getFxImage());
            flamePowerUpDown.setImage(Sprite.explosion_vertical_down_last2.getFxImage());
            flamePowerUpRight.setImage(Sprite.explosion_horizontal_right_last2.getFxImage());
            flamePowerUpLeft.setImage(Sprite.explosion_horizontal_left_last2.getFxImage());
        }
    }

    @Override
    public void creatFlame() {
        flame = new Flame(x, y, Sprite.bomb_exploded.getFxImage());
        flameDown = new Flame(x, y + Sprite.SCALED_SIZE, Sprite.explosion_vertical.getFxImage());
        flameUp = new Flame(x, y - Sprite.SCALED_SIZE, Sprite.explosion_vertical.getFxImage());
        flameLeft = new Flame(x - Sprite.SCALED_SIZE, y, Sprite.explosion_horizontal.getFxImage());
        flameRight = new Flame(x + Sprite.SCALED_SIZE, y, Sprite.explosion_horizontal.getFxImage());
        flamePowerUpDown = new Flame(x, y + 2 * Sprite.SCALED_SIZE, Sprite.explosion_vertical_down_last.getFxImage());
        flamePowerUpUp = new Flame(x, y - 2 * Sprite.SCALED_SIZE, Sprite.explosion_vertical_top_last.getFxImage());
        flamePowerUpLeft = new Flame(x - 2 * Sprite.SCALED_SIZE, y, Sprite.explosion_horizontal_left_last.getFxImage());
        flamePowerUpRight = new Flame(x + 2 * Sprite.SCALED_SIZE, y, Sprite.explosion_horizontal_right_last.getFxImage());
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
            boolean checkFlamePowerUpBeTouch = false;
            for (Obstacle i:canStopFlame) {
                if (isTouched(flamePowerUpUp.getCollishionShape(), i.getCollisonShape())) {
                    checkFlamePowerUpBeTouch = true;
                    break;
                }
            }
            if (!checkFlamePowerUpBeTouch) {
                BombermanGame.getRoot().getChildren().add(flamePowerUpUp.getImageView());
                flameAddInGroup.add(flamePowerUpUp);
            }
        }
        if (!checkFlameDownBeTouch) {
            BombermanGame.getRoot().getChildren().add(flameDown.getImageView());
            flameAddInGroup.add(flameDown);
            boolean checkFlamePowerUpBeTouch = false;
            for (Obstacle i:canStopFlame) {
                if (isTouched(flamePowerUpDown.getCollishionShape(), i.getCollisonShape())) {
                    checkFlamePowerUpBeTouch = true;
                }
            }
            if (!checkFlamePowerUpBeTouch) {
                BombermanGame.getRoot().getChildren().add(flamePowerUpDown.getImageView());
                flameAddInGroup.add(flamePowerUpDown);
            }
        }
        if (!checkFlameLeftBeTouch) {
            BombermanGame.getRoot().getChildren().add(flameLeft.getImageView());
            flameAddInGroup.add(flameLeft);
            boolean checkFlamePowerUpBeTouch = false;
            for (Obstacle i:canStopFlame) {
                if (isTouched(flamePowerUpLeft.getCollishionShape(), i.getCollisonShape())) {
                    checkFlamePowerUpBeTouch = true;
                }
            }
            if (!checkFlamePowerUpBeTouch) {
                BombermanGame.getRoot().getChildren().add(flamePowerUpLeft.getImageView());
                flameAddInGroup.add(flamePowerUpLeft);
            }
        }
        if (!checkFlameRightBeTouch) {
            BombermanGame.getRoot().getChildren().add(flameRight.getImageView());
            flameAddInGroup.add(flameRight);
            boolean checkFlamePowerUpBeTouch = false;
            for (Obstacle i:canStopFlame) {
                if (isTouched(flamePowerUpRight.getCollishionShape(), i.getCollisonShape())) {
                    checkFlamePowerUpBeTouch = true;
                }
            }
            if (!checkFlamePowerUpBeTouch) {
                BombermanGame.getRoot().getChildren().add(flamePowerUpRight.getImageView());
                flameAddInGroup.add(flamePowerUpRight);
            }
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
        bricksInBomb = BombermanGame.getBricks();
        boolean checkFlameUpBeTouch = false;
        boolean checkFlameDownBeTouch = false;
        boolean checkFlameRightBeTouch = false;
        boolean checkFlameLeftBeTouch = false;
        for (Entity i:bricksInBomb) {
            if (isTouched(flameUp.getCollishionShape(), ((Brick) i).getCollisonShape())) {
                brickExploded.add(i);
                checkFlameUpBeTouch = true;
            }
            if (isTouched(flameDown.getCollishionShape(), ((Brick) i).getCollisonShape())) {
                brickExploded.add(i);
                checkFlameDownBeTouch = true;
            }
            if (isTouched(flameLeft.getCollishionShape(), ((Brick) i).getCollisonShape())) {
                brickExploded.add(i);
                checkFlameLeftBeTouch = true;
            }
            if (isTouched(flameRight.getCollishionShape(), ((Brick) i).getCollisonShape())) {
                brickExploded.add(i);
                checkFlameRightBeTouch = true;
            }
        }
        if (!checkFlameUpBeTouch) {
            for (Entity i:bricksInBomb) {
                if (isTouched(flamePowerUpUp.getCollishionShape(), ((Brick) i).getCollisonShape())) {
                    brickExploded.add(i);
                    break;
                }
            }
        }
        if (!checkFlameDownBeTouch) {
            for (Entity i:bricksInBomb) {
                if (isTouched(flamePowerUpDown.getCollishionShape(), ((Brick) i).getCollisonShape())) {
                    brickExploded.add(i);
                    break;
                }
            }
        }
        if (!checkFlameLeftBeTouch) {
            for (Entity i:bricksInBomb) {
                if (isTouched(flamePowerUpLeft.getCollishionShape(), ((Brick) i).getCollisonShape())) {
                    brickExploded.add(i);
                    break;
                }
            }
        }
        if (!checkFlameRightBeTouch) {
            for (Entity i:bricksInBomb) {
                if (isTouched(flamePowerUpRight.getCollishionShape(), ((Brick) i).getCollisonShape())) {
                    brickExploded.add(i);
                    break;
                }
            }
        }
    }
}
