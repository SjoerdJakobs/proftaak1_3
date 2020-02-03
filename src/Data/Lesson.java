package Data;

import Data.Rooms.ClassRoom;

import java.time.LocalTime;

public class Lesson {
    private StudentGroup studentGroup;
    private Teacher teacher;
    private LocalTime beginTime;
    private LocalTime endTime;
    private ClassRoom classRoom;

    public Lesson(StudentGroup studentGroup, Teacher teacher, LocalTime beginTime, LocalTime endTime, ClassRoom classRoom){
        this.studentGroup = studentGroup;
        this.teacher = teacher;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.classRoom = classRoom;
    }
}
