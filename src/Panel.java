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




        addButoon("New","Film");
        addButoon("New","Series");
        addButoon("New","Genre");
        addButoon("New","Studio");
        addButoon("New","Country");
        addButoon("New","Language");
        addButoon("New","Db_user");
        addButoon("New","Studio");
        addButoon("New","Member");

        addButoon("Add","Season");
        addButoon("Add","Episode");
        addButoon("Add","Character");

        addButoon("Add","Team");
        addButoon("Add","Mark");
        addButoon("Add","List");
        addButoon("List_content");
        addButoon("Film_genre");
        addButoon("Film_Studio");
        addButoon("Film_country");
        addButoon("Film_language");

        addButoonList("film","List of films");

    }

    public int addButoon(String s1,String s2) {
        button[j] = new JButton(s1 + " " + s2);
        button[j].setBounds(15 + 135 * (j % 4), 12 + 62 * (j / 4), 120, 50);
        ActionListener le = (ActionEvent e) ->
        {
            new Window(350, 500, new AddPanel(s2), s1 + s2);
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
            new Window(350, 500, new AddPanel(s1), s1);
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
