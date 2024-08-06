package Menus;

import java.util.Scanner;

import Products.ProductService;
import Users.UserService;

public class MainMenu {
    // helper functions
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
                MainMenu.clearPreviousLine();
                System.out.print("Input cannot be empty. Please enter a number between 1 and " + max + ": ");
                continue;
            }

            try {
                choice = Integer.parseInt(input);

                if (choice < 1 || choice > max) {
                    MainMenu.clearPreviousLine();
                    System.out.print("Invalid input. Please enter a number between 1 and " + max + ": ");
                }
            } catch (Exception e) {
                MainMenu.clearPreviousLine();
                System.out.print("Invalid input. Please enter a valid number: ");
            }
        }

        return choice;
    }

    public static void main(String[] args) {
        // set up necessary classes
        Scanner scanner = new Scanner(System.in);
        ProductService productService = new ProductService();
        UserService userService = new UserService();

        // Display main menu
        while (true) {
            clearScreen();
            System.out.println("Ecommerce\n");
            System.out.println("1. Admin Menu");
            System.out.println("2. Buyer Menu");
            System.out.println("3. Seller Menu");
            System.out.println("4. Exit\n");
            System.out.print("Choose an option: ");

            int choice = validateUserInput(scanner, 4);
            clearScreen();

            switch (choice) {
                case 1:
                    AdminMenu.mainMenu(scanner, productService, userService);
                    break;
                case 2:
                    BuyerMenu.mainMenu(scanner, productService);
                    break;
                case 3:
                    break;
                case 4:
                    System.out.println("End Session. Thank you.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
