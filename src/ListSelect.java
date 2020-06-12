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
    private String insert;

    int in;

    public ListSelect(String selectFrom, String insertInto) {
        setLayout(null);

        JPanel panel=new JPanel();
        panel.setLayout(null);

        title = selectFrom.toLowerCase();
        insert=insertInto.toLowerCase();

        String[] Map=Window.getApp().getOpen().get(title).split(",");

        in=insert.split(",",-1).length-1;

        label = new ArrayList();
        buttons=new ArrayList<>();
        bg=new ButtonGroup();
        String test1=Window.getApp().getColTypes().get(Map[in]);
        String[] n;

        String nameTables="";
        if (test1==null) {
            n=Window.getApp().getColTypes().get(Map[in]+",id").split(",");
            nameTables+="id | ";
            int i=0;
            while (i<n.length) nameTables+=n[i++]+" | ";
        }else {
            n=test1.split(",");
            int i=0;
            while (i<n.length) {
                nameTables+=n[i++]+" | ";
            }
        }

        JLabel Title = new JLabel("Select "+Map[in] +" for "+title);
        Title.setBounds(50, 10, 500, 40);
        Title.setFont(font);
        add(Title);
        panel.setLayout(null);


        JLabel labelT=new JLabel(nameTables);
        labelT.setBounds(10, 10 , 3000, 25);
        panel.add(labelT);
        int max=nameTables.length();

        ArrayList<String> s;
        if (in>0 && Map[in-1].equals("series"))
        {

           s=Window.getApp().Print(Window.getApp().Select(
                    "SELECT * FROM "+Map[in]+" WHERE series_id="+insert.split(",")[in-1]+";"));
        }
        else
            {

                s=Window.getApp().Print(Window.getApp().Select("SELECT * FROM "+Map[in]+";"));
            }

        if (s.size()==0)
        {
            JLabel L= new JLabel("NO");
            L.setBounds(40, 10 , 3000, 25);
            panel.add(L);
        }
        else
            {
                for (int i = 0; i < s.size(); i++) {
                    buttons.add(new JRadioButton());
                    buttons.get(i).setBounds(10, 10 + 35 * i+35, 25, 25);
                    panel.add(buttons.get(i));
                    bg.add(buttons.get(i));

                    label.add(new JLabel(s.get(i)));
                    if (s.get(i).length()>max) max=s.get(i).length();
                    label.get(i).setBounds(40, 10 + 35 * i+35, 3000, 25);
                    panel.add(label.get(i));
                }

                buttons.get(0).setSelected(true);

                JButton button =new JButton("Next");
                button.setBounds(100,600,120,50);

                ActionListener le=(ActionEvent e) ->{

                    for(int i=0; i<buttons.size(); i++){
                        if(buttons.get(i).isSelected()){
                            insert+=label.get(i).getText().split(Pattern.quote(" | "))[0]+",";
                        }
                    }
                    System.out.println("OK1");
                    Window.Off();

                    if (insert.split(",",-1).length-1==Map.length)
                    {
                        new Window(350, 400, new AddPanel(title,insert), "New");
                    }else
                    {
                        new Window(600, 800, new ListSelect(title,insert), "New");
                    }

                };

                button.addActionListener(le);
                add(button);
            }

        panel.setPreferredSize(new Dimension(max*6+20,35*s.size()));
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBounds(50, 50, 500, 500);
        add(scrollPane);


    }
}
