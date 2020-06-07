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
        addButoon("New Series");

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

            Window.AddW(new NewFilmPanel());
            //JOptionPane.showMessageDialog(null,"test","out",JOptionPane.PLAIN_MESSAGE);
        }
    }
}
