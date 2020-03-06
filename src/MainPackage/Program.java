package MainPackage;


import MainPackage.ReadWriteData.DataReader;
import MainPackage.ReadWriteData.DataWriter;
import OOFramework.FrameworkProgram;
import TiledParser.Simulation;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.swing.border.Border;
import java.awt.dnd.DropTarget;
import java.io.IOException;


public class Program extends FrameworkProgram {
    sAgenda agenda;
    Simulation simulation;

    DataReader dataReader = new DataReader();
    DataWriter dataWriter = new DataWriter();

    @Override
    public void start(Stage stage) throws Exception {
        super.start(stage);

    }

    @Override
    protected void Init() {
        super.Init();
        try{
            dataReader.Load();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        agenda = new sAgenda(this, dataWriter);
        simulation = new Simulation(this);


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
            pane.setCenter(simulation.getBorderPane());
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

