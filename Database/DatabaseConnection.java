package Database;

import java.sql.*;

// connect to postgresql database
public class DatabaseConnection {
    private static final String url = "jdbc:postgresql://localhost:5432/ecommerce";
    private static final String user = "postgres";
    private static final String password = "postgres";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

}