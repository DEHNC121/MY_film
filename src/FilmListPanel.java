import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class FilmListPanel extends JPanel {
    private ArrayList<JLabel>  label;
    private Font font = new Font("SanSerif",Font.BOLD,20);
    private String title;
    public FilmListPanel(String t) {
        setLayout(null);


        JPanel panel=new JPanel();
        panel.setLayout(null);
        title = t.toLowerCase();

        JLabel Title = new JLabel(title);
        Title.setBounds(50, 10, 500, 40);
        Title.setFont(font);
        add(Title);
        panel.setLayout(null);

        label = new ArrayList();
        String test1=Window.getApp().getColTypes().get(title);
        String[] n;

        String t2="";
        if (test1==null)
        {
            n=Window.getApp().getColTypes().get(title+",id").split(",");
            t2+="id | ";
            int i=0;
            while (i<n.length)
            {

                t2+=n[i++]+" | ";
            }
        }else
        {
            n=test1.split(",");
            int i=0;
            while (i<n.length)
            {

                t2+=n[i++]+" | ";
            }
        }
        JLabel labelT=new JLabel(t2);
        labelT.setBounds(10, 10 , 3000, 25);
        panel.add(labelT);
        int max=t2.length();
        ArrayList<String> s=Window.getApp().Print(Window.getApp().Select("SELECT * FROM "+title+";"));
        for (int i = 0; i < s.size(); i++) {
            label.add(new JLabel(s.get(i)));
            if (s.get(i).length()>max) max=s.get(i).length();
            label.get(i).setBounds(10, 10 + 35 * i+35, 3000, 25);
            panel.add(label.get(i));
        }


        panel.setPreferredSize(new Dimension(max*6,35*s.size()));
        JScrollPane scrollPane = new JScrollPane(panel);

        scrollPane.setBounds(50, 50, 500, 700);

        add(scrollPane);
    }
}
