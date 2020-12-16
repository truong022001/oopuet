package uet.oop.bomberman.Collision;

import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.Character.Bomber;
import uet.oop.bomberman.entities.Character.Character;
import uet.oop.bomberman.entities.StillObject.Brick;
import uet.oop.bomberman.entities.StillObject.Obstacle;
import uet.oop.bomberman.entities.StillObject.Portal;
import uet.oop.bomberman.entities.StillObject.Wall;

import java.util.List;

public class CheckTouchWall extends Collision {
    public void createCheckTouchWall(List<Entity> stillObjects) {
        for (int i = 0; i < stillObjects.size(); i++) {
            Entity object = stillObjects.get(i);
            if (object instanceof Wall) {
                Wall obj = (Wall)object;
                obstacles.add(obj);
            }
            if (object instanceof Brick) {
                Brick obj1 = (Brick)object;
                obstacles.add(obj1);
            }
            if (object instanceof Portal) {
                Portal obj1 = (Portal)object;
                obstacles.add(obj1);
            }
        }
    }

    public boolean Touch(Character character) {
        Rectangle characterShape = character.getCollishionShape();
        for (int i = 0; i < obstacles.size(); i++) {
            Obstacle obstacle = obstacles.get(i);
            Rectangle obstacleShape = obstacle.getCollisonShape();
            if (obstacleShape.getBoundsInParent().intersects(characterShape.getBoundsInParent())) {
                if (obstacle instanceof Portal && character instanceof Bomber) {
                    BombermanGame.checkNextLevel();
                }
                return true;
            }
        }
        return false;
    }

    public boolean TouchBrick(Rectangle collishionShape) {
        for (int i = 0; i < obstacles.size(); i++) {
            Obstacle obstacle = obstacles.get(i);
            Rectangle obstacleShape = obstacle.getCollisonShape();
            if ((obstacle instanceof Brick) && (obstacleShape.getBoundsInParent().intersects(collishionShape.getBoundsInParent()))) {
                return true;
            }
        }
        return false;
    }
}
