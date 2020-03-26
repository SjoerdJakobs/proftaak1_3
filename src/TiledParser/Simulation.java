package TiledParser;

import MainPackage.Simulation.Npc.Npc;
import MainPackage.Simulation.Npc.Student;
import OOFramework.FrameworkProgram;
import OOFramework.Modules.CONSTANTS;
import OOFramework.StandardObject;
import gridMaker.Direction;
import gridMaker.GridMap;
import gridMaker.Tile;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.io.FileNotFoundException;
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

    private ArrayList<Npc> npcs = new ArrayList<>();

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

        npcs.add(new Student(getFrameworkProgram(), graphics2D, new Point2D.Double(18*16,19*16)));
        for(int i=18; i<70; i++){
            npcs.add(new Student(getFrameworkProgram(), graphics2D, new Point2D.Double(i*16,19*16)));
        }

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

        for(Npc npc : npcs){
            Tile[][] tiles = this.grid.getTiles();
            Direction direction = tiles[(int)(Math.round(npc.getPosition().getX()/16))][(int)(Math.round(npc.getPosition().getY()/16))].getDirections().get("canteen");
           // System.out.println(direction);
//            System.out.println((int)npc.getPosition().getX()/16 + " " + (int)npc.getPosition().getY()/16);
//            System.out.println(tiles[18][81].getDirections().get("canteen"));
            if(direction == Direction.ENDPOINT){
                System.out.println("reached destination");
            }
            else if(direction == Direction.DOWN){
                npc.moveTo(deltaTime,new Point2D.Double(npc.getPosition().getX(),npc.getPosition().getY()+16));
            }
            else if(direction == Direction.UP){
                npc.moveTo(deltaTime,new Point2D.Double(npc.getPosition().getX(),npc.getPosition().getY()-16));
            }
            else if(direction == Direction.LEFT){
                npc.moveTo(deltaTime,new Point2D.Double(npc.getPosition().getX()-16,npc.getPosition().getY()));
            }
            else if(direction == Direction.RIGHT){
                npc.moveTo(deltaTime,new Point2D.Double(npc.getPosition().getX()+16,npc.getPosition().getY()));
            }
            else {
                npc.moveTo(deltaTime,new Point2D.Double(npc.getPosition().getX(),npc.getPosition().getY()));
            }
        }

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
