package Products;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Database.DatabaseConnection;

public class ProductDAO {
    // add a product
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

    // update a product
    public void updateProduct(Product product) {
        String sql = "UPDATE public.products SET name=?, price=?, quantity=?, seller_id=? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setInt(4, product.getSellerId());
            preparedStatement.setInt(5, product.getId());

            preparedStatement.executeUpdate();
            System.out.println("Item added.");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // delete a product
    public void deleteProduct(Product product) {
        String sql = "DELETE FROM public.products WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setInt(1, product.getId());
            preparedStatement.executeUpdate();

            System.out.println("Item deleted.");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // search function using generics
    public <T> ArrayList<Product> searchProducts(String field, T value) {
        ArrayList<Product> products = new ArrayList<>();

        String sql = "SELECT\n" +
                "products.id, products.name, price, quantity, seller_id, sellers.name AS seller\n" +
                "FROM\n" +
                "products\n" +
                "JOIN\n" +
                "sellers\n" +
                "ON sellers.id = seller_id\n" +
                "WHERE ";

        if (field == "products.name" | field == "sellers.name") {
            sql += field + " ilike ?";
        } else if (field == "1") {
            sql += Integer.parseInt(field) + " = ?";
        } else {
            sql += field + " = ?";
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            if (field == "products.name" | field == "sellers.name") {
                preparedStatement.setObject(1, "%" + value + "%");
            } else {
                preparedStatement.setObject(1, value);
            }

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                int sellerId = rs.getInt("seller_id");
                String sellerName = rs.getString("seller");

                products.add(new Product(id, name, price, quantity, sellerId, sellerName));
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return products;
    }
}