package Menus;

import java.util.Scanner;

import Products.ProductService;
import Users.UserService;
import Users.Admin;
import Users.Buyer;
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
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit\n");
            System.out.print("Choose an option: ");

            int choice = MenuService.validateUserInput(scanner, 3);
            MenuService.clearScreen();

            switch (choice) {
                case 1:
                    login(scanner);
                    break;
                case 2:
                    register(scanner);
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
            System.out.println();
            System.out.println("Welcome back " + user.getUsername() + "!");
            System.out.println("Press enter to continue... ");
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

    private static void register(Scanner scanner) {
        System.out.println("Register\n");

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.println();
        System.out.println("Choose a role:");
        System.out.println("1. Admin");
        System.out.println("2. Buyer");
        System.out.println("3. Seller\n");
        System.out.print("Choose an option: ");

        int role = MenuService.validateUserInput(scanner, 7);

        User newUser = null;
        boolean registered = false;

        switch (role) {
            case 1:
                newUser = new Admin(username, password, email);
                registered = userService.registerUser(newUser, scanner);
                break;
            case 2:
                newUser = new Buyer(username, password, email);
                registered = userService.registerUser(newUser, scanner);
                break;
            case 3:
                newUser = new Seller(username, password, email);
                newUser = createSellerInformation((Seller) newUser, scanner);
                registered = userService.registerSeller((Seller) newUser, scanner);
                break;
        }

        if (registered) {
            System.out.println();
            System.out.println(newUser.getUsername() + " has been registered!");
            System.out.println("Press enter to continue... ");
            scanner.nextLine();
        } else {
            System.out.println();
            System.out.println("Registration unsuccessful.");
            System.out.println("Press enter to continue... ");
            scanner.nextLine();
        }

    }

    private static Seller createSellerInformation(Seller user, Scanner scanner) {
        MenuService.clearScreen();
        System.out.println("Register\n");
        System.out.println("New seller users must also enter seller information.\n");

        System.out.print("Enter seller name: ");
        String storeName = scanner.nextLine();

        System.out.print("Enter description: ");
        String storeDesc = scanner.nextLine();

        System.out.print("Enter contact number: ");
        String contactNumber = scanner.nextLine();

        System.out.print("Enter seller email: ");
        String email = scanner.nextLine();

        System.out.print("Enter website URL: ");
        String websiteURL = scanner.nextLine();

        System.out.print("Enter address: ");
        String address = scanner.nextLine();

        user.setStoreName(storeName);
        user.setStoreDescription(storeDesc);
        user.setContactNumber(contactNumber);
        user.setStoreEmail(email);
        user.setUrl(websiteURL);
        user.setAddress(address);

        return user;
    }
}