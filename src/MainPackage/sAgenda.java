package MainPackage;

import Data.Rooms.ClassRoom;
import MainPackage.ReadWriteData.DataClasses.GroupData;
import MainPackage.ReadWriteData.DataClasses.LessonData;
import MainPackage.ReadWriteData.DataClasses.TeacherData;
import MainPackage.ReadWriteData.DataWriter;
import MainPackage.ReadWriteData.SavedData;
import OOFramework.FrameworkProgram;
import OOFramework.StandardObject;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class sAgenda extends StandardObject {
    private FXGraphics2D graphics2D;
    private Canvas canvas;
    private Stage stage;

    private ArrayList<HourBlock> hourBlocks;
    private ArrayList<GroupData> groups = new ArrayList<>();

    private double hours;
    private double rooms;

    double xStepSize;
    double yStepSize;

    private ArrayList<TeacherData> teachers;
    private ArrayList<ClassRoom> classrooms;
    private ArrayList<GroupData> studentGroups;

    private SavedData savedData = SavedData.INSTANCE;
    private DataWriter dataWriter;

    protected sAgenda(FrameworkProgram frameworkProgram, DataWriter dataWriter) {
        //the agenda uses input, the standard logic loop and a render loop
        super(frameworkProgram, true, true, true, true);
        this.dataWriter = dataWriter;
        //you can give these two in the constructor but here i get them from the getters in the framework
        this.graphics2D = frameworkProgram.getGraphics2D();
        this.canvas = frameworkProgram.getCanvas();
        this.stage = frameworkProgram.getStage();

        //Columns
        this.hours = 13;
        //Rows
        this.rooms = 6;

        this.canvas.setWidth(1625);
        this.canvas.setHeight(900);

        //This is to know when to start to draw the next line
        this.xStepSize = canvas.getWidth() / hours;
        this.yStepSize = canvas.getHeight() / rooms;

        teachers = new ArrayList<>();
        classrooms = new ArrayList<>();
        studentGroups = new ArrayList<>();

        //Load in teachers, classrooms and studentgroups for now manual
        teachers.add(new TeacherData("Johan", 30, 1, true));
        teachers.add(new TeacherData("Jessica", 30, 2, false));
        teachers.add(new TeacherData("Maurice", 30, 3, true));
        teachers.add(new TeacherData("Hans", 30, 4, true));
        teachers.add(new TeacherData("Joep", 30, 5, true));

        classrooms.add(new ClassRoom(300));
        classrooms.add(new ClassRoom(301));
        classrooms.add(new ClassRoom(302));
        classrooms.add(new ClassRoom(303));
        classrooms.add(new ClassRoom(304));

        studentGroups.add(new GroupData("A"));
        studentGroups.add(new GroupData("B"));
        studentGroups.add(new GroupData("C"));
        studentGroups.add(new GroupData("D"));
        studentGroups.add(new GroupData("E"));

        hourBlocks = new ArrayList<>();
    }

    @Override
    protected void InputLoop(double deltaTime) {
        super.InputLoop(deltaTime);
        this.canvas.setOnMouseClicked(e -> {
            //Check if the click is on an hourblock
            for (HourBlock block : this.hourBlocks) {
                if (e.getX() > block.getTransformedShape().getBounds2D().getMinX() + xStepSize && e.getX() < block.getTransformedShape().getBounds2D().getMaxX() + xStepSize) {
                    if (e.getY() > block.getTransformedShape().getBounds2D().getMinY() + yStepSize && e.getY() < block.getTransformedShape().getBounds2D().getMaxY() + yStepSize) {
                        //Call method to make an edit popUp
                        popup(block);
                    }
                }
            }

        });
    }

    @Override
    protected void MainLoop(double deltaTime) {
        super.MainLoop(deltaTime);
        this.hourBlocks.clear();
        //Convert lessons to hourblocks to draww later
        for (LessonData lesson : savedData.getLessonData()) {
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

        graphics2D.setColor(Color.white);
        graphics2D.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

        graphics2D.setColor(Color.black);

        //Draw vertical lines
        for (int i = 0; i < hours; i++) {
            graphics2D.drawLine((int) xStepSize * i, 0, (int) xStepSize * i, (int) canvas.getHeight());
        }
        //Draw horizontal lines
        for (int i = 0; i < rooms; i++) {
            graphics2D.drawLine(0, (int) yStepSize * i, (int) canvas.getWidth(), (int) yStepSize * i);
        }
        //Draw hours text
        for (int i = 0; i < hours; i++) {
            graphics2D.drawString(((i + 8) + ":00"), (int) (xStepSize * i + xStepSize), 10);
        }
        //Draw room text
        for (int i = 0; i < rooms; i++) {
            graphics2D.drawString("LA:" + (i + 300), 0, (int) (yStepSize * i + yStepSize + 10));
        }

        //Translate the canvas by one block so we can draw the block normally withouth worrying about a offset
        graphics2D.translate(xStepSize, yStepSize);

        //Draw the hourblocks
        for (HourBlock h : hourBlocks) {

            h.draw(graphics2D);
        }

        //Revert change
        graphics2D.translate(-xStepSize, -yStepSize);
    }


    public BorderPane sceneAgenda() {

        BorderPane agendaPane = new BorderPane();
        HBox buttonBox = new HBox(30);

        Button newOne = new Button("NEW");
        Button saveAgenda = new Button("SAVE");

        saveAgenda.setOnAction(event -> {
            try {
                this.dataWriter.Save();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        newOne.setOnAction(e -> {
            final Stage popUpNew = new Stage();
            popUpNew.initOwner(this.stage);
            popUpNew.initModality(Modality.APPLICATION_MODAL);
            popUpNew.setTitle("create a lesson");
            popup(null);
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

    private boolean canAddLesson(LessonData lesson){
        ArrayList<LessonData> teachersavedData = this.savedData.getTeacherLessons(lesson.getTeacher());
        ArrayList<LessonData> classRoomsavedData = this.savedData.getClassroomLessons(lesson.getClassRoom());
        ArrayList<LessonData> studentGroupsavedData = this.savedData.getStudentGroupLessons(lesson.getStudentGroup());

        System.out.println(lesson.getClassRoom().getRoomName());
        //Check if teacher is available
        if(teachersavedData != null){
            if(isOccupied(teachersavedData,lesson)){
                System.out.println("Teacher is not available");
                return false;
            }
        }
        //Check if classroom is available
        if(classRoomsavedData != null){
            if(isOccupied(classRoomsavedData,lesson)){
                System.out.println("Room is not available");
                return false;
            }
        }
        //Check if StudentGroup is available
        if(studentGroupsavedData != null){
            if(isOccupied(studentGroupsavedData,lesson)){
                System.out.println("Group is not available");
                return false;
            }
        }
        return true;
    }

    //Checks if the lesson already taken for a certain variable
    private boolean isOccupied(ArrayList<LessonData> lessons, LessonData newLesson){
        for(LessonData lesson : lessons){
            System.out.println(lesson.getClassRoom().getRoomName());
            //Checks for overlap in time blocks
            if((newLesson.getBeginTime().isAfter(lesson.getBeginTime()) && (newLesson.getBeginTime().isBefore(lesson.getEndTime())))){
                return true;
            }
            else if((newLesson.getEndTime().isAfter(lesson.getBeginTime()) && (newLesson.getEndTime().isBefore(lesson.getEndTime())))){
                return true;
            }
            else if((lesson.getBeginTime().isAfter(newLesson.getBeginTime()))&&(lesson.getEndTime().isBefore(newLesson.getEndTime()))){
                return true;
            }
            else if(newLesson.getBeginTime().compareTo(lesson.getBeginTime())==0){
                return true;
            }
            else if(newLesson.getEndTime().compareTo(lesson.getEndTime())==0){
                return true;
            }
        }
        return false;
    }

    private LessonData getClickedBlockLesson(HourBlock block){
        return block.getLessonData();
    }


    private void popup(HourBlock block){
        Stage popUpEdit = new Stage();
        popUpEdit.initOwner(this.stage);
        popUpEdit.initModality(Modality.APPLICATION_MODAL);

        HBox hbox = makeSceneEditLesson();

        VBox popVBoxInformation = new VBox(10);

        TextField beginTime = new TextField();
        TextField endTime = new TextField();
        ComboBox<GroupData> group = new ComboBox();
        ComboBox<TeacherData> teacher = new ComboBox();
        ComboBox<ClassRoom> room = new ComboBox();

        Label warning = new Label();

        //Add al teachers and classrooms to the combobox
        for(int i = 0; i<5; i++){
            group.getItems().addAll(studentGroups.get(i));
            teacher.getItems().add(teachers.get(i));
            room.getItems().add(classrooms.get(i));
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
                    warning.setText("please enter a correct value at begin time, between 8 and 20.\n Make sure the notation is correct (for example 08:00, two digits each side).");
                } else if (endTime.getText().isEmpty() || LocalTime.parse(endTime.getText()).getHour() < 8 || LocalTime.parse(endTime.getText()).getHour() > 20 || LocalTime.parse(endTime.getText()).getHour() == 20 && LocalTime.parse(endTime.getText()).getMinute() > 0 || ((LocalTime.parse(beginTime.getText()).getHour() * 60) + LocalTime.parse(beginTime.getText()).getMinute()) > ((LocalTime.parse(endTime.getText()).getHour() * 60) + LocalTime.parse(endTime.getText()).getMinute())) {
                    warning.setText("please enter a correct value at end time, between 8 and 20.\n Make sure the end time is later than the begin time.\n And make sure the notation is correct (for example 08:00, two digits each side).");
                } else if (group.getSelectionModel().isEmpty()) {
                    warning.setText("please enter a correct value at group.");
                } else if (teacher.getSelectionModel().isEmpty()) {
                    warning.setText("please enter a correct value at teacher.");
                } else {
                    if(block==null){

                        LessonData newLesson = new LessonData(group.getValue(), teacher.getValue(), LocalTime.parse(beginTime.getText()), LocalTime.parse(endTime.getText()), room.getValue());
                        if(canAddLesson(newLesson)){
                            System.out.println("added new lesson");
                            this.savedData.getLessonData().add(newLesson);
                            popUpEdit.close();
                        }
                        else {
                            System.out.println("not valid");
                        }
                    }
                    else {
                        LessonData clickedBlockLesson = getClickedBlockLesson(block);
                        savedData.getLessonData().remove(clickedBlockLesson);
                        if(canAddLesson(clickedBlockLesson)){
                            System.out.println("Edit");
                            clickedBlockLesson.setTeacher(teacher.getValue());
                            clickedBlockLesson.setClassRoom(room.getValue());
                            clickedBlockLesson.setStudentGroup(group.getValue());
                            clickedBlockLesson.setBeginTime(LocalTime.parse(beginTime.getText()));
                            clickedBlockLesson.setEndTime(LocalTime.parse(endTime.getText()));
                            this.savedData.getLessonData().add(clickedBlockLesson);
                            popUpEdit.close();
                        }
                        else {
                            warning.setText("Cant edit");
                        }
                    }
                }
            } catch (DateTimeParseException dtpe) {
                warning.setText("Please enter a valid time in a form of 08:00, not something else!");
            }
        });

        Button deleteEdit = new Button("DELETE");
        deleteEdit.setOnAction(event -> {
            this.savedData.getLessonData().remove(getClickedBlockLesson(block));
            popUpEdit.close();
        });

        buttons.getChildren().addAll(saveEdit, deleteEdit);

        hbox.getChildren().addAll(popVBoxInformation, buttons, warning);
        Scene popScene = new Scene(hbox, 800, 400);

        popUpEdit.setScene(popScene);
        popUpEdit.show();
    }

}