package Menus;

import java.util.ArrayList;
import java.util.Scanner;

import Products.Product;
import Products.ProductService;

public class BuyerMenu {
    // buyer main menu
    public static void mainMenu(Scanner scanner, ProductService productService) {
        while (true) {
            MenuService.clearScreen();
            System.out.println("Buyer Menu\n");
            System.out.println("1. Browse Products");
            System.out.println("2. Search Products");
            System.out.println("3. Logout\n");
            System.out.print("Choose an option: ");

            int choice = MenuService.validateUserInput(scanner, 3);
            MenuService.clearScreen();

            switch (choice) {
                case 1:
                    MenuService.browseProducts(scanner, productService, "buyer");
                    break;
                case 2:
                    searchProducts(scanner, productService);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // search products
    private static void searchProducts(Scanner scanner, ProductService productService) {
        while (true) {
            MenuService.clearScreen();
            System.out.println("Search Products\n");
            System.out.println("1. Search by Name");
            System.out.println("2. Search by ID");
            System.out.println("3. Search by Seller");
            System.out.println("4. Return to Previous Menu\n");
            System.out.print("Choose an option: ");

            int choice = MenuService.validateUserInput(scanner, 4);
            MenuService.clearScreen();

            switch (choice) {
                case 1:
                    searchByName(scanner, productService);
                    break;
                case 2:
                    searchById(scanner, productService);
                    break;
                case 3:
                    searchBySeller(scanner, productService);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    System.out.println("Press enter to return to previous menu.");
                    scanner.nextLine();
            }
        }
    }

    // search product by name
    private static void searchByName(Scanner scanner, ProductService productService) {
        System.out.println("Search By Name\n");
        System.out.print("Search: ");

        String choice = scanner.nextLine();

        ArrayList<Product> products = productService.getProductsByName(choice);

        MenuService.clearScreen();
        System.out.println("Results for '" + choice + "' (" + products.size() + "): \n");
        productService.printResults(products, scanner, "buyer");
    }

    // search product by ID
    private static void searchById(Scanner scanner, ProductService productService) {
        System.out.println("Search By ID\n");
        System.out.print("Search: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        ArrayList<Product> products = productService.getProductsById(choice);

        MenuService.clearScreen();
        System.out.println("Results for ID '" + choice + "' (" + products.size() + "): \n");
        productService.printResults(products, scanner, "buyer");
    }

    // search product by seller
    private static void searchBySeller(Scanner scanner, ProductService productService) {
        System.out.println("Search By Seller\n");
        System.out.print("Search: ");

        String choice = scanner.nextLine();

        ArrayList<Product> products = productService.getProductsBySellerName(choice);

        MenuService.clearScreen();
        System.out.println("Results for Seller '" + choice + "' (" + products.size() + "): \n");
        productService.printResults(products, scanner, "buyer");
    }
}
