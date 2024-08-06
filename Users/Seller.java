package Users;

public class Seller extends User {
    // constructors
    public Seller() {
        super("seller");
    }

    public Seller(int id, String username, String password, String email) {
        super(id, username, password, email, "seller");
    }
}
