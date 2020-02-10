package Data;

import Data.Rooms.ClassRoom;
import Data.SavedData.LessonData;
import java.time.LocalTime;

public class Lesson {
    private LessonData lessonData;

    public Lesson(int lessonID, StudentGroup studentGroup, Teacher teacher, LocalTime beginTime, LocalTime endTime, ClassRoom classRoom){

    }

    // Getters and Setters
    public String getStudentGroup() { return this.lessonData.studentGroup; }
    public void setStudentGroup(String studentGroup) {
        this.lessonData.studentGroup = studentGroup;
    }

    public String getTeacher() {
        return this.lessonData.teacher;
    }
    public void setTeacher(String teacher) {
        this.lessonData.teacher = teacher;
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

    public String getClassRoom() {
        return this.lessonData.classRoom;
    }
    public void setClassRoom(String classRoom) {
        this.lessonData.classRoom = classRoom;
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
                "studentGroup=" + lessonData.studentGroup +
                ", teacher=" + lessonData.teacher +
                ", beginTime=" + lessonData.beginTime +
                ", endTime=" + lessonData.endTime +
                ", classRoom=" + lessonData.classRoom +
                ", lessonID=" + lessonData.lessonID +
                '}';
    }
}
