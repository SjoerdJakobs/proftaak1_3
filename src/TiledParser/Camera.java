package TiledParser;

import OOFramework.Modules.CONSTANTS;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.Resizable;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class Camera {
    private Point2D centerPoint = new Point2D.Double(-800,-800);
    private double zoom = 0.6;
    private double rotation = 0;
    private Point2D lastMousePos;
    private Canvas canvas;
    private Resizable resizable;
    private FXGraphics2D g2d;

    public Camera(Canvas canvas, Resizable resizable, FXGraphics2D g2d) {
        this.canvas = canvas;
        this.resizable = resizable;
        this.g2d = g2d;

        canvas.setOnMousePressed(e -> {lastMousePos = new Point2D.Double(e.getX(), e.getY());});
        canvas.setOnMouseDragged(e -> mousePan(e));
        canvas.setOnScroll(e-> mouseZoom(e));
    }

    public AffineTransform getTransform(int windowWidth, int windowHeight)  {
        AffineTransform tx = new AffineTransform();
        tx.translate(windowWidth/2, windowHeight/2);
        tx.scale(zoom, zoom);
        tx.translate(centerPoint.getX(), centerPoint.getY());
        tx.rotate(rotation);
        return tx;
    }

    public void mousePan(MouseEvent e) {
        if(e.getButton() == MouseButton.SECONDARY) {
            centerPoint = new Point2D.Double(
                    centerPoint.getX() - (lastMousePos.getX() - e.getX()) / zoom,
                    centerPoint.getY() - (lastMousePos.getY() - e.getY()) / zoom
            );
            lastMousePos = new Point2D.Double(e.getX(), e.getY());
            resizable.draw(g2d);
        }
    }

    public void mouseZoom(ScrollEvent e) {
        double newZoom = zoom * (1 + e.getDeltaY() / CONSTANTS.CAMERAZOOM_SENSITIVIY);
        if (newZoom < 5.0 && newZoom > 0.2) {
            zoom = newZoom;
            resizable.draw(g2d);
        }
    }
}
