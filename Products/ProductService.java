package Products;

import java.util.ArrayList;
import java.util.Scanner;

import Users.UserDAO;
import Users.Seller;

public class ProductService {
    // instance variables
    private ProductDAO productDAO;
    private UserDAO userDAO;

    // constructors
    public ProductService() {
        this.productDAO = new ProductDAO();
        this.userDAO = new UserDAO();
    }

    // editing methods
    public void addProduct(Product product, Scanner scanner) {
        try {
            productDAO.addProduct(product);
            System.out.println("\nProduct added successfully!");
            System.out.print("Press enter to return to seller menu... ");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateProduct(Product product, Scanner scanner) {
        try {
            productDAO.updateProduct(product);
            System.out.println("\nProduct updated successfully!");
            System.out.print("Press enter to return to seller menu... ");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteProduct(Product product, Scanner scanner) {
        try {
            productDAO.deleteProduct(product);
            System.out.println("\nProduct deleted successfully!");
            System.out.print("Press enter to return to seller menu... ");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // search methods
    public ArrayList<Product> getAllProducts() {
        return productDAO.searchProducts("1", 1);
    }

    public ArrayList<Product> getProductsBySellerId(int sellerId) {
        return productDAO.searchProducts("seller_id", sellerId);
    }

    public ArrayList<Product> getProductsBySellerName(String seller) {
        return productDAO.searchProducts("seller_information.store_name", seller);
    }

    public ArrayList<Product> getProductsByName(String name) {
        return productDAO.searchProducts("products.name", name);
    }

    public ArrayList<Product> getProductsById(int id) {
        return productDAO.searchProducts("products.id", id);
    }

    public ArrayList<Product> getProductsByCategoryId(int id) {
        return productDAO.searchProducts("category_id", id);
    }

    public <T> ArrayList<Product> getProductsByFieldAndSeller(String field, T value, int sellerId) {
        ArrayList<Product> results = getProductsBySellerId(sellerId);

        // filter out results that don't match field and value
        results.removeIf(product -> {
            switch (field) {
                case "id":
                    return !Integer.valueOf(product.getId()).equals(value);
                case "name":
                    return !product.getName().equals(value);
                case "price":
                    return !Double.valueOf(product.getPrice()).equals(value);
                case "quantity":
                    return !Integer.valueOf(product.getQuantity()).equals(value);
                default:
                    return true;
            }
        });

        return results;
    }

    public void printResults(ArrayList<Product> products, Scanner scanner, String caller) {
        for (int i = 0; i < products.size(); i++) {
            String itemNumber = String.format("%02d", (i + 1));

            System.out
                    .println(
                            (itemNumber + ". " + products.get(i).getName()) + " (ID: " + products.get(i).getId() + ")");
            System.out.println("    " + products.get(i).getDescription());
            System.out.println(
                    "    $" + products.get(i).getPrice() + " (In Stock: " + products.get(i).getQuantity() + ")");

            if (caller == "admin") {
                Seller seller = userDAO.getSellerById(products.get(i).getSellerId());
                System.out.println();
                System.out.println("    Seller Information:");
                System.out.println("    " + seller.getStoreName() + " (Seller ID: " + seller.getSellerId() + ")");
                System.out.println("    \"" + seller.getStoreDescription() + "\"");
                System.out.println("    " + seller.getContactNumber() + ", " + seller.getStoreEmail());
                System.out.println("    " + seller.getAddress());
                System.out.println("    " + seller.getUrl());
            } else if (caller == "buyer") {
                System.out.println("    Sold by " + products.get(i).getSellerName());
            }

            System.out.println();
        }

        if (caller == "delete" || caller == "update") {
            return;
        } else {
            System.out.print("Press enter to return to previous menu...");
            scanner.nextLine();
        }
    }
}