package controller;

import bussiness.Mountains;
import bussiness.Statistics;
import bussiness.Students;
import java.util.List;
import model.Mountain;
import model.StatisticalInfo;
import model.Student;

/**
 *
 * @author The Miracle Invoker
 */
public class StudentController {

    private final Students studentList;
    private final Mountains mountainList;
    private final Inputter input;

    public StudentController(Students studentList, Mountains mountainList) {
        this.studentList = studentList;
        this.mountainList = mountainList;
        this.input = new Inputter();
    }

    public void addStudentProcess() {
        System.out.println("\n--- MOUNTAIN HIKING REGISTRATION ---");

        String id = getValidId();
        if (id == null) {
            return;
        }

        String name = getValidName();
        if (name == null) {
            return;
        }

        String phone = getValidPhone();
        if (phone == null) {
            return;
        }

        String email = getValidEmail();
        if (email == null) {
            return;
        }

        String code = getValidMountainCode();
        if (code == null) {
            return;
        }

        Student newStudent = new Student(id, name, phone, email, code);
        studentList.addStudent(newStudent);
    }

    private String getValidId() {
        int count = 0;
        while (true) {
            String id = input.inputAndLoop("Enter student ID (e.g. SE123456 ):", Acceptable.STU_VALID_ID);
            if (id == null || id.isEmpty()) {
                System.out.println("Cancel successfully");
                return null;
            }
            id = id.toUpperCase();

            if (!studentList.isStudentExist(id)) {
                return id;
            }

            count++;
            System.out.println("This student has registered. Please re-enter!");
            if (count >= 3 && isCancel()) {
                return null;
            }
        }
    }

    private String getValidName() {
        int count = 0;
        while (true) {
            String name = input.inputAndLoop("Enter student name (e.g. Tu Lam Gia Huy):", Acceptable.NAME_VALID);
            if (name == null || name.isEmpty()) {
                System.out.println("Cancel successfully");
                return null;
            }
            name = input.nameProcessor(name);
            if (!name.isEmpty()) {
                return name;
            }

            count++;
            if (count >= 3 && isCancel()) {
                return null;
            }
        }
    }

    private String getValidPhone() {
        int count = 0;
        while (true) {
            String phone = input.inputAndLoop("Enter student phone (e.g. 0987654321 ):", Acceptable.PHONE_VALID);
            if (phone == null || phone.isEmpty()) {
                System.out.println("Cancel successfully");
                return null;
            }
            return phone;
        }
    }

    private String getValidEmail() {
        int count = 0;
        while (true) {
            String email = input.inputAndLoop("Enter student email (e.g. example@fpt.edu.vn ):", Acceptable.EMAIL_VALID);
            if (email == null || email.isEmpty()) {
                System.out.println("Cancel successfully");
                return null;
            }
            return email.toLowerCase();
        }
    }

    private String getValidMountainCode() {
        int count = 0;
        while (true) {
            String code = input.inputAndLoop("Enter student mountain peak code (e.g. MT01):", Acceptable.MOUNTAIN_CODE_VALID);
            if (code == null || code.isEmpty()) {
                System.out.println("Cancel successfully");
                return null;
            }
            code = code.toUpperCase();

            // Gọi sang tầng nghiệp vụ Mountains để kiểm tra sự tồn tại của mã
            if (mountainList.isExistMountainCode(code)) {
                return code;
            } else {
                System.out.println("The mountain code does not exist in the mountain list!");
            }

            count++;
            if (count >= 3 && isCancel()) {
                return null;
            }
        }
    }

    private boolean isCancel() {
        System.out.println("Do you want to continue?(Y/N)");
        boolean again = input.inputYesNo();
        return again == false;
    }

    public void displayAllStudentsProcess() {
        if (studentList.isEmpty()) {
            System.out.println("No students have registered yet.");
            return;
        }
        controller.ViewStudent.printHeaderStudent();
        for (Student s : studentList) {

            System.out.printf("%-14s|%-21s|%-13s|%-11s|%-14s\n", s.getId(), s.getName(), s.getPhone(), s.getMountainCode(), studentList.displayFee(s.getTutionFee()));
        }
        controller.ViewStudent.printFooterStudent();
    }

    public void updateStudentProcess() {
        System.out.println("\n--- UPDATE REGISTRATION INFORMATION ---");
        String id = input.inputAndLoop("Enter student ID to update (e.g. SE123456):", Acceptable.STU_VALID_ID);

        if (id == null || id.isEmpty()) {
            System.out.println("Cancel successfully");
            return;
        }
        id = id.toUpperCase();

        int index = studentList.searchStudentById(id);
        if (index == -1) {
            System.out.println("This student has not registered yet.");
            return;
        }

        while (true) {
            Menu.printUpdateMenu();
            int choice = Inputter.inputChoice(0, 4);

            switch (choice) {
                case 1:
                    handleUpdateName(index);
                    break;
                case 2:
                    handleUpdatePhone(index);
                    break;
                case 3:
                    handleUpdateEmail(index);
                    break;
                case 4:
                    handleUpdateMountain(index);
                    break;
                case 0:
                    System.out.print("Returning to main menu");
                    UI.Loading();
                    return;
            }
        }
    }

    private void handleUpdateName(int index) {
        String newName = input.inputAndLoop("Enter new student name:", Acceptable.NAME_VALID);
        if (newName != null && !newName.isEmpty()) {
            newName = input.nameProcessor(newName);
            studentList.updateStudentName(index, newName);
            System.out.println("Updated name successfully!");
        }
    }

