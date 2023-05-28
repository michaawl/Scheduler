import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

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
    private JLabel usnrmLbl;
    private JLabel usrnmLbl2;
    static DefaultListModel<String> courseListModel = new DefaultListModel<>();
    static DefaultListModel<String> selectedListModel = new DefaultListModel<>();

    public int addCourse(String filepath, Person user, String addCourse){

        //check if student is in list

        String line = "";
        boolean courseInList = false;
        String editString = "";
        int lineInt = 0;

        try {

            FileReader reader = new FileReader(filepath);
            BufferedReader bufferedReader = new BufferedReader(reader);
            while ((line = bufferedReader.readLine()) != null) {

                String[] data = line.split(",");

                StringTokenizer tokenizer = new StringTokenizer(line, ",");

                if(data[0].equals(user.getUsername())){
                    //skip first element
                    if (tokenizer.hasMoreTokens()) {

                        editString = tokenizer.nextToken();
                    }


                    //element 2 until end
                    while (tokenizer.hasMoreTokens()) {
                        String bookedCourses = tokenizer.nextToken();
                        editString = editString + "," + bookedCourses;

                        if(bookedCourses.equals(addCourse)){
                            System.out.println("Course already booked.");
                            courseInList = true;
                            return 1;
                        }
                    }

                    System.out.println(editString);
                    editString = editString + "," + addCourse;
                    Application.editCSVFile("src/csv/students.csv", lineInt, editString);
                    System.out.println("Course added to selected student.");


                    return 0;
                }

                lineInt++;
            }
        }catch(Exception e){
            System.out.println(e);

        }

        editString = user.getUsername() + "," + addCourse;
        Application.writeToCSVFile(editString,"src/csv/students.csv");

        System.out.println("Added student to student.csv list.");

        return 2;
    }

    public StudentGUI(Person user){


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

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String courseName = courseList.getSelectedValue().toString();

                addCourse("src/csv/students.csv", user, courseName);


            }
        });


        selectCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelMain, "panelCourse");

                usrnmLbl2.setText(user.getUsername());

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

        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                getContentPane().remove(panelMain);
                dispose(); //release any resources associated with it and close the window

                LoginGUI loginGUI = new LoginGUI();
            }
        });
    }

}
