package Data;

import Data.SavedData.TeacherData;
import OOFramework.FrameworkProgram;

public class Teacher extends Person {
    private TeacherData teacherdata;

    public Teacher(FrameworkProgram frameworkProgram, String name, int teacherID, int age, Gender gender) {
        super(frameworkProgram, name, age, gender);

        teacherdata.INSTANCE.setName(name);
        teacherdata.INSTANCE.setAge(age);
        teacherdata.INSTANCE.setStudentID(teacherID);
        teacherdata.INSTANCE.setGender(gender.toString());
    }

    @Override
    public String toString() {
        return "Student{" +
                "name=" + teacherdata.INSTANCE.getName() +
                ", age=" + teacherdata.INSTANCE.getAge() +
                ", studentID=" + teacherdata.INSTANCE.getStudentID() +
                ", gender=" + teacherdata.INSTANCE.getGender() +
                '}';
    }
}
