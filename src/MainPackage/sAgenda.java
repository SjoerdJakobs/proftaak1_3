package MainPackage;

import Data.Agenda;
import Data.Lesson;
import Data.Rooms.ClassRoom;
import Data.StudentGroup;
import Data.Teacher;
import OOFramework.FrameworkProgram;
import OOFramework.Renderable;
import OOFramework.StandardObject;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import javax.xml.soap.Text;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class sAgenda extends StandardObject {
    private FXGraphics2D graphics2D;
    private Canvas canvas;
    private Stage stage;
    private HourBlock hourBlock;
    private Renderable hourBlock2;
    private Agenda agenda;

    private ArrayList<HourBlock> hourBlocks;
    private ArrayList<StudentGroup> groups = new ArrayList<>();
    private ArrayList<Lesson> lessons;


    private double hours;
    private double rooms;

    double xStepSize;
    double yStepSize;

    protected sAgenda(FrameworkProgram frameworkProgram, Agenda agenda) {
        //the agenda uses input, the standard logic loop and a render loop
        super(frameworkProgram, true, true, true, true);

        //you can give these two in the constructor but here i get them from the getters in the framework
        this.graphics2D = frameworkProgram.getGraphics2D();
        this.canvas = frameworkProgram.getCanvas();
        this.stage = frameworkProgram.getStage();
        this.agenda = agenda;
        this.hours = 13;
        this.rooms = 6;
        this.canvas.setWidth(1625);
        this.canvas.setHeight(900);
        this.xStepSize = canvas.getWidth() / hours;
        this.yStepSize = canvas.getHeight() / rooms;
        this.lessons = agenda.getLessons();

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

        this.canvas.setOnMouseClicked(e -> {
            for (HourBlock block : this.hourBlocks) {


                if (e.getX() > block.getTransformedShape().getBounds2D().getMinX() + xStepSize && e.getX() < block.getTransformedShape().getBounds2D().getMaxX() + xStepSize) {
                    if (e.getY() > block.getTransformedShape().getBounds2D().getMinY() + yStepSize && e.getY() < block.getTransformedShape().getBounds2D().getMaxY() + yStepSize) {

                        Stage popUpEdit = new Stage();
                        popUpEdit.initOwner(this.stage);
                        popUpEdit.initModality(Modality.APPLICATION_MODAL);

                        HBox hbox = makeSceneEditLesson();

                        VBox popVBoxInformation = new VBox(10);

                        TextField beginTime = new TextField();
                        TextField endTime = new TextField();
                        ComboBox<StudentGroup> group = new ComboBox();
                        ComboBox<Teacher> teacher = new ComboBox();
                        ComboBox<ClassRoom> room = new ComboBox();

                        Label warning = new Label();

                        this.groups.clear();
                        this.lessons = agenda.getLessons();
                        for (Lesson lesson : lessons) {
                            this.groups.add(lesson.getStudentGroup());
                            teacher.getItems().add(lesson.getTeacher());
                            room.getItems().add(lesson.getClassRoom());
                        }
                        for (StudentGroup sg : this.groups) {
                            group.getItems().add(sg);
                        }


                        popVBoxInformation.getChildren().add(beginTime);
                        popVBoxInformation.getChildren().add(endTime);
                        popVBoxInformation.getChildren().add(group);
                        popVBoxInformation.getChildren().add(teacher);
                        popVBoxInformation.getChildren().add(room);

                        VBox buttons = new VBox(20);
                        Button saveEdit = new Button("SAVE");

                        saveEdit.setOnAction(event -> {
                            try {
                                if (beginTime.getText().isEmpty() || LocalTime.parse(beginTime.getText()).getHour() < 8 || LocalTime.parse(beginTime.getText()).getHour() > 20 || LocalTime.parse(beginTime.getText()).getHour() == 20 && LocalTime.parse(beginTime.getText()).getMinute() > 0) {
                                    warning.setText("please enter a correct value at begin time, between 8 and 20.\n Make sure the notation is correct (for example 08:00, two digits each side.");
                                } else if (endTime.getText().isEmpty() || LocalTime.parse(endTime.getText()).getHour() < 8 || LocalTime.parse(endTime.getText()).getHour() > 20 || LocalTime.parse(endTime.getText()).getHour() == 20 && LocalTime.parse(endTime.getText()).getMinute() > 0 || ((LocalTime.parse(beginTime.getText()).getHour() * 60) + LocalTime.parse(beginTime.getText()).getMinute()) > ((LocalTime.parse(endTime.getText()).getHour() * 60) + LocalTime.parse(endTime.getText()).getMinute())) {
                                    warning.setText("please enter a correct value at end time, between 8 and 20.\n Make sure the end time is later than the begin time.\n And make sure the notation is correct (for example 08:00, two digits each side).");
                                } else if (group.getSelectionModel().isEmpty()) {
                                    warning.setText("please enter a correct value at group.");
                                } else if (teacher.getSelectionModel().isEmpty()) {
                                    warning.setText("please enter a correct value at teacher.");
                                } else {

                                    block.getLesson().setBeginTime(LocalTime.parse(beginTime.getText()));
                                    block.getLesson().setEndTime(LocalTime.parse(endTime.getText()));
                                    block.getLesson().setStudentGroup(group.getValue());
                                    block.getLesson().setClassRoom(room.getValue());
                                    block.getLesson().setTeacher(teacher.getValue());

                                    popUpEdit.close();
                                }
                            } catch (DateTimeParseException dtpe) {
                                warning.setText("Please enter a valid time in a form of 08:00, not something else!");
                            }


                        });


                        Button deleteEdit = new Button("DELETE");
                        deleteEdit.setOnAction(event -> {

                            this.lessons.remove(block.getLesson());
                            popUpEdit.close();

                        });

                        buttons.getChildren().addAll(saveEdit, deleteEdit);

                        hbox.getChildren().addAll(popVBoxInformation, buttons, warning);
                        Scene popScene = new Scene(hbox, 800, 400);

                        popUpEdit.setScene(popScene);
                        popUpEdit.show();

                    }
                }
            }

        });


    }

    @Override
    protected void MainLoop(double deltaTime) {
        super.MainLoop(deltaTime);
        //do general stuff here, no clear idea what yet because i dont think a agenda has much logic now that i think about it

        this.hourBlocks.clear();
        //Color[] colors = {Color.GREEN,Color.RED,Color.BLACK,Color.BLUE,Color.PINK,Color.MAGENTA};
        this.lessons = agenda.getLessons();
        for (Lesson lesson : lessons) {
            double begin = (lesson.getBeginTime().getHour() - 8) + (lesson.getBeginTime().getMinute() / 60.0);
            double width = (lesson.getEndTime().getHour() - 8) + (lesson.getEndTime().getMinute() / 60.0) - begin;
            Point2D point = new Point2D.Double(begin * xStepSize, yStepSize * (lesson.getClassRoom().getRoomName() - 300));
            Shape shape = new Rectangle2D.Double(begin * xStepSize, yStepSize * (lesson.getClassRoom().getRoomName() - 300), width * xStepSize, yStepSize);
            hourBlocks.add(new HourBlock(shape, point, lesson, Color.CYAN));
        }
    }

    @Override
    protected void RenderLoop(double deltaTime) {
        super.RenderLoop(deltaTime);
        //lets draw all stuff here

        graphics2D.setColor(Color.white);
        graphics2D.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());
//        graphics2D.fillRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

        this.xStepSize = canvas.getWidth() / hours;
        this.yStepSize = canvas.getHeight() / rooms;

        graphics2D.setColor(Color.black);

        for (int i = 0; i < hours; i++) {
            graphics2D.drawLine((int) xStepSize * i, 0, (int) xStepSize * i, (int) canvas.getHeight());
        }
        for (int i = 0; i < rooms; i++) {
            graphics2D.drawLine(0, (int) yStepSize * i, (int) canvas.getWidth(), (int) yStepSize * i);
        }
        for (int i = 0; i < hours; i++) {
            graphics2D.drawString(((i + 8) + ":00"), (int) (xStepSize * i + xStepSize), 10);
        }
        for (int i = 0; i < rooms; i++) {
            graphics2D.drawString("LA:" + (i + 300), 0, (int) (yStepSize * i + yStepSize + 10));
        }

        graphics2D.translate(xStepSize, yStepSize);

        for (HourBlock h : hourBlocks) {

            h.draw(graphics2D);
        }

        graphics2D.translate(-xStepSize, -yStepSize);
        //graphics2D.fill(hourBlock2.getTransformedShape());

        //renderable has a draw function as well, you can choose if you want to draw it here or there.
        //  this.stage.setScene(new Scene(borderPane));
    }


    public BorderPane sceneAgenda() {

        BorderPane agendaPane = new BorderPane();
        HBox buttonBox = new HBox(30);

        Button newOne = new Button("NEW");
        Button saveAgenda = new Button("SAVE");

        Button savePopUp = new Button("SAVE");
        Button delete = new Button("DELETE");
        Button edit = new Button("EDIT");


        newOne.setOnAction(e -> {
            final Stage popUpNew = new Stage();
            popUpNew.initOwner(this.stage);
            popUpNew.initModality(Modality.APPLICATION_MODAL);
            popUpNew.setTitle("create a lesson");

            HBox hbox = makeSceneEditLesson();
            VBox popVBoxInformation = new VBox(10);
            TextField beginTime = new TextField();
            TextField endTime = new TextField();
            ComboBox<StudentGroup> group = new ComboBox();
            ComboBox<Teacher> teachers = new ComboBox<>();
            ComboBox<ClassRoom> rooms = new ComboBox<>();

            this.groups.clear();
            this.lessons = agenda.getLessons();
            for (Lesson lesson : lessons) {
                this.groups.add(lesson.getStudentGroup());
                teachers.getItems().add(lesson.getTeacher());
                rooms.getItems().add(lesson.getClassRoom());
            }
            for (StudentGroup sg : this.groups) {
                group.getItems().add(sg);
            }


            popVBoxInformation.getChildren().add(beginTime);
            popVBoxInformation.getChildren().add(endTime);
            popVBoxInformation.getChildren().add(group);
            popVBoxInformation.getChildren().add(teachers);
            popVBoxInformation.getChildren().add(rooms);

            Label warningLabel = new Label();

            savePopUp.setOnAction(ex -> {
                try {
                    if (beginTime.getText().isEmpty() || LocalTime.parse(beginTime.getText()).getHour() < 8 || LocalTime.parse(beginTime.getText()).getHour() > 20 || LocalTime.parse(beginTime.getText()).getHour() == 20 && LocalTime.parse(beginTime.getText()).getMinute() > 0) {
                        warningLabel.setText("please enter a correct value at begin time, between 8 and 20.\n Make sure the notation is correct (for example 08:00, two digits each side.");
                    } else if (endTime.getText().isEmpty() || LocalTime.parse(endTime.getText()).getHour() < 8 || LocalTime.parse(endTime.getText()).getHour() > 20 || LocalTime.parse(endTime.getText()).getHour() == 20 && LocalTime.parse(endTime.getText()).getMinute() > 0 || ((LocalTime.parse(beginTime.getText()).getHour() * 60) + LocalTime.parse(beginTime.getText()).getMinute()) > ((LocalTime.parse(endTime.getText()).getHour() * 60) + LocalTime.parse(endTime.getText()).getMinute())) {
                        warningLabel.setText("please enter a correct value at end time, between 8 and 20.\n Make sure the end time is later than the begin time.\n And make sure the notation is correct (for example 08:00, two digits each side).");
                    } else if (group.getSelectionModel().isEmpty()) {
                        warningLabel.setText("please enter a correct value at group.");
                    } else if (teachers.getSelectionModel().isEmpty()) {
                        warningLabel.setText("please enter a correct value at teacher.");
                    } else {

                        this.agenda.addLesson(new Lesson(group.getValue(), teachers.getValue(), LocalTime.parse(beginTime.getText()), LocalTime.parse(endTime.getText()), rooms.getValue()));
                        popUpNew.close();
                    }
                } catch (DateTimeParseException dtpe) {
                    warningLabel.setText("Please enter a valid time in a form of 08:00, not something else!");
                }
            });

            hbox.getChildren().addAll(popVBoxInformation, savePopUp, warningLabel);
            Scene popScene = new Scene(hbox, 800, 400);
            popUpNew.setScene(popScene);
            popUpNew.show();
        });

        buttonBox.getChildren().addAll(newOne, saveAgenda);
        agendaPane.setBottom(buttonBox);
        agendaPane.setTop(this.canvas);


        return agendaPane;
    }


    private HBox makeSceneEditLesson() {
        HBox popHBox = new HBox(20);

        VBox popVBoxLabels = new VBox(20);

        popVBoxLabels.getChildren().add(new Label("Begin time: "));
        popVBoxLabels.getChildren().add(new Label("End time: "));
        popVBoxLabels.getChildren().add(new Label("Group: "));
        popVBoxLabels.getChildren().add(new Label("Teacher: "));
        popVBoxLabels.getChildren().add(new Label("Classroom: "));

        popHBox.getChildren().addAll(popVBoxLabels);
        return popHBox;
    }

}