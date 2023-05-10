/*
 * Project: GUI
 * User Management Software using a Java GUI
 * Author:  Michael Muehlberger
 * Last Change: 03.05.2023
 */

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ErrorGUI extends JFrame {
    private JPanel errorPanel;
    private JLabel errorImageLbl;
    private JButton errorButton;
    private JLabel errorMsgLbl;
    int errorNo;

    public ErrorGUI(int errorMessage) {

        errorNo = errorMessage;
        this.setContentPane(this.errorPanel);
        this.setVisible(true);
        this.setSize(600, 200);
        errorImageLbl.setText("");
        errorImageLbl.setBorder(new EmptyBorder(0, 30, 0, 0));
        this.errorPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        this.errorMsgLbl.setText("Memory limit exceeded - cannot add another user!");
        this.errorButton.setText("OK");


        //user duplicate
        if (errorMessage == 1) {
            this.setSize(400, 200);
            this.errorMsgLbl.setText("User already exists!");
        }

        //email duplicate
        if (errorMessage == 2) {
            this.setSize(400, 200);
            this.errorMsgLbl.setText("Email adress already exists!");
        }


        errorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();

            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here

        errorImageLbl = new JLabel();
        ImageIcon icon = new ImageIcon("src/media/alert.png");

        if (errorNo == 1) {
            icon = new ImageIcon("src/media/duplicate.png");
        }

        if (errorNo == 2) {
            icon = new ImageIcon("src/media/email.png");
        }

        if (errorNo == 3) {
            icon = new ImageIcon("src/media/alert.png");
        }

        errorImageLbl.setIcon(icon);

    }
}
