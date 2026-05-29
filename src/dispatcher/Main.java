package dispatcher;

import bussiness.Mountains;
import bussiness.Students;
import controller.Inputter;
import controller.Menu;
import controller.UI;
import controller.StudentController;
import java.io.IOException;

/**
 * E:\Huy\,FPT\.Major\Summer26\Lab211\Lab\Lab1\MountainHiking
 * https://github.com/Kuma156/MountainHiking
 *
 * @author The Miracle Invoker
 */
public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Mountains mountainList = new Mountains();
        Students studentList = new Students();

        mountainList.loadData();
        studentList.loadData();

        StudentController controller = new StudentController(studentList, mountainList);

        
        UI.clearScreen();
        while (true) {

            Menu.printMainMenu();

            int choice = Inputter.inputChoice(1, 9);

            switch (choice) {
                case 1: //new register
                    controller.addStudentProcess();
                    break;
                case 2: //update info
                    controller.updateStudentProcess();
                    break;
                case 3: //show all student list
                    controller.displayAllStudentsProcess();
                    break;
                case 4: //delete student
                    controller.deleteStudentProcess();
                    break;
                case 5: //search participant by name
                    controller.searchStudentByNameProcess();
                    break;
                case 6: //filter by campus
                    controller.filterDataByCampusProcess();
                    break;
                case 7: //statistics
                    controller.showStatisticsProcess();
                    break;
                case 8: //save 
                    studentList.saveData();
                    UI.Loading();
                    System.out.println("Data saved to file successfully!");
                    break;
                case 9: //exit
                    controller.exitProcess();
                    break;
                default:
                    System.out.println("This function is not available.");
            }
            UI.pause();
        }
    }

}
