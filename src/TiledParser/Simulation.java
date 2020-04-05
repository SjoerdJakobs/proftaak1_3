package TiledParser;

import Data.Lesson;
import MainPackage.ReadWriteData.DataClasses.GroupData;
import MainPackage.ReadWriteData.DataClasses.LessonData;
import MainPackage.ReadWriteData.DataClasses.StudentData;
import MainPackage.ReadWriteData.SavedData;
import MainPackage.Simulation.Npc.Npc;
import MainPackage.Simulation.Npc.Student;
import OOFramework.FrameworkProgram;
import OOFramework.Modules.CONSTANTS;
import OOFramework.StandardObject;
import gridMaker.Direction;
import gridMaker.GridMap;
import gridMaker.Tile;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalTime;
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
    private Tile[][] allTiles;

    private int timeHours;
    private double timeMinutes;

    private LocalTime time;

    private SavedData savedData = SavedData.INSTANCE;
    private boolean goToLesson = false;

    private TextField textField;

    public Simulation(FrameworkProgram frameworkProgram) {
        super(frameworkProgram);
        this.timeHours = 7;
        this.timeMinutes = 45;
        time = LocalTime.of(timeHours, (int) timeMinutes);
        this.canvas = frameworkProgram.getCanvasSimulation();
        this.graphics2D = frameworkProgram.getGraphics2DSimulation();
        this.stage = frameworkProgram.getStage();
        this.camera = new Camera(canvas, g -> draw(g), graphics2D);

        this.borderPane = new BorderPane();
        this.bottomPane = new GridPane();

        init();

        this.borderPane.setCenter(this.canvas);
        this.borderPane.setBottom(bottomPane);

//        npcs.add(new Student(getFrameworkProgram(), graphics2D, new Point2D.Double(18*16,19*16), ));
//        npcs.add(new Student(getFrameworkProgram(), graphics2D, new Point2D.Double(17*16,19*16)));
//        npcs.add(new Student(getFrameworkProgram(), graphics2D, new Point2D.Double(19*16,19*16)));
//        npcs.add(new Student(getFrameworkProgram(), graphics2D, new Point2D.Double(18*16,19*16)));
//        npcs.add(new Student(getFrameworkProgram(), graphics2D, new Point2D.Double(17*16,19*16)));


    }

    public void setAllNPCStudents() {

        this.npcs.clear();
        int index = 0;
        int xEntry = this.tileMap.getTileMapJSONParser().getBeginArea().getInt("x");
        int yEntry = this.tileMap.getTileMapJSONParser().getBeginArea().getInt("y");
        for (GroupData group : this.savedData.getGroupData()) {
            for (StudentData studentData : group.getStudentData()) {
//                npcs.add(new Student(getFrameworkProgram(), graphics2D, new Point2D.Double(18 * (16 + index), 19 * 16), studentData));
                npcs.add(new Student(getFrameworkProgram(), graphics2D, new Point2D.Double(xEntry-10+index, yEntry-200), studentData,this.allTiles));
                index++;
            }
        }
    }

    public void clearNPCS() {
        this.npcs.clear();
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
        this.allTiles = this.grid.getTiles();
    }

//    public void setLessonData(ArrayList<LessonData> lessonData){
//        this.lessonData = lessonData;
//    }

    @Override
    protected void InputLoop(double deltaTime) {
        super.InputLoop(deltaTime);
        timeMinutes += deltaTime;
    }

    @Override
    protected void MainLoop(double deltaTime) {
        super.MainLoop(deltaTime);
        if (timeMinutes >= 60) {
            timeMinutes = 0;
            timeHours++;
        }
        time = LocalTime.of(timeHours, (int) timeMinutes);
        this.canvas.setWidth(this.stage.getWidth());
        this.canvas.setHeight(this.stage.getHeight());

        for (LessonData lessonData : this.savedData.getLessonData()) {

            for (StudentData studentData : lessonData.getStudentGroup().getStudentData()) {
                for (Npc npc : this.npcs) {
                    if (npc.getStudentData().getName().equals(studentData.getName())) {
                        npc.getLessons().add(lessonData);
                    }

                }
            }
        }

        for(Npc npc : this.npcs){
            npc.sortList(this.time);
        }


        for (Npc npc : this.npcs) {

            if (this.time.isAfter(LocalTime.of(17, 0))) {

                ///Direction direction = this.allTiles[(int) (Math.round(npc.getPosition().getX() / 16))][(int) (Math.round(npc.getPosition().getY() / 16))].getDirections().get("entry");
                npc.setRouteName("entry");
                /**
                 * set the target to the exit of the building
                 */
            } else {

                if(npc.hasLessons()){
                    if (this.time.isAfter(npc.getLessons().get(0).getBeginTime()) && this.time.isBefore(npc.getLessons().get(0).getEndTime())) {
                        //Direction direction = this.allTiles[(int) (Math.round(npc.getPosition().getX() / 16))][(int) (Math.round(npc.getPosition().getY() / 16))].getDirections().get("LA" + npc.getLessons().get(0).getClassRoom().getRoomName());
                        npc.setRouteName("LA" + npc.getLessons().get(0).getClassRoom().getRoomName());

                        /**
                         * set target to classroom
                         */

                    } else {
                        //       npc.setMoved(true);
                        //Direction direction = this.allTiles[(int) (Math.round(npc.getPosition().getX() / 16))][(int) (Math.round(npc.getPosition().getY() / 16))].getDirections().get("canteen");
                        //  System.out.println(direction);
                        //System.out.println(lessonData.getClassRoom().getRoomName());
                        npc.setRouteName("canteen");
                        /**
                         * set target to canteen
                         */
                    }
                }

            }

        }

        for (Npc npc : this.npcs) {
          //  npc.setMoved(false);
            npc.getLessons().clear();
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
        graphics.setColor(Color.white);
        graphics.drawString(time.toString(), 100, 100);
        graphics.setTransform(camera.getTransform((int) canvas.getWidth(), (int) canvas.getHeight()));

        tileMap.draw(graphics, camera);
        //  this.grid.draw(graphics);

    }


    public BorderPane getBorderPane() {
        HBox hBox = new HBox();

        Button pause = new Button("0");
        pause.setOnAction(event -> {
            getFrameworkProgram().setFactor(0);
        });

        Button slowDown = new Button("0.5");
        slowDown.setOnAction(event -> {
            getFrameworkProgram().setFactor(0.5);
        });
        Button normal = new Button("1");
        normal.setOnAction(event -> {
            getFrameworkProgram().setFactor(1);
        });
        Button speedUp = new Button("2");
        speedUp.setOnAction(event -> {
            getFrameworkProgram().setFactor(2);
        });
        hBox.getChildren().addAll(pause, slowDown, normal, speedUp);
        borderPane.setTop(hBox);
        return this.borderPane;
    }

}
