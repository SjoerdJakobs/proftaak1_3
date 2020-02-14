package Data;

import MainPackage.ReadWriteData.DataClasses.TeacherData;
import OOFramework.FrameworkProgram;

public class Teacher extends Person {
    private TeacherData teacherdata;

    public Teacher(FrameworkProgram frameworkProgram) {
        super(frameworkProgram);

    }

    // Getters and Setters
    public String getName() {
        return this.teacherdata.name;
    }
    public void setName(String name) {
        this.teacherdata.name = name;
    }

    public int getAge() {
        return this.teacherdata.age;
    }
    public void setAge(int age) {
        this.teacherdata.age = age;
    }

    /*public int getTeacherID() {
        return this.teacherdata.teacherID;
    }
    public void setTeacherID(int studentID) {
        this.teacherdata.teacherID = studentID;
    }

    public String getGender() {
        return this.teacherdata.gender;
    }
    public void setGender(String gender) {
        this.teacherdata.gender = gender;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name=" + teacherdata.name +
                ", age=" + teacherdata.age +
                ", teacherID=" + teacherdata.teacherID +
                ", gender=" + teacherdata.gender +
                '}';
    }*/
}
