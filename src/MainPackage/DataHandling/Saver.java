package MainPackage.DataHandling;

import java.io.*;

public class Saver {

    public static void save(Object object, String file) {
        try{
            FileOutputStream fos = new FileOutputStream(file, true);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(object);
            oos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
