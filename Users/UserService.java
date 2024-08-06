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

    public ArrayList<Seller> getAllSellers() {
        return userDAO.getAllSellers();
    }

    public void printResults(ArrayList<Seller> sellers, Scanner scanner) {
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
