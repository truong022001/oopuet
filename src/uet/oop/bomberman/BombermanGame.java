package uet.oop.bomberman;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;
import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {
    private static int WIDTH = 31;
    private static int HEIGHT = 13;
    private String fullMap;
    private int level;

    private GraphicsContext gcMap;
    private Canvas canvasMap;
    public static GraphicsContext gcCharacter;
    public static Canvas canvasCharacter;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Bomberman Game");
        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        canvasMap = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        root.getChildren().add(canvasMap);
        gcMap = canvasMap.getGraphicsContext2D();

        canvasCharacter = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        root.getChildren().add(canvasCharacter);
        gcCharacter = canvasCharacter.getGraphicsContext2D();

        Bomber bomberman = new Bomber(Sprite.SCALED_SIZE, Sprite.SCALED_SIZE, Sprite.player_right.getFxImage());
        entities.add(bomberman);
        bomberman.setRightBomberAT(bomberman.createAnimationTimer("right", gcCharacter));
        bomberman.setLeftBomberAT(bomberman.createAnimationTimer("left", gcCharacter));
        bomberman.setUpBomberAT(bomberman.createAnimationTimer("up", gcCharacter));
        bomberman.setDownBomerAT(bomberman.createAnimationTimer("down", gcCharacter));

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

        createMap();
        stillObjects.forEach(g -> g.render(gcMap));
        entities.forEach(g -> g.render(gcCharacter));
        stage.show();
    }

    public void createMap() {
        final char wall = '#', brick = '*', portal = 'x', bomber = 'p', balloon = '1',
            oneal = '2', bombItemp = 'b', flameItem = 'f', speedItem = 's';

        fullMap = "###############################\n" +
                "#p     ** *  1 * 2 *  * * *   #\n" +
                "# # # #*# # #*#*# # # #*#*#*# #\n" +
                "#  x*     ***  *  1   * 2 * * #\n" +
                "# # # # # #*# # #*#*# # # # #*#\n" +
                "#f         x **  *  *   1     #\n" +
                "# # # # # # # # # #*# #*# # # #\n" +
                "#*  *      *  *      *        #\n" +
                "# # # # #*# # # #*#*# # # # # #\n" +
                "#*    **  *       *           #\n" +
                "# #*# # # # # # #*# # # # # # #\n" +
                "#           *   *  *          #\n" +
                "###############################";
        String[] map = fullMap.split("\n");
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                Entity object;
                if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
                    object = new Wall(i, j, Sprite.wall.getFxImage());
                    stillObjects.add(object);
                } else {
                    switch (map[j].charAt(i)) {
                        case brick:
                            object = new Brick(i, j, Sprite.brick.getFxImage());
                            stillObjects.add(object);
                            break;
                        case portal:
                            object = new Portal(i, j, Sprite.portal.getFxImage());
                            stillObjects.add(object);
                            object = new Grass(i, j, Sprite.grass.getFxImage());
                            stillObjects.add(object);
                            break;
                        case balloon:
                            object = new Balloon(i, j, Sprite.balloom_left1.getFxImage());
                            entities.add(object);
                            break;
                        case oneal:
                            object = new Oneal(i, j, Sprite.oneal_left1.getFxImage());
                            entities.add(object);
                            break;
                        case bombItemp:
                            object = new BombItem(i, j, Sprite.powerup_bombs.getFxImage());
                            stillObjects.add(object);
                            object = new Grass(i, j, Sprite.grass.getFxImage());
                            stillObjects.add(object);
                            break;
                        case flameItem:
                            object = new FlameItem(i, j, Sprite.powerup_flames.getFxImage());
                            stillObjects.add(object);
                            object = new Grass(i, j, Sprite.grass.getFxImage());
                            stillObjects.add(object);
                            break;
                        case speedItem:
                            object = new SpeedItem(i, j, Sprite.powerup_speed.getFxImage());
                            stillObjects.add(object);
                            object = new Grass(i, j, Sprite.grass.getFxImage());
                            stillObjects.add(object);
                            break;
                        case wall:
                            object = new Wall(i, j, Sprite.wall.getFxImage());
                            stillObjects.add(object);
                            break;
                        default:
                            object = new Grass(i, j, Sprite.grass.getFxImage());
                            stillObjects.add(object);
                    }
                }
            }
        }
    }

    public void update() {
        entities.forEach(Entity::update);
    }

    public static void setWIDTH(int WIDTH) {
        BombermanGame.WIDTH = WIDTH;
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static void setHEIGHT(int HEIGHT) {
        BombermanGame.HEIGHT = HEIGHT;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public String getFullMap() {
        return fullMap;
    }
}
