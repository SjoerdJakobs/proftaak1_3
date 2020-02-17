package MainPackage.ReadWriteData.DataClasses;

import java.io.Serializable;

public class StudentData implements Serializable
{
    public String name;
    public int group;
    public int age;
    public int studentID;
    public boolean gender;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }
}
