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
    public void addProduct(Product product) {
        productDAO.addProduct(product);
    }

    public void updateProduct(Product product) {
        productDAO.updateProduct(product);
    }

    public void deleteProduct(Product product) {
        productDAO.deleteProduct(product);
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
            System.out.println("    Sold by " + products.get(i).getSellerName());

            if (caller == "admin") {
                Seller seller = userDAO.getSellerById(products.get(i).getId());
                System.out.println("    Seller ID:  " + seller.getId());
                System.out.println("    Seller Email:   " + seller.getEmail());
            }

            System.out.println();
        }

        System.out.print("Press enter to return to previous menu...");
        scanner.nextLine();
    }
}