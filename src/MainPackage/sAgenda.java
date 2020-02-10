package MainPackage;

import Data.Agenda;
import Data.Lesson;
import OOFramework.FrameworkProgram;
import OOFramework.Renderable;
import OOFramework.StandardObject;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import javax.xml.soap.Text;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class sAgenda extends StandardObject {
    private FXGraphics2D graphics2D;
    private Canvas canvas;
    private Stage stage;

    private ArrayList<HourBlock> hourBlocks;

    private HourBlock hourBlock;
    private Renderable hourBlock2;
    private Agenda agenda;

    private double hours;
    private double rooms;

    double xStepSize;
    double yStepSize;

    private double correctionX;
    private double correctionY;

    protected sAgenda(FrameworkProgram frameworkProgram, Agenda agenda) {
        //the agenda uses input, the standard logic loop and a render loop
        super(frameworkProgram, true, true, true, true);

        //you can give these two in the constructor but here i get them from the getters in the framework
        this.graphics2D = frameworkProgram.getGraphics2D();
        this.canvas = frameworkProgram.getCanvas();
        this.stage = frameworkProgram.getStage();
        this.agenda = agenda;
        this.hours = 12;
        this.rooms = 5;
        this.xStepSize = canvas.getWidth() / hours;
        this.yStepSize = canvas.getHeight() / rooms;

    }

    @Override
    protected void Awake() {
        super.Awake();
    }

    @Override
    protected void Sleep() {
        super.Sleep();
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
    protected void InputLoop(double deltaTime) {
        super.InputLoop(deltaTime);
        //lets put stuff like adding data for a lesson here

        this.correctionX = 0.0;
        this.correctionY = 0.0;

        ArrayList<Lesson> lessons = agenda.getLessons();
        this.hourBlocks.clear();
        //Color[] colors = {Color.GREEN,Color.RED,Color.BLACK,Color.BLUE,Color.PINK,Color.MAGENTA};
        for (Lesson lesson : lessons) {
            double begin = (lesson.getBeginTime().getHour() - 8) + (lesson.getBeginTime().getMinute() / 60.0);
            double width = (lesson.getEndTime().getHour() - 8) + (lesson.getEndTime().getMinute() / 60.0) - begin;
            Point2D point = new Point2D.Double(begin * xStepSize - this.correctionX, yStepSize * (lesson.getClassRoom().getRoomName() - 300) - this.correctionY);
            Shape shape = new Rectangle2D.Double(begin * xStepSize - this.correctionX, yStepSize * (lesson.getClassRoom().getRoomName() - 300) - this.correctionY, width * xStepSize, yStepSize);
            hourBlocks.add(new HourBlock(shape, point, lesson, Color.CYAN));
            this.correctionX = this.correctionX + 0.6;
            this.correctionY = this.correctionY + 0.65;
        }


    }

    @Override
    protected void MainLoop(double deltaTime) {
        super.MainLoop(deltaTime);
        //do general stuff here, no clear idea what yet because i dont think a agenda has much logic now that i think about it
    }

    @Override
    protected void RenderLoop(double deltaTime) {
        super.RenderLoop(deltaTime);
        //lets draw all stuff here

        this.canvas.setWidth(this.stage.getWidth() * 0.95);
        this.canvas.setHeight(this.stage.getHeight() * 0.85);

        graphics2D.setColor(Color.white);
        graphics2D.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

//        graphics2D.fillRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

        this.xStepSize = canvas.getWidth() / hours;
        this.yStepSize = canvas.getHeight() / rooms;

        graphics2D.setColor(Color.black);

        for (int i = 0; i < 24; i++) {
            graphics2D.drawLine((int) xStepSize * i, 0, (int) xStepSize * i, (int) canvas.getHeight());
        }

        for (int i = 0; i < 5; i++) {
            graphics2D.drawLine(0, (int) yStepSize * i, (int) canvas.getWidth(), (int) yStepSize * i);
        }

        for (HourBlock h : hourBlocks) {

            h.draw(graphics2D);
        }
        //graphics2D.fill(hourBlock2.getTransformedShape());

        //renderable has a draw function as well, you can choose if you want to draw it here or there.
        //  this.stage.setScene(new Scene(borderPane));
    }


    public BorderPane sceneAgenda() {

        BorderPane agendaPane = new BorderPane();
        HBox buttonBox = new HBox();

        Button save = new Button("SAVE");
        Button delete = new Button("DELETE");
        Button edit = new Button("EDIT");
        Button newOne = new Button("NEW");

        buttonBox.getChildren().addAll(save, delete, edit, newOne);

        VBox roomNames = new VBox();
        roomNames.setSpacing(this.canvas.getHeight()/ 5);
        for (int i = 0; i <= 4; i++) {
            roomNames.getChildren().add(new Label("LA30" + i));
        }


        HBox times = new HBox();
        times.setSpacing(this.canvas.getWidth() / 24);

        times.getChildren().add(new Label("Time: \n \n Room:"));
        for (int i = 8; i <= 20; i++) {
            times.getChildren().add(new Label(i + ":00"));
        }

        agendaPane.setTop(times);
        agendaPane.setLeft(roomNames);
        agendaPane.setBottom(buttonBox);
        agendaPane.setCenter(this.canvas);

        return agendaPane;
    }

}