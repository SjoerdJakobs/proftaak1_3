package MainPackage.ReadWriteData.DataClasses;

import java.io.Serializable;

public class TeacherData implements Serializable
{
    public TeacherData()
    {

    }

    public TeacherData(String name,int age,int teacherId,boolean gender)
    {
        this.name = name;
        this.age = age;
        this.teacherId = teacherId;
        this.gender = gender;
    }
    public String name;
    public int age;
    public int teacherId;
    public boolean gender;
}
