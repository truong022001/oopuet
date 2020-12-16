package uet.oop.bomberman;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import uet.oop.bomberman.Collision.CharacterTouch;
import uet.oop.bomberman.Collision.CheckTouchWall;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.LoadLevelGame.LoadLevel;
import uet.oop.bomberman.entities.Character.Bomber;
import uet.oop.bomberman.entities.Character.Character;
import uet.oop.bomberman.entities.Character.Enemy;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {
    public static int level = 1;
    private static List<Entity> entities = new ArrayList<>();
    private static List<Entity> stillObjects = new ArrayList<>();
    private static List<Entity> bricks = new ArrayList<>();
    private static Bomber bomberman;
    private static CheckTouchWall checkTouchWall = new CheckTouchWall();
    private static CharacterTouch characterTouch = new CharacterTouch();
    private static Group root;
    private static int numberOfEnemy = 0;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Bomberman Game");
        root = new Group();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        bomberman = new Bomber(Sprite.SCALED_SIZE, Sprite.SCALED_SIZE, Sprite.player_right.getFxImage());
        bomberman.setRoot(root);
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

        loadLevel(level);
        stage.show();
    }

    public static void update() {
        entities.forEach(Entity::update);
    }

    /**
     * Hàm tạo map dựa trên thông số level.
     * @param level Màn chơi cần tải map.
     * @param root Group chứa hình ảnh của các enities.
     */
    public static void createMapGame(int level, Group root) {
        LoadLevel.createMap(level, root);
        for (Entity i : stillObjects) {
            root.getChildren().add(i.getImageView());
            i.render();
        }
        for (Entity i : entities) {
            root.getChildren().add(i.getImageView());
            i.render();
        }
    }

    /**
     * Khi nhân vật bomber va chạm với Portal, hàm này sẽ được gọi.
     * Nếu khi đó số lượng enemy bằng không, tải bản đồ của màn chơi kế tiếp.
     * Ngược lại, sẽ chỉ coi đó như va chạm bình thường.
     */
    public static void checkNextLevel() {
        if (numberOfEnemy == 0) {
            bomberman.setAgain();
            level = level + 1;
            clear();
            loadLevel(level);
        }
    }

    public static void loadLevel(int level) {
        //creatBoadStartGame();
        bomberman.setX(32);
        bomberman.setY(32);

        entities.add(bomberman);
        createMapGame(level, root);

        checkTouchWall.createCheckTouchWall(stillObjects);
        bomberman.setCheckItems(stillObjects);
        characterTouch.createListEnemys(entities);
        bomberman.setCharacterTouch(characterTouch);
        for (Entity i: entities) {
            if (i instanceof Character) {
                ((Character) i).setCheckTouchWall(checkTouchWall);
            }
            if (i instanceof Enemy) {
                numberOfEnemy++;
            }
        }
        update();
    }

    /*
    public static void creatBoadStartGame() {
        Canvas canvas = new Canvas(Sprite.SCALED_SIZE * LoadLevel.getWIDTH(), Sprite.SCALED_SIZE * LoadLevel.getHEIGHT());
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();

    }*/

    public static void clear() {
        numberOfEnemy = 0;
        root.getChildren().clear();
        stillObjects.clear();
        entities.clear();
        checkTouchWall.clear();
        bomberman.getCheckItems().clear();
        characterTouch.clear();
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

    public static void addBricks(Entity brick) {
        bricks.add(brick);
    }

    public static void addStillObject(Entity stillObj) {
        stillObjects.add(stillObj);
    }

    public static Group getRoot() {
        return root;
    }

    public static int getNumberOfEnemy() {
        return numberOfEnemy;
    }

    public static void setNumberOfEnemy(int num) {
        numberOfEnemy = num;
    }

    public static List<Entity> getBricks() {
        return bricks;
    }

    public static void setEntities(List<Entity> entities) {
        BombermanGame.entities = entities;
    }

    public static List<Entity> getStillObjects() {
        return stillObjects;
    }

    public static CheckTouchWall getCheckTouchWall() {
        return checkTouchWall;
    }

    public static List<Entity> getEntities() {
        return entities;
    }

    public static CharacterTouch getCharacterTouch() {
        return characterTouch;
    }
}
