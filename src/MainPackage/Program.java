package MainPackage;

import MainPackage.ReadWriteData.DataClasses.TeacherData;
import MainPackage.ReadWriteData.DataReader;
import MainPackage.ReadWriteData.DataWriter;
import MainPackage.ReadWriteData.SavedData;
import OOFramework.FrameworkProgram;

import java.io.IOException;

public class Program extends FrameworkProgram
{
    sAgenda agenda;
    SavedData savedData = SavedData.INSTANCE;
    DataWriter dataWriter = new DataWriter();
    DataReader dataReader = new DataReader();

    @Override
    protected void Init()
    {
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
