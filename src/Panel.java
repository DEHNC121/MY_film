import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Panel extends JPanel {

    JButton button[];
    int j;
    public Panel(){
        setLayout(null);
        button =new JButton[40];
        j=0;

        addButoon("All");
        addButoon("Rating");
        addButoon("Rating films");
        addButoon("Rating series");

        addButoon("Film");
        addButoon("Series");
        addButoon("Genre");
        addButoon("Studio");
        addButoon("Country");
        addButoon("Language");
        addButoon("Db_user");
        addButoon("Member");

        addButoon("Season");
        addButoon("Episode");
        addButoon("Character");

        addButoon("Team");
        addButoon("Mark");
        addButoon("List");
        addButoon("List_content");
        addButoon("Film_genre");
        addButoon("Film_Studio");
        addButoon("Film_country");
        addButoon("Film_language");

        addFuncButton("user's ratings","user_ratings",'s',"Give user by name and get ratings");
        addFuncButton("actors by film","film_actors",'s',"Give film title and get actors who played in");
        addFuncButton("actors by series","series_actors",'s',"Give series title and get actors who played in");
        addFuncButton("film by name","find_film",'s',"Give actors name and get films titles");
        addFuncButton("series by name","find_series",'s',"Give actors name and get series titles");
        addFuncButton("season by name","find_season",'s',"Give actors name and get seasons titles");
        addFuncButton("episode by name","find_episode",'s',"Give actors name and get episodes titles");

        addFuncButton("characters played by","characters_played",'s',"Give actors name and get characters that they played");
        addFuncButton("films by year","films_from",'s',"Give year and get films produced that time");
    }

    public int addFuncButton(String t, String f, char type, String s){
        button[j]=new JButton(t);
        button[j].setBounds(15 + 160 * (j % 4), 12 + 62 * (j / 4), 150, 50);
        ActionListener le = (ActionEvent e) -> {
            JLabel l=new JLabel(s);
            new Window(400, 200, new FuncPanel(t,f,type,s), t);
        };
        button[j].addActionListener(le);
        add(button[j]);
        return j++;
    }
// all

    public int addButoon(String s1) {
        button[j] = new JButton(s1);
        button[j].setBounds(15 + 160 * (j % 4), 12 + 62 * (j / 4), 150, 50);
        ActionListener le = (ActionEvent e) -> {
            if  (s1.equals("All"))
            {
                new Window(750,800, new JScrollPane(new FilmListPanel("all_content_names","")),"List of films");
            }else if (s1.equals("Rating")){
                new Window(750,800, new JScrollPane(new FilmListPanel("all_ratings","")),"List of films");

            }else if (s1.equals("Rating films")){
                new Window(750,800, new JScrollPane(new FilmListPanel("film_ratings","")),"List of films");

            }else if (s1.equals("Rating series")){

                new Window(750,800, new JScrollPane(new FilmListPanel("series_ratings","")),"List of films");

            }else
            new Window(435, 115, new APanel(s1), s1);
        };
        button[j].addActionListener(le);
        add(button[j]);
        return j++;
    }

}
