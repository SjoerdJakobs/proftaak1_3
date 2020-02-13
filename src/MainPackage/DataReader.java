package MainPackage;

import OOFramework.Modules.ASSERT_MSG;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import static OOFramework.Modules.ASSERT_MSG.ASSERT_MSG_TERMINATE;

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

        /**
         * start read/loading code
         */
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
                /*else if(readCase instanceof TeacherData)
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
                }*/
                else
                {
                    ASSERT_MSG_TERMINATE(false,"LOADING AN UNKNOWN OBJECT, CLASS: DataReader");
                }
            }
        } while (readCase != null);
        ois.close();
    }
    /**
     * end read/loading code
     */

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