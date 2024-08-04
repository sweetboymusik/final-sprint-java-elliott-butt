package Products;

import java.util.ArrayList;

public class ProductService {
    // instance variables
    private ProductDAO productDAO;

    // constructors
    public ProductService() {
        this.productDAO = new ProductDAO();
    }

    // methods
    public void addProduct(Product product) {
        productDAO.addProduct(product);
    }

    public void getAllProducts() {
        productDAO.getAllProducts();
    }

    public ArrayList<Product> getProductsBySellerId(int sellerId) {
        return productDAO.getProductsBySellerId(sellerId);
    }
}