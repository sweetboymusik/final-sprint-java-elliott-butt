package Users;

public class Buyer extends User {
    // constructors
    public Buyer() {
        super("buyer");
    }

    public Buyer(int id, String username, String password, String email) {
        super(id, username, password, email, "buyer");
    }
}
