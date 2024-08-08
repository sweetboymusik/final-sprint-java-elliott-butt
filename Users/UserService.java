package Users;

import java.util.ArrayList;
import java.util.Scanner;

import org.mindrot.jbcrypt.BCrypt;

public class UserService {
    // instance variables
    private UserDAO userDAO;

    // constructors
    public UserService() {
        userDAO = new UserDAO();
    }

    // methods
    public User authUser(String username, String password, Scanner scanner) {
        if (username == null || password == null) {
            System.out.println();
            System.out.println("User does not exist");
            System.out.print("Press enter to return to main menu... ");
            scanner.nextLine();
            return null;
        }

        ArrayList<User> users = userDAO.searchUsers("users.username", username);

        if (users.size() == 0) {
            System.out.println();
            System.out.println("No user with the username '" + username + "'.");
            System.out.print("Press enter to return to main menu... ");
            scanner.nextLine();

            return null;
        }

        User user = users.get(0);

        if (!BCrypt.checkpw(password, user.getPassword())) {
            System.out.println();
            System.out.println("Incorrect password.");
            System.out.print("Press enter to return to main menu... ");
            scanner.nextLine();

            return null;
        }

        return user;
    }

    public boolean registerUser(User user, Scanner scanner) {
        if (user.equals(null)) {
            System.out.println("User Is Null");
            System.out.print("Press enter to return to main menu... ");
            scanner.nextLine();

            return false;
        }

        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);
        userDAO.addUser(user);

        return true;
    }

    public void deleteUser(User user, Scanner scanner) {
        try {
            userDAO.deleteUser(user);

            System.out.println("\nUser deleted successfully!");
            System.out.print("Press enter to return to admin menu... ");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean registerSeller(Seller user, Scanner scanner) {
        if (user.equals(null)) {
            System.out.println("User Is Null");
            System.out.print("Press enter to return to main menu... ");
            scanner.nextLine();

            return false;
        }

        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);

        userDAO.addUser(user);
        int id = getUsersByUsername(user.getUsername()).get(0).getId();
        userDAO.addSellerInformation(user, id);

        return true;
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
