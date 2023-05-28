import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentGUI extends JFrame{
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
    static DefaultListModel<String> courseListModel = new DefaultListModel<>();
    static DefaultListModel<String> selectedListModel = new DefaultListModel<>();


    public StudentGUI(){

        panelMain.setLayout(new CardLayout());

        panelMain.add(panelMenu, "panelMenu");
        panelMain.add(panelCourse, "panelCourse");
        panelMain.add(panelTimetable, "panelTimetable");

        CardLayout cardLayout = (CardLayout) panelMain.getLayout();

        //TABLE SETTINGS
        Object[][] data = new Object[16][6];
        table1.setModel(new DefaultTableModel(
                data,
                new String[]{" ", "Monday", "Tuesday", "Wednesay", "Thursday", "Friday"}
        ));

        DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();

        for(int i = 8; i<24; i++){
            tableModel.setValueAt(i + ":00", i-8, 0);
        }


        getContentPane().add(panelMain);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200,600);
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


        selectCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelMain, "panelCourse");

                for(String course : Course.CourseListArray){
                    courseListModel.addElement(course);
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

            }
        });
        mainMenuButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelMain, "panelMenu");
            }
        });
    }

}
