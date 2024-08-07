package Users;

import java.util.ArrayList;
import java.util.Scanner;

public class UserService {
    // instance variables
    private UserDAO userDAO;

    // constructors
    public UserService() {
        userDAO = new UserDAO();
    }

    // methods
    public void deleteUser(User user, Scanner scanner) {
        try {
            userDAO.deleteUser(user);

            System.out.println("\nUser deleted successfully!");
            System.out.println("Press enter to return to admin menu... ");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ArrayList<Seller> getAllSellers() {
        return userDAO.getAllSellers();
    }

    public ArrayList<User> getAllUsers() {
        return new ArrayList<>(userDAO.searchUsers("1", 1));
    }

    public ArrayList<User> getUsersById(int id) {
        return new ArrayList<>(userDAO.searchUsers("id", id));
    }

    public ArrayList<User> getUsersByUsername(String username) {
        return new ArrayList<>(userDAO.searchUsers("users.username", username));
    }

    public ArrayList<User> getUsersByRole(String role) {
        return new ArrayList<>(userDAO.searchUsers("role", role));
    }

    public void printUserResults(ArrayList<User> users, Scanner scanner, String caller) {
        for (int i = 0; i < users.size(); i++) {
            String itemNumber = String.format("%02d", (i + 1));

            System.out.println(itemNumber + ". User ID: " + users.get(i).getId());
            System.out.println("    " + users.get(i).getUsername());
            System.out.println("    " + users.get(i).getEmail());
            System.out.println("    " + users.get(i).getRole());
            System.out.println();
        }

        if (caller == "delete") {
            return;
        } else {
            System.out.print("Press enter to return to previous menu...");
            scanner.nextLine();
        }
    }

    public void printSellerResults(ArrayList<Seller> sellers, Scanner scanner) {
        for (int i = 0; i < sellers.size(); i++) {
            String itemNumber = String.format("%02d", (i + 1));

            System.out
                    .println(
                            (itemNumber + ". " + sellers.get(i).getStoreName()) + " (Seller ID: "
                                    + sellers.get(i).getSellerId() + ")");
            System.out.println("    \"" + sellers.get(i).getStoreDescription() + "\"");
            System.out.println("    " + sellers.get(i).getStoreEmail());
            System.out.println("    " + sellers.get(i).getContactNumber());
            System.out.println("    " + sellers.get(i).getAddress());
            System.out.println("    " + sellers.get(i).getUrl());
            System.out.println("    " + sellers.get(i).getUsername());
            System.out.println();
        }

        System.out.print("Press enter to return to previous menu...");
        scanner.nextLine();
    }
}
