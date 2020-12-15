package uet.oop.bomberman.Collision;

import uet.oop.bomberman.entities.StillObject.Obstacle;
import java.util.ArrayList;
import java.util.List;

public abstract class Collision {
    protected List<Obstacle> obstacles = new ArrayList<Obstacle>();

    public void addObstacle(Obstacle object) {
        obstacles.add(object);
    }

    public void clear() {
        obstacles.clear();
    }
}
