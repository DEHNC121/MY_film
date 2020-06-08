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




        addButoon("Film");
        addButoon("Series");
        addButoon("Genre");
        addButoon("Studio");
        addButoon("Country");
        addButoon("Language");
        addButoon("Db_user");
        addButoon("Studio");
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
        addFuncButton("find film","find_film",'s');

        addButoonList("film","List of films");

    }

    public int addFuncButton(String t, String f, char type){
        button[j]=new JButton(t);
        button[j].setBounds(15 + 135 * (j % 4), 12 + 62 * (j / 4), 120, 50);
        ActionListener le = (ActionEvent e) ->
        {
            new Window(350, 500, new FuncPanel(t,f,type), t);
        };
        button[j].addActionListener(le);
        add(button[j]);
        return j++;
    }


    public int addButoon(String s1) {
        button[j] = new JButton(s1 );
        button[j].setBounds(15 + 135 * (j % 4), 12 + 62 * (j / 4), 120, 50);
        ActionListener le = (ActionEvent e) ->
        {
            new Window(300, 115, new APanel(s1), s1);
        };
        button[j].addActionListener(le);
        add(button[j]);
        return j++;
    }

    public int addButoonList(String type, String title)
    {
        button[j] =new JButton(title);
        button[j].setBounds(15+135*(j%4),12 + 62*(j/4),120,50);
        ActionListener le=(ActionEvent e) ->
        {
            new Window(600,1000, new JScrollPane(new FilmListPanel(type)),"List of films");
        };
        button[j].addActionListener(le);
        add(button[j]);
        return j++;
    }


}
