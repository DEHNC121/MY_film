import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FuncPanel extends JPanel{
    private JTextField input;
    private JLabel label;
    private Font font = new Font("SanSerif",Font.BOLD,20);

    public FuncPanel(String t, String funcName, char type){
        setLayout(null);

        JLabel Title=new JLabel(t);
        Title.setBounds(110,10,250,25);
        Title.setFont(font);
        add(Title);
        input=new JTextField();
        input.setBounds(100,70,120,25);
        add(input);
        JButton button =new JButton("Submit");
        button.setBounds(100,100,120,50);
        ActionListener le=(ActionEvent e) ->{
            if(type=='s') new Window(600,400, new JScrollPane(new FilmListPanel(funcName+"('"+input.getText()+"');")),t);
            if(type=='i') new Window(600,400, new JScrollPane(new FilmListPanel(funcName+"("+input.getText()+");")),t);
        };
        button.addActionListener(le);
        add(button);
    }
}
