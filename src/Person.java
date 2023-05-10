/*
 * Project: GUI
 * User Management Software using a Java GUI
 * Author:  Michael Muehlberger
 * Last Change: 03.05.2023
 */

public class Person {


    private boolean adminStatus;
    private boolean assistantStatus;
    private boolean studentStatus;
    private String username;
    private String email;
    private int userID;
    static int userCount = 0;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Person() {
        userCount++;
        this.userID = userCount;
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
