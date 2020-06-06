import java.sql.*;

public class App {
    private final String url = "jdbc:postgresql://dehncserver.postgres.database.azure.com/postgres";
    private final String user = "dehnc@dehncserver";
    private final String password = "kodkodkod1#";

    public Connection connection;
    public Statement statement;

     public App()
     {
         connection=connect();
         statement=stat(connection);
     }

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


    public void Update(String s) {
        try {

            statement.executeUpdate(s);
            System.out.println("Update successfully.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public ResultSet Select(String s) {
        ResultSet set=null;
        try {

            set=statement.executeQuery(s);
            System.out.println("Select successfully.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return set;
    }
    public void Print(ResultSet set) {
        try {


            System.out.print("----");
            while (set.next())
            {

                System.out.print(set);
            }
            System.out.print("\n----");
            System.out.print("\nDone");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    public static void main(String[] args) {
        App app = new App();



        String sql = "Select * from x;";

        app.Print(app.Select(sql));


    }
}
