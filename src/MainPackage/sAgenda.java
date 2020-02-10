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

    private Scene sceneAgenda;

    private ArrayList<HourBlock> hourBlocks;

    private HourBlock hourBlock;
    private Renderable hourBlock2;
    private Agenda agenda;

<<<<<<< HEAD
    private int rows = 6;
    private int columns = 12;

    private int horStepsize;
    private int vertStepsize;

    protected sAgenda(FrameworkProgram frameworkProgram) {
=======
    private double hours;
    private double rooms;

    double xStepSize;
    double yStepSize;

    protected sAgenda(FrameworkProgram frameworkProgram, Agenda agenda)
    {
>>>>>>> Fabian
        //the agenda uses input, the standard logic loop and a render loop
        super(frameworkProgram, true, true, true, true);

        //you can give these two in the constructor but here i get them from the getters in the framework
        this.graphics2D = frameworkProgram.getGraphics2D();
<<<<<<< HEAD
        this.canvas = frameworkProgram.getCanvas();
        this.stage = frameworkProgram.getStage();

    }

    @Override
    protected void Awake() {
        super.Awake();
    }

    @Override
    protected void Sleep() {
        super.Sleep();
=======
        this.canvas =     frameworkProgram.getCanvas();
        this.stage =      frameworkProgram.getStage();
        this.agenda = agenda;
        this.hours = 12;
        this.rooms = 5;
        this.xStepSize = canvas.getWidth()/hours;
        this.yStepSize = canvas.getHeight()/rooms;
>>>>>>> Fabian
    }

    @Override
    protected void Start() {
        super.Start();
        hourBlocks = new ArrayList<HourBlock>();


        //create a simple rectangle, just repeat this code for more
<<<<<<< HEAD
        this.hourBlock = new HourBlock(new Rectangle2D.Double(-50, -50, 100, 100), new Point2D.Double(400, 400), 10, 12, "klas", "LA 134", "leraar", Color.red);
        hourBlocks.add(hourBlock);
        hourBlocks.add(new HourBlock(new Rectangle2D.Double(-50, -50, 100, 100), new Point2D.Double(600, 400), 14, 15, "klas2", "LD 112", "leraar2", Color.green));
        hourBlocks.add(new HourBlock(new Rectangle2D.Double(-50, -50, 100, 100), new Point2D.Double(800, 400), 14, 15, "klas3", "LD 114", "leraar3", Color.yellow));
=======
//        ArrayList<Lesson> lessons = agenda.getLessons();
//
//        for(Lesson lesson : lessons){
//            double begin = (lesson.getBeginTime().getHour()-8)+(lesson.getBeginTime().getMinute()/60.0);
//            double width = (lesson.getEndTime().getHour()-8)+(lesson.getEndTime().getMinute()/60.0);
//            Point2D point = new Point2D.Double(0,0);
//            Shape shape = new Rectangle2D.Double(begin,0,width,100);
//            hourBlocks.add(new HourBlock(shape,point,lesson,Color.GREEN));
//        }
>>>>>>> Fabian
        //this.hourBlock2 = new Renderable(new Rectangle2D.Double(-50, -50, 100, 100), new Point2D.Double(350, 350), 0 * (float) Math.PI, 1);

//        this.horStepsize = (int)this.canvas.getWidth()/this.rows;
//        this.vertStepsize = (int)this.canvas.getHeight()/this.columns;

//        System.out.println(this.canvas.getWidth());
    }

    @Override
    protected void InputLoop(double deltaTime) {
        super.InputLoop(deltaTime);
        //lets put stuff like adding data for a lesson here
        ArrayList<Lesson> lessons = agenda.getLessons();
        //Color[] colors = {Color.GREEN,Color.RED,Color.BLACK,Color.BLUE,Color.PINK,Color.MAGENTA};
        for(Lesson lesson : lessons){
            double begin = (lesson.getBeginTime().getHour()-8)+(lesson.getBeginTime().getMinute()/60.0);
            double width = (lesson.getEndTime().getHour()-8)+(lesson.getEndTime().getMinute()/60.0)-begin;
            System.out.println(begin + " " + width);
            Point2D point = new Point2D.Double(begin*xStepSize,yStepSize*0);
            Shape shape = new Rectangle2D.Double(begin*xStepSize,yStepSize*0,width*xStepSize,yStepSize);
            hourBlocks.add(new HourBlock(shape,point,lesson,Color.CYAN));
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

<<<<<<< HEAD
        this.canvas.setWidth(this.stage.getWidth()*0.9);
        this.canvas.setHeight(this.stage.getHeight()*0.9);


        //Horizontal lines
//        for (int i = 0; i < this.rows; i++) {
//            this.graphics2D.drawLine(0, horStepsize * i, (int) canvas.getWidth(), horStepsize * i);
//        }
//
//        //Vertical Lines
//        for (int i = 0; i < this.columns; i++) {
//            this.graphics2D.drawLine(vertStepsize * i, 0, vertStepsize * i, (int) canvas.getHeight());
//            //  g2d.drawString((i + 8) + ":00", i * vertStepsize, 10);
//        }
//
//        for (int i = 0; i < agenda.getLessons().size(); i++) {
//            g2d.setColor(new Color((int) (Math.random() * 0x1000000)));
//            double factorBegin = (agenda.getLessons().get(i).getBeginTime().getHour() - 8) + (agenda.getLessons().get(i).getBeginTime().getMinute() / 60.0);
//            double factorEnd = (agenda.getLessons().get(i).getEndTime().getHour() - 8) + (agenda.getLessons().get(i).getEndTime().getMinute() / 60.0);
//            Rectangle2D shape = new Rectangle2D.Double(vertStepsize * factorBegin, horStepsize * 6, vertStepsize * (factorEnd - factorBegin), horStepsize);
//            // DOOR MIDDEL VAN DE Y COORDINAAT TE STELLEN ALS DE horStepsize KAN JE DE POSITIE VAN DE VAKJES BEINVLOEDEN VAN DE VERTICALE POSITIE,
//            // DOOR HET TE VERMENIGVULDIGEN VOOR WELKE RIJ JE HET WILT HEBBEN.
//            g2d.draw(shape);
//            g2d.fill(shape);
//
//        }
//
//        g2d.clearRect(0, 0, 1920, 1080);
//        g2d.setStroke(new BasicStroke(1));
//        //g2d.draw(new Rectangle2D.Double(position.getX() - 50, position.getY() - 50, 100, 100));
//        vertStepsize = (int) ((canvas.getWidth() - vBox.getWidth()) / this.columns);
//        horStepsize = (int) canvas.getHeight() / this.rows;
//
//
//        vBox.setSpacing((1080 - rows * fontSize) / rows);
//

        for (HourBlock h : hourBlocks) {
=======
        for(int i=0; i<24; i++){
            graphics2D.drawLine((int)xStepSize*i,0,(int)xStepSize*i,(int)canvas.getHeight());
        }

        for(int i=0; i<5; i++){
            graphics2D.drawLine(0,(int)yStepSize*i,(int)canvas.getWidth(),(int)yStepSize*i);
        }

        for(HourBlock h : hourBlocks)
        {
>>>>>>> Fabian
            h.draw(graphics2D);
        }
        //graphics2D.fill(hourBlock2.getTransformedShape());

        //renderable has a draw function as well, you can choose if you want to draw it here or there.
      //  this.stage.setScene(new Scene(borderPane));
    }


    public BorderPane sceneAgenda(){

        BorderPane agendaPane = new BorderPane();
        HBox buttonBox = new HBox();

        Button save = new Button("SAVE");
        Button delete = new Button("DELETE");
        Button edit = new Button("EDIT");
        Button newOne = new Button("NEW");

        buttonBox.getChildren().addAll(save, delete, edit, newOne);

        VBox roomNames = new VBox();

        HBox times = new HBox();


        agendaPane.setTop(times);
        agendaPane.setLeft(roomNames);
        agendaPane.setBottom(buttonBox);
        agendaPane.setRight(this.canvas);

        return agendaPane;
    }

}