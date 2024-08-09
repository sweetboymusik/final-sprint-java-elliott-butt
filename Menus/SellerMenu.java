package Menus;

import java.util.ArrayList;
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
                    updateProduct(scanner, productService, user);
                    break;
                case 3:
                    deleteProduct(scanner, productService, user);
                    break;
                case 4:
                    viewProducts(productService, scanner, user);
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
        scanner.nextLine();

        System.out.println();
        System.out.println("Choose a Category:");
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
                System.out.print("Press enter to return to seller menu...");
                scanner.nextLine();
                break;
            default:
                break;
        }

    }

    private static void updateProduct(Scanner scanner, ProductService productService, Seller user) {
        ArrayList<Product> products = productService.getProductsBySellerId(user.getSellerId());
        MenuService.clearScreen();
        System.out.println("Update Product\n");
        productService.printResults(products, scanner, "update");

        while (true) {
            System.out.print("Enter the number of the product you'd like to update: ");

            int choice = MenuService.validateUserInput(scanner, products.size());
            MenuService.clearScreen();
            Product product = products.get(choice - 1);

            System.out.println("Update Product\n");
            System.out.println("1. Name: " + product.getName());
            System.out.println("2. Description: " + product.getDescription());
            System.out.println("3. Price: " + product.getPrice());
            System.out.println("4. Quantity: " + product.getQuantity());
            System.out.println();
            System.out.print("Enter the number of the field you'd like to update: ");

            int choice2 = MenuService.validateUserInput(scanner, 4);
            MenuService.clearScreen();

            System.out.println("Update Product\n");

            switch (choice2) {
                case 1:
                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine();
                    product.setName(newName);
                    break;
                case 2:
                    System.out.print("Enter new description: ");
                    String newDesc = scanner.nextLine();
                    product.setDescription(newDesc);
                    break;
                case 3:
                    System.out.print("Enter new price: ");
                    double newPrice = scanner.nextDouble();
                    product.setPrice(newPrice);
                    break;
                case 4:
                    System.out.print("Enter new quantity: ");
                    int newQuantity = scanner.nextInt();
                    product.setQuantity(newQuantity);
                    break;
                default:
                    break;
            }

            MenuService.clearScreen();
            System.out.println("Update Product\n");
            System.out.println("Name: " + product.getName());
            System.out.println("Description: " + product.getDescription());
            System.out.println("Price: " + product.getPrice());
            System.out.println("Quantity: " + product.getQuantity());
            System.out.println();
            System.out.print("Update product? (Y/N): ");

            String choice3 = scanner.nextLine();

            switch (choice3.toLowerCase()) {
                case "y":
                    productService.updateProduct(product, scanner);
                    return;
                case "n":
                    System.out.println("\nProduct update aborted.");
                    System.out.print("Press enter to return to seller menu...");
                    scanner.nextLine();
                    return;
                default:
                    break;
            }

        }

    }

    private static void deleteProduct(Scanner scanner, ProductService productService, Seller user) {
        ArrayList<Product> products = productService.getProductsBySellerId(user.getSellerId());
        MenuService.clearScreen();
        System.out.println("Delete Product\n");
        productService.printResults(products, scanner, "delete");

        while (true) {
            System.out.print("Enter the number of the product you'd like to delete: ");

            int choice = MenuService.validateUserInput(scanner, products.size());
            MenuService.clearScreen();

            System.out.println("Delete Product\n");
            System.out.println("Name: " + products.get(choice - 1).getName());
            System.out.println("Description: " + products.get(choice - 1).getDescription());
            System.out.println("Price: " + products.get(choice - 1).getPrice());
            System.out.println("Quantity: " + products.get(choice - 1).getQuantity());
            System.out.println("Category: " + products.get(choice - 1).getCategory());
            System.out.println("Seller ID: " + products.get(choice - 1).getSellerId());
            System.out.println();
            System.out.print("Delete product? (Y/N): ");

            String choice3 = scanner.nextLine();

            switch (choice3.toLowerCase()) {
                case "y":
                    productService.deleteProduct(products.get(choice - 1), scanner);
                    return;
                case "n":
                    System.out.println("\nProduct deletion aborted.");
                    System.out.print("Press enter to return to seller menu...");
                    scanner.nextLine();
                    return;
                default:
                    break;
            }
        }
    }

    private static void viewProducts(ProductService productService, Scanner scanner, Seller user) {
        ArrayList<Product> products = productService.getProductsBySellerId(user.getSellerId());
        MenuService.clearScreen();
        System.out.println("View Your Products\n");
        productService.printResults(products, scanner, "seller");
    }
}
