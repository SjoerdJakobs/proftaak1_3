package TiledParser;

import MainPackage.Simulation.Logic.Direction;
import MainPackage.Simulation.Npc.Npc;
import MainPackage.Simulation.Npc.Student;
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

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;

public class Simulation extends StandardObject {
    private FXGraphics2D graphics2D;
    private Canvas canvas;
    private Stage stage;

    private TileMap tileMap;
    private Camera camera;

    private BorderPane borderPane;
    private GridPane bottomPane;
    ArrayList<Npc> npcs = new ArrayList<>();

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
        npcs.add(new Student(getFrameworkProgram(), graphics2D, new Point2D.Double(100,100)));
        npcs.add(new Student(getFrameworkProgram(), graphics2D, new Point2D.Double(100,200)));
        npcs.add(new Student(getFrameworkProgram(), graphics2D, new Point2D.Double(100,300)));
        npcs.add(new Student(getFrameworkProgram(), graphics2D, new Point2D.Double(100,400)));






    }

    //1st method called, before the program launches.
    public void init() {
        try {
            //Load the tilemap with the map.json file
            tileMap = new TileMap("resources/map.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        //Clear the old frame off the screen
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(new Color(17, 17, 17));
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

        //Transform everything around the camera
        graphics.setTransform(camera.getTransform((int)canvas.getWidth(), (int)canvas.getHeight()));

        //Draw the map
        tileMap.draw(graphics, camera);
        //System.out.println("got this far");
        //System.out.println(npcs);
       /*
        for (Npc npc : npcs ) {
            graphics2D.drawImage(npc.getImageToDraw(Direction.RIGHT,true),(int)npc.getCords().getX(),(int)npc.getCords().getY(),32,32,null);
        }
        */
    }


    public BorderPane getBorderPane(){
        return this.borderPane;
    }

}
