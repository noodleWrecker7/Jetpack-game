import com.sun.xml.internal.ws.binding.FeatureListUtil;
import javafx.application.Application;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
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
    public static final int FPS = 60;


    public Game(int w, int h) {
        StateManager.Push(new GameState());
        currentState = StateManager.Peek();
        StateManager.changed = false;
        setSize(w, h);

        addKeyListener(new KeyListener() {
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
        });

        Timer timer = new Timer();
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
        }, 1000, 1000 / FPS);

    }

    @Override
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

    }

    public void keyDown(int e) {
        currentState.keyDown(e);
    }

    public void keyUp(int e) {
        currentState.keyUp(e);
    }


}

public class MainApplication extends Application {

    static Game GAME;

    @Override
    public void start(Stage primaryStage) throws Exception {
        GAME = new Game(1200, 800);
        Canvas background = new Canvas();
        background.getGraphics().drawImage(new BufferedImage("assets/img/raw/")) // todo add background canvas for image
        background.setSize(1200, 800);
        JFrame frame = new JFrame("Jetpack Joyride 2");
        frame.setResizable(true);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(GAME);
        frame.pack();
        GAME.createBufferStrategy(3);
    }

    public static void main(String args[]) {
        Application.launch();


    }
}