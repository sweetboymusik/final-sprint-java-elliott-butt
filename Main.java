import java.util.ArrayList;

import Products.Product;
import Products.ProductService;

public class Main {
    public static void main(String[] args) {
        ProductService dbConnect = new ProductService();

        Product newProd = new Product();
        newProd.setName("Orange");
        newProd.setPrice(10.45);
        newProd.setQuantity(94);
        newProd.setSellerId(103);

        dbConnect.addProduct(newProd);

        ArrayList<Product> products = dbConnect.getProductsBySellerId(103);

        System.out.println(products);
    }
}
