package MainPackage;

import Data.Agenda;
import Data.Lesson;
import Data.Rooms.ClassRoom;
import Data.StudentGroup;
import Data.Teacher;
import OOFramework.ExampleClasses.ExampleStudent;
import OOFramework.FrameworkProgram;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Program extends FrameworkProgram
{
    private ResizableCanvas canvas;
    private VBox vBox;
    public Point2D position = new Point2D.Double(100, 100);

    private int rows = 8;
    private int columns = 12;
    private int fontSize=15;
    Agenda agenda;
    int vertStepsize=0;
    int horStepsize=0;

    @Override
    public void start(Stage stage) throws Exception {
        super.start(stage);
        //stage.setMaximized(true);
        //stage.setFullScreen(true);
        //stage.setResizable(false);
        BorderPane mainPane = new BorderPane();
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(new Menu("Agenda"), new Menu("Simulation"));

        BorderPane borderPane = new BorderPane();

        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        vBox = new VBox();
        borderPane.setRight(canvas);
        borderPane.setLeft(vBox);

        vBox.setSpacing((1080-rows*fontSize)/rows);

        String[] rowLabels = {"unos","dos","tres", "quadros", "vijf", "zes", "seven", "eight", "nein", "tin"};

        for (int i = 0; i < rowLabels.length && i < this.rows; i++) {
            Label label = new Label(rowLabels[i]);
            label.setFont(new Font(fontSize));
            this.vBox.getChildren().add(new Label(rowLabels[i]));
        }


        mainPane.setCenter(borderPane);
        mainPane.setTop(menuBar);
        stage.setScene(new Scene(mainPane));
        stage.setTitle("Block Dragging");
        stage.show();

        canvas.setOnMousePressed(e -> mousePressed(e));
        canvas.setOnMouseReleased(e -> mouseReleased(e));
        canvas.setOnMouseDragged(e -> mouseDragged(e));

        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));

    }

    @Override
    public void draw(FXGraphics2D g2d) {
        super.draw(g2d);
        g2d.setBackground(Color.white);
        g2d.clearRect(0, 0, 1920, 1080);
        g2d.setStroke(new BasicStroke(1));
        //g2d.draw(new Rectangle2D.Double(position.getX() - 50, position.getY() - 50, 100, 100));
        vertStepsize = (int) ((canvas.getWidth() - vBox.getWidth()) / this.columns);
        horStepsize = (int) canvas.getHeight() / this.rows;

        //Horizontal lines
        for (int i = 0; i < this.rows; i++) {
            g2d.drawLine(0, horStepsize * i, (int) canvas.getWidth(), horStepsize * i);
        }

        //Vertical Lines
        for (int i = 0; i < this.columns; i++) {
            g2d.drawLine(vertStepsize * i, 0, vertStepsize * i, (int) canvas.getHeight());
            g2d.drawString((i + 8) + ":00", i * vertStepsize, 10);
        }
        
        for (int i = 0; i < agenda.getLessons().size(); i++) {
            g2d.setColor(new Color((int) (Math.random() * 0x1000000)));
            double factorBegin = (agenda.getLessons().get(i).getBeginTime().getHour()-8)+(agenda.getLessons().get(i).getBeginTime().getMinute()/60.0);
            double factorEnd = (agenda.getLessons().get(i).getEndTime().getHour()-8)+(agenda.getLessons().get(i).getEndTime().getMinute()/60.0);
            Rectangle2D shape = new Rectangle2D.Double(vertStepsize * factorBegin, 0, vertStepsize*(factorEnd-factorBegin), horStepsize);
            g2d.draw(shape);
            g2d.fill(shape);

        }
    }

    @Override
    protected void Init() {
        super.Init();
        agenda= new Agenda();
        Teacher teacher = new Teacher(this, "Johan");
        System.out.println("Hallo");

        for(int i=0; i<5; i++){
            Lesson lesson = new Lesson(new StudentGroup("KlasB"),teacher, LocalTime.of(8+i,55),LocalTime.of(9+i,30),new ClassRoom("LA301"));
            agenda.addLesson(lesson);
        }

        Lesson les = new Lesson(new StudentGroup("KlasB"),teacher, LocalTime.of(14,34),LocalTime.of(17,56),new ClassRoom("LA301"));
        agenda.addLesson(les);
        for(int i=0; i<5; i++){
            //System.out.println(agenda.getLessons().get(i).getTeacher().getName());
            System.out.println(agenda.getLessons().get(i).getBeginTime()+ " " + agenda.getLessons().get(i).getEndTime());
        }


    }

    @Override
    protected void AddToLoop() {
        super.AddToLoop();


    }

    @Override
    protected void ExitProgram() {
        super.ExitProgram();
    }

    private void mousePressed(MouseEvent e) {
    }

    private void mouseReleased(MouseEvent e) {

    }

    private void mouseDragged(MouseEvent e) {
//        position = new Point2D.Double(e.getX(), e.getY());
//        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
    }
}
