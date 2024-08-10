package Products;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Database.DatabaseConnection;

public class ProductDAO {
    // INSERT - add a product
    public void addProduct(Product product) {
        String sql = "INSERT INTO public.products(\n" +
                "\tname, description, category_id, price, quantity, seller_id)\n" +
                "\tVALUES (?, ?, ?, ?, ?, ?);";

        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setInt(3, product.getCategoryId());
            preparedStatement.setDouble(4, product.getPrice());
            preparedStatement.setInt(5, product.getQuantity());
            preparedStatement.setInt(6, product.getSellerId());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // UPDATE - update a product
    public void updateProduct(Product product) {
        String sql = "UPDATE public.products\n" +
                "\tSET name=?, description=?, price=?, quantity=?\n" +
                "\tWHERE id = ?;";

        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setInt(4, product.getQuantity());
            preparedStatement.setInt(5, product.getId());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // DELETE - delete a product
    public void deleteProduct(Product product) {
        String sql = "DELETE FROM public.products WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, product.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // SELECT - search function using generics
    public <T> ArrayList<Product> searchProducts(String field, T value) {
        ArrayList<Product> products = new ArrayList<>();

        String sql = "SELECT\n" +
                "products.id, products.name, description, category_id, categories.name AS category, price, quantity, seller_id, seller_information.store_name AS seller\n"
                +
                "FROM\n" +
                "products\n" +
                "JOIN\n" +
                "seller_information\n" +
                "ON seller_information.id = seller_id\n" +
                "JOIN\n" +
                "categories\n" +
                "ON categories.id = category_id\n" +
                "WHERE ";

        if (field == "products.name" | field == "seller_information.store_name") {
            sql += field + " ilike ?";
        } else if (field == "1") {
            sql += Integer.parseInt(field) + " = ?";
        } else {
            sql += field + " = ?";
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            if (field == "products.name" | field == "seller_information.store_name") {
                preparedStatement.setObject(1, "%" + value + "%");
            } else {
                preparedStatement.setObject(1, value);
            }

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String desc = rs.getString("description");
                String category = rs.getString("category");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                int sellerId = rs.getInt("seller_id");
                String sellerName = rs.getString("seller");

                products.add(new Product(id, name, desc, category, price, quantity, sellerId, sellerName));
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return products;
    }
}