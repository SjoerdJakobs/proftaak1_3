package MainPackage;

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

    @Override
    public void start(Stage stage) throws Exception {
        super.start(stage);

    }

    @Override
    protected void Init() {
        super.Init();


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

