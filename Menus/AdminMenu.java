package Menus;

import java.util.ArrayList;
import java.util.Scanner;

import Products.Product;
import Products.ProductService;
import Users.UserService;

public class AdminMenu {
    public static void mainMenu(Scanner scanner, ProductService productService, UserService userService) {
        while (true) {
            MainMenu.clearScreen();
            System.out.println("Admin Menu\n");
            System.out.println("1. View User List");
            System.out.println("2. View All Products");
            System.out.println("3. Delete User");
            System.out.println("4. Back to Main Menu\n");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            MainMenu.clearScreen();

            switch (choice) {
                case 1:
                    viewUserList(scanner, userService);
                    break;
                case 2:
                    viewProductList(scanner, productService);
                    break;
                case 3:
                    deleteUser(scanner, userService);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void viewUserList(Scanner scanner, UserService userService) {
        System.out.println("View User List");
        System.out.println("TODO: Not yet implemented");

        scanner.nextLine();
    }

    private static void viewProductList(Scanner scanner, ProductService productService) {
        MainMenu.clearScreen();
        System.out.print("View Product List\n");
        ArrayList<Product> products = productService.getAllProducts();
        productService.printResults(products, scanner);
    }

    private static void deleteUser(Scanner scanner, UserService userService) {
        System.out.println("Delete User List");
        System.out.println("TODO: Not yet implemented");

        System.out.println("Press enter to return to previous menu.");
        scanner.nextLine();
    }
}
