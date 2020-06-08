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
    JScrollPane scrollPane;
    public FilmListPanel(String t) {
        setLayout(null);


        JPanel panel=new JPanel();
        panel.setLayout(null);
        title = t.toLowerCase();

        check = new ArrayList();


        JLabel Title = new JLabel(title);
        Title.setBounds(110, 10, 500, 25);
        Title.setFont(font);
        add(Title);
        panel.setLayout(null);

        label = new ArrayList();
        ArrayList<String> s=Window.getApp().Print(Window.getApp().Select("SELECT * FROM "+t+";"));
        for (int i = 0; i < s.size(); i++) {
            check.add(new JRadioButton(s.get(i)));
            bg.add(check.get(i));
            check.get(i).setBounds(10, 10 + 35 * i + 40, 3000, 25);
            panel.add(check.get(i));
        }


        panel.setPreferredSize(new Dimension(400,300));
        JScrollPane scrollPane = new JScrollPane(panel);

        scrollPane.setBounds(50, 30, 500, 300);

        add(scrollPane);
    }
}
