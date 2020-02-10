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

    public ArrayList<Lesson> getLessonFromClassRoom(int classRoomName){
        ArrayList<Lesson> lessonsInClass = new ArrayList<>();
        for(Lesson lesson : this.lessons){
            if(lesson.getClassRoom().getRoomName() == (classRoomName)){
                lessonsInClass.add(lesson);
            }
        }
        return lessonsInClass;
    }

    // Getters and Setters
    public ArrayList<Lesson> getLessons() { return this.lessons; }

    public void setLessons(ArrayList<Lesson> lessons) { this.lessons = lessons; }

//    public ArrayList<ArrayList<Lesson>> getLessonsDividedByClass(){
//        ArrayList<ArrayList<Lesson>> listLessonDividedByClass = new ArrayList<>();
//        ArrayList<Lesson> firstClass = new ArrayList<>();
//        firstClass.add(lessons.get(0));
//        listLessonDividedByClass.add(firstClass);
//        for(Lesson lesson : lessons){
//            for(ArrayList<Lesson> classe : listLessonDividedByClass){
//                for(int i=0; i<classe.size(); i++){
//                    if(classe.get(i).getClassRoom().getRoomName().equals(lesson.getClassRoom().getRoomName())){
//                        classe.add(lesson);
//                    }
//                    else{
//                        ArrayList<Lesson> newClass = new ArrayList<>();
//                        newClass.add(lesson);
//                        listLessonDividedByClass.add(newClass);
//                    }
//                }
//            }
//        }
//        System.out.println(listLessonDividedByClass.size());
//        return listLessonDividedByClass;
//    }

}
