import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Player {

    private String imageUrl;
    private static String filesRoot = "assets/img/raw/";
    private BufferedImage img;

    public Player() {
        imageUrl = "dino-0.png";
        try {
            img = ImageIO.read(new File(filesRoot + imageUrl));
        } catch (Exception e) {
        }
    }

    public void draw(Graphics g) {
        g.drawImage(img, 50, 50, 84, img.getHeight()*2, null);
    }
}
