package Data;

import java.util.ArrayList;

public class StudentGroup {
    private String name;
    private ArrayList<Student> students;
    private int groupID;

    public StudentGroup(String name, int groupID){
        this.name = name;
        this.students = new ArrayList<>();
        this.groupID = groupID;
    }

    public void addStudent(Student student){
        this.students.add(student);
    }

    public void removeStudent(Student student){
        this.students.remove(student);
    }

    public Student getStudent(String name){
        for (Student student : this.students){
            if(student.equals(name)) {
                return student;
            }
        }
        return null;
    }


    //Getters and Setters
    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }
    public ArrayList<Student> getStudents(){ return this.students; }
    public void setStudents(ArrayList<Student> students) { this.students = students; }
    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    @Override
    public String toString() {
        return "StudentGroup{" +
                "name='" + name + '\'' +
                ", students=" + students +
                ", groupID=" + groupID +
                '}';
    }
}
