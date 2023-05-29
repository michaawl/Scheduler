/*
 * Project: Scheduler
 * Scheduler program for Administrator, Assistants and Students
 * Author:  Michael Muehlberger
 * Last Change: 29.05.2023
 */


import java.util.concurrent.CopyOnWriteArrayList;

//room class sets a room and inclues ArrayList RoomArray with all rooms set.
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
