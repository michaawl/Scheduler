/*
 * Project: Scheduler
 * Scheduler program for Administrator, Assistants and Students
 * Author:  Michael Muehlberger
 * Last Change: 29.05.2023
 */

import java.util.concurrent.CopyOnWriteArrayList;

//class Person defines Person with usernames, email, password and whether the person is admin/assistant/student, PersonArray with all users, Studentcourse list with all courses a specific student has set
public class Person {


    private boolean adminStatus;
    private boolean assistantStatus;
    private boolean studentStatus;
    private String username;
    private String email;
    private String password;

    static CopyOnWriteArrayList<Person> PersonArray = new CopyOnWriteArrayList<>();

    public CopyOnWriteArrayList<String> getStudentCourseList() {
        return studentCourseList;
    }

    public void setStudentCourseList(CopyOnWriteArrayList<String> studentCourseList) {
        this.studentCourseList = studentCourseList;
    }

    private CopyOnWriteArrayList<String> studentCourseList = new CopyOnWriteArrayList<>();

    static int userCount = 0;

    public Person() {
        userCount++;

        //set all false, so only the status set becomes true afterwards
        this.adminStatus = false;
        this.assistantStatus = false;
        this.studentStatus = false;

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAssistantStatus() {
        return assistantStatus;
    }

    public void setAssistantStatus(boolean assistantStatus) {
        this.assistantStatus = assistantStatus;
    }

    public boolean isStudentStatus() {
        return studentStatus;
    }

    public void setStudentStatus(boolean studentStatus) {
        this.studentStatus = studentStatus;
    }

    public boolean isAdminStatus() {
        return adminStatus;
    }

    public void setAdminStatus(boolean adminStatus) {
        this.adminStatus = adminStatus;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
