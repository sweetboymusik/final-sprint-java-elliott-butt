package Menus;

import java.util.Scanner;

import Products.Product;
import Products.ProductService;
import Users.UserService;
import Users.Seller;

public class SellerMenu {
    public static void mainMenu(Scanner scanner, ProductService productService, UserService userService, Seller user) {
        while (true) {
            MenuService.clearScreen();
            System.out.println("Seller Menu\n");
            System.out.println("1. Add Product");
            System.out.println("2. Update Product");
            System.out.println("3. Delete Product");
            System.out.println("4. View Your Products");
            System.out.println("5. Logout\n");
            System.out.print("Choose an option: ");

            int choice = MenuService.validateUserInput(scanner, 5);
            MenuService.clearScreen();

            switch (choice) {
                case 1:
                    addProduct(scanner, productService, user);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    return;
                default:
                    break;
            }
        }
    }

    private static void addProduct(Scanner scanner, ProductService productService, Seller user) {
        System.out.println("Add Product\n");

        System.out.print("Enter Product Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Product Description: ");
        String description = scanner.nextLine();

        System.out.print("Enter Product Quantity: ");
        int quantity = scanner.nextInt();

        System.out.print("Enter Product Price: ");
        double price = scanner.nextDouble();

        System.out.println("\nChoose a Category:");
        System.out.println("1. Electronics");
        System.out.println("2. Furniture");
        System.out.println("3. Office Supplies");
        System.out.println("4. Fitness");
        System.out.println("5. Kitchen");
        System.out.println("6. Books");
        System.out.println("7. Clothing\n");
        System.out.print("Choose an option: ");

        int category = MenuService.validateUserInput(scanner, 7);
        MenuService.clearScreen();

        System.out.println("Add Product\n");
        System.out.println("Name: " + name);
        System.out.println("Description: " + description);
        System.out.println("Price: " + price);
        System.out.println("Quantity: " + quantity);
        System.out.println("Category: " + category);
        System.out.println("Seller ID: " + user.getSellerId());
        System.out.println();
        System.out.print("Add this product? (Y/N): ");

        String choice3 = scanner.nextLine();

        switch (choice3.toLowerCase()) {
            case "y":
                Product newProduct = new Product(name, description, category, price, quantity, user.getSellerId());
                productService.addProduct(newProduct, scanner);
                break;
            case "n":
                System.out.println("\nProduct was not added.");
                System.out.print("Press enter to return to admin menu...");
                scanner.nextLine();
                break;
            default:
                break;
        }

    }

}
