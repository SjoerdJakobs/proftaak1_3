package Data;

import MainPackage.ReadWriteData.DataClasses.LessonData;
import java.time.LocalTime;

public class Lesson {
    private LessonData lessonData;

    public Lesson(){

    }

    public Lesson(LessonData lessonData){
        this.lessonData = lessonData;
    }

    // Getters and Setters
    public int getStudentGroup() { return this.lessonData.studentGroupId; }
    public void setStudentGroup(int studentGroup) {
        this.lessonData.studentGroupId = studentGroup;
    }

    public int getTeacher() {
        return this.lessonData.teacherId;
    }
    public void setTeacher(int teacher) {
        this.lessonData.teacherId = teacher;
    }

    public LocalTime getBeginTime() {
        return this.lessonData.beginTime;
    }
    public void setBeginTime(LocalTime beginTime) {
        this.lessonData.beginTime = beginTime;
    }

    public LocalTime getEndTime() {
        return this.lessonData.endTime;
    }
    public void setEndTime(LocalTime endTime) {
        this.lessonData.endTime = endTime;
    }

    public int getClassRoom() {
        return this.lessonData.classRoomId;
    }
    public void setClassRoom(int classRoomId) {
        this.lessonData.classRoomId = classRoomId;
    }

    public int getLessonID() {
        return this.lessonData.lessonID;
    }
    public void setLessonID(int lessonID) {
        this.lessonData.lessonID = lessonID;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "studentGroup=" + lessonData.studentGroupId +
                ", teacher=" + lessonData.teacherId +
                ", beginTime=" + lessonData.beginTime +
                ", endTime=" + lessonData.endTime +
                ", classRoom=" + lessonData.classRoomId +
                ", lessonID=" + lessonData.lessonID +
                '}';
    }
}
