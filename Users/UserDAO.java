package Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Database.DatabaseConnection;

public class UserDAO {
    public ArrayList<Seller> getAllSellers() {
        String sql = "SELECT \n" +
                "\tseller_information.id AS seller_id,\n" +
                "\tuser_id,\n" +
                "\tstore_name,\n" +
                "\tstore_description,\n" +
                "\tcontact_number,\n" +
                "\tseller_information.email AS store_email,\n" +
                "\twebsite_url,\n" +
                "\taddress,\n" +
                "\tusername,\n" +
                "\tusers.email AS user_email\n" +
                "FROM\n" +
                "\tseller_information\n" +
                "JOIN\n" +
                "\tusers\n" +
                "\tON users.id = seller_information.user_id\n";

        ArrayList<Seller> sellers = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Seller seller = new Seller();

                seller.setId(rs.getInt("user_id"));
                seller.setAddress(rs.getString("address"));
                seller.setStoreName(rs.getString("store_name"));
                seller.setStoreEmail(rs.getString("store_email"));
                seller.setSellerId(rs.getInt("seller_id"));
                seller.setStoreDescription(rs.getString("store_description"));
                seller.setContactNumber(rs.getString("contact_number"));
                seller.setUrl(rs.getString("website_url"));
                seller.setUsername(rs.getString("username"));
                seller.setEmail(rs.getString("user_email"));

                sellers.add(seller);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return sellers;
    }

    public Seller getSellerById(int id) {
        String sql = "SELECT \n" +
                "\tseller_information.id AS seller_id,\n" +
                "\tuser_id,\n" +
                "\tstore_name,\n" +
                "\tstore_description,\n" +
                "\tcontact_number,\n" +
                "\tseller_information.email AS store_email,\n" +
                "\twebsite_url,\n" +
                "\taddress,\n" +
                "\tusername,\n" +
                "\tusers.email AS user_email\n" +
                "FROM\n" +
                "\tseller_information\n" +
                "JOIN\n" +
                "\tusers\n" +
                "\tON users.id = seller_information.user_id\n" +
                "WHERE seller_information.id = ?";

        Seller seller = new Seller();

        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                seller.setId(rs.getInt("user_id"));
                seller.setAddress(rs.getString("address"));
                seller.setStoreName(rs.getString("store_name"));
                seller.setStoreEmail(rs.getString("store_email"));
                seller.setSellerId(rs.getInt("seller_id"));
                seller.setStoreDescription(rs.getString("store_description"));
                seller.setContactNumber(rs.getString("contact_number"));
                seller.setUrl(rs.getString("website_url"));
                seller.setUsername(rs.getString("username"));
                seller.setEmail(rs.getString("user_email"));
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return seller;
    }
}
