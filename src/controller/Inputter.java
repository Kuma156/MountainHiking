package controller;

import bussiness.Mountains;
import java.util.InputMismatchException;
import java.util.Scanner;
import model.Mountain;

/**
 *
 * @author The Miracle Invoker
 */
public class Inputter implements Acceptable {

    private static Scanner sc;

    public Inputter() {
        this.sc = new Scanner(System.in);
    }

    public String getString(String mess) {
        System.out.println(mess);
        return sc.nextLine();
    }

    public int getInt(String mess) {
        int result = 0;
        String temp = getString(mess);
        if (Acceptable.isValid(temp, Acceptable.INTEGER_VALID)) {
            result = Integer.parseInt(temp);
        }
        return result;
    }

    public double getDouble(String mess) {
        double result = 0;
        String temp = getString(mess);
        if (Acceptable.isValid(temp, Acceptable.DOUBLE_VALID)) {
            result = Double.parseDouble(temp);
        }
        return result;
    }

    public String inputYesNo() {
        String temp = "";
        boolean loopMore = true;

        do {

            temp = sc.nextLine().trim().toLowerCase();

            if (Acceptable.isValid(temp, Acceptable.YESNO_VALID)) {
                System.out.println("Your choose is invalid!. Re-enter...");
            } else {
                loopMore = false;
            }
        } while (loopMore);

        return temp;
    }

    public String inputAndLoop(String mess, String pattern) {
        String result = "", again = "y";
        boolean more = true;
        int count = 0;
        String temp;
        do {
            result = getString(mess);
            more = !Acceptable.isValid(result, pattern);
            if (more) {
                count++;
                System.out.println("Data is invalid! Re-enter...");
            }

            if (count >= 3) {
                System.out.println("Do you want to continue?(Y/N)");
                again = inputYesNo();

                if ("n".equalsIgnoreCase(again) || "no".equalsIgnoreCase(again)) {
                    break;
                }
            }
        } while (more);
        return result.trim();
    }

    public String nameProcessor(String name) {
        name = name.trim();
        if (name.isEmpty()) {
            return "";
        }
        StringBuffer tmp = new StringBuffer();
        tmp.setLength(0);
        String[] parts = name.split("\\s+");
        for (String part : parts) {
            part = part.trim();
            part = part.toLowerCase();
            part = part.substring(0, 1).toUpperCase() + part.substring(1);
            tmp.append(part + " ");
        }

        name = tmp.toString().trim();
        return name;
    }

    public static int inputChoice(int min, int max) {

        while (true) {
            try {
                int choice = Integer.parseInt(sc.nextLine());

                if (choice < min || choice > max) {
                    System.out.println("Choice must be from " + min + " to " + max);
                } else {
                    return choice;
                }
            } catch (NumberFormatException e) {
                System.out.print("Please enter valid number: ");
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }

        }
    }
}
