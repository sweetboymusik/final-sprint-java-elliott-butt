package Products;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import Database.DatabaseConnection;

public class ProductDAO {
    // methods
    public void addProduct(Product product) {
        String sql = "INSERT INTO public.products(\n" +
                "\tname, price, quantity, seller_id)\n" +
                "\tVALUES (?, ?, ?, ?);";

        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setInt(4, product.getSellerId());

            preparedStatement.executeUpdate();
            System.out.println("Item added.");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ArrayList<Product> getProductsBySellerId(int sellerId) {
        ArrayList<Product> products = new ArrayList<>();
        String sql = "SELECT *  FROM products WHERE seller_id = ?";

        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, sellerId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Product product = new Product();

                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setSellerId(rs.getInt("seller_id"));

                products.add(product);
            }

            return products;
        } catch (Exception e) {
            System.out.println(e);
        }

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
            System.out.println(e);
        }
    }
}