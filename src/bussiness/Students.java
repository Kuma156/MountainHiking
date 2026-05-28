package bussiness;

import controller.Acceptable;
import controller.FileIOHandler;
import controller.Inputter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Student;

/**
 *
 * @author The Miracle Invoker
 */
public class Students extends ArrayList<Student> {

    private FileIOHandler ioHandler = new FileIOHandler();
    private String path = "data\registrations.dat";
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

    public boolean printAll() {
        if (this.isEmpty()) {
            System.out.println("No students have registered yet");
            return false;
        }
        controller.UI.printHeaderStudent();
        for (Student s : this) {
            System.out.println("%14s|%21s|%13s|%11s|%14s" + s.getId() + s.getName() + s.getMountainCode() + displayFee(s.getTutionFee()));

        }
        controller.UI.printFooterStudent();
        return true;
    }

    public void addStudent(Mountains mountainList) {
        int count = 0;
        String again = "y";
        String id, name, phone, email, code;
        Inputter input = new Inputter();
        System.out.println("\n--- MOUNTAIN HIKING REGISTRATION ---");
        while (true) {
            id = input.inputAndLoop("Enter student ID (e.g. SE123456 ):", Acceptable.STU_VALID_ID);

            if (id == null) {
                System.out.println("Cancel successfully");
                return;
            } else {
                id = id.toUpperCase();
            }

            if (searchStudentById(id) == -1) {
                break;
            }
            count++;
            System.out.println("This student has registered. Please re-enter!");
            if (count >= 3) {
                System.out.println("Do you want to continue?(Y/N)");
                again = Inputter.inputYesNo();

                if ("n".equalsIgnoreCase(again) || "no".equalsIgnoreCase(again)) {
                    return;
                }
            }

        }
        count = 0;
        while (true) {
            name = input.nameProcessor(input.inputAndLoop("Enter student name (e.g. Tu Lam Gia Huy):", Acceptable.NAME_VALID));

            if (name == null) {
                System.out.println("Cancel successfully");
                return;
            }
            if (count >= 3) {
                System.out.println("Do you want to continue?(Y/N)");
                again = Inputter.inputYesNo();

                if ("n".equalsIgnoreCase(again) || "no".equalsIgnoreCase(again)) {
                    return;
                }
            }
            break;
        }
        count = 0;
        while (true) {
            phone = input.inputAndLoop("Enter student phone (e.g. 0987654321 ):", Acceptable.PHONE_VALID);

            if (phone == null) {
                System.out.println("Cancel successfully");
                return;
            }
            count++;
            if (count >= 3) {
                System.out.println("Do you want to continue?(Y/N)");
                again = Inputter.inputYesNo();

                if ("n".equalsIgnoreCase(again) || "no".equalsIgnoreCase(again)) {
                    return;
                }
            }

            break;
        }

        count = 0;

        while (true) {
            email = input.inputAndLoop("Enter student email (e.g. example@fpt.edu.vn ):", Acceptable.EMAIL_VALID);

            if (email == null) {
                System.out.println("Cancel successfully");
                return;
            } else {
                email = email.toLowerCase();
            }
            count++;
            if (count >= 3) {
                System.out.println("Do you want to continue?(Y/N)");
                again = Inputter.inputYesNo();

                if ("n".equalsIgnoreCase(again) || "no".equalsIgnoreCase(again)) {
                    return;
                }
            }
            break;
        }

        count = 0;

        while (true) {
            code = input.inputAndLoop("Enter student mountain peak code (e.g. MT01):", Acceptable.MOUNTAIN_CODE_VALID);

            if (code == null) {
                System.out.println("Cancel successfully");
                return;
            } else {
                code = code.toUpperCase();
            }
            count++;
            if (count >= 3) {
                System.out.println("Do you want to continue?(Y/N)");
                again = Inputter.inputYesNo();

                if ("n".equalsIgnoreCase(again) || "no".equalsIgnoreCase(again)) {
                    return;
                }
            }
            if (mountainList.isExistMountainCode(code)) {
                break;
            } else {
                System.out.println("The mountain code is not exist in mountain list! ");
            }

        }

        Student newStudent = new Student(id, name, phone, email, code);
        this.add(newStudent);
        this.isSaved = false;
        System.out.println("Registered successfully");
    }

    public void updateStudent(String id) {
        String changeNameCheck = null;
        int index = searchStudentById(id);

        if (index == -1) {
            System.out.println("This id has not existed");
        } else {
            controller.UI.printUpdateMenu();
        }
        while (true) {
            int choice = input.inputChoice(1, 4);
            String temp = null;

            switch (choice) {
                case 1:
                    temp = Inputter.inputName();
                    this.get(index).setName(temp);
                    System.out.println("Updated successfully!");
                    return;
                case 2:
                    temp = Inputter.inputPhone();
                    this.get(index).setPhone(temp);
                    System.out.println("Updated successfully!");
                    return;
                case 3:
                    temp = Inputter.inputEmail();
                    this.get(index).setEmail(temp);
                    System.out.println("Updated successfully!");
                    return;
                case 4:
                    temp = Inputter.inputMountainPeakCode();
                    this.get(index).setMountainPeakCode(temp);
                    System.out.println("Updated successfully!");
                    return;
                case 0:
                    System.out.print("Returning");
                    controller.UI.Loading();
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    public void deleteStudent(String id) {

    }

    public int searchStudentById(String id) {
        int index = -1;
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getId().equals(id)) {
                index = i;
                break;
            }
        }
        return index;
    }
}

