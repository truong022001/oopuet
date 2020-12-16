package uet.oop.bomberman.Collision;

import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.entities.Character.Bomber;
import uet.oop.bomberman.entities.Character.Character;
import uet.oop.bomberman.entities.Character.Enemy;
import uet.oop.bomberman.entities.Entity;

import java.util.ArrayList;
import java.util.List;

public class CharacterTouch {
    protected List<Enemy> enemys = new ArrayList<Enemy>();

    public void createListEnemys(List<Entity> entities) {
        for (int i = 0; i < entities.size(); i++) {
            Entity object = entities.get(i);
            if (object instanceof Enemy) {
                Enemy obj = (Enemy) object;
                this.addEnemy(obj);
            }
        }
    }

    public boolean touchEnemy(Character character) {
        Rectangle characterShape = character.getCollishionShape();
        for (int i = 0; i < enemys.size(); i++) {
            Enemy enemy = enemys.get(i);
            Rectangle enemyShape = enemy.getCollishionShape();
            if (enemyShape.getBoundsInParent().intersects(characterShape.getBoundsInParent())
                && enemy != character) {
                if (character instanceof Bomber) {
                    ((Bomber) character).die();
                }
                return true;
            }
        }
        return false;
    }

    public void addEnemy(Enemy object) {
        enemys.add(object);
    }

    public void clear() {
        enemys.clear();
    }

}
