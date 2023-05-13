import javax.imageio.IIOException;
import java.io.*;

public class Application {


    static void writeToCSVFile(String line, String filepath){

        try{
            FileWriter csvWriter = new FileWriter(filepath, true);
            csvWriter.write(line + "\n");
            csvWriter.flush();

        }catch (IOException e){

        }

    }

    public static void main(String[] args) {

        String filepath = "src/csv/users.csv";
        BufferedReader reader = null;
        String line = "";

        try {

            reader = new BufferedReader(new FileReader(filepath));
            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                Person person = new Person();

                person.setUsername(data[0]);
                person.setEmail(data[1]);
                person.setPassword(data[2]);
                person.setAdminStatus(Boolean.parseBoolean(data[3]));
                person.setAssistantStatus(Boolean.parseBoolean(data[4]));
                person.setStudentStatus(Boolean.parseBoolean(data[5]));

                Person.PersonArray.add(person);

                System.out.println();


            }

        } catch ( Exception e){
            System.out.println(e);

        }

        RegisterGUI startFrame = new RegisterGUI();

    }

}
