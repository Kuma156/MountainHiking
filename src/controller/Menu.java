package controller;

/**
 *
 * @author The Miracle Invoker
 */
public class Menu {
    public static void printUpdateMenu() {
        System.out.println("======================================");
        System.out.println("| 1. Update Name                     |");
        System.out.println("| 2. Update Phone Number             |");
        System.out.println("| 3. Update Email                    |");
        System.out.println("| 4. Update Mountain Peak Code       |");
        System.out.println("| 0. Return                          |");
        System.out.println("======================================");
        System.out.print("==> Your choice: ");
    }
    
    public static void printMainMenu() {
        System.out.println("=====Mountain Hiking Challenge Registration Menu=====");
        System.out.println("|1. New Registration                                |");
        System.out.println("|2. Update Registration Information                 |");
        System.out.println("|3. Display Registered List                         |");
        System.out.println("|4. Delete Registration Information                 |");
        System.out.println("|5. Search Participants by Name                     |");
        System.out.println("|6. Filter Data by Campus                           |");
        System.out.println("|7. Statistics of registration number by Location   |");
        System.out.println("|8. Save Data to File                               |");
        System.out.println("|9. Exit the program                                |");
        System.out.println("=====================================================");
        System.out.print("Please choose an option (1-9): ");
    }
}
