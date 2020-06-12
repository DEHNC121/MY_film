import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FuncPanel extends JPanel{
    private JTextField input;
    private JLabel label;
    private Font font = new Font("SanSerif",Font.BOLD,20);

    public FuncPanel(String t, String funcName, char type,String description){
        setLayout(null);

        JLabel Title=new JLabel(t);
        Title.setBounds(110,10,250,25);
        Title.setFont(font);
        add(Title);
        JLabel desc=new JLabel(description);
        desc.setBounds(50,40,3000,25);
        desc.setFont(font.deriveFont(Font.PLAIN,12));
        add(desc);
        input=new JTextField();
        input.setBounds((400-120)/2,80,120,25);
        add(input);
        JButton button =new JButton("Submit");
        button.setBounds((400-120)/2,100,120,50);
        ActionListener le=(ActionEvent e) ->{
            if(type=='s') new Window(600,400, new JScrollPane(new FilmListPanel(funcName+"('"+input.getText()+"');")),t);
            if(type=='i') new Window(600,400, new JScrollPane(new FilmListPanel(funcName+"("+input.getText()+");")),t);
        };
        button.addActionListener(le);
        add(button);
    }
}
