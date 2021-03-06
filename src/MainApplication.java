import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.scene.canvas.*;

import java.util.Date;

//import java.awt.*;


class GameData {

    static int coins = 0;
    static int currentJetpack = 0;
    static boolean paused = false;
    static long runDistance = 0;

    public static void load(String path) {
        // BufferedReader reader = new BufferedReader(new FileReader(path));
    }

}

class Game extends Canvas {
    State currentState;
    AnimationTimer timer;
    public static final int FPS = 60;
    long then;
    GraphicsContext ctx;

    public Game(int w, int h) {
        StateManager.Push(new GameState());
        currentState = StateManager.Peek();
        StateManager.changed = false;
        setWidth(w);
        setHeight(h);

        this.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                currentState.keyDown(event.getCode());
            }
        });
        setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                currentState.keyUp(event.getCode());
            }
        });

        ctx = this.getGraphicsContext2D();

        System.out.println("here");
        then = new Date().getTime() * 1000000;
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                //if(GameData.startingFrame &&  > )
//                System.out.println((float)(now - then) / 1000000000f);
                update((float)(now - then) / 1000000000f);
                then = (now);
            }
        };
        timer.start();

        //setSize(w, h);


        /*Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (StateManager.changed) {
                    currentState = StateManager.Peek();
                    StateManager.changed = false;
                    System.out.println("State changed");
                }
                repaint();
            }
        }, 1000, 1000 / FPS);*/

    }


    public void update(float delta) {
        if (StateManager.changed) {
            currentState = StateManager.Peek();
            StateManager.changed = false;
        }
        if(GameData.paused) {
            return;
        }
//        System.out.println("Delta (s): " + delta);
        ctx.clearRect(0,0,this.getWidth(), this.getHeight());
        currentState.tick(ctx, delta);


    }

    /*public void keyDown(KeyCode e) {
        currentState.keyDown(e);
    }

    public void keyUp(int e) {
        currentState.keyUp(e);
    }*/


}

public class MainApplication extends Application {

    static Game GAME;

    // todo change game object to its own class maybe MainApp? -- keep main app, have game extend scene instead, can use update methods,
    // todo     write each individual scene as its own extension, or first an abstract class of GameScene() which has the method, and have
    // todo     each individual scene extend that so they can be used interchangably,
    // todo Ideally remove the need for gameState etc
    // todo use scenes instead of states, self contained easier to implement
    // todo use animation timer to implement handle method which calls stage.getScene.update

    State currentGameState;
    AnimationTimer timer;
    public static final int FPS = 60;
    long then;

    public MainApplication() {
        StateManager.Push(new GameState());
        currentGameState = StateManager.Peek();
        StateManager.changed = false;


    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GAME = new Game(1200, 800);
        GAME.setFocusTraversable(true);
        Canvas background = new Canvas();
        background.setWidth(1200);
        background.setHeight(800);
        background.getGraphicsContext2D().drawImage(new Image("file:assets/img/raw/background.png"), 0,0, 1200, 800);

        //JFrame frame = new JFrame("Jetpack Joyride 2");
//        frame.setResizable(true);
//        frame.setVisible(true);
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        frame.add(background);
//        frame.add(GAME);
//        frame.pack();
//        GAME.createBufferStrategy(3);
//        background.getGraphicsContext2D().drawImage(ImageIO.read(new File("assets/img/raw/background.png")), 0,0, null); // todo add background canvas for image
        Group group = new Group();
        group.setVisible(true);
        GAME.setVisible(true);
        background.setVisible(true);
        Scene scene = new Scene(group, 1200, 800);
        primaryStage.setScene(scene);
        group.getChildren().add(background);
        group.getChildren().add(GAME);
        primaryStage.show();


    }


    public static void main(String args[]) {
        Application.launch();


    }
}