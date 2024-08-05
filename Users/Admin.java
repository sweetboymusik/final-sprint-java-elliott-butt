package Users;

public class Admin extends User {
    // constructors
    public Admin(String username, String password, String email) {
        super(username, password, email, "admin");
    }
}
