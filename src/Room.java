import java.util.concurrent.CopyOnWriteArrayList;

public class Room {

    private String room;
    static CopyOnWriteArrayList<Room> RoomArray = new CopyOnWriteArrayList<>();

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
