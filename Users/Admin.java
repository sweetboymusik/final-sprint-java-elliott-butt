package Users;

public class Admin extends User {
    // constructors
    public Admin() {
        super("admin");
    }

    public Admin(String username, String password, String email) {
        super(username, password, email, "admin");
    }

    public Admin(int id, String username, String password, String email) {
        super(id, username, password, email, "admin");
    }
}
