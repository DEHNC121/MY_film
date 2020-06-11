import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class ListSelect extends JPanel {
    private ArrayList<JLabel>  label;
    private ArrayList<JRadioButton> buttons;
    private ButtonGroup bg;
    private Font font = new Font("SanSerif",Font.BOLD,20);
    private String title;
    private static String insert;

    public ListSelect(String selectFrom, String insertInto) {
        setLayout(null);

        JPanel panel=new JPanel();
        panel.setLayout(null);
        title = selectFrom.toLowerCase();
        insert=insertInto.toLowerCase();
        JLabel Title = new JLabel(title);
        Title.setBounds(50, 10, 500, 40);
        Title.setFont(font);
        add(Title);
        panel.setLayout(null);

        label = new ArrayList();
        buttons=new ArrayList<>();
        bg=new ButtonGroup();
        String test1=Window.getApp().getColTypes().get(title);
        String[] n;

        String t2="";
        if (test1==null) {
            n=Window.getApp().getColTypes().get(title+",id").split(",");
            t2+="id | ";
            int i=0;
            while (i<n.length) t2+=n[i++]+" | ";
        }else {
            n=test1.split(",");
            int i=0;
            while (i<n.length) {
                t2+=n[i++]+" | ";
            }
        }
        JLabel labelT=new JLabel(t2);
        labelT.setBounds(10, 10 , 3000, 25);
        panel.add(labelT);
        int max=t2.length();
        ArrayList<String> s=Window.getApp().Print(Window.getApp().Select("SELECT * FROM "+title+";"));
        for (int i = 0; i < s.size(); i++) {
            buttons.add(new JRadioButton());
            buttons.get(i).setBounds(10, 10 + 35 * i+35, 25, 25);
            panel.add(buttons.get(i));
            bg.add(buttons.get(i));

            label.add(new JLabel(s.get(i)));
            if (s.get(i).length()>max) max=s.get(i).length();
            label.get(i).setBounds(20, 10 + 35 * i+35, 3000, 25);
            panel.add(label.get(i));
        }
        buttons.get(0).setSelected(true);
        panel.setPreferredSize(new Dimension(max*6+20,35*s.size()));
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBounds(50, 50, 500, 500);
        add(scrollPane);

        JButton button =new JButton("Submit");
        button.setBounds(100,400,120,50);
        ActionListener le=(ActionEvent e) ->{
            String input;
            for(int i=0; i<buttons.size(); i++){
                if(buttons.get(i).isSelected()){
                    input=label.get(i).getText();
               }
            }
            System.out.println("OK");
            //new Window(350, 500, new AddPanel(title), s);
        };
        button.addActionListener(le);
        add(button);
    }
}
