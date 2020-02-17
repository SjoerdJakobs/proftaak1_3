package Data.Rooms;

import OOFramework.FrameworkProgram;
import OOFramework.StandardObject;

import java.io.Serializable;

public abstract class Room implements Serializable
{
    private int roomName;

    public Room(int roomName){
        this.roomName = roomName;
    }

    // Getters and Setters
    public int getRoomName() { return this.roomName; }
    public void setRoomName(int roomName) { this.roomName = roomName; }

    @Override
    public String toString() {
        return "LA" + roomName;
    }
}
