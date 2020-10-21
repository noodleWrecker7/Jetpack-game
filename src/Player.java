import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Player {

    private String imageUrl;
    private static String filesRoot = "assets/img/raw/";
    private Image img;

    public Player() {
        imageUrl = "dino-0.png";
        try {
            img = new Image(filesRoot + imageUrl);
        } catch (Exception e) {
        }
    }

    public void draw(GraphicsContext g) {
        g.drawImage(img, 50, 50);

    }
}
