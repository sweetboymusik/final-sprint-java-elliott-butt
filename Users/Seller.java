package Users;

public class Seller extends User {
    // constructors
    public Seller(String username, String password, String email) {
        super(username, password, email, "seller");
    }
}
