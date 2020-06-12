import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class ListDrop extends JPanel {
    private ArrayList<JLabel>  label;
    private ArrayList<JRadioButton> buttons;
    private ButtonGroup bg;
    private Font font = new Font("SanSerif",Font.BOLD,20);
    private String title;
    private boolean go=false;
    private boolean go1=false;

    public boolean done=true;

    int in;



    public ListDrop(String selectFrom) {
        setLayout(null);

        JPanel panel=new JPanel();
        panel.setLayout(null);

        title = selectFrom.toLowerCase();


        label = new ArrayList();
        buttons=new ArrayList<>();
        bg=new ButtonGroup();
        String test1=Window.getApp().getColTypes().get(title);
        String[] n;

        String nameTables="";
        if (test1==null) {
            n=Window.getApp().getColTypes().get(title+",id").split(",");
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

        JLabel Title = new JLabel("Delete from "+title);
        Title.setBounds(50, 10, 500, 40);
        Title.setFont(font);
        add(Title);
        panel.setLayout(null);


        JLabel labelT=new JLabel(nameTables);
        labelT.setBounds(10, 10 , 3000, 25);
        panel.add(labelT);
        int max=nameTables.length();

        ArrayList<String> s;

        s=Window.getApp().Print(Window.getApp().Select("SELECT * FROM "+title+";"));


        if (s.size()==0) {

            JOptionPane.showMessageDialog(null,
                    "You can't delete " + title + " because there is no " + title, "Error", JOptionPane.PLAIN_MESSAGE);

            done = false;
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

            JButton button =new JButton("Delete");
            button.setBounds(100,600,120,50);

            ActionListener le=(ActionEvent e) ->{

                for(int i=0; i<buttons.size(); i++){
                    if(buttons.get(i).isSelected()){
                         if (Window.getApp().Delete(title,label.get(i).getText().split(Pattern.quote(" | "))))
                         {
                             JOptionPane.showMessageDialog(null,
                                     "Delete success !","Dane",JOptionPane.PLAIN_MESSAGE);

                         }else
                             {
                                 JOptionPane.showMessageDialog(null,
                                         "Delete error !","Error",JOptionPane.PLAIN_MESSAGE);
                             };
                    }
                }
                System.out.println("OK2");

                Window.Off();



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
