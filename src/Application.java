import javax.imageio.IIOException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Application {


    static void writeToCSVFile(String line, String filepath) {

        try {
            BufferedWriter csvWriter = new BufferedWriter(new FileWriter(filepath, true)); //append just adds and doesnt delete everything
            csvWriter.write(line + "\n");
            csvWriter.flush();

        } catch (IOException e) {

        }
    }

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

        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {


            for (String stringLine : lines) {
                writer.write(stringLine);
                writer.newLine();
            }

            System.out.print(lines);
            writer.flush();

        } catch (IOException e) {
            System.out.println(e);
        }


    }

    static void editCSVFile(String filePath, int editLineInt, String editLineString) {

        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line = "";

            int lineInt = 0;

            while ((line = reader.readLine()) != null) {

                if (lineInt != editLineInt) {
                    lines.add(line);
                }
                else {
                    lines.add(editLineString);
                }

                lineInt++;
            }

        } catch (IOException e) {

        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {


            for (String stringLine : lines) {
                writer.write(stringLine);
                writer.newLine();
            }

            System.out.print(lines);
            writer.flush();

        } catch (IOException e) {
            System.out.println(e);
        }


    }


    public static void main(String[] args) {

        //copy data from users.csv file to PersonArray
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

                Person.PersonArray.add(person);

            }
        } catch (Exception e) {
            System.out.println(e);
        }

        //copy data from courses.csv file to CourseListArray
        filepath = "src/csv/courses.csv";
        line = "";

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
            System.out.println(e);

        }

        //copy data from rooms.csv file to RoomArray
        filepath = "src/csv/rooms.csv";
        line = "";

        try {

            FileReader reader = new FileReader(filepath);
            BufferedReader bufferedReader = new BufferedReader(reader);
            while ((line = bufferedReader.readLine()) != null) {

                String[] data = line.split(",");

                Room room = new Room();

                room.setRoom(data[0]);

                Room.RoomArray.add(room);
            }
            }catch(Exception e){
                System.out.println(e);

            }

        //copy data from timetable.csv file to CourseArray
        filepath = "src/csv/timetable.csv";
        line = "";

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
            System.out.println(e);
        }


            //LoginGUI logingui = new LoginGUI();
            AdminGUI adminGUI = new AdminGUI();

            //RegisterGUI registerGUI = new RegisterGUI();


        }
}
