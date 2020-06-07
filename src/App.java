import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class App {
    private final String url = "jdbc:postgresql://dehncserver.postgres.database.azure.com/postgres";
    private final String user = "dehnc@dehncserver";
    private final String password = "kodkodkod1#";
    private Map<String, String> types;
    public Connection connection;
    public Statement statement;
    public App()
    {
        connection=connect();
        statement=stat(connection);
        types=new HashMap<>();
        types.put("film","ssisis");
        types.put("type","is");
        types.put("episode","ssiiis");
        types.put("series","ssisi");
        types.put("season","issisi");
        types.put("member","ss");
        types.put("team","iiis");
        types.put("character","iiis");
        types.put("genre","ss");
        types.put("film_genre","iii");
        types.put("db_user","ssss");
        types.put("list","is");
        types.put("list_content","iii");
        types.put("mark","iiiis");
        types.put("language","s");
        types.put("film_language","iisis");
        types.put("studio","sis");
        types.put("film_studio","iii");
        types.put("country","s");
        types.put("film_country","iii");


        new Window(570,380,this);
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

    public Map<String, String> getTypes() {
        return types;
    }

    public ResultSet Find(String type, String name){
        ResultSet set=null;
        try{
            type=type.toLowerCase();
            if(type.equals("film")) set=statement.executeQuery("SELECT * FROM find_film('"+name+"')");
            if(type.equals("episode")) set=statement.executeQuery("SELECT * FROM find_episode('"+name+"')");
            if(type.equals("season")) set=statement.executeQuery("SELECT * FROM find_season('"+name+"')");
            if(type.equals("series")) set=statement.executeQuery("SELECT * FROM find_series('"+name+"')");
            else set=statement.executeQuery("SELECT 7");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return set;
    }

    public void Update(String s, String[] args) {
        try {
            int k=0;
            s=s.toLowerCase();
            String insert="INSERT INTO "+s;
            k=types.get(s).length();
            if(s.equals("film"))insert+="(name,description,year,release_date,length,colour)";
            else if(s.equals("type"))insert+="(type,name)";
            else if(s.equals("episode"))insert+="(name,description, length,number,season_id,colour)";
            if(s.equals("series"))insert+="(name,description,year,release_date,seasons)";
            if(s.equals("season"))insert+="(series_id,name,description, year,release_date, episodes)";
            if(s.equals("member"))insert+="(name, sex)";
            if(s.equals("team"))insert+="";
            if(s.equals("character"))insert+="";
            if(s.equals("genre"))insert+="(name,description)";
            if(s.equals("film_genre"))insert+="(film_id,type,genre_id)";
            if(s.equals("db_user"))insert+="(name, mail, login, password)";
            if(s.equals("list"))insert+="(user_id,title)";
            if(s.equals("list_content"))insert+="(list_id,film_id,type)";
            if(s.equals("mark"))insert+="(film_id, type, value, user_id, description)";
            if(s.equals("language"))insert+="(language)";
            if(s.equals("film_language"))insert+="(film_id,type,is_silent,language_id,form";
            if(s.equals("studio"))insert+="(name,establishment_year,is_active)";
            if(s.equals("film_studio"))insert+="(film_id,type,studio_id)";
            if(s.equals("country"))insert+="(country)";
            if(s.equals("film_country"))insert+="(film_id,type,country_id)";
            insert+=" VALUES (";
            String t=types.get(s);
            for(int i=0; i<k-1; i++){
                if(t.charAt(i)=='s') insert+="'"+args[i]+"', ";
                else insert+=args[i]+", ";
            }
            if(t.charAt(k-1)=='s') insert+="'"+args[k-1]+"'";
            else insert+=args[k-1];
            insert+=");";
            System.out.println(insert);
            statement.executeUpdate(insert);
            System.out.println("Update successfully.");
        } catch (SQLException e) {
            System.out.println("POSTGRESQL:"+e.getMessage());
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
    public void Print(ResultSet rs) {
        try {
            if (rs==null)
            {
                return;
            }
            System.out.println("--\n");
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();

            while (rs.next()) {
                for(int i = 1 ; i <= columnsNumber; i++){
                    System.out.print(rs.getString(i) + " | ");
                }
                System.out.println();
            }
            System.out.println("--\n");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) {
        App app = new App();
        //app.Update("SEaSon",new String[]{"1","Season 1"," ","2016","2016-07-15","8"});
        String[] sql = {"Select * from Film;", "Select * from series;", "Select * from season;"};
        for(int i=0; i<sql.length;i++) app.Print(app.Select(sql[i]));


    }
}