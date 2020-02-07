package MainPackage;

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

        agenda = new sAgenda(this);

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
