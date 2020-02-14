package MainPackage.ReadWriteData;

import MainPackage.ReadWriteData.DataClasses.GroupData;
import MainPackage.ReadWriteData.DataClasses.LessonData;
import MainPackage.ReadWriteData.DataClasses.StudentData;
import MainPackage.ReadWriteData.DataClasses.TeacherData;

import java.io.*;
import java.util.ArrayList;

import static OOFramework.Modules.CONSTANTS.STANDARD_SAVE_FILE_PATH;

public class DataWriter {

    SavedData savedData;

    public DataWriter()
    {
        savedData = SavedData.INSTANCE;
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

        ArrayList<TeacherData> teacherDatas = savedData.getTeacherData();
        ArrayList<StudentData> studentDatas = savedData.getStudentData();
        ArrayList<LessonData> lessonDatas = savedData.getLessonData();
        ArrayList<GroupData> groupDatas = savedData.getGroupData();

        for(TeacherData td : teacherDatas)
        {
            oos.writeObject(td);
        }
        for(StudentData sd : studentDatas)
        {
            oos.writeObject(sd);
        }
        for(LessonData ld : lessonDatas)
        {
            oos.writeObject(ld);
        }
        for(GroupData gd : groupDatas)
        {
            oos.writeObject(gd);
        }
        oos.writeObject(null);
        oos.close();
    }
}