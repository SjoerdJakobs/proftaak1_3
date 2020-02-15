package MainPackage.ReadWriteData.DataClasses;

import java.time.LocalTime;

public class LessonData {

    public LessonData()
    {

    }

    public LessonData(int studentGroupId,int teacherId,LocalTime beginTime,LocalTime endTime,int classRoomId,int lessonID)
    {
        this.studentGroupId = studentGroupId;
        this.teacherId = teacherId;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.classRoomId = classRoomId;
        this.lessonID = lessonID;
    }

    public int studentGroupId;
    public int teacherId;
    public LocalTime beginTime;
    public LocalTime endTime;
    public int classRoomId;
    public int lessonID;
}
