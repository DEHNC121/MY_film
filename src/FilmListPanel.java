import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class FilmListPanel extends JPanel {
    private ArrayList<JRadioButton> check;
    private ArrayList<JLabel>  label;
    private ButtonGroup bg= new ButtonGroup();
    private Font font = new Font("SanSerif",Font.BOLD,20);
    private String title;
    public FilmListPanel(String t) {
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        title = t.toLowerCase();

        check = new ArrayList();


        JLabel Title = new JLabel(title);
        Title.setBounds(110, 10, 5000, 25);
        Title.setFont(font);
        panel.add(Title);

        label = new ArrayList();
        ArrayList<String> s=Window.getApp().Print(Window.getApp().Select("SELECT * FROM film;"));
        for (int i = 0; i < s.size(); i++) {
            String[] st=s.get(i).split(Pattern.quote(" | "));
            check.add(new JRadioButton(st[0]+" "+st[1]+" "+st[3]));
            bg.add(check.get(i));
            check.get(i).setBounds(10, 10 + 35 * i + 40, 3000, 25);
            panel.add(check.get(i));
        }


        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        scrollPane.setBounds(50, 30, 300, 1000);

        add(scrollPane);
    }
}
