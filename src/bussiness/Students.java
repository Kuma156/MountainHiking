package bussiness;

import controller.FileIOHandler;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import model.Student;

/**
 * @author The Miracle Invoker
 */
public class Students extends ArrayList<Student> implements Serializable {

    private FileIOHandler ioHandler = new FileIOHandler();
    private String path = "data\\registrations.dat";
    private boolean isSaved = true;

    public Students() {
    }

    public void loadData() throws IOException, ClassNotFoundException {
        List<Student> list = ioHandler.loadStudentsFromBinary(path);
        this.clear();
        this.addAll(list);
        this.isSaved = true;
    }

    public void saveData() {
        boolean saveStatus = ioHandler.saveStudentsToBinary(path, this);
        if (saveStatus) {
            System.out.println("Saved successfully!");
            this.isSaved = true;
        }
    }

    public String displayFee(double fee) {
        return String.format("%,.0f", fee);
    }

    public boolean isStudentExist(String id) {
        return searchStudentById(id) != -1;
    }

    public void addStudent(Student student) {
        this.add(student);
        this.isSaved = false;
        System.out.println("Registered successfully");
    }

    public void updateStudentName(int index, String newName) {
        this.get(index).setName(newName);
        this.isSaved = false;
    }

    public void updateStudentPhone(int index, String newPhone) {
        Student s = this.get(index);
        s.setPhone(newPhone);

        double baseFee = 6000000;
        if (controller.Acceptable.isValid(newPhone, controller.Acceptable.VIETTEL_VALID)
                || controller.Acceptable.isValid(newPhone, controller.Acceptable.VNPT_VALID)) {
            s.setTutionFee(baseFee * 0.65);
        } else {
            s.setTutionFee(baseFee);
        }
        this.isSaved = false;
    }

    public void updateStudentEmail(int index, String newEmail) {
        this.get(index).setEmail(newEmail);
        this.isSaved = false;
    }

    public void updateStudentMountain(int index, String newCode) {
        this.get(index).setMountainPeakCode(newCode);
        this.isSaved = false;
    }

    public void deleteStudent(String id) {
        int index = searchStudentById(id);
        if (index != -1) {
            this.remove(index);
            this.isSaved = false;
        }
    }

    public int searchStudentById(String id) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getId().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }

    public List<Student> searchParticipantsByName(String name) {
        List<Student> resultList = new ArrayList<>();

        for (Student s : this) {

            if (s.getName().toLowerCase().contains(name.toLowerCase())) {
                resultList.add(s);
            }
        }

        return resultList;
    }

    public List<Student> filterDataByCampus(String code) {
        List<Student> resultList = new ArrayList<>();

        for (Student s : this) {

            if (s.getId().toLowerCase().contains(code.toLowerCase())) {
                resultList.add(s);
            }
        }

        return resultList;
    }

    public Statistics displayStatistics() {
        Statistics statMap = new Statistics();

        for (Student s : this) {
            String mountainCode = s.getMountainCode();
            double fee = s.getTutionFee();

            if (statMap.containsKey(mountainCode)) {

                model.StatisticalInfo info = statMap.get(mountainCode);
                info.setNumberOfStudent(info.getNumberOfStudent() + 1);
                info.setTotalCost(info.getTotalCost() + fee);
            } else {

                model.StatisticalInfo newInfo = new model.StatisticalInfo(mountainCode, 1, fee);
                statMap.put(mountainCode, newInfo);
            }
        }

        return statMap;
    }

    public boolean getIsSaved() {
        return isSaved;
    }
}
