package MainPackage.ReadWriteData.DataClasses;

import Data.Gender;

import java.io.Serializable;

public class StudentData implements Serializable
{
    private String name;
    private String group;
    private int age;
    private int studentID;
    private Gender gender;

    private StudentData()
    {

    }

    public StudentData(String name, String group, int age, int studentID, Gender gender)
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

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
