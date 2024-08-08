package Menus;

import java.util.Scanner;

import Products.ProductService;
import Users.UserService;
import Users.Seller;
import Users.User;

public class MainMenu {
    // set up necessary classes
    private static Scanner scanner = new Scanner(System.in);
    private static ProductService productService = new ProductService();
    private static UserService userService = new UserService();

    public static void main(String[] args) {
        while (true) {
            MenuService.clearScreen();
            System.out.println("Ecommerce Program\n");
            System.out.println("1: Login");
            System.out.println("2: Register");
            System.out.println("3: Exit\n");
            System.out.print("Choose an option: ");

            int choice = MenuService.validateUserInput(scanner, 3);
            MenuService.clearScreen();

            switch (choice) {
                case 1:
                    login(scanner);
                    break;
                case 2:
                    break;
                case 3:
                    System.out.println("Thank you for using our program. See you next time!");
                    return;
                default:
                    break;
            }
        }
    }

    private static void login(Scanner scanner) {
        System.out.println("Login\n");

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = userService.authUser(username, password, scanner);

        if (user != null) {
            System.out.println("Welcome: " + user.getUsername());
            scanner.nextLine();

            switch (user.getRole()) {
                case "admin":
                    AdminMenu.mainMenu(scanner, productService, userService);
                    break;
                case "buyer":
                    BuyerMenu.mainMenu(scanner, productService);
                    break;
                case "seller":
                    Seller seller = (Seller) user;
                    seller.getSellerId();

                    SellerMenu.mainMenu(scanner, productService, userService, seller);
                    break;
                default:
                    break;
            }
        }
    }
}