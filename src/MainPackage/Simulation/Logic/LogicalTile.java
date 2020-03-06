package MainPackage.Simulation.Logic;

import java.util.HashMap;

public class LogicalTile {
    HashMap<String,Direction> givenDirections;

    public void addInstruction(String location, Direction direction){
        givenDirections.put(location,direction);
        
    }

    public Direction getDirection(String target){
        return givenDirections.get(target);
    }




}
