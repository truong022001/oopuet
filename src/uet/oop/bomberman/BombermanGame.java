package uet.oop.bomberman;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.LoadLevelGame.LoadLevel;
import uet.oop.bomberman.graphics.Sprite;
import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {
    private static int level = 1;
    private static List<Entity> entities = new ArrayList<>();
    private static List<Entity> stillObjects = new ArrayList<>();
    private CheckTouchWall checkTouchWall = new CheckTouchWall();
    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Bomberman Game");
        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        Bomber bomberman = new Bomber(Sprite.SCALED_SIZE, Sprite.SCALED_SIZE, Sprite.player_right.getFxImage());
        entities.add(bomberman);
        scene.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        bomberman.keyPressed(event);
                    }
                }
        );
        scene.setOnKeyReleased(
                new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        bomberman.keyReleased(event);
                    }
                }
        );

        createMapGame(level, root);
        update();
        checkTouchWall.createCheckTouchWall(stillObjects);
        bomberman.setCheckTouchWall(checkTouchWall);
        for (Entity i: entities) {
            if (i instanceof Oneal ) {
                ((Oneal) i).setCheckTouchWall(checkTouchWall);
            }
            if (i instanceof Balloon) {
                ((Balloon) i).setCheckTouchWall(checkTouchWall);
            }
        }
        stage.show();
    }

    public void update() {
        entities.forEach(Entity::update);
    }

    public void createMapGame(int level, Group root) {
        LoadLevel.createMap(level, root);
        for (Entity i:stillObjects) {
            root.getChildren().add(i.getImageView());
            i.render();
        }
        for (Entity i:entities) {
            root.getChildren().add(i.getImageView());
            i.render();
        }
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public static void addEntity(Entity entity) {
        entities.add(entity);
    }

    public static void addStillObject(Entity stillObj) {
        stillObjects.add(stillObj);
    }
}
