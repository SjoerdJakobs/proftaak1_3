package MainPackage;

import Data.Agenda;
import Data.Lesson;
import OOFramework.FrameworkProgram;
import OOFramework.Renderable;
import OOFramework.StandardObject;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class sAgenda extends StandardObject
{
    private FXGraphics2D graphics2D;
    private Canvas canvas;
    private Stage stage;

    private ArrayList<HourBlock> hourBlocks;

    private HourBlock hourBlock;
    private Renderable hourBlock2;
    private Agenda agenda;

    protected sAgenda(FrameworkProgram frameworkProgram, Agenda agenda)
    {
        //the agenda uses input, the standard logic loop and a render loop
        super(frameworkProgram, true, true, true, true);

        //you can give these two in the constructor but here i get them from the getters in the framework
        this.graphics2D = frameworkProgram.getGraphics2D();
        this.canvas =     frameworkProgram.getCanvas();
        this.stage =      frameworkProgram.getStage();
        this.agenda = agenda;
    }

    @Override
    protected void Start() {
        super.Start();
        hourBlocks = new ArrayList<HourBlock>();

        //create a simple rectangle, just repeat this code for more
//        ArrayList<Lesson> lessons = agenda.getLessons();
//
//        for(Lesson lesson : lessons){
//            double begin = (lesson.getBeginTime().getHour()-8)+(lesson.getBeginTime().getMinute()/60.0);
//            double width = (lesson.getEndTime().getHour()-8)+(lesson.getEndTime().getMinute()/60.0);
//            Point2D point = new Point2D.Double(0,0);
//            Shape shape = new Rectangle2D.Double(begin,0,width,100);
//            hourBlocks.add(new HourBlock(shape,point,lesson,Color.GREEN));
//        }
        //this.hourBlock2 = new Renderable(new Rectangle2D.Double(-50, -50, 100, 100), new Point2D.Double(350, 350), 0 * (float) Math.PI, 1);

    }

    @Override
    protected void InputLoop(double deltaTime)
    {
        super.InputLoop(deltaTime);
        //lets put stuff like adding data for a lesson here
        ArrayList<Lesson> lessons = agenda.getLessons();
        //Color[] colors = {Color.GREEN,Color.RED,Color.BLACK,Color.BLUE,Color.PINK,Color.MAGENTA};
        for(Lesson lesson : lessons){
            double begin = (lesson.getBeginTime().getHour()-8)+(lesson.getBeginTime().getMinute()/60.0);
            double width = (lesson.getEndTime().getHour()-8)+(lesson.getEndTime().getMinute()/60.0)-begin;
            System.out.println(begin + " " + width);
            Point2D point = new Point2D.Double(begin*300,0);
            Shape shape = new Rectangle2D.Double(begin*300,0,width*100,100);
            hourBlocks.add(new HourBlock(shape,point,lesson,Color.CYAN));
        }
    }

    @Override
    protected void MainLoop(double deltaTime)
    {
        super.MainLoop(deltaTime);
        //do general stuff here, no clear idea what yet because i dont think a agenda has much logic now that i think about it
    }

    @Override
    protected void RenderLoop(double deltaTime)
    {
        super.RenderLoop(deltaTime);
        //lets draw all stuff here

        for(HourBlock h : hourBlocks)
        {
            h.draw(graphics2D);
        }
        //graphics2D.fill(hourBlock2.getTransformedShape());

        //renderable has a draw function as well, you can choose if you want to draw it here or there.
    }
}