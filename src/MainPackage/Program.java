package MainPackage;

import MainPackage.ReadWriteData.SavedData;
import OOFramework.FrameworkProgram;

public class Program extends FrameworkProgram
{
    sAgenda agenda;
    SavedData savedData = SavedData.INSTANCE;

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
