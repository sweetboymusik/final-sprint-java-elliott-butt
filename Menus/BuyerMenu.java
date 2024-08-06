package Menus;

import java.util.ArrayList;
import java.util.Scanner;

import Products.Product;
import Products.ProductService;

public class BuyerMenu {
    public static void mainMenu(Scanner scanner, ProductService productService) {
        while (true) {
            MainMenu.clearScreen();
            System.out.println("Buyer Menu\n");
            System.out.println("1. Browse Products");
            System.out.println("2. Search Products");
            System.out.println("3. Back to Main Menu\n");
            System.out.print("Choose an option: ");

            int choice = MainMenu.validateUserInput(scanner, 3);
            MainMenu.clearScreen();

            switch (choice) {
                case 1:
                    browseProducts(scanner, productService);
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

    private static void browseProducts(Scanner scanner, ProductService productService) {
        while (true) {
            MainMenu.clearScreen();
            System.out.println("Buyer Menu\n");
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

            int choice = MainMenu.validateUserInput(scanner, 9);
            MainMenu.clearScreen();
            ArrayList<Product> products = new ArrayList<>();

            switch (choice) {
                case 1:
                    products = productService.getAllProducts();
                    break;
                case 9:
                    return;
                default:
                    products = productService.getProductsByCategoryId(choice - 1);
                    System.out
                            .println("Browse " + products.get(1).getCategory() + " (" + products.size() + "): \n");
                    break;
            }

            productService.printResults(products, scanner, "buyer");
        }
    }

    private static void searchProducts(Scanner scanner, ProductService productService) {
        while (true) {
            MainMenu.clearScreen();
            System.out.println("Search Products\n");
            System.out.println("1. Search by Name");
            System.out.println("2. Search by ID");
            System.out.println("3. Search by Seller");
            System.out.println("4. Return to Previous Menu\n");
            System.out.print("Choose an option: ");

            int choice = MainMenu.validateUserInput(scanner, 4);
            MainMenu.clearScreen();

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

    private static void searchByName(Scanner scanner, ProductService productService) {
        System.out.println("Search By Name\n");
        System.out.print("Search: ");

        String choice = scanner.nextLine();

        ArrayList<Product> products = productService.getProductsByName(choice);

        MainMenu.clearScreen();
        System.out.println("Results for '" + choice + "' (" + products.size() + "): \n");
        productService.printResults(products, scanner, "buyer");
    }

    private static void searchById(Scanner scanner, ProductService productService) {
        System.out.println("Search By ID\n");
        System.out.print("Search: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        ArrayList<Product> products = productService.getProductsById(choice);

        MainMenu.clearScreen();
        System.out.println("Results for ID '" + choice + "' (" + products.size() + "): \n");
        productService.printResults(products, scanner, "buyer");
    }

    private static void searchBySeller(Scanner scanner, ProductService productService) {
        System.out.println("Search By Seller\n");
        System.out.print("Search: ");

        String choice = scanner.nextLine();

        ArrayList<Product> products = productService.getProductsBySellerName(choice);

        MainMenu.clearScreen();
        System.out.println("Results for Seller '" + choice + "' (" + products.size() + "): \n");
        productService.printResults(products, scanner, "buyer");
    }
}
