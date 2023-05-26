import javax.imageio.IIOException;
import java.io.*;

public class Application {


    static void writeToCSVFile(String line, String filepath){

        try{
            BufferedWriter csvWriter = new BufferedWriter(new FileWriter(filepath, true)); //append just adds and doesnt delete everything
            csvWriter.write(line + "\n");
            csvWriter.flush();

        }catch (IOException e){

        }
    }

    static void deleteFromCSVFile(String filepath){

        try{

            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            FileWriter csvWriter = new FileWriter(filepath);

        }catch (IOException e){

        }

    }


    public static void main(String[] args) {

        String filepath = "src/csv/users.csv";
        String line = "";

        try {

            BufferedReader reader = new BufferedReader(new FileReader(filepath));
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

        //LoginGUI logingui = new LoginGUI();
        //AdminGUI adminGUI = new AdminGUI();
        RegisterGUI registerGUI = new RegisterGUI();


    }

}
