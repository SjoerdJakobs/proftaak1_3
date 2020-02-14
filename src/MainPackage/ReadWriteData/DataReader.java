package MainPackage.ReadWriteData;

import MainPackage.ReadWriteData.DataClasses.GroupData;
import MainPackage.ReadWriteData.DataClasses.LessonData;
import MainPackage.ReadWriteData.DataClasses.StudentData;
import MainPackage.ReadWriteData.DataClasses.TeacherData;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import static OOFramework.Modules.ASSERT_MSG.ASSERT_MSG_TERMINATE;
import static OOFramework.Modules.CONSTANTS.STANDARD_SAVE_FILE_PATH;

public class DataReader {

    SavedData savedData;

    public DataReader()
    {
        savedData = SavedData.INSTANCE;
    }

    public void Load() throws IOException, ClassNotFoundException
    {
        ReadFile();
    }

    public void ReadFile() throws IOException, ClassNotFoundException
    {
        File f = new File(STANDARD_SAVE_FILE_PATH);
        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object readCase;
        ArrayList<TeacherData> teacherData = new ArrayList<TeacherData>();
        ArrayList<StudentData> studentData = new ArrayList<StudentData>();
        ArrayList<LessonData> lessonData = new ArrayList<LessonData>();
        ArrayList<GroupData> groupData = new ArrayList<GroupData>();
        do {
            readCase = ois.readObject();
            if (readCase != null) {
                if(readCase instanceof TeacherData)
                {
                    teacherData.add((TeacherData) readCase);
                }
                else if(readCase instanceof StudentData)
                {
                    studentData.add((StudentData) readCase);
                }
                else if(readCase instanceof LessonData)
                {
                    lessonData.add((LessonData)readCase);
                }
                else if(readCase instanceof GroupData)
                {
                    groupData.add((GroupData)readCase);
                }
                else
                {
                    ASSERT_MSG_TERMINATE(false,"LOADING AN UNKNOWN OBJECT, CLASS: DataReader");
                }
            }
        } while (readCase != null);
        ois.close();
        savedData.setTeacherData(teacherData);
        savedData.setStudentData(studentData);
        savedData.setLessonData(lessonData);
        savedData.setGroupData(groupData);
    }
}