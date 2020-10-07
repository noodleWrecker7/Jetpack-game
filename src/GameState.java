import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;

//import javafx.scene.image.Image;

import javax.imageio.ImageIO;

public class GameState extends State {

    private boolean started = false;
    private Player player;


    public GameState() {

        player = new Player();


    }

    public void tick(Graphics g) {
        if (!started) {
            g.drawString("Press space to start", MainApplication.GAME.getWidth() / 2, MainApplication.GAME.getHeight() / 2);
            //return;
        }
        player.draw(g);


    }

    public void keyDown(int e) {
        System.out.println(e);
        if (e == 74) {
            StateManager.Push(new GameState());
        }
    }

    public void keyUp(int e) {

    }

    @Override
    public void Entered() {
        super.Entered();
        System.out.println(GameData.currentJetpack);
        // todo update jetpack
    }

    @Override
    public void Leaving() {
        super.Leaving();
    }
}
