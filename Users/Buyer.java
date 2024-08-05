package Users;

public class Buyer extends User {
    // constructors
    public Buyer(String username, String password, String email) {
        super(username, password, email, "buyer");
    }
}
