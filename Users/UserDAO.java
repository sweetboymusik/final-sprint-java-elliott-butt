package Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Database.DatabaseConnection;

public class UserDAO {
    // INSERT - add a user
    public void addUser(User user) {
        String sql = "INSERT INTO public.users(\n" +
                "\tusername, password, email, role)\n" +
                "\tVALUES (?, ?, ?, ?);";

        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getRole());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // DELETE - delete a user
    public void deleteUser(User user) {
        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // INSERT - add seller information
    public void addSellerInformation(Seller user, int userId) {
        String sql = "INSERT INTO public.seller_information(\n" +
                "\tuser_id, store_name, store_description, contact_number, email, website_url, address)\n" +
                "\tVALUES (?, ?, ?, ?, ?, ?, ?);";

        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, user.getStoreName());
            preparedStatement.setString(3, user.getStoreDescription());
            preparedStatement.setString(4, user.getContactNumber());
            preparedStatement.setString(5, user.getStoreEmail());
            preparedStatement.setString(6, user.getUrl());
            preparedStatement.setString(7, user.getAddress());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // SELECT - get all sellers
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

    // SELECT - get all users
    public ArrayList<User> getAllUsers() {
        String sql = "SELECT * FROM users";

        ArrayList<User> users = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                User user;

                if (rs.getString("role") == "admin") {
                    user = new Admin();
                } else if (rs.getString("role") == "buyer") {
                    user = new Buyer();
                } else {
                    user = new Seller();
                }

                user.setId(rs.getInt("id"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setRole(rs.getString("role"));

                users.add(user);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return users;
    }

    // SELECT - get seller by ID
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

    // SELECT - search function using generics
    public <T> ArrayList<User> searchUsers(String field, T value) {
        ArrayList<User> users = new ArrayList<>();

        String sql = "SELECT\n" +
                "\tusers.id,\n" +
                "\tusername,\n" +
                "\tpassword,\n" +
                "\tusers.email,\n" +
                "\trole,\n" +
                "\tseller_information.id AS seller_ID\n" +
                "FROM users\n" +
                "LEFT JOIN\n" +
                "\tseller_information\n" +
                "\tON users.id = seller_information.user_id\n" +
                "WHERE  ";

        if (field == "users.username") {
            sql += field + " ilike ?";
        } else if (field == "1") {
            sql += Integer.parseInt(field) + " = ?";
        } else {
            sql += field + " = ?";
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            if (field == "users.username") {
                preparedStatement.setObject(1, "%" + value + "%");
            } else {
                preparedStatement.setObject(1, value);
            }

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String role = rs.getString("role");
                int sellerId = rs.getInt("seller_id");

                switch (role) {
                    case "admin":
                        users.add(new Admin(id, username, password, email));
                        break;
                    case "buyer":
                        users.add(new Buyer(id, username, password, email));
                        break;
                    case "seller":
                        users.add(new Seller(id, username, password, email, sellerId));
                        break;
                    default:
                        break;
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return users;
    }
}
