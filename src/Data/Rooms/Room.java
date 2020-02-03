package Data.Rooms;

import OOFramework.FrameworkProgram;
import OOFramework.StandardObject;

public abstract class Room {
    private String roomName;

    public Room(String roomName){
        this.roomName = roomName;
    }

    // Getters and Setters
    public String getRoomName() { return this.roomName; }
    public void setRoomName(String roomName) { this.roomName = roomName; }
}
