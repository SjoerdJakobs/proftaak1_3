package OOFramework;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class FrameworkProgram extends Application
{
    private boolean running = false;
    private boolean paused  = false;

    private ArrayList<BaseObject> objects = new ArrayList<BaseObject>();

    private ArrayList<StandardObject> inputObjects  = new ArrayList<StandardObject>();
    private ArrayList<StandardObject> mainObjects   = new ArrayList<StandardObject>();
    private ArrayList<StandardObject> renderObjects = new ArrayList<StandardObject>();

    private double deltaTime = 0;

    protected Stage stage;
    protected double angle = 0.0;
    protected Canvas canvas;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        canvas = new Canvas(1920, 1080);
        FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());
        primaryStage.setScene(new Scene(new Group(canvas)));
        primaryStage.setTitle("Hello Animation");
        primaryStage.show();

        this.Init();

        new AnimationTimer() {
            long last = -1;
            @Override
            public void handle(long now) {
                if (last == -1)
                    last = now;
                Run(g2d);
                last = now;
            }
        }.start();
    }

    public void draw(FXGraphics2D g2d) {
        g2d.setBackground(Color.white);
        g2d.clearRect(0,0,1920,1080);
        //AffineTransform tx = new AffineTransform();
        //tx.translate(1920/2, 1080/2);
        //tx.rotate(angle);
        //tx.translate(200, 0);
        //g2d.fill(tx.createTransformedShape(new Rectangle2D.Double(-50,-50,100,100)));
    }

    long lastTime = System.nanoTime();

    public void Run(FXGraphics2D g2d) {

        /**
         * calculate deltatime
         */
        long time = System.nanoTime();
        deltaTime = ((double) (time - lastTime) / 1000_000_000);//delta time in seconds
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
        for (StandardObject object : renderObjects) {
            object.RenderLoop(deltaTime);
        }

        draw(g2d);

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
}

