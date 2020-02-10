package Data;

import OOFramework.FrameworkProgram;

public class Student extends Person {
    private StudentGroup studentGroup;
    private int studentID;

    public Student(FrameworkProgram frameworkProgram, String name, StudentGroup studentGroup, int studentID, int age, Gender gender) {
        super(frameworkProgram, name, age, gender);
        this.studentGroup = studentGroup;
        this.studentID = studentID;
    }

    // Getters and Setters
    public StudentGroup getStudentGroup() { return this.studentGroup; }
    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentGroup=" + studentGroup +
                ", name=" + getName() +
                ", age=" + getAge() +
                ", gender=" + getGender() +
                ", studentID=" + studentID +
                '}';
    }
}
