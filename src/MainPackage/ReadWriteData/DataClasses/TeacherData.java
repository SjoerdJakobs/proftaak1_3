package MainPackage.ReadWriteData.DataClasses;

import Data.Gender;

import java.io.Serializable;

import static OOFramework.Modules.CONSTANTS.TEACHER_SERIAL_VERSION_UID;

public class TeacherData implements Serializable
{
    private String name;
    private int age;
    private int teacherId;
    private Gender gender;


    public TeacherData()
    {

    }

    public TeacherData(String name, int age, int teacherId, Gender gender)
    {
        this.name = name;
        this.age = age;
        this.teacherId = teacherId;
        this.gender = gender;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return name;
    }
}
