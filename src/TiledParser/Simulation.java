package TiledParser;

import OOFramework.FrameworkProgram;
import OOFramework.Modules.CONSTANTS;
import OOFramework.StandardObject;
import gridMaker.GridMap;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Simulation extends StandardObject {
    private FXGraphics2D graphics2D;
    private Canvas canvas;
    private Stage stage;

    private TileMap tileMap;
    private Camera camera;

    private BorderPane borderPane;
    private GridPane bottomPane;

    private GridMap grid;

    public Simulation(FrameworkProgram frameworkProgram) {
        super(frameworkProgram);

        this.canvas = frameworkProgram.getCanvasSimulation();
        this.graphics2D = frameworkProgram.getGraphics2DSimulation();
        this.stage = frameworkProgram.getStage();

        this.camera = new Camera(canvas, g -> draw(g), graphics2D);

        this.borderPane = new BorderPane();
        this.bottomPane = new GridPane();

        init();

        this.borderPane.setCenter(this.canvas);
        this.borderPane.setBottom(bottomPane);
    }

    public void init() {
        try {
            tileMap = new TileMap(CONSTANTS.MAP_JSONFILE);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.grid = new GridMap(this.tileMap.getTileMapJSONParser(), this.tileMap.getSprites());
        //this.grid.addRoute(80, 30, 80, 31, "route0");
        this.grid.setAllRoutes();
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


    public void start(Stage stage) {
        draw(graphics2D);
        stage.setTitle("Simulation");
        stage.show();
    }

    public void draw(FXGraphics2D graphics) {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(new Color(17, 17, 17));
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

        graphics.setTransform(camera.getTransform((int) canvas.getWidth(), (int) canvas.getHeight()));

        tileMap.draw(graphics, camera);
        this.grid.draw(graphics);

    }


    public BorderPane getBorderPane() {
        return this.borderPane;
    }

}
