import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class APanel extends JPanel {

    JButton button[];
    int j=0;
    public APanel(String title) {
        setLayout(null);
        button = new JButton[3];
        addButoon("See", title);
        addButoon("New", title);
        addButoon("Delete", title);
    }

    public void addButoon(String s,String title) {
        if (s.equals("See")) {
            button[j] = new JButton(s);
            button[j].setBounds(15 + 135 * (j % 4), 12 + 62 * (j / 4), 120, 50);
            ActionListener le = (ActionEvent e) -> {
                Window.Off();
                new Window(1000, 700, new FilmListPanel(title), s);
            };
            button[j].addActionListener(le);
            add(button[j]);
        }
        else if (s.equals("New")) {
            button[j] = new JButton(s);
            button[j].setBounds(15 + 135 * (j % 4), 12 + 62 * (j / 4), 120, 50);
            ActionListener le = (ActionEvent e) -> {
                Window.Off();
                if (Window.getApp().getOpen().get(title.toLowerCase())==null)
                {
                    new Window(350, 400, new AddPanel(title,""), s);
                }else
                {
                    ListSelect p=new ListSelect(title,"");

                    if (p.done)
                        new Window(600, 720,p, s);
                }
            };
            button[j].addActionListener(le);
            add(button[j]);
        }
        else if (s.equals("Delete")) {
            button[j] = new JButton(s);
            button[j].setBounds(15 + 135 * (j % 4), 12 + 62 * (j / 4), 120, 50);
            ActionListener le = (ActionEvent e) -> {
                Window.Off();
                ListDrop p = new ListDrop(title);

                if (p.done)
                    new Window(600, 720, p, s);
            };
            button[j].addActionListener(le);
            add(button[j]);
        }
        j++;
    }


}
