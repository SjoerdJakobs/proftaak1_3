package MainPackage.ReadWriteData;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import static OOFramework.Modules.CONSTANTS.STANDARD_SAVE_FILE_PATH;

public class DataReader {

    public DataReader()
    {

    }

    public void Load() throws IOException, ClassNotFoundException
    {
        ReadData();
    }

    public void ReadData() throws IOException, ClassNotFoundException
    {
        File f = new File(STANDARD_SAVE_FILE_PATH);
        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object readCase;
        do {
            readCase = ois.readObject();
            if (readCase != null) {
                /*if(readCase instanceof TeacherData)
                {
                    //save teacher stuff in data class
                }
                else if(readCase instanceof StudentData)
                {
                    //save student stuff in data class
                }
                else if(readCase instanceof LessonData)
                {
                    //save lesson stuff in data class
                }
                else if(readCase instanceof ClassData)
                {
                    //save class stuff in data class
                }
                else
                {
                    ASSERT_MSG_TERMINATE(false,"LOADING AN UNKNOWN OBJECT, CLASS: DataReader");
                }*/
            }
        } while (readCase != null);
        ois.close();
    }
}