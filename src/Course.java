/*
 * Project: Scheduler
 * Scheduler program for Administrator, Assistants and Students
 * Author:  Michael Muehlberger
 * Last Change: 29.05.2023
 */


import java.util.concurrent.CopyOnWriteArrayList;

//Class defines course with coursename, room, start time, end time, day of course, CourseListArray with all course names, CourseArray with all courses set
public class Course {

    private String course;
    private String room;
    private int startTime;
    private int endTime;

    private String day;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    static CopyOnWriteArrayList<String> CourseListArray = new CopyOnWriteArrayList<>();
    static CopyOnWriteArrayList<Course> CourseArray = new CopyOnWriteArrayList<>();


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

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
