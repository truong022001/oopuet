package uet.oop.bomberman;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

//import static uet.oop.bomberman.entities.CheckTouchWall.createCheckTouchWall;

public class BombermanGame extends Application {
    private static int WIDTH = 31;
    private static int HEIGHT = 13;
    private String fullMap;
    private int level;
    private Group root=new Group();
    static List<Entity> entities = new ArrayList<>();
    static List<Entity> stillObjects = new ArrayList<>();
    private CheckTouchWall checkTouchWall=new CheckTouchWall();
    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }
    @Override
    public void start(Stage stage) {
        stage.setTitle("Bomberman Game");
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
        createMap();
        for (Entity i:stillObjects) {
            root.getChildren().add(i.getImageView());
            i.render();
        }

        for (Entity i:entities) {
            root.getChildren().add(i.getImageView());
            i.render();
        }
        bomberman.setRoot(root);
        //root.getChildren().remove(bomberman.getImageView()); cách để loại bỏ animation
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

    private void createMap() {
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
        Entity object;
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
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
                            object = new Brick(i, j, Sprite.brick.getFxImage());
                            stillObjects.add(object);
                            break;
                        case balloon:
                            object = new Balloon(i*Sprite.SCALED_SIZE, j*Sprite.SCALED_SIZE, Sprite.balloom_left1.getFxImage());
                            entities.add(object);
                            break;
                        case oneal:
                            object = new Oneal(i*Sprite.SCALED_SIZE, j*Sprite.SCALED_SIZE, Sprite.oneal_left1.getFxImage());
                            entities.add(object);
                            break;
                        case bombItemp:
                            object = new BombItem(i, j, Sprite.powerup_bombs.getFxImage());
                            stillObjects.add(object);
                            object = new Brick(i, j, Sprite.brick.getFxImage());
                            stillObjects.add(object);
                            break;
                        case flameItem:
                            object = new FlameItem(i, j, Sprite.powerup_flames.getFxImage());
                            stillObjects.add(object);
                            object = new Brick(i, j, Sprite.brick.getFxImage());
                            stillObjects.add(object);
                            break;
                        case speedItem:
                            object = new SpeedItem(i, j, Sprite.powerup_speed.getFxImage());
                            stillObjects.add(object);
                            object = new Brick(i, j, Sprite.brick.getFxImage());
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
    public static void getImageOfBomb(Group root,Bomb bomb){
        root.getChildren().remove(bomb.getImageView());
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
