import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddPanel extends JPanel {

    private ArrayList<JTextField> input;
    private ArrayList<JTextField> inputDate;
    private ArrayList<JLabel>  label;
    private Font font = new Font("SanSerif",Font.BOLD,20);
    private String title;

    public AddPanel(String t){
        title=t.toLowerCase();
        setLayout(null);

        input=new ArrayList();
        inputDate=new ArrayList();

        JLabel Title=new JLabel("Add "+title);
        Title.setBounds(110,10,180,25);
        Title.setFont(font);
        add(Title);

        label=new ArrayList();

        String s=Window.getApp().getTypes().get(title);

        String[] n=Window.getApp().getColTypes().get(title).split(",");

        for (int i=0;i<s.length();i++)
        {
            input.add(new JTextField());
            if (s.charAt(i)=='d')
            {

                int j=inputDate.size();
                inputDate.add(new JTextField());
                inputDate.add(new JTextField());
                inputDate.add(new JTextField());

                inputDate.get(j).setBounds(110,10+35*i+40,35,25);
                add(inputDate.get(j));

                j++;
                inputDate.get(j).setBounds(155,10+35*i+40,20,25);
                add(inputDate.get(j));
                j++;

                inputDate.get(j).setBounds(185,10+35*i+40,20,25);
                add(inputDate.get(j));

                JLabel lej=new JLabel("-");

                lej.setBounds(147,10+35*i+40,10,25);

                add(lej);

                lej=new JLabel("-");
                lej.setBounds(177,10+35*i+40,10,25);

                add(lej);

            }else
                {
                    input.get(i).setBounds(110,10+35*i+40,180,25);
                    add(input.get(i));
                }

            label.add(new JLabel(n[i]+":"));
            label.get(i).setBounds(10,10+35*i+40,180,25);
            label.get(i).setFont(Window.Sfont);
            add(label.get(i));
        }
        JButton button =new JButton("Submit");
        button.setBounds(100,s.length()*35+15+40,120,50);


        ActionListener le=(ActionEvent e) ->
        {
            String ero=null;
            int j=0;
            int year=0;
            for (int i=0;i<s.length()&&ero==null;i++)
            {
                if  (s.charAt(i)=='s')
                {
                    try {
                        if (n[i].equals("year")) {
                            year = Integer.parseInt(input.get(i).getText());
                            if (year <= 1910) {
                                ero = n[i];
                            }
                        }
                    }
                    catch (Exception ex)
                        {
                            ero=n[i];
                        }
                }else if (s.charAt(i)=='i')
                {
                    try{
                        Integer.parseInt( input.get(i).getText());
                    }catch (Exception ex)
                    {
                        ero=n[i];
                    }
                }
                else if (s.charAt(i)=='d')
                {
                    try{
                        int test;
                        test=Integer.parseInt(inputDate.get(j).getText());
                        if (test<year){
                            ero=n[i];
                        }
                    }catch (Exception ex)
                    {
                        ero=n[i];
                    }
                }
            }
            if  (ero!=null)
            {

            }else {
                String[] date;
                int i = 0;
                date = new String[this.input.size()];
                for (JTextField l : this.input) {
                    date[i++] = l.getText();
                }
                Window.getApp().Update(this.title, date);
            }
        };
        button.addActionListener(le);
        add(button);
    }
}