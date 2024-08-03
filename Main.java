import Products.ProductDAO;

public class Main {
    public static void main(String[] args) {
        ProductDAO dbConnect = new ProductDAO();

        dbConnect.getAllProducts();
    }

}
