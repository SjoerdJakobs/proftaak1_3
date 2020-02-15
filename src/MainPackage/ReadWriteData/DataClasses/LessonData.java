package MainPackage.ReadWriteData.DataClasses;

import Data.Rooms.ClassRoom;
import Data.StudentGroup;
import javafx.scene.layout.GridPane;

import java.io.Serializable;
import java.time.LocalTime;

public class LessonData implements Serializable {

    public GroupData studentGroupId;
    public TeacherData teacherId;
    public LocalTime beginTime;
    public LocalTime endTime;
    public ClassRoom classRoomId;
    public int lessonID;

    public LessonData()
    {

    }

    public LessonData(GroupData studentGroupId, TeacherData teacherId, LocalTime beginTime, LocalTime endTime, ClassRoom classRoomId)
    {
        this.studentGroupId = studentGroupId;
        this.teacherId = teacherId;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.classRoomId = classRoomId;
     //   this.lessonID = lessonID;
    }

    public GroupData getStudentGroup() {
        return studentGroupId;
    }

    public void setStudentGroup(GroupData studentGroupId) {
        this.studentGroupId = studentGroupId;
    }

    public TeacherData getTeacher() {
        return teacherId;
    }

    public void setTeacher(TeacherData teacherId) {
        this.teacherId = teacherId;
    }

    public LocalTime getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(LocalTime beginTime) {
        this.beginTime = beginTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public ClassRoom getClassRoom() {
        return classRoomId;
    }

    public void setClassRoom(ClassRoom classRoomId) {
        this.classRoomId = classRoomId;
    }

//    public int getLessonID() {
//        return lessonID;
//    }
//
//    public void setLessonID(int lessonID) {
//        this.lessonID = lessonID;
//    }
}
