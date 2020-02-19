package TiledParser;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.io.IOException;

public class Simulation extends Application {
    private FXGraphics2D graphics2D;
    private Canvas canvas;

    private TileMap tileMap;
    private Camera camera;

    public void init() {
        this.camera = new Camera();

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
                update((now - last) / 1000000000.0);
                last = now;
            }
        }.start();

        this.canvas = new Canvas(stage.getWidth(), stage.getHeight());
        this.graphics2D = new FXGraphics2D(canvas.getGraphicsContext2D());

        BorderPane mainPane = new BorderPane();
        mainPane.setCenter(canvas);

        canvas.setOnScroll(e -> onMouseScoll(e));
        canvas.setOnMouseDragged(e -> onMouseDrag(e));

        draw(graphics2D);
        stage.setScene(new Scene(mainPane));
        stage.setTitle("Simulation");
        stage.show();
    }

    public void draw(FXGraphics2D graphics) {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(new Color(17, 17, 17));
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());
        graphics.scale(camera.getZoom(), camera.getZoom());

        tileMap.draw(graphics, camera);
    }

    public void update(double deltaTime) {

    }

    public void onMouseScoll(ScrollEvent event) {
        camera.zoom(event.getDeltaY());
        draw(graphics2D);
    }

    public void onMouseDrag(MouseEvent event){
        System.out.println(event.getEventType());

        camera.pan(event.getX(), event.getY());
        draw(graphics2D);
    }
}
