package Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Database.DatabaseConnection;

public class UserDAO {
    // get seller by ID
    public Seller getSellerById(int id) {
        String sql = "SELECT * FROM sellers WHERE id = ?";
        Seller seller = new Seller();

        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                seller.setId(rs.getInt("id"));
                seller.setUsername("username");
                seller.setEmail("email@example.com");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return seller;

    }
}
