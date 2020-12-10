package Obstacles;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

import java.awt.geom.Line2D;

public class Zapper extends Obstacle {
    //Path path;

    float x1, x2, y1, y2;
    Color color;

    public Zapper(float x1, float y1, float x2, float y2) {
     /*   path = new Path();
        MoveTo moveto = new MoveTo();
        moveto.setX(x1);
        moveto.setY(y1);
        LineTo lineto = new LineTo();
        lineto.setX(x2);
        lineto.setY(y2);
        path.getElements().addAll(moveto, lineto);

        path.setStroke(Color.YELLOW);
        path.setStrokeWidth(10);*/

        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public boolean isColliding(float pX, float pY, int pW, int pH, long runDist) {
        Line2D line = new Line2D.Float(x1 - runDist, y1, x2 - runDist, y2);

        if (line.intersects(pX, pY, pW, pH)) {
            color = Color.RED;
            return true;
        }
        color = Color.YELLOW;
        return false;
    }

    public void draw(GraphicsContext g, long runDist) {
        g.setStroke(color);
        g.setLineWidth(10);
        g.strokeLine(x1 - runDist, y1, x2 - runDist, y2);
    }
}

