package Data;

import MainPackage.ReadWriteData.DataClasses.StudentData;
import OOFramework.FrameworkProgram;

public class Student extends Person{
    private StudentData studentData;

    public Student(FrameworkProgram frameworkProgram) {
        super(frameworkProgram);

    }

    // Getters and Setters
    public int getGroup() {
        return this.studentData.group;
    }
    public void setGroup(int group) {
        this.studentData.group = group;
    }

    public String getName() {
        return this.studentData.name;
    }
    public void setName(String name) {
        this.studentData.name = name;
    }

    public int getAge() {
        return this.studentData.age;
    }
    public void setAge(int age) {
        this.studentData.age = age;
    }

    public int getStudentID() {
        return this.studentData.studentID;
    }
    public void setStudentID(int studentID) {
        this.studentData.studentID = studentID;
    }

    public Gender getGender() {
        return this.studentData.gender;
    }
    public void setGender(Gender gender) {
        this.studentData.gender = gender;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentGroup=" + studentData.group +
                ", name=" + studentData.name +
                ", age=" + studentData.age +
                ", studentID=" + studentData.studentID +
                ", gender=" + studentData.gender +
                '}';
    }
}
