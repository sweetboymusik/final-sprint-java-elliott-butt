package Users;

import java.util.ArrayList;

import Products.Product;

public class Seller extends User {
    // instance variables
    private String sellerID;
    private ArrayList<Product> products;

    // constructors
    public Seller(String username, String password, String email, String sellerID) {
        super(username, password, email);
        this.sellerID = sellerID;
        products = new ArrayList<Product>();
    }

    // methods
    public void addProduct(String name, double price, int quantity) {
        // add new product
    }

    public void updateProduct() {
    }

    public void deleteProduct() {
    }

    public void viewAllProducts() {
    }
}
