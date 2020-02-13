package MainPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class DataReader {

    public static void main(String[] args) throws Exception {

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


        Counter loaded;
        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object readCase;
        do {
            readCase = ois.readObject();
            if (readCase != null) {
                if(readCase instanceof Counter)
                {
                    loaded = (Counter)readCase;
                    System.out.println(loaded.count);
                }
            }
        } while (readCase != null);
        ois.close();
    }

}

class Counter implements Serializable {

    int count;

    Counter(int count) {
        this.count = count;
    }
}

class AntiCounter implements Serializable {

    double count;

    AntiCounter(double count) {
        this.count = count;
    }
}