import com.sun.xml.internal.ws.binding.FeatureListUtil;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.canvas.*;

import javax.imageio.ImageIO;
import javax.swing.*;
//import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Timer;
import java.util.TimerTask;

class GameData {

    static int coins = 0;
    static int currentJetpack = 0;




    public static void load(String path) {
        // BufferedReader reader = new BufferedReader(new FileReader(path));
    }

    private GameData(){}
}

class Game extends Canvas {
    State currentState;
    AnimationTimer timer;
    public static final int FPS = 60;
    long then;

    public Game(int w, int h) {
        StateManager.Push(new GameState());
        currentState = StateManager.Peek();
        StateManager.changed = false;

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update(now-then);
                then = now;
            }
        };
        //setSize(w, h);

        /*addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // nah
            }

            @Override
            public void keyPressed(KeyEvent e) {
                keyDown(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                keyUp(e.getKeyCode());
            }
        });*/

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

    private void update(long delta){
        System.out.println("tick");
    }

    /*@Override
    public void paint(Graphics g) {
        currentState.tick(g);
    }

    @Override
    public void update(Graphics g) {
        Graphics offgc;

        Image offscreen;

        offscreen = createImage(getWidth(), getHeight());
        offgc = offscreen.getGraphics();

        paint(offgc);

        g.drawImage(offscreen, 0, 0, this);

    }*/

    public void keyDown(int e) {
        currentState.keyDown(e);
    }

    public void keyUp(int e) {
        currentState.keyUp(e);
    }


}

public class MainApplication extends Application {

    static Game GAME;

    // todo change game object to its own class maybe MainApp? -- keep main app, have game extend scene instead, can use update methods,
    // todo     write each individual scene as its own extension, or first an abstract class of GameScene() which has the method, and have
    // todo     each individual scene extend that so they can be used interchangably,
    // todo Ideally remove the need for gameState etc
    // todo use scenes instead of states, self contained easier to implement
    // todo use animation timer to implement handle method which calls stage.getScene.update

    @Override
    public void start(Stage primaryStage) throws Exception {
        GAME = new Game(1200, 800);
        Canvas background = new Canvas();
        background.setWidth(1200);
        background.setHeight(800);
        //JFrame frame = new JFrame("Jetpack Joyride 2");
//        frame.setResizable(true);
//        frame.setVisible(true);
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        frame.add(background);
//        frame.add(GAME);
//        frame.pack();
//        GAME.createBufferStrategy(3);
//        background.getGraphicsContext2D().drawImage(ImageIO.read(new File("assets/img/raw/background.png")), 0,0, null); // todo add background canvas for image
        Group group = new Group(GAME);
        Scene scene = new Scene(group, 1200, 800);
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    public static void main(String args[]) {
        Application.launch();


    }
}