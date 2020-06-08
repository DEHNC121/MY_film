import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Panel extends JPanel {

    JButton button[];
    int j;
    public Panel(){
        setLayout(null);
        button =new JButton[16];
        j=0;




        addButoon("New","Film");
        addButoon("New" ,"Series");

        addButoon("Add","Season");
        addButoon("Add","Episode");
        addButoon("Add","Genre");
        addButoon("Add","Studio");

        addButoon("Add","Country");
        addButoon("Add","Language");
        addButoon("Add","Team");
        addButoon("Add","Db_user");
        addButoon("Add","Studio");
        addButoon("Add","Add Team");

        ActionListener le=(ActionEvent e) ->
        {
            new Window(600,1000, new FilmListPanel("List of films"),"List of films");
        };
        button[addButoon("Add","List of films")].addActionListener(le);

    }

    public int addButoon(String s1,String s2)
    {
        button[j] =new JButton(s1+" "+s2);
        button[j].setBounds(15+135*(j%4),12 + 62*(j/4),120,50);
        ActionListener le=(ActionEvent e) ->
        {
            new Window(330,500, new AddPanel(s2),s1+s2);
        };
        button[j].addActionListener(le);
        add(button[j]);
        return j++;
    }

    class ButtonFilm implements ActionListener{
        private String title;
        ButtonFilm(String s) {
            title=s;
        }
        @Override
        public void actionPerformed(ActionEvent e) {

            new Window(600,1000, new FilmListPanel(title),"List of films");
        }
    }


}
