/*
 * Project: Scheduler
 * Scheduler program for Administrator, Assistants and Students
 * Author:  Michael Muehlberger
 * Last Change: 29.05.2023
 */


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AllCourses extends JFrame {
    private JPanel panelMain;
    private JButton backButton;
    private JScrollPane scrollPane;
    static DefaultListModel<String> courseListModel = new DefaultListModel<>();
public AllCourses(Person user){

    // Add the main panel to the JFrame content pane
    getContentPane().add(panelMain);

    // Set JFrame properties
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(1200,600);

    setLocationRelativeTo(null); //centering window

    setVisible(true);

    JList allcoursesList = new JList();
    allcoursesList.setModel(courseListModel);


    scrollPane.setViewportView(allcoursesList);

    courseListModel.clear();
    for(Course course : Course.CourseArray){

        String courseInfo = "";
        courseInfo = "Course: " + course.getCourse() + ". Room: " + course.getRoom() +
                ". Date: " + course.getDay() + ", " +course.getStartTime() + "-" + course.getEndTime()+
                " o'clock";

        courseListModel.addElement(courseInfo);
    }

    backButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            getContentPane().remove(panelMain);
            dispose();

            if(user.isAdminStatus()){
                AdminGUI adminGUI = new AdminGUI(user);
            }

            if(user.isAssistantStatus()){
                AssistantGUI assistantGUI = new AssistantGUI(user);
            }

            if(user.isStudentStatus()){
                StudentGUI studentGUI = new StudentGUI(user);
            }
        }
    });
}
}
