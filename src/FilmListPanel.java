import javax.swing.*;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class FilmListPanel extends JPanel {
    private ArrayList<JLabel>  label;
    private Font font;

    private Font fontTitel = new Font("SanSerif",Font.BOLD,20);
    private String title;
    public FilmListPanel(String t,String func){
        setLayout(null);
        try{
            InputStream myStream = this.getClass().getResourceAsStream("res/consola.ttf");
            font = Font.createFont(Font.TRUETYPE_FONT, myStream);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
            font = font.deriveFont(Font.PLAIN, 12);
        } catch (Exception e){
            System.out.println("FONT ERROR");
            font=new Font("SanSerif",Font.PLAIN,12);
        }
        JPanel panel=new JPanel();
        panel.setLayout(null);
        title = t.toLowerCase();

        JLabel Title = new JLabel(title);
        Title.setBounds(50, 10, 500, 40);
        Title.setFont(fontTitel);
        add(Title);
        panel.setLayout(null);

        label = new ArrayList();
        String test1=Window.getApp().getColTypes().get(title);
        String[] n;

        String t2="";
        if (test1==null) {
            n=Window.getApp().getColTypes().get(title+",id").split(",");
            t2+="id | ";
            int i=0;
            while (i<n.length) {
                t2+=n[i++]+" | ";
            }
        }else {
            n=test1.split(",");
            int i=0;
            while (i<n.length) {
                t2+=n[i++]+" | ";
            }
        }
        ArrayList<String> temp;
        int max=t2.length();
        if (func.equals(""))
        {

            temp=Window.getApp().Print(Window.getApp().Select("SELECT * FROM "+title+";"));
        }
        else
            temp=Window.getApp().Print(Window.getApp().Select("SELECT * FROM "+func+";"));

        temp.add(t2);
        ArrayList<String> s=App.display(temp);
        int ma=0;
        for (String te:s) {
            if (ma < te.length()) {
                ma=te.length();

            }
        }
        JLabel titleLabel=new JLabel(s.get(s.size()-1));
        titleLabel.setBounds(10, 10, 3000, 25);
        titleLabel.setFont(font);
        panel.add(titleLabel);

        for (int i = 0; i < s.size()-1; i++) {
            label.add(new JLabel(s.get(i)));
            if (s.get(i).length()>max) max=s.get(i).length();
            label.get(i).setBounds(10, 10 + 35 * i+35, 3000, 25);
            label.get(i).setFont(font);
            panel.add(label.get(i));
        }

        panel.setPreferredSize(new Dimension(7*ma,35*s.size()));
        JScrollPane scrollPane = new JScrollPane(panel);

        scrollPane.setBounds(40, 50, 650, 700);

        add(scrollPane);
    }
}
