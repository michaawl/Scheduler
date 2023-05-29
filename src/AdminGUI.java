/*
 * Project: Scheduler
 * Scheduler program for Administrator, Assistants and Students
 * Author:  Michael Muehlberger
 * Last Change: 29.05.2023
 */


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class AdminGUI extends JFrame{
    private JPanel panelMain;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JButton ccCreate;
    private JButton ccVC;
    private JButton ccVR;
    private JButton ccBack;
    private JButton preferencesButton;
    private JButton registerAndEditUsersButton;
    private JButton logOutButton;
    private JButton mainCC;
    private JButton mainEditor;
    private JTabbedPane tabbedPane1;
    private JTextField textField1;
    private JButton addButton;
    private JButton deleteSelectedCourseButton;
    private JButton backButton3;
    private JTextField textField2;
    private JButton addButton1;
    private JButton deleteSelectedRoomButton;
    private JButton backButton2;
    private JButton addRemoveStudentsFromButton;
    private JButton addStudentToAButton;
    private JButton removeStudentFromSelectedButton;
    private JButton backButton4;
    private JButton changeRoomButton;
    private JButton backButton1;
    private JComboBox comboBox6;
    private JComboBox comboBox9;
    private JButton confrimEditButton;
    private JButton deleteButton;
    private JButton checkConsistencyButton;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel panel21;
    private JPanel panel22;
    private JPanel arCPanel;
    private JPanel arRPanel;
    private JPanel cePanel;
    private javax.swing.JPanel JPanel;
    private JPanel JPanel2;
    private JScrollPane scrollPane1;
    private JScrollPane scrollPane2;
    private JLabel msgLbl;
    private JScrollPane scrollPane3;
    private JScrollPane scrollPane4;
    private JScrollPane scrollPane5;
    private JLabel h;
    private JScrollPane scrollPane6;
    private JButton selectStudentButton;
    private JLabel slctdLbl;
    private JScrollPane scrollPaneS1;
    private JScrollPane scrollPaneS2;
    private JScrollPane scrollPaneS3;
    private JLabel studentMsg;
    private JButton viewAllCoursesButton;

    static DefaultListModel<String> courseListModel = new DefaultListModel<>();
    static DefaultListModel<String> roomListModel = new DefaultListModel<>();
    static DefaultListModel<String> timetableListModel = new DefaultListModel<>();

    static DefaultListModel<String> studentListModel = new DefaultListModel<>();
    static DefaultListModel<String> selectedCourseListModel = new DefaultListModel<>();

    public AdminGUI(Person user){

        // Set the layout manager for the main panel
        panelMain.setLayout(new CardLayout());

        // Add panels to the main panel
        panelMain.add(panel1, "panel1");
        panelMain.add(panel2, "panel2");
        panelMain.add(panel3, "panel3");
        panelMain.add(panel4, "panel4");

        // Show panel2
        CardLayout cardLayout = (CardLayout) panelMain.getLayout();
        cardLayout.show(panelMain, "panel2");

        // Add the main panel to the JFrame content pane
        getContentPane().add(panelMain);

        // Set JFrame properties
        setTitle("Admin GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200,600);
        panel21.setBorder(new EmptyBorder(50, 20, 20, 20));
        panel22.setBorder(new EmptyBorder(50, 20, 20, 20));
        setLocationRelativeTo(null); //centering window

        setVisible(true);


        /*
        ################### CREATER COURSE PANEL ###################
         */

        JList ccCourseList = new JList();
        JList ccRoomList = new JList();

        ccCourseList.setModel(courseListModel);
        ccRoomList.setModel(roomListModel);

        scrollPane1.setViewportView(ccCourseList);
        scrollPane2.setViewportView(ccRoomList);


        ccCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int start = Integer.parseInt(comboBox2.getSelectedItem().toString());
                int end = Integer.parseInt(comboBox3.getSelectedItem().toString());
                String courseStrg = ccCourseList.getSelectedValue().toString();
                String day = comboBox1.getSelectedItem().toString();
                String room = "";

                ArrayList<String> collisionStudents = new ArrayList<>();
                collisionStudents = timeConsistancyChecker(courseStrg, day, start, end);


                if(collisionStudents.isEmpty()){
                    try{


                        room = ccRoomList.getSelectedValue().toString();

                        if(roomConsistancyChecker(room, day, start, end)){

                            msgLbl.setText("Created course: " + courseStrg + " in room " +
                                    room + " from " + day + ", " + start + ":00h to " +end + ":00h");

                            String line = courseStrg + "," + room + "," + day + "," + start + "," + end;

                            Application.writeToCSVFile(line, "src/csv/timetable.csv");

                            Course course = new Course();
                            course.setCourse(courseStrg);
                            course.setRoom(room);
                            course.setStartTime(start);
                            course.setEndTime(end);
                            course.setDay(day);

                            Course.CourseArray.add(course);

                        }else{
                            System.out.println("Room no available");
                        }

                    }catch (Exception e4){
                        room = "noroom";
                    }
                }else{

                    System.out.printf("Cannot change course because of collision, change time or remove students from Course. \nCollision students are:");

                    for(String collisionStudent : collisionStudents){
                        System.out.printf(" " + collisionStudent);
                    }

                }
            }
        });

        ccBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelMain, "panel2");
            }
        });

        mainCC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelMain, "panel1");

                courseListModel.clear();
                roomListModel.clear();

                for(String course: Course.CourseListArray){
                    courseListModel.addElement(course);
                }

                for(Room room : Room.RoomArray){
                    roomListModel.addElement(room.getRoom());
                }

            }
        });

        /*
        ################### EDITOR ###################
         */

        JList eCourseList = new JList<>();
        JList eTimetableList = new JList<>();
        JList eRoomList = new JList();
        JList eRoomList2 = new JList();

        eCourseList.setModel(courseListModel);
        eTimetableList.setModel(timetableListModel);
        eRoomList.setModel(roomListModel);
        eRoomList2.setModel(roomListModel);

        scrollPane3.setViewportView(eCourseList);
        scrollPane4.setViewportView(eRoomList);
        scrollPane5.setViewportView(eTimetableList);
        scrollPane6.setViewportView(eRoomList2);

        mainEditor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelMain, "panel3");

                courseListModel.clear();
                roomListModel.clear();
                timetableListModel.clear();

                for(String course: Course.CourseListArray){
                    courseListModel.addElement(course);
                }

                for(Room room : Room.RoomArray){
                    roomListModel.addElement(room.getRoom());
                }

                for(Course course : Course.CourseArray){

                    String courseInfo = "";
                    courseInfo = "Course: " + course.getCourse() + ". Room: " + course.getRoom() +
                    ". Date: " + course.getDay() + ", " +course.getStartTime() + "-" + course.getEndTime()+
                    " o'clock";

                    timetableListModel.addElement(courseInfo);
                }
            }
        });

        //####### Edit Course #######

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String addCourseString = textField1.getText();
                if(!addCourseString.isBlank()){

                    Application.writeToCSVFile(addCourseString, "src/csv/courses.csv");

                    Course.CourseListArray.add(addCourseString);

                    courseListModel.clear();

                    for(String course: Course.CourseListArray){
                        courseListModel.addElement(course);
                    }
                }
            }
        });

        deleteSelectedCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int deleteLine = eCourseList.getSelectedIndex();
                String selectedCourse = eCourseList.getSelectedValue().toString();
                System.out.println(deleteLine);

                Application.deleteFromCSVFile("src/csv/courses.csv", deleteLine);
                Course.CourseListArray.remove(deleteLine);

                courseListModel.clear();
                for (String course : Course.CourseListArray) {
                    courseListModel.addElement(course);
                }



                    for (Course courseElement : Course.CourseArray) {

                        if (selectedCourse.equals(courseElement.getCourse())) {
                            Application.deleteFromCSVFileByFirstEntry("src/csv/timetable.csv", selectedCourse);
                            Course.CourseArray.remove(courseElement);
                        }
                    }

                timetableListModel.clear();

                for(Course course : Course.CourseArray){

                    String courseInfo = "";
                    courseInfo = "Course: " + course.getCourse() + ". Room: " + course.getRoom() +
                            ". Date: " + course.getDay() + ", " +course.getStartTime() + "-" + course.getEndTime()+
                            " o'clock";

                    timetableListModel.addElement(courseInfo);
                }

            }

        });

        //####### Edit Room #######

        addButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String addRoomString = textField2.getText();
                if(!addRoomString.isBlank()){

                    Application.writeToCSVFile(addRoomString, "src/csv/rooms.csv");

                    Room newRoom = new Room();
                    newRoom.setRoom(addRoomString);
                    Room.RoomArray.add(newRoom);

                    roomListModel.clear();

                    for(Room room : Room.RoomArray){
                        roomListModel.addElement(room.getRoom());
                    }
                }

            }
        });

        deleteSelectedRoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int deleteLine = eRoomList.getSelectedIndex();
                String roomString = eRoomList.getSelectedValue().toString();

                if(deleteLine==0){

                    System.out.println("noroom cannot be deleted.");

                }
                else {


                    System.out.println(deleteLine);

                    Application.deleteFromCSVFile("src/csv/rooms.csv",deleteLine);
                    Room.RoomArray.remove(deleteLine);

                    removeRoomfromCourse("src/csv/timetable.csv", roomString);

                    for (Course courseElement : Course.CourseArray) {

                        if (courseElement.getRoom().equals(roomString)) {
                            courseElement.setRoom("noroom");
                        }
                    }

                    roomListModel.clear();
                    for(Room room : Room.RoomArray){
                        roomListModel.addElement(room.getRoom());
                    }

                    timetableListModel.clear();

                    for(Course course : Course.CourseArray){

                        String courseInfo = "";
                        courseInfo = "Course: " + course.getCourse() + ". Room: " + course.getRoom() +
                                ". Date: " + course.getDay() + ", " +course.getStartTime() + "-" + course.getEndTime()+
                                " o'clock";

                        timetableListModel.addElement(courseInfo);
                    }

                }

            }
        });

        /*
        ################### EDIT COURSES ###################
         */

        changeRoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String editLine = "";
                String editRoom = eRoomList2.getSelectedValue().toString();
                int editIndex = eTimetableList.getSelectedIndex();

                Course editCourse = Course.CourseArray.get(editIndex);

                if(roomConsistancyChecker(editRoom, editCourse.getDay(), editCourse.getStartTime(), editCourse.getEndTime())){

                    Course.CourseArray.get(editIndex).setRoom(editRoom);

                    editLine = editCourse.getCourse() + "," + editRoom + "," +
                            editCourse.getDay() + "," + editCourse.getStartTime() + "," +
                            editCourse.getEndTime();

                    Application.editCSVFile("src/csv/timetable.csv", editIndex, editLine);

                    System.out.println(editLine);

                    timetableListModel.clear();

                    for(Course course : Course.CourseArray){

                        String courseInfo = "";
                        courseInfo = "Course: " + course.getCourse() + ". Room: " + course.getRoom() +
                                ". Date: " + course.getDay() + ", " +course.getStartTime() + "-" + course.getEndTime()+
                                " o'clock";

                        timetableListModel.addElement(courseInfo);

                }

                } else{
                    System.out.println("Selected room not available.");
                }
            }
        });

        confrimEditButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String editLine = "";
                int newStart = Integer.parseInt(comboBox6.getSelectedItem().toString());
                int newEnd = Integer.parseInt(comboBox9.getSelectedItem().toString());
                int editIndex = eTimetableList.getSelectedIndex();

                Course editCourse = Course.CourseArray.get(editIndex);

                String day = editCourse.getDay();
                String courseEdit = editCourse.getCourse();

                ArrayList<String> collisionStudents = new ArrayList<>();
                collisionStudents = timeConsistancyChecker(courseEdit, day, newStart, newEnd);

                if (collisionStudents.isEmpty()) {
                    Course.CourseArray.get(editIndex).setStartTime(newStart);
                    Course.CourseArray.get(editIndex).setEndTime(newEnd);

                    editLine = editCourse.getCourse() + "," + editCourse.getRoom() + "," +
                            editCourse.getDay() + "," + newStart + "," +
                            newEnd;

                    Application.editCSVFile("src/csv/timetable.csv", editIndex, editLine);

                    System.out.println(editLine);

                    timetableListModel.clear();

                    for (Course course : Course.CourseArray) {

                        String courseInfo = "";
                        courseInfo = "Course: " + course.getCourse() + ". Room: " + course.getRoom() +
                                ". Date: " + course.getDay() + ", " + course.getStartTime() + "-" + course.getEndTime() +
                                " o'clock";

                        timetableListModel.addElement(courseInfo);
                    }

                } else{

                    System.out.printf("Cannot change course because of collision, change time or remove students from Course. \nCollision students are:");

                    for(String collisionStudent : collisionStudents){
                        System.out.printf(" " + collisionStudent);
                    }

                }
            }

        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int deleteLine = eTimetableList.getSelectedIndex();

                Application.deleteFromCSVFile("src/csv/timetable.csv",deleteLine);
                Course.CourseArray.remove(deleteLine);

                timetableListModel.clear();

                for(Course course : Course.CourseArray){

                    String courseInfo = "";
                    courseInfo = "Course: " + course.getCourse() + ". Room: " + course.getRoom() +
                            ". Date: " + course.getDay() + ", " +course.getStartTime() + "-" + course.getEndTime()+
                            " o'clock";

                    timetableListModel.addElement(courseInfo);
                }

            }
        });

        backButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelMain, "panel2");
            }
        });

        /*
        ################### STUDENT EDITOR ###################
         */

        JList studentList = new JList();
        JList selectedCourseList = new JList();
        JList courseList = new JList();

        studentList.setModel(studentListModel);
        selectedCourseList.setModel(selectedCourseListModel);
        courseList.setModel(courseListModel);

        scrollPaneS1.setViewportView(studentList);
        scrollPaneS2.setViewportView(selectedCourseList);
        scrollPaneS3.setViewportView(courseList);

        addRemoveStudentsFromButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelMain, "panel4");

                studentListModel.clear();
                selectedCourseListModel.clear();
                courseListModel.clear();

                for(String course: Course.CourseListArray){
                    courseListModel.addElement(course);
                }

                for(Person person : Person.PersonArray){
                    if(person.isStudentStatus()){
                        studentListModel.addElement(person.getUsername());
                    }
                }
            }
        });

        addStudentToAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("hi");

                try {
                    String username = studentList.getSelectedValue().toString();
                    String courseName = courseList.getSelectedValue().toString();

                    if(slctdLbl.getText().equals("none")){
                        studentMsg.setText("Select Student");

                    } else {
                        for(Person student : Person.PersonArray){

                            if(student.getUsername().equals(username)){

                                if(StudentGUI.checkConsistancy(student, courseName)) {

                                    StudentGUI.addDelCourse("src/csv/students.csv", student, courseName, true); //true add, false remove


                                    selectedCourseListModel.clear();

                                    for (String course : student.getStudentCourseList()) {
                                        selectedCourseListModel.addElement(course);
                                    }

                                }else {
                                    System.out.println("Already booked other courses at that time!");
                                }

                                break;
                            }

                        }
                    }

                } catch (Exception e5){
                    studentMsg.setText("Select student and course.");
                    e5.printStackTrace();
                }


            }
        });

        selectStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String username = studentList.getSelectedValue().toString();


                selectedCourseListModel.clear();

                for(Person student : Person.PersonArray){
                    if(student.getUsername().equals(username)){
                        Person user = student;
                        slctdLbl.setText(username);

                        for (String course : user.getStudentCourseList()) {
                            selectedCourseListModel.addElement(course);
                        }
                    }
                }

            }
        });

        backButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelMain, "panel2");
            }
        });

        removeStudentFromSelectedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    String username = studentList.getSelectedValue().toString();
                    String removeCourse = selectedCourseList.getSelectedValue().toString();

                    if(username.equals("none")){
                        studentMsg.setText("Select Student");

                    } else {

                        for(Person student : Person.PersonArray){

                            if(student.getUsername().equals(username)){

                                StudentGUI.addDelCourse("src/csv/students.csv", student, removeCourse, false); //true add, false remove

                                selectedCourseListModel.clear();

                                for (String course : student.getStudentCourseList()) {
                                    selectedCourseListModel.addElement(course);
                                }

                                break;
                            }
                        }

                    }

                } catch (Exception e5){
                    studentMsg.setText("No Student selected");
                }


            }

        });


         /*
        ################### END ###################
         */


        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                getContentPane().remove(panelMain);
                dispose(); //release any resources associated with it and close the window

                LoginGUI loginGUI = new LoginGUI();

            }
        });


        backButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelMain, "panel2");
            }
        });
        backButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelMain, "panel2");
            }
        });

        registerAndEditUsersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                getContentPane().remove(panelMain);
                dispose();
                RegisterGUI startFrame = new RegisterGUI(user);
            }
        });

        viewAllCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getContentPane().remove(panelMain);
                dispose();
                AllCourses allCourses = new AllCourses(user);
            }
        });
        preferencesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getContentPane().remove(panelMain);
                dispose();
                Preferences preferences = new Preferences(user);
            }
        });
    }

    static ArrayList<String> timeConsistancyChecker(String editCourse, String day, int startTime, int endTime){

        ArrayList<String> stringList = new ArrayList<>();
        boolean studentIsInCourse = false;

        for(Person allStudents : Person.PersonArray){

            if(allStudents.isStudentStatus()){
                studentIsInCourse = false;

                for(String studentCourse : allStudents.getStudentCourseList()){

                    if(studentCourse.equals(editCourse)){
                        studentIsInCourse = true;
                    }
                }

                if(studentIsInCourse) {

                    for(String studentCourse : allStudents.getStudentCourseList()){

                        for(Course allCourses : Course.CourseArray){

                            if(allCourses.getCourse().equals(studentCourse) && allCourses.getDay().equals(day) && !(allCourses.getCourse().equals(editCourse))){

                                int c1_S = allCourses.getStartTime();
                                int c1_E = allCourses.getEndTime();

                                int c2_S = startTime;
                                int c2_E = endTime;

                                if((c2_S <= c1_S) && (c2_E > c1_S)){
                                    stringList.add(allStudents.getUsername());
                                }

                                if((c2_S < c1_E) && (c2_S >= c1_S)){
                                    stringList.add(allStudents.getUsername());
                                }

                            }
                        }
                    }

                }
            }
        }

        return stringList;
    }

    static boolean roomConsistancyChecker(String room, String day, int startTime, int endTime){

        for(Course existingCourse : Course.CourseArray){

            if(existingCourse.getRoom().equals(room) && existingCourse.getDay().equals(day)){
                if(existingCourse.getStartTime()>= startTime && existingCourse.getStartTime()<endTime){
                    return false;
                }

                if(existingCourse.getEndTime()<startTime && existingCourse.getEndTime() > startTime){
                    return false;
                }

            }

        }

        return true;
    }

    static void removeRoomfromCourse(String filePath, String room) {

        List<String> lines = new ArrayList<>();
        String editString = "";
        boolean edited = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line = "";

            int lineInt = 0;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                StringTokenizer tokenizer = new StringTokenizer(line, ",");
                edited = false;

                if (tokenizer.hasMoreTokens()) {

                    editString = tokenizer.nextToken();

                    if(tokenizer.hasMoreTokens() && data[1].equals(room)){

                        String nextString = tokenizer.nextToken();
                        editString = editString + ",noroom";

                        while (tokenizer.hasMoreTokens()) {

                                nextString = tokenizer.nextToken();
                                editString = editString + "," + nextString;

                            }

                        lines.add(editString);
                        edited = true;

                        }

                    }

                if(edited==false){
                    lines.add(line);
                }

                lineInt++;
            }

        } catch (IOException e) {

        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {


            for (String stringLine : lines) {
                writer.write(stringLine);
                writer.newLine();
            }

            System.out.print(lines);
            writer.flush();

        } catch (IOException e) {
            System.out.println(e);
        }


    }

}
