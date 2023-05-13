/*
 * Project: GUI
 * User Management Software using a Java GUI
 * Author:  Michael Muehlberger
 * Last Change: 03.05.2023
 */

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class RegisterGUI extends JFrame {

    private JPanel mainPanel;
    private JLabel titleLabel;
    private JLabel progressLabel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JLabel leftLabel;
    private JLabel rightLabel;
    private JTextField usrnmTxtField;
    private JTextField emailTxtField;
    private JPasswordField passwordField1;
    private JButton button1;
    private JLabel usrnNmLabel;
    private JLabel emailLabel;
    private JLabel pwLabel;
    private JLabel imageLabel;
    private JScrollPane scrollPane1;
    private JRadioButton adminRadioButton;
    private JRadioButton assistantRadioButton;
    private JRadioButton studentRadioButton;
    static DefaultListModel<String> listModel = new DefaultListModel<>();


    public RegisterGUI() {


        setTitle("Register a user");
        setContentPane(mainPanel);
        setVisible(true);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        imageLabel.setText("");

        mainPanel.setSize(500, 500);
        rightPanel.setBorder(new EmptyBorder(50, 10, 10, 10));
        leftPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        titleLabel.setFont(new Font("Calibri", Font.BOLD, 23));
        titleLabel.setBorder(new EmptyBorder(10, 0, 10, 0));

        leftLabel.setFont(new Font("Calibri", Font.BOLD, 16));
        rightLabel.setFont(new Font("Calibri", Font.BOLD, 16));
        button1.setText("Create User");

        adminRadioButton.setBorder(new EmptyBorder(10, 10, 0, 0));
        assistantRadioButton.setBorder(new EmptyBorder(0, 10, 0, 0));
        studentRadioButton.setBorder(new EmptyBorder(0, 10, 0, 0));


        JList list1 = new JList();

        list1.setModel(listModel);

        scrollPane1.setViewportView(list1);

        //set ppl who are already in CSV file

        for (Person pers : Person.PersonArray) {

            String listString = "";

            listString = pers.getUserID() + ". ";
            listString += pers.getUsername() + " (";

            if (pers.isAdminStatus()) {
                listString += "Admin, ";
            }

            if (pers.isAssistantStatus()) {
                listString += "Assistant, ";
            }

            if (pers.isStudentStatus()) {
                listString += "Student, ";
            }

            listString += pers.getEmail() + ")";


            Person.PersonArray.add(pers);
            progressLabel.setText(String.valueOf("Total Users: " + Person.userCount));

            listModel.addElement(listString);

        }

        progressLabel.setText(String.valueOf("Total Users: " + Person.userCount));


        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean errorOcc = false;


                if ((usrnmTxtField.getText().isBlank()) || (emailTxtField.getText().isBlank()) || (passwordField1.getPassword().length == 0)) {
                    return;
                }

                if (!studentRadioButton.isSelected() && !assistantRadioButton.isSelected() && !adminRadioButton.isSelected()) {
                    JOptionPane.showMessageDialog(button1, "Select role");
                    return;
                }

                for (int i = 0; i < Person.userCount; i++) {

                    if (Person.PersonArray.get(i).getUsername().equals(usrnmTxtField.getText())) {
                        // Error message: username duplicate
                        usrnmTxtField.setText("");
                        Icon icon = new ImageIcon("src/media/duplicate.png");
                        JOptionPane.showMessageDialog(button1, "User already exists!", "Error - User already exists", JOptionPane.PLAIN_MESSAGE, icon);
                        errorOcc = true;
                    }

                    if (Person.PersonArray.get(i).getEmail().equals(emailTxtField.getText())) {
                        // Error message: email duplicate
                        emailTxtField.setText("");

                        Icon icon = new ImageIcon("src/media/email.png");
                        JOptionPane.showMessageDialog(button1, "Email adress already exists!", "Error - Email already exists", JOptionPane.PLAIN_MESSAGE, icon);
                        errorOcc = true;
                    }
                }

                if (errorOcc) {
                    return;
                }

                Person pers = new Person();
                pers.setUsername(usrnmTxtField.getText());
                pers.setEmail(emailTxtField.getText());
                pers.setAdminStatus(adminRadioButton.isSelected());
                pers.setAssistantStatus(assistantRadioButton.isSelected());
                pers.setStudentStatus(studentRadioButton.isSelected());

                char[] password = passwordField1.getPassword();
                String passwordString = new String(password);
                pers.setPassword(passwordString);
                String listString = "";

                listString = pers.getUserID() + ". ";
                listString += pers.getUsername() + " (";

                if (pers.isAdminStatus()) {
                    listString += "Admin, ";
                }

                if (pers.isAssistantStatus()) {
                    listString += "Assistant, ";
                }

                if (pers.isStudentStatus()) {
                    listString += "Student, ";
                }

                listString += pers.getEmail() + ")";


                Person.PersonArray.add(pers);
                progressLabel.setText(String.valueOf("Total Users: " + Person.userCount));

                listModel.addElement(listString);

                //write to csv file
                listString = pers.getUsername() + "," + pers.getEmail() + "," + pers.getPassword() + "," + pers.isAdminStatus() + "," + pers.isAssistantStatus() + "," + pers.isStudentStatus();
                Application.writeToCSVFile(listString, "src/csv/users.csv");

            }
        });
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here

        imageLabel = new JLabel();

        double scaleRatio = 0.3;
        ImageIcon icon = new ImageIcon("src/media/management.png");
        Image img = icon.getImage();
        Image scaleImg = img.getScaledInstance((int) (icon.getIconWidth() * scaleRatio), (int) (icon.getIconHeight() * scaleRatio), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaleImg);

        imageLabel.setIcon(scaledIcon);

    }
}

