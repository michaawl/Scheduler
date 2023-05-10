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

public class Application extends JFrame {

    private JPanel mainPanel;
    private JList list1;
    private JProgressBar progressBar1;
    private JLabel titleLabel;
    private JLabel progressLabel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JLabel leftLabel;
    private JLabel rightLabel;
    private JTextField usrnmTxtField;
    private JTextField emailTxtField;
    private JPasswordField passwordField1;
    private JCheckBox checkBox1;
    private JButton button1;
    private JLabel usrnNmLabel;
    private JLabel emailLabel;
    private JLabel pwLabel;
    private JLabel admnLabel;
    private JLabel imageLabel;
    static DefaultListModel<String> listModel = new DefaultListModel<>();
    static ArrayList<Person> PersonArray = new ArrayList<>();


    public Application() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean errorOcc = false;

                if ((usrnmTxtField.getText().isBlank()) || (emailTxtField.getText().isBlank()) || (passwordField1.getPassword().length == 0)) {
                    JOptionPane.showMessageDialog(button1, "Please fill out all fields!");
                    return;
                }

                // Error message 3: too many users
                if (Person.userCount == 19) {
                    ErrorGUI error = new ErrorGUI(3);
                    return;
                }


                for (int i = 0; i < Person.userCount; i++) {

                    if (PersonArray.get(i).getUsername().equals(usrnmTxtField.getText())) {
                        // Error message 1: username duplicate
                        usrnmTxtField.setText("");
                        ErrorGUI error = new ErrorGUI(1);
                        errorOcc = true;
                    }

                    if (PersonArray.get(i).getEmail().equals(emailTxtField.getText())) {
                        // Error message 1: email duplicate
                        emailTxtField.setText("");
                        ErrorGUI error = new ErrorGUI(2);
                        errorOcc = true;
                    }
                }

                if (errorOcc) {
                    return;
                }

                Person pers = new Person();
                pers.setUsername(usrnmTxtField.getText());
                pers.setEmail(emailTxtField.getText());
                pers.setAdminStatus(checkBox1.isSelected());
                String listString = "";

                listString = pers.getUserID() + ". ";
                listString += pers.getUsername() + " (";

                if (pers.isAdminStatus()) {
                    listString += "Admin, ";
                }

                listString += pers.getEmail() + ")";


                PersonArray.add(pers);
                progressBar1.setMaximum(19);
                progressBar1.setValue(Person.userCount);
                progressLabel.setText(Person.userCount + " / 19");

                usrnmTxtField.setText("");
                emailTxtField.setText("");
                passwordField1.setText("");

                listModel.addElement(listString);

            }
        });
    }

    public static void main(String[] args) {

        Application frame = new Application();
        frame.setContentPane(frame.mainPanel);
        frame.setVisible(true);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.imageLabel.setText("");

        frame.mainPanel.setSize(500, 500);
        frame.rightPanel.setBorder(new EmptyBorder(50, 10, 10, 10));
        frame.leftPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        frame.titleLabel.setFont(new Font("Calibri", Font.BOLD, 23));
        frame.titleLabel.setBorder(new EmptyBorder(10, 0, 10, 0));

        frame.leftLabel.setFont(new Font("Calibri", Font.BOLD, 16));
        frame.rightLabel.setFont(new Font("Calibri", Font.BOLD, 16));
        frame.button1.setText("Create User");
        frame.checkBox1.setText("");

        frame.list1.setModel(listModel);


        //generates users to test user maximum
        /*
        for (int i = 0; i <= 17; i++) {

            Person pers = new Person();
            String textCaseString = "Person";
            pers.setUsername(textCaseString + i);
            pers.setEmail(textCaseString + i + "@edu.com");
            PersonArray.add(pers);

            textCaseString = pers.getUserID() + ". ";
            textCaseString += pers.getUsername() + " (";
            textCaseString += pers.getEmail() + ")";

            listModel.addElement(textCaseString);
        }

         */

        frame.progressBar1.setMaximum(19);
        frame.progressBar1.setValue(Person.userCount);
        frame.progressLabel.setText(Person.userCount + " / 19");

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

