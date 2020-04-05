package MainPackage;

import Data.Gender;
import Data.Rooms.ClassRoom;
import Data.Teacher;
import MainPackage.ReadWriteData.DataClasses.GroupData;
import MainPackage.ReadWriteData.DataClasses.LessonData;
import MainPackage.ReadWriteData.DataClasses.StudentData;
import MainPackage.ReadWriteData.DataClasses.TeacherData;
import MainPackage.ReadWriteData.DataWriter;
import MainPackage.ReadWriteData.SavedData;
import MainPackage.Simulation.Npc.Student;
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
    private ArrayList<ComboBox<String>> groupBoxes = new ArrayList<>();

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

        this.hours = 13;
        this.rooms = 6;
        this.canvas.setWidth(1625);
        this.canvas.setHeight(900);
        this.xStepSize = canvas.getWidth() / hours;
        this.yStepSize = canvas.getHeight() / rooms;
        //this.savedData.getLessonData() = agenda.getsavedData.getLessonData()();

        teachers = new ArrayList<>();
        classrooms = new ArrayList<>();
        studentGroups = new ArrayList<>();

        //Load in teachers, classrooms and studentgroups later for now manual
        teachers = this.savedData.getTeacherData();
        studentGroups = this.savedData.getGroupData();

        classrooms.add(new ClassRoom(301));
        classrooms.add(new ClassRoom(302));
        classrooms.add(new ClassRoom(303));
        classrooms.add(new ClassRoom(304));
        classrooms.add(new ClassRoom(305));

