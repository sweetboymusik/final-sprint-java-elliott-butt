package Menus;

import java.util.ArrayList;
import java.util.Scanner;

import Products.ProductService;
import Users.UserService;
import Users.Seller;
import Users.User;

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
                    MenuService.browseProducts(scanner, productService, "admin");
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
        System.out.println("View User List\n");
        ArrayList<User> users = userService.getAllUsers();
        userService.printUserResults(users, scanner, "view");
    }

    private static void viewSellerList(Scanner scanner, UserService userService) {
        System.out.println("View Seller List\n");
        ArrayList<Seller> sellers = userService.getAllSellers();
        userService.printSellerResults(sellers, scanner);
    }

    private static void deleteUser(Scanner scanner, UserService userService) {
        while (true) {
            MenuService.clearScreen();
            System.out.println("Delete User\n");
            System.out.println("1. Search by Username");
            System.out.println("2. Search by ID");
            System.out.println("3. Search by Role");
            System.out.println("4. Return to Previous Menu\n");
            System.out.print("Choose an option: ");

            int choice = MenuService.validateUserInput(scanner, 4);
            MenuService.clearScreen();

            switch (choice) {
                case 1:
                    searchByUsername(scanner, userService);
                    break;
                case 2:
                    searchById(scanner, userService);
                    break;
                case 3:
                    searchByRole(scanner, userService);
                    break;
                default:
                    return;
            }
        }
    }

    private static void searchById(Scanner scanner, UserService userService) {
        System.out.println("Search By ID\n");
        System.out.print("Search: ");

        int choice1 = scanner.nextInt();
        scanner.nextLine();

        ArrayList<User> users = userService.getUsersById(choice1);

        MenuService.clearScreen();
        System.out.println("Results for ID '" + choice1 + "' (" + users.size() + "): \n");
        userService.printUserResults(users, scanner, "delete");
        deleteSelect(users, scanner, userService);
    }

    private static void searchByUsername(Scanner scanner, UserService userService) {
        System.out.println("Search By Username\n");
        System.out.print("Search: ");

        String choice1 = scanner.nextLine();

        ArrayList<User> users = userService.getUsersByUsername(choice1);

        MenuService.clearScreen();
        System.out.println("Results for Username '" + choice1 + "' (" + users.size() + "): \n");
        userService.printUserResults(users, scanner, "delete");
        deleteSelect(users, scanner, userService);
    }

    private static void searchByRole(Scanner scanner, UserService userService) {
        System.out.println("Search By Role\n");
        System.out.println("1. Admin");
        System.out.println("2. Buyer");
        System.out.println("3. Seller\n");
        System.out.print("Choose an option: ");

        int choice = MenuService.validateUserInput(scanner, 4);
        ArrayList<User> users = new ArrayList<>();
        MenuService.clearScreen();

        switch (choice) {
            case 1:
                users = userService.getUsersByRole("admin");
                System.out.println("Results for Role 'admin':\n");
                break;
            case 2:
                users = userService.getUsersByRole("buyer");
                System.out.println("Results for Role 'buyer':\n");
                break;
            case 3:
                users = userService.getUsersByRole("seller");
                System.out.println("Results for Role 'seller':\n");
                break;
            default:
                break;
        }

        userService.printUserResults(users, scanner, "delete");
        deleteSelect(users, scanner, userService);
    }

    private static void deleteSelect(ArrayList<User> users, Scanner scanner, UserService userService) {
        while (true) {
            System.out.print("Enter the number of the user you'd like to delete: ");

            int choice2 = MenuService.validateUserInput(scanner, users.size());
            MenuService.clearScreen();

            System.out.println("Delete User\n");
            System.out.println("ID: " + users.get(choice2 - 1).getId());
            System.out.println(users.get(choice2 - 1).getUsername());
            System.out.println(users.get(choice2 - 1).getEmail());
            System.out.println(users.get(choice2 - 1).getRole());
            System.out.println();
            System.out.print("Are you sure you'd like to delete this record?\n");
            System.out.print(
                    "If your are deleting a user with the 'seller' role, all related products will also be deleted.\n");
            System.out.print("Delete user? (Y/N): ");

            String choice3 = scanner.nextLine();

            switch (choice3.toLowerCase()) {
                case "y":
                    userService.deleteUser(users.get(choice2 - 1), scanner);
                    break;
                case "n":
                    System.out.println("\nUser deletion aborted.");
                    System.out.print("Press enter to return to admin menu...");
                    scanner.nextLine();
                    break;
                default:
                    break;
            }

            break;
        }
    }
}