    private void handleUpdatePhone(int index) {
        String newPhone = input.inputAndLoop("Enter new student phone:", Acceptable.PHONE_VALID);
        if (newPhone != null && !newPhone.isEmpty()) {
            studentList.updateStudentPhone(index, newPhone);
            System.out.println("Updated phone and recalculated tuition fee successfully!");
        }
    }

    private void handleUpdateEmail(int index) {
        String newEmail = input.inputAndLoop("Enter new student email:", Acceptable.EMAIL_VALID);
        if (newEmail != null && !newEmail.isEmpty()) {
            studentList.updateStudentEmail(index, newEmail.toLowerCase());
            System.out.println("Updated email successfully!");
        }
    }

    private void handleUpdateMountain(int index) {
        while (true) {
            String newCode = input.inputAndLoop("Enter new mountain peak code (e.g. MT01):", Acceptable.MOUNTAIN_CODE_VALID);
            if (newCode == null || newCode.isEmpty()) {
                break;
            }

            newCode = newCode.toUpperCase();

            if (mountainList.isExistMountainCode(newCode)) {
                studentList.updateStudentMountain(index, newCode);
                System.out.println("Updated mountain peak code successfully!");
                break;
            } else {
                System.out.println("The mountain code does not exist in the mountain list! Please re-enter.");
            }
        }
    }

    public void deleteStudentProcess() {
        String id = input.inputAndLoop("Enter student ID to update (e.g. SE123456):", Acceptable.STU_VALID_ID);

        if (id == null || id.isEmpty()) {
            System.out.println("Cancel successfully");
            return;
        }
        id = id.toUpperCase();

        int index = studentList.searchStudentById(id);
        if (index == -1) {
            System.out.println("This student has not registered yet.");
            return;
        }

        Student targetStudent = studentList.get(index);

        ViewStudent.printHeaderStudent();
        System.out.printf("%-14s|%-21s|%-13s|%-11s|%14s\n",
                targetStudent.getId(),
                targetStudent.getName(),
                targetStudent.getPhone(),
                "MT" + targetStudent.getMountainCode(),
                studentList.displayFee(targetStudent.getTutionFee())
        );
        ViewStudent.printFooterStudent();

        System.out.println("Are you sure you want to delete this registration? (Y/N)");

        boolean choose = input.inputYesNo();

        if (choose == true) {
            studentList.deleteStudent(id);
            System.out.println("The registration has been successfully deleted.");
        }
    }

    public void searchStudentByNameProcess() {
        System.out.println("-----SEARCH PARTICIPANTS BY NAME-----");
        String name = input.getString("Enter student name to search: ");

        if (name == null || name.trim().isEmpty()) {
            System.out.println("Invalid keyword!");
            return;
        }
        name = name.trim();

        List<Student> foundList = studentList.searchParticipantsByName(name);

        if (foundList.isEmpty()) {
            System.out.println("No one matches the search criteria!");
            return;
        }

        ViewStudent.printHeaderStudent();
        for (Student s : foundList) {
            System.out.printf("%-14s|%-21s|%-13s|%-11s|%-14s\n",
                    s.getId(),
                    s.getName(),
                    s.getPhone(),
                    s.getMountainCode(),
                    studentList.displayFee(s.getTutionFee())
            );
        }
        ViewStudent.printFooterStudent();
    }

    public void filterDataByCampusProcess() {
        System.out.println("-----FILTER DATA BY CAMPUS-----");
        String campusCode = input.getString("Enter the campus code: ");

        if (campusCode == null || campusCode.trim().isEmpty() || !Acceptable.isValid(campusCode, Acceptable.CAMPUS_CODE_VALID)) {
            System.out.println("Invalid code!");
            return;
        }
        campusCode = campusCode.trim();

        List<Student> foundList = studentList.filterDataByCampus(campusCode);

        if (foundList.isEmpty()) {
            System.out.println("No students have registered under this campus.");
            return;
        }

        System.out.println("Found " + foundList.size() + " result(s) matching:");

        ViewStudent.printHeaderStudent();
        for (Student s : foundList) {
            System.out.printf("%-14s|%-21s|%-13s|%-11s|%-14s\n",
                    s.getId(),
                    s.getName(),
                    s.getPhone(),
                    s.getMountainCode(),
                    studentList.displayFee(s.getTutionFee())
            );
        }
        ViewStudent.printFooterStudent();
    }

    public void showStatisticsProcess() {
        System.out.println("\n--- STATISTICAL INFORMATION ---");

        Statistics statMap = studentList.displayStatistics();

        if (statMap == null || statMap.isEmpty()) {
            System.out.println("No data available for statistics.");
            return;
        }

        statMap.show();
    }

    public void exitProcess() {
        System.out.println("-----EXIT APPLICATION-----");

        if (!studentList.getIsSaved()) {
            System.out.print("Do you want to save the changes before exiting? (Y/N): ");

            boolean confirm = input.inputYesNo();

            if (confirm == true) {

                studentList.saveData();
                System.out.println("Data saved successfully!");
            } else {
                System.out.println("You have unsaved changes. Are you sure you want to exit without saving? (Y/N)");
                confirm = input.inputYesNo();
                if (confirm == true) {
                    studentList.saveData();
                    System.out.println("Data saved successfully!");
                } else {
                    System.out.println("Exiting without saving changes...");
                }
            }
        }
        System.exit(0);
    }

}
