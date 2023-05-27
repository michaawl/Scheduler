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
    private JButton editorButton;
    private JTabbedPane tabbedPane1;
    private JList list3;
    private JTextField textField1;
    private JButton addButton;
    private JButton deleteSelectedCourseButton;
    private JButton backButton3;
    private JList list4;
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
    private JList list7;
    private JList list8;
    private JList list9;
    private JButton changeRoomButton;
    private JButton backButton1;
    private JComboBox comboBox6;
    private JComboBox comboBox7;
    private JComboBox comboBox8;
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

    static DefaultListModel<String> cclistModel1 = new DefaultListModel<>();
    static DefaultListModel<String> cclistModel2 = new DefaultListModel<>();

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

        JList ccListC = new JList();
        JList ccListR = new JList();

        ccListC.setModel(cclistModel1);
        ccListR.setModel(cclistModel2);

        scrollPane1.setViewportView(ccListC);
        scrollPane2.setViewportView(ccListR);

        ccCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int start = Integer.parseInt(comboBox2.getSelectedItem().toString());
                int end = Integer.parseInt(comboBox3.getSelectedItem().toString());
                String courseStrg = ccListC.getSelectedValue().toString();
                String room = ccListR.getSelectedValue().toString();
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

                cclistModel1.clear();
                cclistModel2.clear();

                for(Course course: Course.CourseArray){
                    cclistModel1.addElement(course.getCourse());
                }

                for(Room room : Room.RoomArray){
                    cclistModel2.addElement(room.getRoom());
                }

            }
        });

        /*
        ################### CREATER COURSE PANEL ###################
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
        editorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelMain, "panel3");
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
