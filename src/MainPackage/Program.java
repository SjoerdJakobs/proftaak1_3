package MainPackage;

import MainPackage.ReadWriteData.DataClasses.TeacherData;
import MainPackage.ReadWriteData.DataReader;
import MainPackage.ReadWriteData.DataWriter;
import MainPackage.ReadWriteData.SavedData;
import Data.MakeAgenda;
import OOFramework.FrameworkProgram;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.swing.border.Border;


public class Program extends FrameworkProgram {
    sAgenda agenda;
    SavedData savedData = SavedData.INSTANCE;
    DataWriter dataWriter = new DataWriter();
    DataReader dataReader = new DataReader();

    @Override
    protected void Init() {
        super.Init();
        //Save();
        Load();
    }

    public void Load()
    {
        try {
            dataReader.Load();
            for (TeacherData td : savedData.getTeacherData())
            {
                System.out.println(td.name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Save()
    {
        try {
            savedData.getTeacherData().add(new TeacherData("yee",1,1,false));
            savedData.getTeacherData().add(new TeacherData("yee1",2,2,true));
            savedData.getTeacherData().add(new TeacherData("52345672",3,3,false));
            savedData.getTeacherData().add(new TeacherData("yee555",4,999,false));
            savedData.getTeacherData().add(new TeacherData("yee4",5,5,true));
            savedData.getTeacherData().add(new TeacherData("yee5",6,6,false));

            dataWriter.Save();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        MakeAgenda makeAgenda = new MakeAgenda();
        agenda = new sAgenda(this,makeAgenda.getAgenda());

        BorderPane pane = new BorderPane();


        MenuBar mainBar = new MenuBar();
        Menu menuAgenda = new Menu("Agenda");
        MenuItem itemAgenda = new MenuItem("agenda");
        Menu menuSimulation = new Menu("Simulation");
        MenuItem itemSimulation = new MenuItem("simulaion");

        itemAgenda.setOnAction(event -> {
            pane.setCenter(agenda.sceneAgenda());

        });

        itemSimulation.setOnAction(e ->{
            agenda.setActive(false);
        });

        menuAgenda.getItems().add(itemAgenda);
        menuSimulation.getItems().add(itemSimulation);

        mainBar.getMenus().addAll(menuAgenda, menuSimulation);

        pane.setTop(mainBar);

        Scene mainScene = new Scene(pane);

        this.stage.setScene(mainScene);
//        agenda.setActive(false);



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
}

