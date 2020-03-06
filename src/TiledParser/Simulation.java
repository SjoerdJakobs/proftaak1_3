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
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.io.IOException;

public class Simulation extends StandardObject {
    private FXGraphics2D graphics2D;
    private Canvas canvas;
    private Stage stage;

    private TileMap tileMap;
    private Camera camera;

    private BorderPane borderPane;
    private GridPane bottomPane;

    public Simulation(FrameworkProgram frameworkProgram) {
        super(frameworkProgram);

        this.canvas = frameworkProgram.getCanvasSimulation();
        this.graphics2D = frameworkProgram.getGraphics2DSimulation();
        this.stage = frameworkProgram.getStage();

        this.borderPane = new BorderPane();
        this.bottomPane = new GridPane();

        init();

        this.borderPane.setCenter(this.canvas);
        this.borderPane.setBottom(bottomPane);

    }

    public void init() {
        this.camera = new Camera();

        try {
            tileMap = new TileMap("resources/map.json");
        } catch (IOException e) {
            e.printStackTrace();
        }

        canvas.setOnScroll(e -> onMouseScoll(e));
        canvas.setOnMouseDragged(e -> onMouseDrag(e));
    }



    @Override
    protected void InputLoop(double deltaTime) {
        super.InputLoop(deltaTime);
    }

    @Override
    protected void MainLoop(double deltaTime) {
        super.MainLoop(deltaTime);
        this.canvas.setWidth(this.stage.getWidth());
        this.canvas.setHeight(this.stage.getHeight());
    }

    @Override
    protected void RenderLoop(double deltaTime) {
        super.RenderLoop(deltaTime);
        draw(this.graphics2D);
    }

    public void start(Stage stage) throws Exception {



        draw(graphics2D);
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


    public void onMouseScoll(ScrollEvent event) {
        camera.zoom(event.getDeltaY());
        draw(graphics2D);
    }

    public void onMouseDrag(MouseEvent event){
        camera.pan(event.getX(), event.getY());
        draw(graphics2D);
    }

    public BorderPane getBorderPane(){
        return this.borderPane;
    }

}
