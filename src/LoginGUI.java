import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame{
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton loginbutton;
    private JLabel titleLbl;
    private JLabel usrnmLbl;
    private JLabel pwLbl;
    private JPanel loginPanel;
    private JLabel errorLbl;
    private JPanel panel1;


    public LoginGUI(){

        setContentPane(loginPanel);
        setVisible(true);
        setSize(600,400);

        titleLbl.setFont(new Font("Calibri", Font.BOLD, 23));
        titleLbl.setText("Scheduler - Log In");
        titleLbl.setBorder(new EmptyBorder(50, 0, 0, 0));
        usrnmLbl.setText("Username:");
        pwLbl.setText("Password:");
        errorLbl.setText("");
        errorLbl.setForeground(Color.RED);
        loginbutton.setText("Log In");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel1.setBorder(new EmptyBorder(50, 20, 0, 20));

        loginbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String username = textField1.getText();
                char[] password = passwordField1.getPassword();
                String passwordString = new String(password);
                boolean userexists = false;
                boolean passwordexists = false;

                errorLbl.setForeground(Color.RED);

                if(textField1.getText().isBlank() || passwordField1.getPassword().length == 0){
                    errorLbl.setText("Please type in username and password.");
                    return;
                }
                for(Person p: Person.PersonArray){
                    if(p.getUsername().equals(username)){
                        userexists = true;

                        if(p.getPassword().equals(passwordString)){
                            passwordexists = true;
                            errorLbl.setForeground(Color.GREEN);
                            errorLbl.setText("Login successful!");
                        }

                    }
                }

                if(!userexists){
                    errorLbl.setText("Username doesn't exist");
                }

                if(userexists && !passwordexists){
                    errorLbl.setText("Incorrect Password");
                }

            }
        });

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
