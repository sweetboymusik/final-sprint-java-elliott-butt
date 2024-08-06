package Menus;

import java.util.ArrayList;
import java.util.Scanner;

import Products.Product;
import Products.ProductService;
import Users.UserService;

public class AdminMenu {
    public static void mainMenu(Scanner scanner, ProductService productService, UserService userService) {
        while (true) {
            MenuService.clearScreen();
            System.out.println("Admin Menu\n");
            System.out.println("1. View User List");
            System.out.println("2. View Seller List");
            System.out.println("3. View Product List");
            System.out.println("4. Delete User");
            System.out.println("5. Back to Main Menu\n");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            MenuService.clearScreen();

            switch (choice) {
                case 1:
                    viewUserList(scanner, userService);
                    break;
                case 2:
                    viewSellerList(scanner, userService);
                    break;
                case 3:
                    viewProductList(scanner, productService);
                    break;
                case 4:
                    deleteUser(scanner, userService);
                    break;
                case 5:
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

    private static void viewSellerList(Scanner scanner, UserService userService) {
        System.out.println("View User List");
        System.out.println("TODO: Not yet implemented");

        scanner.nextLine();
    }

    private static void viewProductList(Scanner scanner, ProductService productService) {
        MenuService.clearScreen();
        System.out.println("View Product List\n");
        ArrayList<Product> products = productService.getAllProducts();
        productService.printResults(products, scanner, "admin");
    }

    private static void deleteUser(Scanner scanner, UserService userService) {
        System.out.println("Delete User List");
        System.out.println("TODO: Not yet implemented");

        System.out.println("Press enter to return to previous menu.");
        scanner.nextLine();
    }
}
