package Products;

import java.util.ArrayList;

public class ProductService {
    // instance variables
    private ProductDAO productDAO;

    // constructors
    public ProductService() {
        this.productDAO = new ProductDAO();
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

    public ArrayList<Product> getProductsByName(String name) {
        return productDAO.searchProducts("name", name);
    }

    public ArrayList<Product> getProductsById(int id) {
        return productDAO.searchProducts("id", id);
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
}