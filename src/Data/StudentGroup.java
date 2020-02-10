package Data;

import Data.SavedData.GroupData;
import Data.SavedData.SavedData;
import Data.SavedData.StudentData;

import java.util.ArrayList;

public class StudentGroup {
    private GroupData groupData;

    public StudentGroup(){

    }

    // Getters and Setters
    public String getName() {
        return this.groupData.name;
    }
    public void setName(String name) {
        this.groupData.name = name;
    }

    public int getGroupID() {
        return this.groupData.groupID;
    }
    public void setGroupID(int groupID) {
        this.groupData.groupID = groupID;
    }

    public ArrayList<Integer> getStudentsIDs() {
        return this.groupData.studentsIDs;
    }
    public void setStudentsIDs(ArrayList<Integer> studentsIDs) {
        this.groupData.studentsIDs = studentsIDs;
    }


    public void addStudent(int studentID){
        groupData.studentsIDs.add(studentID);
    }

    public void removeStudent(int studentID){
        groupData.studentsIDs.remove(studentID);
    }

    public boolean isInGroup(int studentID){
        return groupData.studentsIDs.contains(studentID);
    }

    @Override
    public String toString() {
        return "StudentGroup{" +
                "name='" + groupData.name + '\'' +
                ", groupID=" + groupData.groupID +
                ", students=" + groupData.studentsIDs +
                '}';
    }
}
