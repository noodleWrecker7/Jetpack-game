import Obstacles.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;

import java.util.Date;


//import javafx.scene.image.Image;

//import javax.imageio.ImageIO;

public class GameState extends State {

    Date date, date2;
    long t1, t2;

    private boolean started = false;
    private Player player;
    private Obstacle zapper;
    private float xSpeed = 100.f; // pixels/second

    public GameState() {

        player = new Player();

        zapper = new Zapper(100, 100, 500, 500);
    }

    public void render(GraphicsContext g) {
        player.draw(g);

        zapper.draw(g, GameData.runDistance);
    }

    public void tick(GraphicsContext g, float delta) {
        if (!started) {
            g.fillText("Press space to start", MainApplication.GAME.getWidth() / 2, MainApplication.GAME.getHeight() / 2);
            return;
        }
//        long t1 = new Date().getTime();
        player.tick(delta);


        if (zapper.isColliding(player.getX(), player.getY(), player.getWidth(), player.getHeight(), GameData.runDistance)){
            System.out.println("COLLISION");
        }


            GameData.runDistance += xSpeed * delta;
//        long t2 = new Date().getTime();
        render(g);
    }

    public void keyDown(KeyCode e) {
        if (!started) {
            started = true;
            return;
        }

        switch (e) {
            case SPACE:
                player.boost(true);
                break;
        }
    }

    public void keyUp(KeyCode e) {
        switch (e) {
            case SPACE:
                player.boost(false);
                break;
        }
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
