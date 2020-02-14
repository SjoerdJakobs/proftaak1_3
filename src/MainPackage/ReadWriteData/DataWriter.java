package MainPackage.ReadWriteData;

import java.io.*;

import static OOFramework.Modules.CONSTANTS.STANDARD_SAVE_FILE_PATH;

public class DataWriter {

    public DataWriter()
    {

    }

    public void Save() throws IOException, ClassNotFoundException
    {
        WriteToFile();
    }

    public void WriteToFile() throws IOException, ClassNotFoundException
    {
        File f = new File(STANDARD_SAVE_FILE_PATH);
        FileOutputStream fos = new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(null);
        oos.close();
    }
}
