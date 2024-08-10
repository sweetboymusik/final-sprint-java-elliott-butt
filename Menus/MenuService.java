package Menus;

import java.util.ArrayList;
import java.util.Scanner;

import Products.ProductService;
import Products.Product;

public class MenuService {
    // clear console screen
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // clear previous console line
    public static void clearPreviousLine() {
        System.out.print("\033[1A");
        System.out.print("\033[2K");
    }

    // validate user input
    // when presented with a numbered choice
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

    // browse products
    // function here because it is re-used in two different menus
    public static void browseProducts(Scanner scanner, ProductService productService, String caller) {
        while (true) {
            MenuService.clearScreen();
            System.out.println("Browse Products\n");
            System.out.println("1. All Products");
            System.out.println("2. Electronics");
            System.out.println("3. Furniture");
            System.out.println("4. Office Supplies");
            System.out.println("5. Fitness");
            System.out.println("6. Kitchen");
            System.out.println("7. Books");
            System.out.println("8. Clothing");
            System.out.println("9. Back to Main Menu\n");
            System.out.print("Choose an option: ");

            int choice = MenuService.validateUserInput(scanner, 9);
            ArrayList<Product> products = new ArrayList<>();
            MenuService.clearScreen();

            switch (choice) {
                case 1:
                    products = productService.getAllProducts();
                    System.out
                            .println("Browse All Products (" + products.size() + "): \n");
                    break;
                case 9:
                    return;
                default:
                    products = productService.getProductsByCategoryId(choice - 1);
                    System.out
                            .println("Browse " + products.get(1).getCategory() + " (" + products.size() + "): \n");
                    break;
            }

            productService.printResults(products, scanner, caller);
        }
    }
}
