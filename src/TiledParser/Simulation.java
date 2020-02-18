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
import java.io.IOException;

public class Simulation extends Application {
    private Canvas canvas;
    private FXGraphics2D graphics2D;
    private TileMap tileMap;

    public void init() {
        try {
            tileMap = new TileMap("resources/map.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start(Stage stage) throws Exception {
        stage.setWidth(1650);
        stage.setHeight(1000);

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

        this.canvas = new Canvas(stage.getWidth(), stage.getHeight());
        this.graphics2D = new FXGraphics2D(canvas.getGraphicsContext2D());

        BorderPane mainPane = new BorderPane();
        mainPane.setCenter(canvas);

        draw(graphics2D);
        stage.setScene(new Scene(mainPane));
        stage.setTitle("Simulation");
        stage.show();
    }

    public void draw(FXGraphics2D graphics) {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.black);
        //graphics.scale(10,10);

        tileMap.draw(graphics);
    }

    public void update(FXGraphics2D graphics) {

    }
}
