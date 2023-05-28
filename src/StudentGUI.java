import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.concurrent.CopyOnWriteArrayList;

public class StudentGUI extends JFrame {
    private JPanel panelMain;
    private JButton selectCoursesButton;
    private JButton viewTimetableButton;
    private JButton logOutButton;
    private JButton addButton;
    private JButton removeButton;
    private JButton mainMenuButton;
    private JButton mainMenuButton1;
    private JPanel panelMenu;
    private JPanel panelCourse;
    private JPanel panelTimetable;
    private JScrollPane scrollPane1;
    private JScrollPane scrollPane2;
    private JScrollPane scrollPane;
    private JTable table1;
    private JLabel usnrmLbl;
    private JLabel usrnmLbl2;
    static DefaultListModel<String> courseListModel = new DefaultListModel<>();
    static DefaultListModel<String> selectedListModel = new DefaultListModel<>();
    static DefaultTableModel tableModel = new DefaultTableModel();

    public int addDelCourse(String filepath, Person user, String addDelCourse, boolean addTrue) {

        //check if student is in list

        String line = "";
        String editString = "";
        int lineInt = 0;

        try {

            FileReader reader = new FileReader(filepath);
            BufferedReader bufferedReader = new BufferedReader(reader);
            while ((line = bufferedReader.readLine()) != null) {

                String[] data = line.split(",");

                StringTokenizer tokenizer = new StringTokenizer(line, ",");

                if (data[0].equals(user.getUsername())) {
                    //skip first element
                    if (tokenizer.hasMoreTokens()) {

                        editString = tokenizer.nextToken();
                    }


                    //element 2 until end
                    while (tokenizer.hasMoreTokens()) {
                        if (addTrue) {
                            // ADD
                            String bookedCourses = tokenizer.nextToken();
                            editString = editString + "," + bookedCourses;

                            if (bookedCourses.equals(addDelCourse)) {
                                System.out.println("Course already booked.");
                                return 1;
                            }
                        } else {
                            // DELETE
                            String bookedCourses = tokenizer.nextToken();
                            if (bookedCourses.equals(addDelCourse)) {
                                continue;
                            } else {
                                editString = editString + "," + bookedCourses;
                            }
                        }

                    }



                    CopyOnWriteArrayList<String> updateList = user.getStudentCourseList();

                    if(addTrue){

                        System.out.println(editString);
                        editString = editString + "," + addDelCourse;
                        Application.editCSVFile("src/csv/students.csv", lineInt, editString);
                        System.out.println("Course added to selected student.");

                        updateList.add(addDelCourse);
                    }else {
                        System.out.println(editString);
                        editString = editString;
                        Application.editCSVFile("src/csv/students.csv", lineInt, editString);
                        System.out.println("Course deleted from selected student.");

                        updateList.remove(addDelCourse);
                    }

                    user.setStudentCourseList(updateList);

                    return 0;
                }

                lineInt++;
            }
        } catch (Exception e) {
            System.out.println(e);

        }

        editString = user.getUsername() + "," + addDelCourse;
        Application.writeToCSVFile(editString, "src/csv/students.csv");

        System.out.println("Added student to student.csv list.");

        return 2;
    }

    public StudentGUI(Person user) {


        panelMain.setLayout(new CardLayout());

        panelMain.add(panelMenu, "panelMenu");
        panelMain.add(panelCourse, "panelCourse");
        panelMain.add(panelTimetable, "panelTimetable");

        usnrmLbl.setText(user.getUsername());

        CardLayout cardLayout = (CardLayout) panelMain.getLayout();

        //TABLE SETTINGS
        Object[][] data = new Object[16][6];
        table1.setModel(new DefaultTableModel(
                data,
                new String[]{" ", "Monday", "Tuesday", "Wednesay", "Thursday", "Friday"}
        ));

        tableModel = (DefaultTableModel) table1.getModel();

        for (int i = 8; i < 24; i++) {
            tableModel.setValueAt(i + ":00", i - 8, 0);
        }


        getContentPane().add(panelMain);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 600);
        setLocationRelativeTo(null); //centering window

        setVisible(true);


        /*
        ################ COURSE MENU ################
         */

        JList courseList = new JList<>();
        JList selectedList = new JList<>();

        courseList.setModel(courseListModel);
        selectedList.setModel(selectedListModel);

