import javax.imageio.IIOException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Application {


    static void writeToCSVFile(String line, String filepath){

        try{
            BufferedWriter csvWriter = new BufferedWriter(new FileWriter(filepath, true)); //append just adds and doesnt delete everything
            csvWriter.write(line + "\n");
            csvWriter.flush();

        }catch (IOException e){

        }
    }

    static void deleteFromCSVFile(String filePath, int deleteLine){

        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line = "";

            int lineInt = 0;

            while ((line = reader.readLine()) != null) {

                if(lineInt != deleteLine) {
                    lines.add(line);
                }

                lineInt++;
            }

        } catch (IOException e) {

        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))){


            for (String stringLine : lines) {
                writer.write(stringLine);
                writer.newLine();
            }

            System.out.print(lines);
            writer.flush();

        }catch (IOException e){
            System.out.println(e);
        }


    }


    public static void main(String[] args) {

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

        } catch ( Exception e){
            System.out.println(e);

        }

        //LoginGUI logingui = new LoginGUI();
        AdminGUI adminGUI = new AdminGUI();

        //RegisterGUI registerGUI = new RegisterGUI();


    }

}
