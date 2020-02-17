package MainPackage.ReadWriteData.DataClasses;

import java.io.Serializable;
import java.util.ArrayList;

public class GroupData implements Serializable {
    public String name;
   // public int groupID;
   // public ArrayList<StudentData> students;

    public GroupData()
    {

    }

    public GroupData(String name)
    {
        this.name = name;
       // this.groupID = groupID;
     //   this.students = students;
    }


//    public void addStudent(StudentData studentData){
//        this.students.add(studentData);
//    }
//
//    public void removeStudent(StudentData studentData){
//        this.students.remove(studentData);
//    }
//
//    public StudentData getStudent(String name){
//        for (StudentData studentData : this.students){
//            if(studentData.getName().equals(name)) {
//                return studentData;
//            }
//        }
//        return null;
//    }


    //Getters and Setters
    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }
//    public ArrayList<StudentData> getStudentData(){ return this.students; }
//    public void setStudentData(ArrayList<StudentData> studentData) { this.students = studentData; }


    @Override
    public String toString() {
        return name ;
    }

}
