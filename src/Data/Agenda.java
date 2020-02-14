package Data;

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

    public ArrayList<Lesson> getLessonFromClassRoom(String classRoomName){
        ArrayList<Lesson> lessonsInClass = new ArrayList<>();
        for(Lesson lesson : this.lessons){
            if(lesson.getClassRoom().equals(classRoomName)){
                lessonsInClass.add(lesson);
            }
        }
        return lessonsInClass;
    }

    // Getters and Setters
    public ArrayList<Lesson> getLessons() { return this.lessons; }
    public void setLessons(ArrayList<Lesson> lessons) { this.lessons = lessons; }
}
