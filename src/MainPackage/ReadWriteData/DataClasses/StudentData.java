package MainPackage.ReadWriteData.DataClasses;

import java.io.Serializable;

public class StudentData implements Serializable
{
    public StudentData()
    {

    }

    public StudentData(String name,int group,int age,int studentID,boolean gender)
    {
        this.name = name;
        this.group = group;
        this.age = age;
        this.studentID = studentID;
        this.gender = gender;

    }
    public String name;
    public int group;
    public int age;
    public int studentID;
    public boolean gender;
}
