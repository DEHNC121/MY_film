

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class App {
    private Map<String, String> types;
    private Map<String, String> colTypes;
    private Map<String, String> open;
    public Connection connection;
    public Statement statement;
    public App()
    {
        open=new HashMap<>();
        open.put("season","series");
        open.put("mark","type");
        open.put("list","series");
        open.put("list_content","series");
        open.put("film_genre","series");
        open.put("film_studio","series");
        open.put("episode","season");
        open.put("film_country","series");
        open.put("film_language","series");
        open.put("team","series");
        open.put("character","series");


        connection=connect();
        statement=stat(connection);
        types=new HashMap<>();
        types.put("film","ssidib");
        types.put("type","is");
        types.put("episode","ssiiib");
        types.put("series","ssidi");
        types.put("season","Kssidi");
        types.put("member","ss");
        types.put("team","iiis");
        types.put("character","iiis");
        types.put("genre","ss");
        types.put("film_genre","iii");
        types.put("db_user","ssss");
        types.put("list","is");
        types.put("list_content","iii");
        types.put("mark","iibis");
        types.put("language","s");
        types.put("film_language","iisis");
        types.put("studio","sis");
        types.put("film_studio","iii");
        types.put("country","s");
        types.put("film_country","iii");

        colTypes=new HashMap<>();
        colTypes.put("film,id","name,description,year,release_date,length,colour");
        colTypes.put("type","type,name");
        colTypes.put("episode,id","name,description, length,number,season_id,colour");
        colTypes.put("series,id","name,description,year,release_date,seasons");
        colTypes.put("season,id","series_id,name,description,year,release_date,seasons");
        colTypes.put("member,id","name,sex");
        colTypes.put("team","film_id,type,member_id,position");
        colTypes.put("character","film_id,type,member_id,name");
        colTypes.put("genre,id","name,description");
        colTypes.put("film_genre","film_id,type,genre_id");
        colTypes.put("db_user,id","name,mail,login,password");
        colTypes.put("list,id","user_id,title");
        colTypes.put("list_content","list_id,film_id,type");
        colTypes.put("mark,id","film_id, type, value, user_id, description");
        colTypes.put("language,id","language");
        colTypes.put("film_language","film_id,type,is_silent,language_id,form");
        colTypes.put("studio,id","name,establishment_year,is_active");
        colTypes.put("film_studio","film_id,type,studio_id");
        colTypes.put("country,id","country");
        colTypes.put("film_country","film_id,type,country_id");

        colTypes.put("all_content_names","id,name,type");
        colTypes.put("all_ratings","name,rating");
        colTypes.put("film_ratings","name,rating");
        colTypes.put("series_ratings","name,rating");

        new Window(680,600,this);
    }
    public Connection connect() {
        Connection conn = null;
        try {
            String url = "jdbc:postgresql://dehncserver.postgres.database.azure.com/postgres";
            String user = "dehnc@dehncserver";
            String password = "kodkodkod1#";
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

    public Map<String, String> getTypes() { return types; }

    public Map<String, String> getColTypes() {
        return colTypes;
    }

    public Map<String,String> getOpen(){ return open; }

    public String Update(String s, String[] args) {
        String end=null;
        try {
            int k=0;
            k=types.get(s).length();
            s=s.toLowerCase();
            String insert="INSERT INTO "+s+"(";
            if  (colTypes.get(s)==null)
            {
                insert+=colTypes.get(s+",id");
            }else {

                    insert+=colTypes.get(s);
            }
            insert+=") VALUES (";
            String t=types.get(s);
            for(int i=0; i<k-1; i++){
                if(t.charAt(i)=='s' || t.charAt(i)=='d') insert+="'"+args[i]+"', ";
                else insert+=args[i]+", ";
            }
            if(t.charAt(k-1)=='s' || t.charAt(k-1)=='d') insert+="'"+args[k-1]+"'";
            else insert+=args[k-1];
            insert+=");";
            end=insert;
            statement.executeUpdate(insert);
            return end+"\nUpdate successfully!!!";
        } catch (SQLException e) {
            return end+"\nPOSTGRESQL:"+e.getMessage();
        }
    }

    public ResultSet Select(String s) {
        ResultSet set=null;
        System.out.println(s);
        try {
            set=statement.executeQuery(s);
            System.out.println("Select successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return set;
    }
    public ArrayList<String> Print(ResultSet rs) {
        ArrayList<String> s=new ArrayList<>();
        try {
            if (rs==null)return s;
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();

            while (rs.next()) {
                String row="";
                for(int i = 1 ; i <= columnsNumber; i++){
                    row+=rs.getString(i) + " | ";
                }
                s.add(row);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return s;
    }
    public static void main(String[] args) {
        App app = new App();

    }
}