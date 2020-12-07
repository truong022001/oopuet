package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
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
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;

    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        Bomber bomberman = new Bomber(Sprite.SCALED_SIZE, Sprite.SCALED_SIZE, Sprite.player_right.getFxImage());
        entities.add(bomberman);

        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        bomberman.setRightBomberAT(bomberman.createAnimationTimer("right", gc));
        bomberman.setLeftBomberAT(bomberman.createAnimationTimer("left", gc));
        bomberman.setUpBomberAT(bomberman.createAnimationTimer("up", gc));
        bomberman.setDownBomerAT(bomberman.createAnimationTimer("down", gc));
        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);
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
        stage.setScene(scene);
        stage.show();
        createMap();
    }

    //Can viet lai de nhan du lieu tu file txt
    public void createMap() {
        String FullMap = "###############################\n" +
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
        String[] map = FullMap.split("\n");
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                Entity object;
                if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
                    object = new Wall(i, j, Sprite.wall.getFxImage());
                } else {
                    switch (map[j].charAt(i)) {
                        case '*':
                            object = new Brick(i, j, Sprite.brick.getFxImage());
                            break;
                        case 'x':
                            object = new Portal(i, j, Sprite.portal.getFxImage());
                            break;
                        case '1':
                            object = new Balloon(i, j, Sprite.balloom_left1.getFxImage());
                            entities.add(object);
                            break;
                        case '2':
                            object = new Oneal(i, j, Sprite.oneal_left1.getFxImage());
                            break;
                        case 'b':
                            object = new BombItem(i, j, Sprite.powerup_bombs.getFxImage());
                            break;
                        case 'f':
                            object = new FlameItem(i, j, Sprite.powerup_flames.getFxImage());
                            break;
                        case 's':
                            object = new SpeedItem(i, j, Sprite.powerup_speed.getFxImage());
                            break;
                        case '#':
                            object = new Wall(i, j, Sprite.wall.getFxImage());
                            break;
                        default:
                            object = new Grass(i, j, Sprite.grass.getFxImage());
                    }
                }
                stillObjects.add(object);
            }
        }
        render();
    }

    public void update() {
        entities.forEach(Entity::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}
