package Data;

import Data.SavedData.StudentData;
import OOFramework.FrameworkProgram;

import java.io.Serializable;

public class Student extends Person {
    private StudentData studentData;

    public Student(FrameworkProgram frameworkProgram, String name, StudentGroup studentGroup, int studentID, int age, Gender gender) {
        super(frameworkProgram, name, age, gender);

        studentData.INSTANCE.setGroup(studentGroup.getName());
        studentData.INSTANCE.setName(name);
        studentData.INSTANCE.setAge(age);
        studentData.INSTANCE.setStudentID(studentID);
        studentData.INSTANCE.setGender(gender.toString());
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentGroup=" + studentData.INSTANCE.getGroup() +
                ", name=" + studentData.INSTANCE.getName() +
                ", age=" + studentData.INSTANCE.getAge() +
                ", studentID=" + studentData.INSTANCE.getStudentID() +
                ", gender=" + studentData.INSTANCE.getGender() +
                '}';
    }
}
