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

        addFuncButton("user's ratings","user_ratings",'s');
        addFuncButton("actors by film","film_actors",'s');
        addFuncButton("actors by series","series_actors",'s');
        addFuncButton("film by name","find_film",'s');
        addFuncButton("series by name","find_series",'s');
        addFuncButton("season by name","find_season",'s');
        addFuncButton("episode by name","find_episode",'s');

        addFuncButton("characters played by","characters_played",'s');
        addFuncButton("films by year","films_from",'s');
    }

    public int addFuncButton(String t, String f, char type){
        button[j]=new JButton(t);
        button[j].setBounds(15 + 160 * (j % 4), 12 + 62 * (j / 4), 150, 50);
        ActionListener le = (ActionEvent e) ->
        {
            new Window(350, 500, new FuncPanel(t,f,type), t);
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
                new Window(600,800, new JScrollPane(new FilmListPanel("all_content_names")),"List of films");
            }else if (s1.equals("Rating")){
                new Window(600,800, new JScrollPane(new FilmListPanel("all_ratings")),"List of films");

            }else if (s1.equals("Rating films")){
                new Window(600,800, new JScrollPane(new FilmListPanel("film_ratings")),"List of films");

            }else if (s1.equals("Rating series")){

                new Window(600,800, new JScrollPane(new FilmListPanel("series_ratings")),"List of films");

            }else
            new Window(300, 115, new APanel(s1), s1);
        };
        button[j].addActionListener(le);
        add(button[j]);
        return j++;
    }

}
