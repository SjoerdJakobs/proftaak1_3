package MainPackage;

import Data.MakeAgenda;
import OOFramework.FrameworkProgram;
import javafx.stage.Stage;

public class Program extends FrameworkProgram
{
    sAgenda agenda;
    @Override
    public void start(Stage stage) throws Exception {
        super.start(stage);

    }

    @Override
    protected void Init()
    {
        super.Init();
        MakeAgenda makeAgenda = new MakeAgenda(this);
        agenda = new sAgenda(this,makeAgenda.getAgenda());

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
