import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddPanel extends JPanel {

    private ArrayList<JTextField> input;
    private ArrayList<JTextField> inputDate;
    private ArrayList<JRadioButton> radioButtons;
    private ArrayList<JLabel>  label;
    private ButtonGroup bg;
    private Font font = new Font("SanSerif",Font.BOLD,20);
    private String title;
    private boolean hasButtons;

    public AddPanel(String t){
        title=t.toLowerCase();
        setLayout(null);
        hasButtons=false;
        input=new ArrayList();
        inputDate=new ArrayList();
        radioButtons=new ArrayList<>();
        bg= new ButtonGroup();
        JLabel Title=new JLabel("Add "+title);
        Title.setBounds(110,10,250,25);
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

                inputDate.get(j).setBounds(30+110,10+35*i+40,40,25);
                add(inputDate.get(j));

                j++;
                inputDate.get(j).setBounds(30+160,10+35*i+40,25,25);
                add(inputDate.get(j));
                j++;

                inputDate.get(j).setBounds(30+195,10+35*i+40,25,25);
                add(inputDate.get(j));

                JLabel lej=new JLabel("-");

                lej.setBounds(30+153,10+35*i+40,10,25);

                add(lej);

                lej=new JLabel("-");
                lej.setBounds(30+188,10+35*i+40,10,25);

                add(lej);

            } else if(s.charAt(i)=='b'){
                hasButtons=true;
                if (n[i].replaceAll("\\s+","").equals("colour")){
                    int j=radioButtons.size();
                    radioButtons.add(new JRadioButton("colour"));
                    bg.add(radioButtons.get(j));
                    radioButtons.get(j).setBounds(30+110,10+35*i+40,80,25);
                    add(radioButtons.get(j));

                    j++;
                    radioButtons.add(new JRadioButton("black/white"));
                    bg.add(radioButtons.get(j));
                    radioButtons.get(j).setBounds(30+200,10+35*i+40,120,25);
                    add(radioButtons.get(j));
                }
                else if (n[i].replaceAll("\\s+","").equals("value")){
                    System.out.println(n[i]);
                    radioButtons.add(new JRadioButton("0"));
                    radioButtons.add(new JRadioButton("0.5"));
                    radioButtons.add(new JRadioButton("1"));
                    radioButtons.add(new JRadioButton("1.5"));
                    radioButtons.add(new JRadioButton("2"));
                    radioButtons.add(new JRadioButton("2.5"));
                    radioButtons.add(new JRadioButton("3"));
                    radioButtons.add(new JRadioButton("3.5"));
                    radioButtons.add(new JRadioButton("4"));
                    radioButtons.add(new JRadioButton("4.5"));
                    radioButtons.add(new JRadioButton("5"));
                    System.out.println(radioButtons.size());
                    for(int k=0; k<radioButtons.size(); k++){
                        System.out.println(k);
                        bg.add(radioButtons.get(k));
                        radioButtons.get(k).setBounds(110+k*60,10+35*i+40,60,25);
                        add(radioButtons.get(k));
                    }
                }
            }
            else
                {
                    input.get(i).setBounds(140,10+35*i+40,180,25);
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
            int buttonIndex=-1;
            String buttonInput=null;
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
                        input.get(i).setText(inputDate.get(j).getText()+"-"+inputDate.get(++j).getText()+"-"+inputDate.get(++j).getText());

                    }catch (Exception ex)
                    {
                        ero=n[i];
                    }
                }
                else if (s.charAt(i)=='b'){
                    hasButtons=true;
                    buttonIndex=i;
                    if(n[i].replaceAll("\\s+","").equals("colour")){
                        if(radioButtons.get(0).isSelected()) buttonInput="'C'";
                        if(radioButtons.get(1).isSelected()) buttonInput="'B/W'";
                    }
                    if(n[i].replaceAll("\\s+","").equals("value")){
                        if(radioButtons.get(0).isSelected()) buttonInput="0";
                        if(radioButtons.get(1).isSelected()) buttonInput="0.5";
                        if(radioButtons.get(2).isSelected()) buttonInput="1";
                        if(radioButtons.get(3).isSelected()) buttonInput="1.5";
                        if(radioButtons.get(4).isSelected()) buttonInput="2";
                        if(radioButtons.get(5).isSelected()) buttonInput="2.5";
                        if(radioButtons.get(6).isSelected()) buttonInput="3";
                        if(radioButtons.get(7).isSelected()) buttonInput="3.5";
                        if(radioButtons.get(8).isSelected()) buttonInput="4";
                        if(radioButtons.get(9).isSelected()) buttonInput="4.5";
                        if(radioButtons.get(10).isSelected()) buttonInput="5";
                    }
                }
            }
            if (hasButtons==true && buttonInput==null){
                JOptionPane.showMessageDialog(null,"You have to choose a value","Error",JOptionPane.PLAIN_MESSAGE);
            }
            else if (ero!=null)
            {
                JOptionPane.showMessageDialog(null,"Error in "+ero,"Error",JOptionPane.PLAIN_MESSAGE);

            }else {
                String[] date;
                date = new String[this.input.size()];
                for(int i=0; i<date.length; i++){
                    if(i!=buttonIndex){
                        date[i]=this.input.get(i).getText();
                    }
                    else {
                            date[i]=buttonInput;
                    }
                }
                Window.getApp().Update(this.title, date);
            }
        };
        button.addActionListener(le);
        add(button);
    }
}