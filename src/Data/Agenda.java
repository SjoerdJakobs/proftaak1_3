package Data;

import Data.Rooms.ClassRoom;

import java.util.ArrayList;

public class Agenda {
    private ArrayList<Lesson> lessons;

    public Agenda(){
        lessons = new ArrayList<>();
    }

    public void addLesson(Lesson lesson){
        lessons.add(lesson);
    }

    public void removeLesson(Lesson lesson){
        lessons.remove(lesson);
    }

//    public ArrayList<Lesson> getLessonFromClassRoom(int classRoomName){
//        ArrayList<Lesson> lessonsInClass = new ArrayList<>();
//        for(Lesson lesson : this.lessons){
//            if(lesson.getClassRoom().getRoomName() == (classRoomName)){
//                lessonsInClass.add(lesson);
//            }
//        }
//        return lessonsInClass;
//    }
//
//    // Getters and Setters
//    public ArrayList<Lesson> getLessons() { return this.lessons; }
//
//    public void setLessons(ArrayList<Lesson> lessons) { this.lessons = lessons; }
//
//    public ArrayList<Lesson> getTeacherLessons(Teacher teacher){
//        ArrayList<Lesson> lessonsTeacher = new ArrayList<>();
//        if(!lessons.isEmpty()){
//            for(Lesson lesson : lessons){
//                if(lesson.getTeacher().getName().equals(teacher.getName())){
//                    lessonsTeacher.add(lesson);
//                }
//            }
//            return lessonsTeacher;
//        }
//        else {
//            return null;
//        }
//
//    }
//
//    public ArrayList<Lesson> getClassroomLessons(ClassRoom classRoom){
//        ArrayList<Lesson> lessonsClassroom = new ArrayList<>();
//        if(!lessons.isEmpty()){
//            for(Lesson lesson : lessons){
//                if(lesson.getClassRoom().getRoomName()==classRoom.getRoomName()){
//                    lessonsClassroom.add(lesson);
//                }
//            }
//            return lessonsClassroom;
//        }
//        else {
//            return null;
//        }
//
//    }
//
//    public ArrayList<Lesson> getStudentGroupLessons(StudentGroup studentGroup){
//        ArrayList<Lesson> lessonsStudentGroup = new ArrayList<>();
//        if(!lessons.isEmpty()){
//            for(Lesson lesson : lessons){
//                if(lesson.getStudentGroup().getName().equals(studentGroup.getName())){
//                    lessonsStudentGroup.add(lesson);
//                }
//            }
//            return lessonsStudentGroup;
//        }
//        else {
//            return null;
//        }
    }


