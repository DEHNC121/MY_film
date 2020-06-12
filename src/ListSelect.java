import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class ListSelect extends JPanel {
    private ArrayList<JLabel>  label;
    private ArrayList<JRadioButton> buttons;
    private ButtonGroup bg;
    private Font font;
    private String title;
    private String insert;
    private boolean go=false;
    private boolean go1=false;

    public boolean done=true;

    int in;

    public ListSelect(String selectFrom, String insertInto) {
        setLayout(null);

        JPanel panel=new JPanel();
        panel.setLayout(null);

        title = selectFrom.toLowerCase();
        insert=insertInto.toLowerCase();

        String[] Map=Window.getApp().getOpen().get(title).split(",");

        in=insert.split(",",-1).length-1;

        try{
            InputStream myStream = new BufferedInputStream(new FileInputStream("res/consola.ttf"));
            font = Font.createFont(Font.TRUETYPE_FONT, myStream);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
            font = font.deriveFont(Font.PLAIN, 12);
        } catch (Exception e){
            System.out.println("FONT ERROR");
            font=new Font("SanSerif",Font.PLAIN,12);
        }

        if (in==1 && Map[in-1].equals("type")) {

            String test = insert.split(",", -1)[in-1];
            if (test.equals("1")) {

                Map[in] = "film";
            } else if (test.equals("2")) {

                //Map[in] = "episode";
                Map[in] = "series";

                go1=true;
            } else if (test.equals("3")) {

                //Map[in] = "season";
                Map[in] = "series";

                go1=true;
            } else if (test.equals("4")) {

                Map[in] = "series";
            }
        }
        else if (in==2 && insert.split(",", -1)[in-2].equals("2a")) {
            Map[in] = "episode";
            go=true;
        }
        else if (in==2 && Map[in-2].equals("type")) {

            String test = insert.split(",", -1)[in-2];
            if (test.equals("2b")) {

                Map[in] = "season";
                go=true;
            }
            else if (test.equals("3b")) {

                Map[in] = "season";
                go=true;
            }
        }

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

        ArrayList<String> s;
        if (in>0 && Map[in-1].equals("series")) {
           s=Window.getApp().Print(Window.getApp().Select(
                    "SELECT * FROM "+Map[in]+" WHERE series_id="+insert.split(",")[in-1]+";"));
        }
        else {
            s=Window.getApp().Print(Window.getApp().Select("SELECT * FROM "+Map[in]+";"));
        }
        if (in==2 && insert.split(",", -1)[in-2].equals("2a")) {
            s=Window.getApp().Print(Window.getApp().Select(
                    "SELECT * FROM "+Map[in]+" WHERE season_id="+insert.split(",")[in-1]+";"));
        }
        else if (in==2 && Map[in-2].equals("type")) {
            String test = insert.split(",", -1)[in-2];
            if (test.equals("2b")) {
                s=Window.getApp().Print(Window.getApp().Select(
                        "SELECT * FROM "+Map[in]+" WHERE series_id="+insert.split(",")[in-1]+";"));
            }
            else if (test.equals("3b")) {
                s=Window.getApp().Print(Window.getApp().Select(
                        "SELECT * FROM "+Map[in]+" WHERE series_id="+insert.split(",")[in-1]+";"));
            }
        }

        if (s.size()==0) {
                if(in>0){
                    JOptionPane.showMessageDialog(null,
                            "You can't add new "+title+" because there is no "+Map[in]+ " for this " +Map[in-1],"Error",JOptionPane.PLAIN_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(null,
                            "You can't add new "+title+" because there is no "+Map[in]+ " for this " +title,"Error",JOptionPane.PLAIN_MESSAGE);
                }
                done=false;
        }
        else {
            s.add(nameTables);
            ArrayList<String> out=App.display(s);

            int max=nameTables.length();

            for (int i = 0; i < out.size()-1; i++) {
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
            JLabel labelT=new JLabel(out.get(out.size()-1));
            labelT.setBounds(40, 10 , 3000, 25);
            labelT.setFont(font);
            panel.add(labelT);

            buttons.get(0).setSelected(true);

            JButton button =new JButton("Next");
            button.setBounds(400,600,120,50);

            ActionListener le=(ActionEvent e) ->{
                for(int i=0; i<buttons.size(); i++){
                    if(buttons.get(i).isSelected()){
                        if (go) {
                            String add="";
                            int te=insert.split(",").length;
                            for(int i1=0; i1<te; i1++) {
                                if (i1+1==te) {
                                    add+=label.get(i).getText().split(Pattern.quote(" | "))[0]+",";
                                }else {
                                    if (in==2 &&i1==0&& insert.split(",", -1)[in-2].equals("2b"))
                                        add+="2a,";
                                    else if(in==2 &&i1==0
                                            && insert.split(",", -1)[in-2].substring(insert.split(",", -1)[in-2].length()-1).equals("a"))
                                                add+=insert.split(",")[i1].substring(0,insert.split(",")[i1].length()-1)+",";
                                    else if (in==2 &&i1==0&& insert.split(",", -1)[in-2].equals("3b"))
                                        add+="3,";
                                    else
                                        add+=insert.split(",")[i1];
                                }
                            }
                            insert=add;
                        }
                        else if (go1) {
                            String add="";
                            int te=insert.split(",").length;
                            for(int i1=0; i1<te; i1++) {
                                if (i1 == 0) {
                                    add += insert.split(",")[i1] + "b,";
                                }
                                else add += insert.split(",")[i1];
                                }
                                insert=add+label.get(i).getText().split(Pattern.quote(" | "))[0]+",";
                            }else
                            insert+=label.get(i).getText().split(Pattern.quote(" | "))[0]+",";
                        }
                    }
                    System.out.println("OK1");
                    Window.Off();

                    if (insert.split(",",-1).length-1==Map.length) {
                        new Window(350, 400, new AddPanel(title,insert), "New");
                    }else {
                        ListSelect p=new ListSelect(title,insert);


                        if (p.done) new Window(800, 700, p, "New");
                    }
            };
            button.addActionListener(le);
            add(button);
            panel.setPreferredSize(new Dimension(max*6+20,35*s.size()));
            JScrollPane scrollPane = new JScrollPane(panel);
            scrollPane.setBounds(50, 50, 500, 500);
            add(scrollPane);
        }
    }
}
