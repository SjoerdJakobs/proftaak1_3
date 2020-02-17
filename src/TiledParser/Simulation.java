package TiledParser;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class Simulation extends Application {
    private Canvas canvas;
    private FXGraphics2D graphics2D;


    public void start(Stage stage) throws Exception {
        stage.setWidth(1650);
        stage.setHeight(1000);

        this.canvas = new Canvas(stage.getWidth(), stage.getHeight());
        this.graphics2D = new FXGraphics2D(canvas.getGraphicsContext2D());

        BorderPane mainPane = new BorderPane();
        mainPane.setCenter(canvas);

        new AnimationTimer() {
            long last = -1;

            @Override
            public void handle(long now) {
                if (last == -1)
                    last = now;
                update(graphics2D);
                last = now;
            }
        }.start();

        draw(graphics2D);
        stage.setScene(new Scene(mainPane));
        stage.setTitle("Simulation");
        stage.show();
    }

    public void draw(FXGraphics2D graphics) {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());


    }

    public void update(FXGraphics2D graphics) {

    }
}
