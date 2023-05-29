/*
 * Project: Scheduler
 * Scheduler program for Administrator, Assistants and Students
 * Author:  Michael Muehlberger
 * Last Change: 29.05.2023
 */


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;
import java.util.concurrent.CopyOnWriteArrayList;

public class Preferences extends JFrame{
    private JTabbedPane tabbedPane1;
    private JPanel panelMain;
    private JComboBox comboBox1;
    private JComboBox comboBox3;
    private JComboBox comboBox5;
    private JButton setPreferenceButton;
    private JButton mainMenuButton1;
    private JButton checkPreferenceStatusButton;
    private JButton removeButton;
    private JScrollPane scrollPaneCourse;
    private JScrollPane scrollPaneRoom;
    private JScrollPane scrollPanePreference;
    private JLabel prfrcsLbl;

    static DefaultListModel<String> preferenceListModel = new DefaultListModel<>();
    static DefaultListModel<String> courseListModel = new DefaultListModel<>();
    static DefaultListModel<String> roomListModel = new DefaultListModel<>();

    public Preferences(Person user){

        // Set the layout manager for the main panel
        panelMain.setLayout(new CardLayout());


        // Add the main panel to the JFrame content pane
        getContentPane().add(panelMain);

        // Set JFrame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200,600);

        setLocationRelativeTo(null); //centering window

        setVisible(true);

        JList preferenceList = new JList<>();
        JList courseList = new JList();
        JList roomList = new JList();

        preferenceList.setModel(preferenceListModel);
        courseList.setModel(courseListModel);
        roomList.setModel(roomListModel);

       scrollPaneCourse.setViewportView(courseList);
       scrollPanePreference.setViewportView(preferenceList);
       scrollPaneRoom.setViewportView(roomList);

       refreshLists();

        setPreferenceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String csvAddString = "";

                String courseStrg = courseList.getSelectedValue().toString();
                String roomStrg = roomList.getSelectedValue().toString();
                String startTime = comboBox1.getSelectedItem().toString();
                String endTime = comboBox3.getSelectedItem().toString();
                String dayStrg = comboBox5.getSelectedItem().toString();

                csvAddString = user.getUsername() + "," + courseStrg + "," + roomStrg + "," + dayStrg + "," + startTime + "," + endTime;

                Application.writeToCSVFile(csvAddString, "src/csv/preferences.csv");

                Preference preference = new Preference();
                preference.setCreator(user.getUsername());
                preference.setCourse(courseStrg);
                preference.setRoom(roomStrg);
                preference.setDay(dayStrg);
                preference.setStartTime(Integer.parseInt(startTime));
                preference.setEndTime(Integer.parseInt(endTime));

                Preference.PreferenceArray.add(preference);

                refreshLists();


            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int deleteLine = preferenceList.getSelectedIndex();

                Application.deleteFromCSVFile("src/csv/preferences.csv", deleteLine);
                Preference.PreferenceArray.remove(deleteLine);

                refreshLists();

            }
        });
        checkPreferenceStatusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int index = preferenceList.getSelectedIndex();
                Preference selectedP = Preference.PreferenceArray.get(index);

                String courseStrg = selectedP.getCourse();
                String roomStrg = selectedP.getRoom();
                String dayStrg = selectedP.getDay();
                String startTime = String.valueOf(selectedP.getStartTime());
                String endTime = String.valueOf(selectedP.getEndTime());

                String line = courseStrg + "," + roomStrg + "," + dayStrg + "," + startTime + "," + endTime;;

                if(checkPreference(line)){
                    prfrcsLbl.setText("Course set - Preference can be deleted!");
                } else {
                    prfrcsLbl.setText("Course not set yet.");
                }

            }
        });
        mainMenuButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                getContentPane().remove(panelMain);
                dispose(); //release any resources associated with it and close the window

                if(user.isAdminStatus()){
                    AdminGUI adminGUI = new AdminGUI(user);
                }

                if(user.isAssistantStatus()){
                    AssistantGUI assistantGUI = new AssistantGUI(user);
                }


            }
        });
    }

    public void refreshLists(){

        preferenceListModel.clear();
        courseListModel.clear();
        roomListModel.clear();

        for(Preference preference : Preference.PreferenceArray){

            String preferenceInfo = "";
            preferenceInfo = "Creator: " + preference.getCreator() + ", Course: " + preference.getCourse() + ", Room: " + preference.getRoom() +
            ", Day: " + preference.getDay() + ", Start time: " + preference.getStartTime() + ", End Time: " + preference.getEndTime();

            preferenceListModel.addElement(preferenceInfo);
        }

        for(Room room : Room.RoomArray){
            roomListModel.addElement(room.getRoom());
        }

        for(String course: Course.CourseListArray){
            courseListModel.addElement(course);
        }

    }

    public boolean checkPreference(String preferenceCheckString){

        String line = "";
        String courseString = "";
        String filepath = "src/csv/timetable.csv";

        try {

            FileReader reader = new FileReader(filepath);
            BufferedReader bufferedReader = new BufferedReader(reader);
            while ((line = bufferedReader.readLine()) != null) {

                if(line.equals(preferenceCheckString)){
                    return true;
                }

            }
        } catch (Exception e) {
            System.out.println(e);

        }

        return false;

    }

}
