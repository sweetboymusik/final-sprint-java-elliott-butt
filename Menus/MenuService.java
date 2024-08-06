package Menus;

import java.util.Scanner;

public class MenuService {
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void clearPreviousLine() {
        System.out.print("\033[1A");
        System.out.print("\033[2K");
    }

    public static int validateUserInput(Scanner scanner, int max) {
        int choice = -1;

        while (choice < 1 || choice > max) {
            String input = scanner.nextLine();

            // check if enter is pressed by itself
            if (input.trim().isEmpty()) {
                clearPreviousLine();
                System.out.print("Input cannot be empty. Please enter a number between 1 and " + max + ": ");
                continue;
            }

            try {
                choice = Integer.parseInt(input);

                if (choice < 1 || choice > max) {
                    clearPreviousLine();
                    System.out.print("Invalid input. Please enter a number between 1 and " + max + ": ");
                }
            } catch (Exception e) {
                clearPreviousLine();
                System.out.print("Invalid input. Please enter a valid number: ");
            }
        }

        return choice;
    }
}
