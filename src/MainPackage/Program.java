package MainPackage;


import Data.Gender;
import MainPackage.ReadWriteData.DataClasses.GroupData;
import MainPackage.ReadWriteData.DataClasses.StudentData;
import MainPackage.ReadWriteData.DataReader;
import MainPackage.ReadWriteData.DataWriter;
import MainPackage.ReadWriteData.SavedData;
import OOFramework.FrameworkProgram;
import TiledParser.Simulation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;


public class Program extends FrameworkProgram {

    private Random random;

    sAgenda agenda;
    Simulation simulation;

    SavedData savedData = SavedData.INSTANCE;
    DataReader dataReader = new DataReader();
    DataWriter dataWriter = new DataWriter();

    @Override
    public void start(Stage stage) throws Exception {
        super.start(stage);

    }

    @Override
    protected void Init() {
        super.Init();
        try {
            dataReader.Load();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        agenda = new sAgenda(this, dataWriter);
        simulation = new Simulation(this);


        this.random = new Random();

        BorderPane pane = new BorderPane();


        MenuBar mainBar = new MenuBar();
        Menu menuAgenda = new Menu("Agenda");
        MenuItem itemAgenda = new MenuItem("agenda");
        Menu menuSimulation = new Menu("Simulation");
        MenuItem itemSimulation = new MenuItem("simulation");

        itemAgenda.setOnAction(event -> {
            pane.setCenter(agenda.sceneAgenda());
            this.simulation.clearNPCS();

        });

        itemSimulation.setOnAction(e -> {
            pane.setCenter(simulation.getBorderPane());
            this.simulation.setAllNPCStudents();
           // this.simulation.setLessonData(this.savedData.getLessonData());
        });

        menuAgenda.getItems().add(itemAgenda);
        menuSimulation.getItems().add(itemSimulation);

        mainBar.getMenus().addAll(menuAgenda, menuSimulation);

        pane.setTop(mainBar);

        Scene mainScene = new Scene(pane);

        this.stage.setScene(mainScene);
//        agenda.setActive(false);


        GroupData groupA = new GroupData("A");
        for (int i = 0; i < 1; i++) {
            groupA.addStudent(new StudentData("studentA" + (i + 1), "A", 16 + i % 2, i, getRandomGender()));
        }

        GroupData groupB = new GroupData("B");
        for (int i = 10; i < 20; i++) {
            groupB.addStudent(new StudentData("studentB" + (i + 1), "B", 16 + i % 2, i, getRandomGender()));
        }

        GroupData groupC = new GroupData("C");
        for (int i = 20; i < 30; i++) {
            groupC.addStudent(new StudentData("studentC" + (i + 1), "C", 16 + i % 2, i, getRandomGender()));
        }

        GroupData groupD = new GroupData("D");
        for (int i = 30; i < 40; i++) {
            groupD.addStudent(new StudentData("studentD" + (i + 1), "D", 16 + i % 2, i, getRandomGender()));
        }

        GroupData groupE = new GroupData("E");
        for (int i = 40; i < 50; i++) {
            groupE.addStudent(new StudentData("studentE" + (i + 1), "E", 16 + i % 2, i, getRandomGender()));
        }

        this.savedData.getGroupData().clear();

        if (!checkGroup(groupA)) {
            this.savedData.getGroupData().add(groupA);

        }
        if (!checkGroup(groupB)) {
            this.savedData.getGroupData().add(groupB);

        }
        if (!checkGroup(groupC)) {
            this.savedData.getGroupData().add(groupC);

        }
        if (!checkGroup(groupD)) {
            this.savedData.getGroupData().add(groupD);

        }
        if (!checkGroup(groupE)) {
            this.savedData.getGroupData().add(groupE);
        }

    }


    public boolean checkGroup(GroupData groupData) {
        boolean isIn = false;
        for (GroupData group : this.savedData.getGroupData()) {
            if (group.getName().equals(groupData.getName())) {
                isIn = true;
            }

        }
        return isIn;
    }

    @Override
    protected void AddToLoop() {
        super.AddToLoop();
        //menu switch?

    }

    @Override
    protected void ExitProgram() {
        super.ExitProgram();
    }


    private Gender getRandomGender() {

        if (this.random.nextDouble() > 0.5) {
            return Gender.MALE;
        } else {
            return Gender.FEMALE;
        }
    }
}

