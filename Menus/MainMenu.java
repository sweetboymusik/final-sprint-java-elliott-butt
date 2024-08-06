package Menus;

import java.util.Scanner;

import Products.ProductService;
import Users.UserService;

public class MainMenu {
    public static void main(String[] args) {
        // set up necessary classes
        Scanner scanner = new Scanner(System.in);
        ProductService productService = new ProductService();
        UserService userService = new UserService();

        // Display main menu
        while (true) {
            MenuService.clearScreen();
            System.out.println("Ecommerce\n");
            System.out.println("1. Admin Menu");
            System.out.println("2. Buyer Menu");
            System.out.println("3. Seller Menu");
            System.out.println("4. Exit\n");
            System.out.print("Choose an option: ");

            int choice = MenuService.validateUserInput(scanner, 4);
            MenuService.clearScreen();

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
