import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
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
        try{
            InputStream myStream = getClass().getResourceAsStream("consola.ttf");;
            font = Font.createFont(Font.TRUETYPE_FONT, myStream);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
            font = font.deriveFont(Font.PLAIN, 12);
        } catch (Exception e){
            System.out.println("FONT ERROR");
            font=new Font("SanSerif",Font.PLAIN,12);
        }

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

        int max=nameTables.length();

        ArrayList<String> s;

        s=Window.getApp().Print(Window.getApp().Select("SELECT * FROM "+title+";"));
        s.add(nameTables);

        if (s.size()==0) {

            JOptionPane.showMessageDialog(null,
                    "You can't delete " + title + " because there is no " + title, "Error", JOptionPane.PLAIN_MESSAGE);

            done = false;
        }
        else
        {
            ArrayList<String> out=App.display(s);
            JLabel labelT=new JLabel(out.get(out.size()-1));
            labelT.setBounds(10, 10 , 3000, 25);
            labelT.setFont(font);
            panel.add(labelT);
            for (int i = 0; i < out.size(); i++) {
                buttons.add(new JRadioButton());
                buttons.get(i).setBounds(10, 10 + 35 * i+35, 25, 25);
                panel.add(buttons.get(i));
                bg.add(buttons.get(i));

                label.add(new JLabel(out.get(i)));
                if (out.get(i).length()>max) max=out.get(i).length();
                label.get(i).setBounds(40, 10 + 35 * i+35, 3000, 25);
                label.get(i).setFont(font);
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
