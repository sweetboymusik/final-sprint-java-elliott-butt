package Products;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Database.DatabaseConnection;

public class ProductDAO {
    public ProductDAO() {
    };

    // methods
    public void addProduct(Product product) {
    }

    public ArrayList<Product> getProductsBySellerId(int sellerId) {
        return new ArrayList<>();
    }

    public void getAllProducts() {
        String sql = "SELECT *  FROM products";

        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                int sellerId = rs.getInt("seller_id");

                System.out.printf("%-5d %-25s %-10.2f %-10d %-10d%n", id, name, price, quantity, sellerId);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}