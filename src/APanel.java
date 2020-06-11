import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class APanel extends JPanel {

    JButton button[];
    int j=0;
    public APanel(String title){
        setLayout(null);
        button =new JButton[2];


        addButoon("See",title);
        addButoon("New",title);

    }


    public void addButoon(String s,String title) {
        if (s.equals("See"))
        {
            button[j] = new JButton(s);
            button[j].setBounds(15 + 135 * (j % 4), 12 + 62 * (j / 4), 120, 50);
            ActionListener le = (ActionEvent e) ->
            {
                new Window(600, 800, new FilmListPanel(title), s);
            };
            button[j].addActionListener(le);
            add(button[j]);
        }else
            {
                button[j] = new JButton(s);
                button[j].setBounds(15 + 135 * (j % 4), 12 + 62 * (j / 4), 120, 50);
                ActionListener le = (ActionEvent e) -> {
                    new Window(350, 500, new AddPanel(title), s);
                };
                button[j].addActionListener(le);
                add(button[j]);
            }

        j++;
    }



}
