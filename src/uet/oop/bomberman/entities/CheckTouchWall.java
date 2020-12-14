package uet.oop.bomberman.entities;

import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class CheckTouchWall {
    private List<Obstacle> obstacles = new ArrayList<Obstacle>();

    public void addObstacle(Obstacle object) {
        obstacles.add(object);
    }

    public boolean Touch(Rectangle collishionShape) {
        for (int i = 0; i < obstacles.size(); i++) {
            Obstacle obstacle = obstacles.get(i);
            Rectangle obstacleShape = obstacle.getCollisonShape();
            if (obstacleShape.getBoundsInParent().intersects(collishionShape.getBoundsInParent())) {
                return true;
            }
        }
        return false;
    }

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
        }
    }
}
