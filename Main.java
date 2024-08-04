import java.util.ArrayList;

import Products.Product;
import Products.ProductService;

public class Main {
    public static void main(String[] args) {
        ProductService dbConnect = new ProductService();
        ArrayList<Product> products = dbConnect.getAllProducts();
        System.out.println(products);
    }
}