        scrollPane1.setViewportView(courseList);
        scrollPane2.setViewportView(selectedList);


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String courseName = courseList.getSelectedValue().toString();

                if(checkConsistancy(user, courseName)){

                    addDelCourse("src/csv/students.csv", user, courseName, true); //true add, false remove

                    selectedListModel.clear();

                    if (!user.getStudentCourseList().isEmpty()) {
                        for (String course : user.getStudentCourseList()) {
                            selectedListModel.addElement(course);
                        }
                    }

                } else{
                    System.out.println("Already booked other courses at that time!");
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String courseName = selectedList.getSelectedValue().toString();

                addDelCourse("src/csv/students.csv", user, courseName, false); //true add, false remove

                selectedListModel.clear();

                if (!user.getStudentCourseList().isEmpty()) {
                    for (String course : user.getStudentCourseList()) {
                        selectedListModel.addElement(course);
                    }
                }

            }
        });


        selectCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelMain, "panelCourse");

                usrnmLbl2.setText(user.getUsername());

                selectedListModel.clear();
                courseListModel.clear();

                for (String course : Course.CourseListArray) {
                    courseListModel.addElement(course);
                }

                if (!user.getStudentCourseList().isEmpty()) {
                    for (String course : user.getStudentCourseList()) {
                        selectedListModel.addElement(course);
                    }
                }

            }
        });

        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelMain, "panelMenu");
            }
        });

        /*
        ################ COURSE MENU ################
         */


/*
        ################ TIMETABLE ################
         */

        viewTimetableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelMain, "panelTimetable");

                for(String studentCourseString : user.getStudentCourseList()){

                    for(Course course : Course.CourseArray){

                        if(course.getCourse().equals(studentCourseString)){

                            int startTime = course.getStartTime();
                            int endTime = course.getEndTime();
                            String day = course.getDay();
                            String room = course.getRoom();

                            setValueToTimetable(startTime, endTime, day, room, studentCourseString);

                        }
                    }

                }
            }
        });
        mainMenuButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelMain, "panelMenu");
            }
        });

        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                getContentPane().remove(panelMain);
                dispose(); //release any resources associated with it and close the window

                LoginGUI loginGUI = new LoginGUI();
            }
        });

    }

    static boolean checkConsistancy(Person user, String course){

        Boolean[][] checkArray = new Boolean[16][6];
        int row = 0;
        int column = 0;
        int start = 0;
        int end = 0;

        for(int i = 0; i<16; i++){

            for(int m = 0; m<6; m++){
                checkArray[i][m] = false;
            }

        }

        for(Course checkCourse : Course.CourseArray){

            if(checkCourse.getCourse().equals(course)){

                start = checkCourse.getStartTime();
                end = checkCourse.getEndTime();
                String day = checkCourse.getDay();

                column = getColumnofDay(day);

                row = start - 8;

                while(row<(end-8)){

                    if(checkArray[row][column] == false){
                        checkArray[row][column] = true;
                    }else {
                        return false;
                    }
                    row++;
                }

            }

        }


        for(String studentCourseString : user.getStudentCourseList()){

            for(Course checkCourse : Course.CourseArray){

                if(checkCourse.getCourse().equals(studentCourseString)){

                    start = checkCourse.getStartTime();
                    end = checkCourse.getEndTime();
                    String day = checkCourse.getDay();

                    column = getColumnofDay(day);

                    row = start - 8;

                    while(row<(end-8)){

                        if(checkArray[row][column] == false){
                            checkArray[row][column] = true;
                        }else {
                            return false;
                        }
                        row++;
                    }

                }
            }
        }




        return true;
    }


    static public void setValueToTimetable(int start, int end, String day, String room, String course){

        int row = 0;
        int column = 0;
        String tableString = course + " (Room: " + room + ")";

        column = getColumnofDay(day);



        if(column>-1){

            row = start - 8;

            while(row<(end-8)){
                tableModel.setValueAt(tableString,row,column);
                row++;
            }
        }



    }

    static int getColumnofDay(String day){

        switch (day) {
            case "Monday":
                return 1;

            case "Tuesday":
                return 2;

            case "Wednesday":
                return 3;


            case "Thursday":
                return 4;

            case "Friday":
                return 5;

            default:
                System.out.println("Invalid day.");
                return -1;

        }

    }

}
