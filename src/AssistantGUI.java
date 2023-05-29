/*
 * Project: Scheduler
 * Scheduler program for Administrator, Assistants and Students
 * Author:  Michael Muehlberger
 * Last Change: 29.05.2023
 */


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//GUI of Assistant users
public class AssistantGUI extends JFrame{
    private JButton preferencesButton;
    private JButton logOutButton;
    private JButton viewAllCoursesButton;
    private JPanel panelMain;
    private JLabel usrLbl;

    AssistantGUI(Person user){

        getContentPane().add(panelMain);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200,600);

        usrLbl.setText(user.getUsername());

        setLocationRelativeTo(null); //centering window

        setVisible(true);

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
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getContentPane().remove(panelMain);
                dispose();

                LoginGUI loginGUI = new LoginGUI();
            }
        });
    }
}
