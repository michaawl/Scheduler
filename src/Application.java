/*
 * Project: Scheduler
 * Scheduler program for Administrator, Assistants and Students
 * Author:  Michael Muehlberger
 * Last Change: 29.05.2023
 */


import java.io.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Application {


    //adds a String line to a given csv line in filepath
    static void writeToCSVFile(String line, String filepath) {

        try {
            BufferedWriter csvWriter = new BufferedWriter(new FileWriter(filepath, true)); //append just adds and doesnt delete everything
            csvWriter.write(line + "\n");
            csvWriter.flush();

        } catch (IOException e) {
            System.out.println("Error occured writing to csv file.");
        }
    }

    //deletes a line from a CSV File based on the first entry given in deleteLine
    static void deleteFromCSVFileByFirstEntry(String filepath, String deleteLine) {

        String line = "";
        List<String> lines = new ArrayList<>();

        try {

            FileReader reader = new FileReader(filepath);
            BufferedReader bufferedReader = new BufferedReader(reader);
            while ((line = bufferedReader.readLine()) != null) {

                String[] data = line.split(",");

                if (!(data[0].equals(deleteLine))) {

                    lines.add(line);

                }
            }
        } catch (Exception e) {
            System.out.println("Error occured while reading in deleteing csv file line by first entry.");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))) {


            for (String stringLine : lines) {
                writer.write(stringLine);
                writer.newLine();
            }

            System.out.print(lines);
            writer.flush();

        } catch (IOException e) {
            System.out.println("Error occured while writing in deleteing csv file line by first entry.");
        }
    }

    //deletes a line from a csvFile by number of line deleteLine
    static void deleteFromCSVFile(String filePath, int deleteLine) {

        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line = "";
            int lineInt = 0;

            while ((line = reader.readLine()) != null) {

                if (lineInt != deleteLine) {
                    lines.add(line);
                }

                lineInt++;
            }

        } catch (IOException e) {
            System.out.println("Error occured while reading in deleteing csv file line by line.");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {


            for (String stringLine : lines) {
                writer.write(stringLine);
                writer.newLine();
            }

            System.out.print(lines);
            writer.flush();

        } catch (IOException e) {
            System.out.println("Error occured while wrirting in deleteing csv file line by line.");
        }
    }

    //edits a line given by int editLineInt into string editLineString
    static void editCSVFile(String filePath, int editLineInt, String editLineString) {

        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line = "";
            int lineInt = 0;

            while ((line = reader.readLine()) != null) {

                if (lineInt != editLineInt) {
                    lines.add(line);
                } else {
                    lines.add(editLineString);
                }

                lineInt++;
            }

        } catch (IOException e) {
            System.out.println("Error occured while reading in editing csv file.");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            for (String stringLine : lines) {
                writer.write(stringLine);
                writer.newLine();
            }

            System.out.print(lines);
            writer.flush();

        } catch (IOException e) {
            System.out.println("Error occured while writing in editing csv file.");
        }


    }

    //adds booked courses from students (from csvFile) to studentCourseList ArrayList
    public static void addStudentSet(String filepath, Person student) {


        String line = "";
        CopyOnWriteArrayList<String> studentList = new CopyOnWriteArrayList<>();

        try {

            FileReader reader = new FileReader(filepath);
            BufferedReader bufferedReader = new BufferedReader(reader);
            while ((line = bufferedReader.readLine()) != null) {

                String[] data = line.split(",");

                StringTokenizer tokenizer = new StringTokenizer(line, ",");

                if (data[0].equals(student.getUsername())) {
                    //skip first element
                    if (tokenizer.hasMoreTokens()) {
                        tokenizer.nextToken();
                    }

                    //element 2 until end
                    while (tokenizer.hasMoreTokens()) {
                        String bookedCourses = tokenizer.nextToken();

                        studentList.add(bookedCourses);

                    }

                    if (!studentList.isEmpty()) {
                        student.setStudentCourseList(studentList);
                    }

                    return;
                }

            }
        } catch (Exception e) {
            System.out.println("Error occured when reading/writing students.csv");
        }
    }


    public static void main(String[] args) {


        // ##### copy data from users.csv file to PersonArray #####

        String filepath = "src/csv/users.csv";
        String line = "";

        try {

            FileReader reader = new FileReader(filepath);
            BufferedReader bufferedReader = new BufferedReader(reader);
            while ((line = bufferedReader.readLine()) != null) {

                String[] data = line.split(",");

                Person person = new Person();

                person.setUsername(data[0]);
                person.setEmail(data[1]);
                person.setPassword(data[2]);
                person.setAdminStatus(Boolean.parseBoolean(data[3]));
                person.setAssistantStatus(Boolean.parseBoolean(data[4]));
                person.setStudentStatus(Boolean.parseBoolean(data[5]));

                if (person.isStudentStatus()) {
                    addStudentSet("src/csv/students.csv", person);
                }

                Person.PersonArray.add(person);

            }
        } catch (Exception e) {
            System.out.println("Error occured when reading/writing users.csv");
        }

        // ##### copy data from courses.csv file to CourseListArray #####

        filepath = "src/csv/courses.csv";

        try {

            FileReader reader = new FileReader(filepath);
            BufferedReader bufferedReader = new BufferedReader(reader);
            while ((line = bufferedReader.readLine()) != null) {

                String[] data = line.split(",");

                String course = "";

                course = data[0];

                Course.CourseListArray.add(course);
            }
        } catch (Exception e) {
            System.out.println("Error occured when reading/writing courses.csv");

        }

        // ##### copy data from rooms.csv file to RoomArray #####

        filepath = "src/csv/rooms.csv";

        try {

            FileReader reader = new FileReader(filepath);
            BufferedReader bufferedReader = new BufferedReader(reader);
            while ((line = bufferedReader.readLine()) != null) {

                String[] data = line.split(",");

                Room room = new Room();

                room.setRoom(data[0]);

                Room.RoomArray.add(room);
            }
        } catch (Exception e) {
            System.out.println("Error occured when reading/writing rooms.csv");

        }

        // ##### copy data from timetable.csv file to CourseArray #####

        filepath = "src/csv/timetable.csv";

        try {

            FileReader reader = new FileReader(filepath);
            BufferedReader bufferedReader = new BufferedReader(reader);
            while ((line = bufferedReader.readLine()) != null) {

                String[] data = line.split(",");

                Course course = new Course();

                course.setCourse(data[0]);
                course.setRoom(data[1]);
                course.setDay(data[2]);
                course.setStartTime(Integer.parseInt(data[3]));
                course.setEndTime(Integer.parseInt(data[4]));

                Course.CourseArray.add(course);

            }
        } catch (Exception e) {
            System.out.println("Error occured when reading/writing timetable.csv");
        }

        // ##### copy data from preferences.csv file to PreferenceArray #####

        filepath = "src/csv/preferences.csv";

        try {

            FileReader reader = new FileReader(filepath);
            BufferedReader bufferedReader = new BufferedReader(reader);
            while ((line = bufferedReader.readLine()) != null) {

                String[] data = line.split(",");

                Preference preference = new Preference();

                preference.setCreator(data[0]);
                preference.setCourse(data[1]);
                preference.setRoom(data[2]);
                preference.setDay(data[3]);
                preference.setStartTime(Integer.parseInt(data[4]));
                preference.setEndTime(Integer.parseInt(data[5]));

                Preference.PreferenceArray.add(preference);

            }
        } catch (Exception e) {
            System.out.println("Error occured when reading/writing preferences.csv");

        }

        LoginGUI logingui = new LoginGUI();

        //AdminGUI adminGUI = new AdminGUI(Person.PersonArray.get(0));
        //RegisterGUI registerGUI = new RegisterGUI();
        //StudentGUI studentGUI = new StudentGUI(Person.PersonArray.get(2));
        //Preferences preferences = new Preferences(Person.PersonArray.get(0));
        //AssistantGUI assistantGUI = new AssistantGUI(Person.PersonArray.get(1));

    }

}
