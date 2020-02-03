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

    // Getter and Setters

    public StudentGroup getStudentGroup() {
        return studentGroup;
    }

    public void setStudentGroup(StudentGroup studentGroup) {
        this.studentGroup = studentGroup;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
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
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }
}
