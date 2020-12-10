import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;

import javafx.scene.paint.*;

import java.awt.image.BufferedImage;
import java.io.File;

public class Player {

    private String imageUrl;
    private static String filesRoot = "assets/img/raw/";
    private Image img;
    private float multiplier = 1f;

    private static class Movement {
        static boolean left = false, right = false, up = false;
        static float boostSpeed = 640f; // pixels per second
        static float weight = 850.f; // pixels per second per second
        static float ySpeed = 0.0f;
    }

    private static class Position {
        static float x = 100f, y = 100f;
    }

    public float getX() {
        return Position.x;
    }

    public float getY() {
        return Position.y;
    }

    public int getWidth() {
        return (int) img.getWidth();
    }

    public int getHeight() {
        return (int) img.getHeight();
    }


    public void boost(boolean isBoosting) {
        Movement.up = isBoosting;
    }

    public Player() {
        imageUrl = "dino-0.png";
        try {
//            img = new Image(filesRoot + imageUrl);
            img = new Image("file:assets/img/raw/dino-0.png", 100, 100, true, false);
        } catch (Exception e) {
            System.out.print("Failed to load player image:");
            System.out.println(e.getMessage());
        }
    }

    public void draw(GraphicsContext g) {
        g.drawImage(img, Position.x, Position.y);
        //g.fillRect((int) Position.x, (int) Position.y, 50,50 );
//        g.setFill(Color.BLUE);
//        g.fillRect(200, 200, 200, 200);


    }

    public void tick(float delta) {
        if (Movement.up) {
            Movement.ySpeed = Movement.boostSpeed;
        } else {
            if (Movement.ySpeed > 0) multiplier = 4f;
            else multiplier = 1;
            Movement.ySpeed -= Movement.weight * delta * multiplier;
        }
        Position.y -= Movement.ySpeed * delta;
        if (Position.y < 0) {
            Position.y = 0;
            Movement.ySpeed = 0;
        }

        if (Position.y + img.getHeight() > 800) {
            Position.y = (float) (800 - img.getHeight());
            Movement.ySpeed = 0;
        }

    }
}
