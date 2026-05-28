package controller;

import java.util.Scanner;

/**
 *
 * @author The Miracle Invoker
 */
public class UI {

    public static void pause() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter to continue!");
        sc.nextLine();

    }

    public static void Loading() {
        for (int i = 0; i <= 5; i++) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException ex) {
            }
            System.out.print(".");

        }
        System.out.println("");
    }

    public static void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println("");
        }
    }

    public static void printHeaderStudent() {
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("Student ID    | Name                | Phone       | Peak Code | Fee          ");
        System.out.println("-----------------------------------------------------------------------------");
    }

    public static void printFooterStudent() {
        System.out.println("-----------------------------------------------------------------------------");
    }

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
    
}
