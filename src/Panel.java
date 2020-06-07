import javax.swing.*;

public class Panel extends JPanel {

    JButton button[];
    int j;
    public Panel(){
        setLayout(null);
        button =new JButton[15];
        j=0;

        addButoon("New Film");
        addButoon("New Series");
    }

    public void addButoon(String s)
    {
        button[j] =new JButton(s);
        button[j].setBounds(15+135*j,10,120,50);
        add(button[j]);
        j++;
    }
}
