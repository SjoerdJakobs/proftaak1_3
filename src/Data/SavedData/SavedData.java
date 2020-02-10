package Data.SavedData;

import Data.Student;

import java.util.HashMap;

public enum SavedData {
    INSTANCE;

    private HashMap<Integer, StudentData> studentData = new HashMap<>();
    private HashMap<Integer, TeacherData> teacherData = new HashMap<>();
    private HashMap<Integer, LessonData> lessonData = new HashMap<>();
    private HashMap<Integer, GroupData> groupData = new HashMap<>();

    // Getters and Setters
    public HashMap<Integer, StudentData> getStudentData() {
        return studentData;
    }
    public void setStudentData(HashMap<Integer, StudentData> studentData) {
        this.studentData = studentData;
    }

    public HashMap<Integer, TeacherData> getTeacherData() {
        return teacherData;
    }
    public void setTeacherData(HashMap<Integer, TeacherData> teacherData) {
        this.teacherData = teacherData;
    }

    public HashMap<Integer, LessonData> getLessonData() {
        return lessonData;
    }
    public void setLessonData(HashMap<Integer, LessonData> lessonData) {
        this.lessonData = lessonData;
    }

    public HashMap<Integer, GroupData> getGroupData() {
        return groupData;
    }
    public void setGroupData(HashMap<Integer, GroupData> groupData) {
        this.groupData = groupData;
    }
}
