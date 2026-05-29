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

}
