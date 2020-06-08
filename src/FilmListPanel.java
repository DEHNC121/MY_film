import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class FilmListPanel extends JPanel {
    private ArrayList<JLabel>  label;
    private Font font = new Font("SanSerif",Font.BOLD,20);
    private String title;
    JScrollPane scrollPane;
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
        int max=0;
        ArrayList<String> s=Window.getApp().Print(Window.getApp().Select("SELECT * FROM "+t+";"));
        for (int i = 0; i < s.size(); i++) {
            label.add(new JLabel(s.get(i)));
            if (s.get(i).length()>max) max=s.get(i).length();
            label.get(i).setBounds(10, 10 + 35 * i, 3000, 25);
            panel.add(label.get(i));
        }


        panel.setPreferredSize(new Dimension(max*6,35*s.size()));
        JScrollPane scrollPane = new JScrollPane(panel);

        scrollPane.setBounds(50, 50, 500, 700);

        add(scrollPane);
    }
}
