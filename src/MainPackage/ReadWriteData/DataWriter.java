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
/*
        Counter counter = new Counter(10);
        Counter counter1 = new Counter(19);
        AntiCounter antiCounter = new AntiCounter(14);

        File f = new File("MyFile.txt");
        FileOutputStream fos = new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(counter);
        oos.writeObject(counter1);
        oos.writeObject(antiCounter);
        oos.writeObject(null);
        oos.close();
 */
