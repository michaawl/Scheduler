import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.CopyOnWriteArrayList;

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
    private JList list5;
    private JList list6;
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

    static DefaultListModel<String> courseListModel = new DefaultListModel<>();
    static DefaultListModel<String> roomListModel = new DefaultListModel<>();
    static DefaultListModel<String> timetableListModel = new DefaultListModel<>();

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
                String room = "";

                try{
                     room = ccRoomList.getSelectedValue().toString();
                }catch (Exception e4){
                     room = "noroom";
                }
                String day = comboBox1.getSelectedItem().toString();

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
            }
        });

        confrimEditButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String editLine = "";
                int  newStart = Integer.parseInt(comboBox6.getSelectedItem().toString());
                int newEnd = Integer.parseInt(comboBox9.getSelectedItem().toString());
                int editIndex = eTimetableList.getSelectedIndex();

                Course editCourse = Course.CourseArray.get(editIndex);

                Course.CourseArray.get(editIndex).setStartTime(newStart);
                Course.CourseArray.get(editIndex).setEndTime(newEnd);

                editLine = editCourse.getCourse() + "," + editCourse.getRoom() + "," +
                        editCourse.getDay() + "," + newStart + "," +
                        newEnd;

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

        checkConsistencyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        backButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelMain, "panel2");
            }
        });

        /*
        ################### EDITOR ###################
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

        addRemoveStudentsFromButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelMain, "panel4");
            }
        });
        backButton4.addActionListener(new ActionListener() {
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
