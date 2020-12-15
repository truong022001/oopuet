package uet.oop.bomberman.Collision;

import javafx.animation.AnimationTimer;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.Character.Bomber;
import uet.oop.bomberman.entities.StillObject.Items.Items;
import uet.oop.bomberman.entities.StillObject.Obstacle;

import java.util.List;

public class CheckItems extends Collision {

    public void createCheckItems(List<Entity> stillObjects) {
        for (int i = 0; i < stillObjects.size(); i++) {
            Entity object = stillObjects.get(i);
            if (object instanceof Items) {
                Items obj = (Items) object;
                obstacles.add(obj);
            }
        }
    }

    public void Touch(Bomber bomber) {
        Rectangle bomberCollishionShape = bomber.getCollishionShape();
        for (int i = 0; i < obstacles.size(); i++) {
            Obstacle obstacle = obstacles.get(i);
            Rectangle obstacleShape = obstacle.getCollisonShape();
            if (obstacleShape.getBoundsInParent().intersects(bomberCollishionShape.getBoundsInParent())) {
                ((Items) obstacle).powerUp(bomber);
                BombermanGame.getRoot().getChildren().remove(obstacle.getImageView());
                obstacles.remove(obstacle);
                //checkTimePowerUp(bomber);
            }
        }
    }

    /* Hàm này dùng để giới hạn thời gian sử dụng Items, nhưng có vẻ không cần.
    public void checkTimePowerUp(Bomber bomber) {
        new AnimationTimer() {
            long beginTime = System.currentTimeMillis();

            @Override
            public void handle(long now) {
                if (System.currentTimeMillis() - beginTime >= 10000) {
                    bomber.powerUp("nothing");
                    this.stop();
                }
            }
        }.start();
    } */
}
