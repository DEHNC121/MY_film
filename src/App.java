import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String user = "postgres";
    private final String password = "kodkod";

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

    public Statement stat(Connection conn) {
        Statement statement = null;
        try {

            statement=conn.createStatement();
            System.out.println("Statement successfully.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return statement;
    }

    public static void main(String[] args) {
        App app = new App();
        Connection connection=app.connect();

        String sql = "CREATE TABLE REGISTRATION " +
                "(id INTEGER not NULL, " +
                " first VARCHAR(255), " +
                " last VARCHAR(255), " +
                " age INTEGER, " +
                " PRIMARY KEY ( id ))";
    }
}
