import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JButton addButton2;
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
    private JPanel scrollPane6;
    private JScrollPane scrollPane7;
    private JLabel h;

    static DefaultListModel<String> courseListModel = new DefaultListModel<>();
    static DefaultListModel<String> roomListModel = new DefaultListModel<>();

    public AdminGUI(){

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
                String room = ccRoomList.getSelectedValue().toString();
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

            }
        });

        ccVC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        ccVC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        ccVR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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

                for(Course course: Course.CourseArray){
                    courseListModel.addElement(course.getCourse());
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
        JList eCourseList2 = new JList<>();
        JList eRoomList = new JList();

        eCourseList.setModel(courseListModel);
        eCourseList2.setModel(courseListModel);
        eRoomList.setModel(roomListModel);

        scrollPane3.setViewportView(eCourseList);
        scrollPane4.setViewportView(eRoomList);
        scrollPane5.setViewportView(eCourseList2);

        mainEditor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelMain, "panel3");

                courseListModel.clear();
                roomListModel.clear();

                for(Course course: Course.CourseArray){
                    courseListModel.addElement(course.getCourse());
                }

                for(Room room : Room.RoomArray){
                    roomListModel.addElement(room.getRoom());
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

                    Course newCourse = new Course();
                    newCourse.setCourse(addCourseString);
                    Course.CourseArray.add(newCourse);

                    courseListModel.clear();

                    for(Course course: Course.CourseArray){
                        courseListModel.addElement(course.getCourse());
                    }
                }
            }
        });

        deleteSelectedCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int deleteLine = eCourseList.getSelectedIndex();
                System.out.println(deleteLine);

                Application.deleteFromCSVFile("src/csv/courses.csv",deleteLine);
                Course.CourseArray.remove(deleteLine);

                courseListModel.clear();
                for(Course course: Course.CourseArray){
                    courseListModel.addElement(course.getCourse());
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
                System.out.println(deleteLine);

                Application.deleteFromCSVFile("src/csv/rooms.csv",deleteLine);
                Room.RoomArray.remove(deleteLine);

                roomListModel.clear();
                for(Room room : Room.RoomArray){
                    roomListModel.addElement(room.getRoom());
                }

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
        backButton1.addActionListener(new ActionListener() {
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
                RegisterGUI startFrame = new RegisterGUI();
            }
        });

    }

}
