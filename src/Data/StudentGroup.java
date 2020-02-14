package Data;

import java.util.ArrayList;

public class StudentGroup {
    private String name;
    private ArrayList<StudentData> studentData;

    public StudentGroup(String name){
        this.name = name;
        this.studentData = new ArrayList<>();
    }

    public void addStudent(StudentData studentData){
        this.studentData.add(studentData);
    }

    public void removeStudent(StudentData studentData){
        this.studentData.remove(studentData);
    }

    public StudentData getStudent(String name){
        for (StudentData studentData : this.studentData){
            if(studentData.equals(name)) {
                return studentData;
            }
        }
        return null;
    }


    //Getters and Setters
    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }
    public ArrayList<StudentData> getStudentData(){ return this.studentData; }
    public void setStudentData(ArrayList<StudentData> studentData) { this.studentData = studentData; }


    @Override
    public String toString() {
        return name ;
    }
}
