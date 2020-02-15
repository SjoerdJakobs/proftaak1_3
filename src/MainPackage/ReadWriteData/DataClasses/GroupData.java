package MainPackage.ReadWriteData.DataClasses;

import java.io.Serializable;
import java.util.ArrayList;

public class GroupData implements Serializable {

    public GroupData()
    {

    }

    public GroupData(String name,int groupID,ArrayList<Integer> studentsIDs)
    {
        this.name = name;
        this.groupID = groupID;
        this.studentsIDs = studentsIDs;
    }

    public String name;
    public int groupID;
    public ArrayList<Integer> studentsIDs;
}
