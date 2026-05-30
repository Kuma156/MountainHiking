package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import model.Mountain;
import model.Student;

/**
 *
 * @author The Miracle Invoker
 */
public class FileIOHandler {

    public List<Mountain> readMountainFromCsv(String path) throws IOException {
        List<Mountain> mountainList = new ArrayList<>();
        File file = new File(path);

        if (!file.exists()) {
            System.out.println("Error: The mountain is not found");
            return mountainList;
        }

        try ( BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split(",");
                if (data.length >= 3) {
                    String code = data[0].trim();
                    String mountainName = data[1].trim();
                    String province = data[2].trim();
                    String description = (data.length > 3) ? data[3].trim() : null;

                    Mountain m = new Mountain(code, mountainName, province, description);
                    mountainList.add(m);
                }

            }
        } catch (IOException e) {
            System.out.println("File reader error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return mountainList;
    }

    public List<Student> loadStudentsFromBinary(String path) throws IOException, ClassNotFoundException {
        List<Student> students = new ArrayList<>();
        File file = new File(path);

        if (!file.exists()) {
            return students;
        }

        try ( FileInputStream fis = new FileInputStream(file);  ObjectInputStream ois = new ObjectInputStream(fis)) {

            Object obj = ois.readObject();
            if (obj instanceof List) {
                students = (List<Student>) obj;
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Read binary file error: " + e.getMessage());
        }
        return students;
    }

    public boolean saveStudentsToBinary(String path, List<Student> students) {
        try {
            if(!(new File(path).exists())){
                (new File(path)).createNewFile();
            }
            
            OutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(students);
            oos.close();
            fos.close();
            return true;

        } catch (IOException e) {
            System.out.println("Write binary file error: " + e.getMessage());
            return false;
        }
    }

}
