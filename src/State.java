import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

abstract public class State {

    public void Entered() {

    }

    public void Leaving() {

    }

    public void tick(GraphicsContext g){

    }

    public void keyDown(int e) {

    }

    public void keyUp(int e) {

    }
}
