import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;

import java.awt.*;

abstract public class State {

    public void Entered() {

    }

    public void Leaving() {

    }

    public void tick(GraphicsContext g, float delta){

    }

    public void keyDown(KeyCode e) {

    }

    public void keyUp(KeyCode e) {

    }
}
