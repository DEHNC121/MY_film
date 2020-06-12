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
    private String title;
    public FilmListPanel(String t){
        setLayout(null);
        try{
            InputStream myStream = new BufferedInputStream(new FileInputStream("res/consola.ttf"));
            font = Font.createFont(Font.TRUETYPE_FONT, myStream);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
            font = font.deriveFont(Font.PLAIN, 10);
        } catch (Exception e){

            System.out.println("FONT ERROR");
            System.out.println(e);
            System.out.println("-------------------");
            font=new Font("SanSerif",Font.PLAIN,20);
        }
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
        int max=t2.length();
        ArrayList<String> temp=Window.getApp().Print(Window.getApp().Select("SELECT * FROM "+title+";"));
        temp.add(t2);
        ArrayList<String> s=App.display(temp);
        JLabel titleLabel=new JLabel(s.get(s.size()-1));
        titleLabel.setBounds(10, 10, 3000, 25);
        panel.add(titleLabel);
        for (int i = 0; i < s.size()-1; i++) {
            label.add(new JLabel(s.get(i)));
            if (s.get(i).length()>max) max=s.get(i).length();
            label.get(i).setBounds(10, 10 + 35 * i+35, 3000, 25);
            label.get(i).setFont(font);
            panel.add(label.get(i));
        }

        panel.setPreferredSize(new Dimension(max*6,35*s.size()));
        JScrollPane scrollPane = new JScrollPane(panel);

        scrollPane.setBounds(50, 50, 500, 700);

        add(scrollPane);
    }
}
