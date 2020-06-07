import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Panel extends JPanel {

    JButton button[];
    int j;
    public Panel(){
        setLayout(null);
        button =new JButton[15];
        j=0;


        button[addButoon("New Film")].addActionListener(new ButtonFilm());
        button[addButoon("New Series")].addActionListener(new ButtonAdd());

        addButoon("Add Season");
        addButoon("Add Series");
        addButoon("Add Genre");
        addButoon("Add Studio");

        addButoon("Add Country");
        addButoon("Add Language");
        addButoon("Add Team");
        addButoon("Add User");
        addButoon("Add Studio");

        addButoon("Add Team");

    }

    public int addButoon(String s)
    {
        button[j] =new JButton(s);
        button[j].setBounds(15+135*(j%4),12 + 62*(j/4),120,50);
        add(button[j]);
        return j++;
    }

    class ButtonFilm implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {

            new Window(330,500, new NewFilmPanel("film"),"AddFilm");
            //JOptionPane.showMessageDialog(null,"test","out",JOptionPane.PLAIN_MESSAGE);
        }
    }
    class ButtonAdd implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {

            new Window(330,500, new AddPanel("series"),"AddFilm");
            //JOptionPane.showMessageDialog(null,"test","out",JOptionPane.PLAIN_MESSAGE);
        }
    }
}
