package Data;

import Data.Rooms.ClassRoom;

import java.util.ArrayList;

public enum DataLoader {

    INSTANCE;


    private ArrayList<ClassRoom> classRooms;
    private ArrayList<Lesson> lessons;
    private ArrayList<Teacher> teachers;
    private ArrayList<StudentGroup> groups;
    private ArrayList<StudentData> students;


    public ArrayList<ClassRoom> getClassRooms() {
        return classRooms;
    }

    public void setClassRooms(ArrayList<ClassRoom> classRooms) {
        this.classRooms = classRooms;
    }

    public ArrayList<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(ArrayList<Lesson> lessons) {
        this.lessons = lessons;
    }

    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(ArrayList<Teacher> teachers) {
        this.teachers = teachers;
    }

    public ArrayList<StudentGroup> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<StudentGroup> groups) {
        this.groups = groups;
    }

    public ArrayList<StudentData> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<StudentData> students) {
        this.students = students;
    }



    public void writeAllDataIO(){

        //need to implement the code for writing a file
    }


    public void readAllDataIO(){

        //need to implement the code for reading a file and setting the right values
    }

}