//        studentGroups.add(new GroupData("A"));
//        studentGroups.add(new GroupData("B"));
//        studentGroups.add(new GroupData("C"));
//        studentGroups.add(new GroupData("D"));
//        studentGroups.add(new GroupData("E"));
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
                        popUpEdit.setTitle("Edit lesson");

                        HBox hbox = makeSceneLabelsForPopUp(new String[]{"Begin time: ", "End time: ", "Group: ", "Teacher: ", "Classroom: "});

                        VBox popVBoxInformation = new VBox(10);

                        TextField beginTime = new TextField();
                        TextField endTime = new TextField();
                        ComboBox<GroupData> group = new ComboBox();
                        ComboBox<TeacherData> teacher = new ComboBox();
                        ComboBox<ClassRoom> room = new ComboBox();

                        Label warning = new Label();

                        for (int i = 0; i < this.classrooms.size(); i++) {
                            room.getItems().add(classrooms.get(i));
                        }

                        for (int i = 0; i < this.studentGroups.size(); i++) {
                            group.getItems().addAll(studentGroups.get(i));
                        }

                        for (int i = 0; i < this.teachers.size(); i++) {
                            teacher.getItems().add(teachers.get(i));
                        }

                        // Fill in the saved values
                        beginTime.setText(block.getBeginTime().toString());
                        endTime.setText(block.getEndTime().toString());
                        group.getSelectionModel().select(block.getLessonData().studentGroupId);
                        teacher.getSelectionModel().select(block.getTeacher());
                        room.getSelectionModel().select(block.getRoomNr());

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
                                    LessonData clickedBlockLesson = getClickedBlockLesson(block);
                                    savedData.getLessonData().remove(clickedBlockLesson);
                                    if (canAddLesson(clickedBlockLesson)) {
                                        System.out.println("Edit");
                                        clickedBlockLesson.setTeacher(teacher.getValue());
                                        clickedBlockLesson.setClassRoom(room.getValue());
                                        clickedBlockLesson.setStudentGroup(group.getValue());
                                        clickedBlockLesson.setBeginTime(LocalTime.parse(beginTime.getText()));
                                        clickedBlockLesson.setEndTime(LocalTime.parse(endTime.getText()));
                                        this.savedData.getLessonData().add(clickedBlockLesson);
                                        popUpEdit.close();
                                    } else {
                                        warning.setText("Cant edit");
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
            }
        });
    }


    @Override
    protected void MainLoop(double deltaTime) {
        super.MainLoop(deltaTime);
        //do general stuff here, no clear idea what yet because i dont think a agenda has much logic now that i think about it

        this.hourBlocks.clear();
        //Color[] colors = {Color.GREEN,Color.RED,Color.BLACK,Color.BLUE,Color.PINK,Color.MAGENTA};
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
        //lets draw all stuff here

        graphics2D.setColor(Color.white);
        graphics2D.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

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

        Button newOne = new Button("NEW LESSON");
        Button saveAgenda = new Button("SAVE");
        Button addStudent = new Button("ADD STUDENT");
        Button addTeacher = new Button("ADD TEACHER");
        Button managePeople = new Button("PEOPLE MANAGER");

        Button savePopUp = new Button("SAVE");
        Button delete = new Button("DELETE");
        Button edit = new Button("EDIT");

        /** The save button logic. */
        saveAgenda.setOnAction(event -> {
            try {
                this.dataWriter.Save();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        /** Makes a window pop up to add a Lesson to the program. */
        newOne.setOnAction(e -> {
            final Stage popUpNew = new Stage();
            popUpNew.initOwner(this.stage);
            popUpNew.initModality(Modality.APPLICATION_MODAL);
            popUpNew.setTitle("create a lesson");

            HBox hbox = makeSceneLabelsForPopUp(new String[]{"Begin time: ", "End time: ", "Group: ", "Teacher: ", "Classroom: "});
            VBox popVBoxInformation = new VBox(10);
            TextField beginTime = new TextField();
            TextField endTime = new TextField();
            ComboBox<GroupData> group = new ComboBox();
            ComboBox<TeacherData> teacher = new ComboBox<>();
            ComboBox<ClassRoom> room = new ComboBox<>();
//
//            for (int i = 0; i < 5; i++) {
//                group.getItems().addAll(studentGroups.get(i));
//                room.getItems().add(classrooms.get(i));
//            }
//
//            for (TeacherData t : teachers) {
//                teacher.getItems().add(t);
//            }
            for (int i = 0; i < this.classrooms.size(); i++) {
                room.getItems().add(classrooms.get(i));
            }

            for (int i = 0; i < this.studentGroups.size(); i++) {
                group.getItems().addAll(studentGroups.get(i));
            }

            for (int i = 0; i < this.teachers.size(); i++) {
                teacher.getItems().add(teachers.get(i));
            }



            popVBoxInformation.getChildren().add(beginTime);
            popVBoxInformation.getChildren().add(endTime);
            popVBoxInformation.getChildren().add(group);
            popVBoxInformation.getChildren().add(teacher);
            popVBoxInformation.getChildren().add(room);

            Label warningLabel = new Label();

            savePopUp.setOnAction(ex -> {
                try {
                    if (beginTime.getText().isEmpty() || LocalTime.parse(beginTime.getText()).getHour() < 8 || LocalTime.parse(beginTime.getText()).getHour() > 20 || LocalTime.parse(beginTime.getText()).getHour() == 20 && LocalTime.parse(beginTime.getText()).getMinute() > 0) {
                        warningLabel.setText("please enter a correct value at begin time, between 8 and 20.\n Make sure the notation is correct (for example 08:00, two digits each side).");
                    } else if (endTime.getText().isEmpty() || LocalTime.parse(endTime.getText()).getHour() < 8 || LocalTime.parse(endTime.getText()).getHour() > 20 || LocalTime.parse(endTime.getText()).getHour() == 20 && LocalTime.parse(endTime.getText()).getMinute() > 0 || ((LocalTime.parse(beginTime.getText()).getHour() * 60) + LocalTime.parse(beginTime.getText()).getMinute()) > ((LocalTime.parse(endTime.getText()).getHour() * 60) + LocalTime.parse(endTime.getText()).getMinute())) {
                        warningLabel.setText("please enter a correct value at end time, between 8 and 20.\n Make sure the end time is later than the begin time.\n And make sure the notation is correct (for example 08:00, two digits each side).");
                    } else if (group.getSelectionModel().isEmpty()) {
                        warningLabel.setText("please enter a correct value at group.");
                    } else if (teacher.getSelectionModel().isEmpty()) {
                        warningLabel.setText("please enter a correct value at teacher.");
                    } else {
                        LessonData newLesson = new LessonData(group.getValue(), teacher.getValue(), LocalTime.parse(beginTime.getText()), LocalTime.parse(endTime.getText()), room.getValue());
                        if (canAddLesson(newLesson)) {
                            System.out.println("added new lesson");
                            System.out.println( newLesson.toString());
                            System.out.println("de grote van de studenten lijst: " + group.getValue().getStudentData().size());

                            this.savedData.getLessonData().add(newLesson);
                            popUpNew.close();
                        } else {
                            System.out.println("not valid");
                            warningLabel.setText("This lesson cannot be added");
                        }

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


        /** Makes a window pop up to add a Student to the program. */
        addStudent.setOnAction(e -> {
            final Stage popUpNew = new Stage();
            popUpNew.initOwner(this.stage);
            popUpNew.initModality(Modality.APPLICATION_MODAL);
            popUpNew.setTitle("add a student");

            HBox hbox = makeSceneLabelsForPopUp(new String[]{"Name: ", "Age: ", "StudentID: ", "Gender: ", "Group: "});
            VBox popVBoxInformation = new VBox(10);
            TextField name = new TextField();
            TextField age = new TextField();
            TextField studentID = new TextField();
            ComboBox<GroupData> group = new ComboBox();
            ComboBox<Gender> gender = new ComboBox<>();

            gender.getItems().add(0, Gender.MALE);
            gender.getItems().add(1, Gender.FEMALE);
            for (int i = 0; i < 5; i++) {
                group.getItems().addAll(studentGroups.get(i));
            }

            popVBoxInformation.getChildren().add(name);
            popVBoxInformation.getChildren().add(age);
            popVBoxInformation.getChildren().add(studentID);
            popVBoxInformation.getChildren().add(gender);
            popVBoxInformation.getChildren().add(group);

            Label warningLabel = new Label();

            savePopUp.setOnAction(ex -> {
                if (name.getText().isEmpty()) {
                    warningLabel.setText("Please enter a name.");
                } else if (age.getText().isEmpty()) {
                    warningLabel.setText("Please enter an age.");
                } else if (studentID.getText().isEmpty()) {
                    warningLabel.setText("Please enter a studentID");
                } else if (gender.getSelectionModel().isEmpty()) {
                    warningLabel.setText("Please select a gender.");
                } else if (group.getSelectionModel().isEmpty()) {
                    warningLabel.setText("Please select a group.");
                } else {
                    StudentData newStudent = new StudentData(name.getText(), group.getValue().name, Integer.parseInt(age.getText()), Integer.parseInt(studentID.getText()), gender.getValue());
                    if (canAddStudent(newStudent)) {
                        this.savedData.getStudentData().add(newStudent);
                        popUpNew.close();
                        updateGroupBox(groupBoxes.get(studentGroups.indexOf(group.getValue())), group.getValue());
                    } else {
                        warningLabel.setText("Student already exists.");
                    }
                }
            });

            hbox.getChildren().addAll(popVBoxInformation, savePopUp, warningLabel);
            Scene popScene = new Scene(hbox, 550, 400);
            popUpNew.setScene(popScene);
            popUpNew.show();
        });

        /** Makes a window pop up to add a Teacher to the program. */
        addTeacher.setOnAction(e -> {
            final Stage popUpNew = new Stage();
            popUpNew.initOwner(this.stage);
            popUpNew.initModality(Modality.APPLICATION_MODAL);
            popUpNew.setTitle("Add a teacher");

            HBox hbox = makeSceneLabelsForPopUp(new String[]{"Name: ", "Age: ", "TeacherID: ", "Gender: "});
            VBox popVBoxInformation = new VBox(10);
            TextField name = new TextField();
            TextField age = new TextField();
            TextField teacherID = new TextField();
            ComboBox<Gender> gender = new ComboBox<>();

            gender.getItems().add(0, Gender.MALE);
            gender.getItems().add(1, Gender.FEMALE);

            popVBoxInformation.getChildren().add(name);
            popVBoxInformation.getChildren().add(age);
            popVBoxInformation.getChildren().add(teacherID);
            popVBoxInformation.getChildren().add(gender);

            Label warningLabel = new Label();

            savePopUp.setOnAction(ex -> {
                if (name.getText().isEmpty()) {
                    warningLabel.setText("Please enter a name.");
                } else if (age.getText().isEmpty()) {
                    warningLabel.setText("Please enter an age.");
                } else if (teacherID.getText().isEmpty()) {
                    warningLabel.setText("Please enter a teacherID");
                } else if (gender.getSelectionModel().isEmpty()) {
                    warningLabel.setText("Please select a gender.");
                } else {
                    TeacherData newTeacher = new TeacherData(name.getText(), Integer.parseInt(age.getText()), Integer.parseInt(teacherID.getText()), gender.getValue());
                    if (canAddTeacher(newTeacher)) {
                        this.savedData.getTeacherData().add(newTeacher);
                        popUpNew.close();
                        updateTeacherBox();
                    } else {
                        warningLabel.setText("Teacher already exists.");
                    }
                }
            });

            hbox.getChildren().addAll(popVBoxInformation, savePopUp, warningLabel);
            Scene popScene = new Scene(hbox, 550, 400);
            popUpNew.setScene(popScene);
            popUpNew.show();
        });

        /** Opens a PopUp that gives the user the ability to add and delete people from groups */ 
        managePeople.setOnAction(e -> {
            final Stage popUpNew = new Stage();
            popUpNew.initOwner(this.stage);
            popUpNew.initModality(Modality.APPLICATION_MODAL);
            popUpNew.setTitle("People manager");
            popUpNew.setOnCloseRequest(event -> {
                groupBoxes.clear();
            });

            HBox hBox = new HBox();
            VBox vBoxLeft = new VBox();
            VBox vBoxRight = new VBox();

            Label warningLabel = new Label("");

            vBoxLeft.getChildren().add(addStudent);

            vBoxLeft.getChildren().add(new Label("Group A"));
            vBoxLeft.getChildren().add(buildGroupRow(studentGroups.get(0)));

            vBoxLeft.getChildren().add(new Label("Group B"));
            vBoxLeft.getChildren().add(buildGroupRow(studentGroups.get(1)));

            vBoxLeft.getChildren().add(new Label("Group C"));
            vBoxLeft.getChildren().add(buildGroupRow(studentGroups.get(2)));

            HBox buttonRow = new HBox();
            buttonRow.getChildren().add(addTeacher);
            buttonRow.getChildren().add(warningLabel);
            vBoxRight.getChildren().add(buttonRow);

            vBoxRight.getChildren().add(new Label("Teachers"));
            FlowPane teacherFlowPane = new FlowPane();
            ComboBox<String> teacherBox = new ComboBox<>();
            teacherBox.setMaxWidth(125);
            teacherBox.setPrefWidth(125);
            teacherFlowPane.getChildren().add(teacherBox);
            Button deleteButton = new Button("Delete");
            deleteButton.setOnAction(event -> {
                if(!removeTeacherFromData(teacherBox.getValue())){
                    warningLabel.setText("This teacher has a lesson planned.\nPlease remove this lesson first.");
                } else {
                    warningLabel.setText("");
                }
                updateTeacherBox();
            });
            teacherFlowPane.getChildren().add(deleteButton);

            vBoxRight.getChildren().add(new Label("Group D"));
            vBoxRight.getChildren().add(buildGroupRow(studentGroups.get(3)));

            vBoxRight.getChildren().add(new Label("Group E"));
            vBoxRight.getChildren().add(buildGroupRow(studentGroups.get(4)));

            groupBoxes.add(teacherBox);
            vBoxRight.getChildren().add(2, teacherFlowPane);
            updateTeacherBox();

            hBox.getChildren().addAll(vBoxLeft, vBoxRight);
            Scene popScene = new Scene(hBox, 600, 300);
            popUpNew.setScene(popScene);
            popUpNew.show();
        });

        buttonBox.getChildren().addAll(newOne, saveAgenda, managePeople);
        agendaPane.setBottom(buttonBox);
        agendaPane.setTop(this.canvas);

        return agendaPane;
    }

    /** Return a FlowPane with a new ComboBox and DeleteButton for a new Studentgroup */
    private FlowPane buildGroupRow(GroupData groupData){
        FlowPane flowPane = new FlowPane();
        ComboBox<String> group = new ComboBox<>();
        groupBoxes.add(group);
        updateGroupBox(group, groupData);
        group.setMaxWidth(125);
        group.setPrefWidth(125);
        flowPane.getChildren().add(group);
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(event -> {removeStudentFromData(group.getValue()); updateGroupBox(group, groupData);});
        flowPane.getChildren().add(deleteButton);
        return flowPane;
    }

    /** Update the teacher ComboBox with all the studentData from the SavedData enum */
    private void updateGroupBox(ComboBox<String> group, GroupData groupData){
        for(StudentData s : savedData.getStudentData()){
            if(s.getGroup().equals(groupData.name))
                if(!group.getItems().contains(s.getName() + " " + s.getStudentID()))
                    group.getItems().add(s.getName() + " " + s.getStudentID());
        }
    }

    /** Update the teacher ComboBox with all the teacherData from the SavedData enum */
    private void updateTeacherBox(){
        for(TeacherData t : savedData.getTeacherData()){
            if(!groupBoxes.get(5).getItems().contains(t.getName() + " " + t.getTeacherId()))
                groupBoxes.get(5).getItems().add(t.getName() + " " + t.getTeacherId());
        }
    }

    /** Remove the person from the savedData.studentData ArrayList
     *  Remove the person from the correlating ComboBox */
    private void removeStudentFromData(String person){
        while(person.contains(" ")){
            person = person.substring(person.indexOf(" ") + 1);
        }
        for(StudentData s : savedData.getStudentData()){
            if(s.getStudentID() == Integer.valueOf(person)) {
                savedData.getStudentData().remove(s);
                GroupData groupData = studentGroups.get(0);
                for(GroupData gd : studentGroups){
                    if(gd.name.equals(s.getGroup())) { groupData = gd; break; }
                }
                groupBoxes.get(studentGroups.indexOf(groupData)).getItems().remove(s.getName() + " " + s.getStudentID());
                groupBoxes.get(studentGroups.indexOf(groupData)).getSelectionModel().select("");
                break;
            }
        }
    }

    /** Remove the person from the savedData.teacherData ArrayList
     *  Remove the person from the ComboBox */
    private boolean removeTeacherFromData(String person){
        while(person.contains(" ")){
            person = person.substring(person.indexOf(" ") + 1);
        }
        for(TeacherData t : savedData.getTeacherData()){
            if(t.getTeacherId() == Integer.valueOf(person)) {
                // Check of the teacher has any lessons planned
                for(HourBlock block : hourBlocks){
                    if(block.getTeacher().getTeacherId() == Integer.valueOf(person)) { return false; }
                }
                savedData.getTeacherData().remove(t);
                groupBoxes.get(5).getItems().remove(t.getName() + " " + t.getTeacherId());
                groupBoxes.get(5).getSelectionModel().select("");
                break;
            }
        }
        return true;
    }

    /** Create labels from the receiving Strings and return them in a HBox */
    private HBox makeSceneLabelsForPopUp(String[] labels) {
        HBox popHBox = new HBox(20);

        VBox popVBoxLabels = new VBox(20);

        for (String l : labels) {
            popVBoxLabels.getChildren().add(new Label(l));
        }

        popHBox.getChildren().addAll(popVBoxLabels);
        return popHBox;
    }

    /** Check if we can add the lesson to the agenda */
    private boolean canAddLesson(LessonData lesson) {
        ArrayList<LessonData> teachersavedData = this.savedData.getTeacherLessons(lesson.getTeacher());
        ArrayList<LessonData> classRoomsavedData = this.savedData.getClassroomLessons(lesson.getClassRoom());
        ArrayList<LessonData> studentGroupsavedData = this.savedData.getStudentGroupLessons(lesson.getStudentGroup());

        //Check if teacher is available
        if (teachersavedData != null) {

            for (LessonData teacherLesson : teachersavedData) {
                if ((lesson.getBeginTime().isAfter(teacherLesson.getBeginTime()) && (lesson.getBeginTime().isBefore(teacherLesson.getEndTime())))) {
                    return false;
                } else if ((lesson.getEndTime().isAfter(teacherLesson.getBeginTime()) && (lesson.getEndTime().isBefore(teacherLesson.getEndTime())))) {
                    return false;
                } else if ((teacherLesson.getBeginTime().isAfter(lesson.getBeginTime())) && (teacherLesson.getEndTime().isBefore(lesson.getEndTime()))) {
                    return false;
                } else if (lesson.getBeginTime().compareTo(teacherLesson.getBeginTime()) == 0) {
                    return false;
                } else if (lesson.getEndTime().compareTo(teacherLesson.getEndTime()) == 0) {
                    return false;
                }
            }
        }

        //Check if classroom is available
        if (classRoomsavedData != null) {

            for (LessonData classroomLesson : classRoomsavedData) {
                if ((lesson.getBeginTime().isAfter(classroomLesson.getBeginTime()) && (lesson.getBeginTime().isBefore(classroomLesson.getEndTime())))) {
                    return false;
                } else if ((lesson.getEndTime().isAfter(classroomLesson.getBeginTime()) && (lesson.getEndTime().isBefore(classroomLesson.getEndTime())))) {
                    return false;
                } else if ((classroomLesson.getBeginTime().isAfter(lesson.getBeginTime())) && (classroomLesson.getEndTime().isBefore(lesson.getEndTime()))) {
                    return false;
                } else if (lesson.getBeginTime().compareTo(classroomLesson.getBeginTime()) == 0) {
                    return false;
                } else if (lesson.getEndTime().compareTo(classroomLesson.getEndTime()) == 0) {
                    return false;
                }
            }
        }

        //Check if StudentGroup is available
        if (studentGroupsavedData != null) {

            for (LessonData studentGroupLesson : studentGroupsavedData) {
                if ((lesson.getBeginTime().isAfter(studentGroupLesson.getBeginTime()) && (lesson.getBeginTime().isBefore(studentGroupLesson.getEndTime())))) {
                    return false;
                } else if ((lesson.getEndTime().isAfter(studentGroupLesson.getBeginTime()) && (lesson.getEndTime().isBefore(studentGroupLesson.getEndTime())))) {
                    return false;
                } else if ((studentGroupLesson.getBeginTime().isAfter(lesson.getBeginTime())) && (studentGroupLesson.getEndTime().isBefore(lesson.getEndTime()))) {
                    return false;
                } else if (lesson.getBeginTime().compareTo(studentGroupLesson.getBeginTime()) == 0) {
                    return false;
                } else if (lesson.getEndTime().compareTo(studentGroupLesson.getEndTime()) == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /** Return true if the student doesn't exist yet */
    private boolean canAddStudent(StudentData newStudent) {
        ArrayList<StudentData> students = this.savedData.getStudentData();

        for (StudentData s : students) {
            if (s.getName().equals(newStudent.getName())
                    && s.getAge() == newStudent.getAge()
                    && s.getStudentID() == newStudent.getStudentID()
                    && s.getGender().equals(newStudent.getGender())
                    && s.getGroup().equals(newStudent.getGroup())) {
                return false;
            }
        }
        return true;
    }

    /** Return true if the teacher doesn't exist yet */
    private boolean canAddTeacher(TeacherData newTeacher) {
        ArrayList<TeacherData> teachers = this.savedData.getTeacherData();

        for (TeacherData t : teachers) {
            if (t.getName().equals(newTeacher.getName())
                    && t.getAge() == newTeacher.getAge()
                    && t.getTeacherId() == newTeacher.getTeacherId()
                    && t.getGender().equals(newTeacher.getGender())) {
                return false;
            }
        }
        return true;
    }

    private LessonData getClickedBlockLesson(HourBlock block) {
        return block.getLessonData();
    }
}