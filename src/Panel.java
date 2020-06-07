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


        button[addButoon("New Film")].addActionListener(new ButtonAdd("Film"));
        button[addButoon("New Series")].addActionListener(new ButtonAdd("Series"));

        button[addButoon("Add Season")].addActionListener(new ButtonAdd("Season"));
        button[addButoon("Add Episode")].addActionListener(new ButtonAdd("Episode"));
        button[addButoon("Add Genre")].addActionListener(new ButtonAdd("Genre"));
        button[addButoon("Add Studio")].addActionListener(new ButtonAdd("Studio"));

        button[addButoon("Add Country")].addActionListener(new ButtonAdd("Country"));
        button[addButoon("Add Language")].addActionListener(new ButtonAdd("Language"));
        button[addButoon("Add Team")].addActionListener(new ButtonAdd("Team"));
        button[addButoon("Add User")].addActionListener(new ButtonAdd("db_user"));
        button[addButoon("Add Studio")].addActionListener(new ButtonAdd("Studio"));

        addButoon("Add Team");

    }

    public int addButoon(String s)
    {
        button[j] =new JButton(s);
        button[j].setBounds(15+135*(j%4),12 + 62*(j/4),120,50);
        add(button[j]);
        return j++;
    }

    class ButtonAdd implements ActionListener
    {

        private String title;
        ButtonAdd(String s)
        {
            title=s;
        }
        @Override
        public void actionPerformed(ActionEvent e) {

            new Window(330,500, new AddPanel(title),"AddFilm");
            //JOptionPane.showMessageDialog(null,"test","out",JOptionPane.PLAIN_MESSAGE);
        }
    }
}
