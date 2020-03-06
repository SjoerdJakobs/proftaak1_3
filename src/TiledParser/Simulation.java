package TiledParser;

import OOFramework.FrameworkProgram;
import OOFramework.StandardObject;
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

public class Simulation extends StandardObject {
    private FXGraphics2D graphics2D;
    private Canvas canvas;

    private TileMap tileMap;
    private Camera camera;

   public Simulation(FrameworkProgram frameworkProgram){
       super(frameworkProgram);
       this.canvas = new Canvas(1920, 1080);
       init();
   }

    public void init() {
        this.camera = new Camera();
        this.graphics2D = new FXGraphics2D(canvas.getGraphicsContext2D());
        try {
            tileMap = new TileMap("resources/map.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BorderPane sceneSimulation(){
       BorderPane pane = new BorderPane();


        canvas.setOnScroll(e -> onMouseScoll(e));
        canvas.setOnMouseDragged(e -> onMouseDrag(e));
        pane.setCenter(canvas);
        return pane;

    }

    public void draw(FXGraphics2D graphics) {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(new Color(17, 17, 17));
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());
        graphics.scale(camera.getZoom(), camera.getZoom());

        tileMap.draw(graphics, camera);
    }

    public void onMouseScoll(ScrollEvent event) {
        camera.zoom(event.getDeltaY());
        draw(graphics2D);
    }

    public void onMouseDrag(MouseEvent event){
        camera.pan(event.getX(), event.getY());
        draw(graphics2D);
    }

    @Override
    protected void RenderLoop(double deltaTime) {
        super.RenderLoop(deltaTime);
        draw(graphics2D);
    }
}
