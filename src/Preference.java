/*
 * Project: Scheduler
 * Scheduler program for Administrator, Assistants and Students
 * Author:  Michael Muehlberger
 * Last Change: 29.05.2023
 */


import java.util.concurrent.CopyOnWriteArrayList;

//class Preference sets preference with course, room, startTime, endTime, day and creator of Preference, Preference Array includes all preferences.
public class Preference {

    private String course;
    private String room;
    private int startTime;
    private int endTime;
    private String day;
    private String creator;

    static CopyOnWriteArrayList<Preference> PreferenceArray = new CopyOnWriteArrayList<>();

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }


}
