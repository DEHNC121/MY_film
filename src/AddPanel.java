import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddPanel extends JPanel {

    private ArrayList<JTextField> input;
    private ArrayList<JLabel>  label;
    private Font font = new Font("SanSerif",Font.BOLD,20);
    private String title;

    public AddPanel(String t){
        title=t.toLowerCase();
        setLayout(null);

        input=new ArrayList();

        JLabel Title=new JLabel("Add "+title);
        Title.setBounds(110,10,180,25);
        Title.setFont(font);
        add(Title);

        label=new ArrayList();

        String s=Window.getApp().getTypes().get(title);

        String[] n=Window.getApp().getColTypes().get(title).split(",");

        for (int i=0;i<s.length();i++)
        {
            input.add(new JTextField());
            input.get(i).setBounds(110,10+35*i+40,180,25);
            add(input.get(i));

            label.add(new JLabel(n[i]+":"));
            label.get(i).setBounds(10,10+35*i+40,180,25);
            label.get(i).setFont(Window.Sfont);
            add(label.get(i));
        }
        JButton button =new JButton("Submit");
        button.setBounds(100,s.length()*35+15+40,120,50);


        ActionListener le=(ActionEvent e) ->
        {
            String[] date;
            int i=0;
            date=new  String[this.input.size()];
            for (JTextField l:this.input)
            {
                date[i++]=l.getText();
            }
            Window.getApp().Update(this.title,date);
        };
        button.addActionListener(le);
        add(button);
    }
}