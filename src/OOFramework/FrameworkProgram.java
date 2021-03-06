package OOFramework;

import OOFramework.Modules.CONSTANTS;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

import static OOFramework.Modules.CONSTANTS.*;

public abstract class FrameworkProgram extends Application
{
    private boolean running = false;
    private boolean paused  = false;

    private ArrayList<BaseObject> objects = new ArrayList<BaseObject>();
    private double factor;

    private ArrayList<StandardObject> inputObjects  = new ArrayList<StandardObject>();
    private ArrayList<StandardObject> mainObjects   = new ArrayList<StandardObject>();
    private ArrayList<StandardObject> renderObjects = new ArrayList<StandardObject>();

    private double deltaTime = 0;

    protected Stage stage;
    protected Canvas canvas;
    protected FXGraphics2D graphics2D;

    protected Canvas canvasSimulation;
    protected FXGraphics2D graphics2DSimulation;

    protected MenuBar menuBar; // even laten staan voor mezelf, het moet ergens omen te staan waar het makkelijk de heletijd toe gevoegd kan worden

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        this.stage.setWidth(1650);
        this.stage.setHeight(1000);

        this.canvas = new Canvas(this.stage.getWidth(), this.stage.getHeight());
        this.canvasSimulation = new Canvas(this.stage.getWidth(), this.stage.getHeight());

        this.graphics2D = new FXGraphics2D(canvas.getGraphicsContext2D());
        this.graphics2DSimulation = new FXGraphics2D(canvasSimulation.getGraphicsContext2D());
//        this.stage.setMaximized(true);
//        this.stage.setFullScreen(true);
//        this.stage.setResizable(false);


        this.stage.setScene(new Scene(new Group(canvas)));
        this.stage.setTitle(TITLE);
        this.stage.getIcons().add(new Image(PROGRAM_ICON));
        this.stage.show();

        this.Init();

        new AnimationTimer() {
            long last = -1;
            @Override
            public void handle(long now) {
                if (last == -1)
                    last = now;
                Run(graphics2D);
                last = now;
            }
        }.start();
    }

    long lastTime = System.nanoTime();

    public void Run(FXGraphics2D g2d) {

        /**
         * calculate deltatime
         */
        long time = System.nanoTime();
        deltaTime = ((double) (time - lastTime) / 1000_000_000) * factor;//delta time in seconds
        lastTime = time;

        //uncomment to print the deltatime in seconds
        //String s = String.format("%.5f", deltaTime);
        //System.out.println(s);

        AddToLoop();

        for (StandardObject object : inputObjects) {
            object.InputLoop(deltaTime);
        }

        for (StandardObject object : mainObjects) {
            object.MainLoop(deltaTime);
        }
        //clear the screen
        g2d.setBackground(Color.white);
        g2d.clearRect(0,0,1920,1080);

        for (StandardObject object : renderObjects) {
            object.RenderLoop(deltaTime);
        }

        Iterator<BaseObject> it = objects.iterator();
        while (it.hasNext()) {
            BaseObject bo = it.next();
            if (bo.ShouldDestruct()) {
                bo.Destroy();
                it.remove();
            } else if (bo.isActive() && !bo.isActivated()) {
                bo.AddToLists();
                bo.setActivated(true);
                bo.Awake();
            } else if (!bo.isActive() && bo.isActivated()) {
                bo.RemoveFromLists();
                bo.setActivated(false);
                bo.Sleep();
            }
        }

        //here lies the thread.sleep
        //if we have above 60 frames in the end of the project, it will be added here to save battery
        //Etienne/Joep if you see this, Sjoerd stopped the group from adding it in the begin..... fite me
    }

    protected void Init()
    {
        this.running = true;
    }

    protected void AddToLoop()
    {

    }

    protected void ExitProgram()
    {
        this.running = false;
    }


    public boolean isRunning()
    {
        return this.running;
    }

    public boolean isPaused()
    {
        return this.paused;
    }

    public Stage getStage() {
        return stage;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public FXGraphics2D getGraphics2D()
    {
        return graphics2D;
    }

    public ArrayList<BaseObject> getObjects()
    {
        return this.objects;
    }

    public ArrayList<StandardObject> getInputObjects()
    {
        return inputObjects;
    }

    public ArrayList<StandardObject> getMainObjects()
    {
        return mainObjects;
    }

    public ArrayList<StandardObject> getRenderObjects()
    {
        return renderObjects;
    }

    public Canvas getCanvasSimulation() {
        return canvasSimulation;
    }

    public FXGraphics2D getGraphics2DSimulation() {
        return graphics2DSimulation;
    }

    public void setFactor(double factor){
        this.factor = factor;
    }
}

