package MainPackage.ReadWriteData;

import MainPackage.ReadWriteData.DataClasses.GroupData;
import MainPackage.ReadWriteData.DataClasses.LessonData;
import MainPackage.ReadWriteData.DataClasses.StudentData;
import MainPackage.ReadWriteData.DataClasses.TeacherData;

import java.util.ArrayList;

//https://dzone.com/articles/java-singletons-using-enum
public enum SavedData {
    INSTANCE;

    private ArrayList<StudentData> studentData = new ArrayList<StudentData>();
    private ArrayList<TeacherData> teacherData = new ArrayList<TeacherData>();
    private ArrayList<LessonData> lessonData = new ArrayList<LessonData>();
    private ArrayList<GroupData> groupData = new ArrayList<GroupData>();

    // Getters and Setters
    public ArrayList<StudentData> getStudentData() {
        return studentData;
    }
    public void setStudentData(ArrayList<StudentData> studentData) {
        this.studentData = studentData;
    }

    public ArrayList<TeacherData> getTeacherData() {
        return teacherData;
    }
    public void setTeacherData(ArrayList<TeacherData> teacherData) {
        this.teacherData = teacherData;
    }

    public ArrayList<LessonData> getLessonData() {
        return lessonData;
    }
    public void setLessonData(ArrayList<LessonData> lessonData) {
        this.lessonData = lessonData;
    }

    public ArrayList<GroupData> getGroupData() {
        return groupData;
    }
    public void setGroupData(ArrayList<GroupData> groupData) {
        this.groupData = groupData;
    }

    // Finders by ID
    public GroupData findStudentGroupDataByID(int groupID){
        for(GroupData gData : this.groupData){
            if(gData.groupID == groupID) { return gData; }
        }
        return null;
    }

    public TeacherData findTeacherDataByID(int teacherID){
        for(TeacherData tData : this.teacherData){
            if(tData.teacherId == teacherID) { return tData; }
        }
        return null;
    }

    public StudentData findStudentDataByID(int studentID){
        for(StudentData sData : this.studentData){
            if(sData.studentID == studentID) { return sData; }
        }
        return null;
    }

    public LessonData findLessonDataByID(int lessonID){
        for(LessonData lData : lessonData){
            if(lData.lessonID == lessonID) { return lData; }
        }
        return null;
    }

    // Finders by Name
    public GroupData findStudentGroupDataByName(String groupName){
        for(GroupData gData : this.groupData){
            if(gData.name.equals(groupName)) { return gData; }
        }
        return null;
    }

    public TeacherData findTeacherDataByName(String teacherName){
        for(TeacherData tData : this.teacherData){
            if(tData.name.equals(teacherName)) { return tData; }
        }
        return null;
    }

    public StudentData findStudentDataByName(String studentName){
        for(StudentData sData : this.studentData){
            if(sData.equals(studentName)) { return sData; }
        }
        return null;
    }
}
