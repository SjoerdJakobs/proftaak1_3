package Data;

import OOFramework.FrameworkProgram;

public class Teacher extends Person {
    private int teacherID;

    public Teacher(FrameworkProgram frameworkProgram, String name, int teacherID, int age, Gender gender) {
        super(frameworkProgram, name, age, gender);
        this.teacherID = teacherID;
    }

    // Getters and Setters
    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    @Override
    public String toString() {
        return "Student{" +
                ", name=" + getName() +
                ", age=" + getAge() +
                ", gender=" + getGender() +
                ", teacherID=" + teacherID +
                '}';
    }
}
