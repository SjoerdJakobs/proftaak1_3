package MainPackage;

import MainPackage.ReadWriteData.SavedData;
import OOFramework.FrameworkProgram;
import javafx.stage.Stage;

public class Program extends FrameworkProgram
{
    sAgenda agenda;
    SavedData savedData = SavedData.INSTANCE;
    @Override
    public void start(Stage stage) throws Exception {
        super.start(stage);

    }

    @Override
    protected void Init()
    {
        super.Init();
        //int id = savedData.id;
        //System.out.println(id);
        //agenda = new sAgenda(this);

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
