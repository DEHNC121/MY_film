import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Panel extends JPanel {

    JButton button[];
    int j;
    public Panel(){
        setLayout(null);
        button =new JButton[25];
        j=0;




        addButoon("New","Film");
        addButoon("New" ,"Series");
        addButoon("New","Genre");
        addButoon("New","Studio");
        addButoon("New","Country");
        addButoon("New","Language");
        addButoon("New","Db_user");
        addButoon("New","Studio");
        addButoon("New","Member");

        addButoon("Add","Season","Series");
        addButoon("Add","Episode","Season");
        addButoon("Add","Character","Type");

        addButoon("Add","Team","Film");
        addButoon("Add","Mark","Type");
        addButoon("Add","List","Db_user");

        addButoon("Add","List_content","Db_user");



        addButoon("List of films");

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

    public int addButoon(String s1,String s2,String s3) {
        button[j] = new JButton(s1 + " " + s2);
        button[j].setBounds(15 + 135 * (j % 4), 12 + 62 * (j / 4), 120, 50);
        ActionListener le = (ActionEvent e) ->
        {
            new Window(600,1000, new FilmListPanel("List of "+s3),"List of "+s3);
        };
        button[j].addActionListener(le);
        add(button[j]);
        return j++;
    }

    public int addButoon(String s1,String s2,String s3,String s4) {
        button[j] = new JButton(s1 + " " + s2);
        button[j].setBounds(15 + 135 * (j % 4), 12 + 62 * (j / 4), 120, 50);
        ActionListener le = (ActionEvent e) ->
        {
            new Window(600,1000, new FilmListPanel("List of "+s3),"List of "+s3);
        };
        button[j].addActionListener(le);
        add(button[j]);
        return j++;
    }

    public int addButoon(String s2)
    {
        button[j] =new JButton(s2);
        button[j].setBounds(15+135*(j%4),12 + 62*(j/4),120,50);
        ActionListener le=(ActionEvent e) ->
        {
            new Window(600,1000, new FilmListPanel("List of films"),"List of films");
        };
        button[j].addActionListener(le);
        add(button[j]);
        return j++;
    }


}
