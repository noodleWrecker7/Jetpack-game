package Obstacles;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

abstract public class Obstacle {

    private float x, y;

    public Obstacle() { // x coord is based as an offset from rundistance, if this x coordinate becomes less than the run distance then it has been passed
    }

    public boolean isColliding(float pX, float pY, int pW, int pH, long runDist) {
        return false;
    }

    public void draw(GraphicsContext g, long runDist) {

    }
}




