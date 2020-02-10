package Data.SavedData;

import java.io.Serializable;

public enum TeacherData implements Serializable {
    INSTANCE;

    private String name;
    private int age;
    private int teacherID;
    private String gender;

    // Getters and Setters
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

    public int getStudentID() {
        return teacherID;
    }
    public void setStudentID(int studentID) {
        this.teacherID = studentID;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
}
