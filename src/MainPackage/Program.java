package MainPackage;

import Data.Gender;
import Data.SavedData.SavedData;
import Data.SavedData.StudentData;
import Data.Student;
import Data.StudentGroup;
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
