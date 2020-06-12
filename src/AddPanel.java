

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

    public AddPanel(String t, String toInsert){
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

        String test1=Window.getApp().getColTypes().get(title);
        String[] n;
        if (test1==null) {
            n=Window.getApp().getColTypes().get(title+",id").split(",");
        }else {
            n=test1.split(",");
        }

        for (int i=0;i<s.length();i++) {
            input.add(new JTextField());
            if (s.charAt(i)=='d') {
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

                    radioButtons.get(0).setSelected(true);

                    add(radioButtons.get(j));
                }
                else if (n[i].replaceAll("\\s+","").equals("value")){
                    JPanel panel = new JPanel ();
                    panel.setLayout (null);
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

                    radioButtons.get(0).setSelected(true);

                    panel.setPreferredSize(new Dimension(670,20));
                    for(int k=0; k<radioButtons.size(); k++){
                        bg.add(radioButtons.get(k));
                        radioButtons.get(k).setBounds(10+k*60,2,60,25);//10+35*i+40
                        panel.add(radioButtons.get(k));
                    }
                    JScrollPane scrollPane = new JScrollPane (panel);
                    scrollPane.setBounds(140, 35*i+42, 180, 40);
                    add (scrollPane);
                }
            } else if(n[i].equals("type")){
                input.get(i).setText(toInsert.split(",")[0]);

                JLabel L=new JLabel("Already chosen");
                L.setBounds(140,10+35*i+40,180,25);
                add(L);
            }
            else if(n[i].equals("film_id")){
                input.get(i).setText(toInsert.split(",")[1]);
                JLabel L=new JLabel("Already chosen");
                L.setBounds(140,10+35*i+40,180,25);
                add(L);
            }
            else if(n[i].contains("id")){
                if (toInsert.split(",").length==1)
                input.get(i).setText(toInsert.split(",")[0]);
                else if (toInsert.split(",").length==2)
                    input.get(i).setText(toInsert.split(",")[1]);
                else
                    input.get(i).setText(toInsert.split(",")[2]);

                JLabel L=new JLabel("Already chosen");
                L.setBounds(140,10+35*i+40,180,25);
                add(L);
            }
            else {
                input.get(i).setBounds(140,10+35*i+40,180,25);
                add(input.get(i));
            }
            if(!n[i].equals("release_date")) label.add(new JLabel(n[i]+":"));
            else label.add(new JLabel(n[i]+" [yyyy-mm-dd]:"));
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
            String error=null;
            int j=0;
            int year=0;
            for (int i=0; i<s.length() && error==null; i++) {
                if  (s.charAt(i)=='s') {
                    try {
                        if (input.get(i).getText().equals("")) {
                            error="you have to fill all of the fields";
                        }
                    }
                    catch (Exception ex) {
                            error="input in field "+ n[i]+" is incorrect";
                    }
                }else if (s.charAt(i)=='i') {
                    try{
                        if (n[i].equals("year")) {
                            year = Integer.parseInt(input.get(i).getText());
                            if (year < 1910) error = "earliest possible year is 1910";

                        }
                        else if(n[i].equals("length")){
                            int parse=Integer.parseInt( input.get(i).getText());
                            if(parse<=0) error="input in field "+ n[i]+" must be greater than 0";
                        }
                        else Integer.parseInt( input.get(i).getText());
                    }catch (NumberFormatException ex) {
                        error=input.get(i).getText()+" is not a natural number";
                    }
                }
                else if (s.charAt(i)=='d') {
                    try{
                        int k=j;
                        String d=inputDate.get(j).getText()+"-"+inputDate.get(++j).getText()+"-"+inputDate.get(++j).getText();
                        int yyyy=Integer.parseInt(inputDate.get(k).getText());
                        int mm=Integer.parseInt(inputDate.get(++k).getText());
                        int dd=Integer.parseInt(inputDate.get(++k).getText());
                        if (yyyy<year){
                            error="year of release_date can't be earlier than 'year'";
                        }
                        else if(mm<1 || mm>12) error= mm +" is not a correct month";
                        else if(dd<1 || dd>31) error= dd +" is not a correct day";
                        input.get(i).setText(d);
                    }catch (NumberFormatException ex) {
                        error="release_date is not correct";
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
            if (error!=null) {
                JOptionPane.showMessageDialog(null,"Error: "+error,"Error",JOptionPane.PLAIN_MESSAGE);

            }else {
                String[] date;
                if(!toInsert.equals("")){
                    date = new String[this.input.size()+1];
                    date[0]=toInsert;
                    for(int i=1; i<date.length; i++){
                        if(i!=buttonIndex){
                            date[i]=this.input.get(i).getText();
                        }
                        else {
                            date[i]=buttonInput;
                        }
                    }
                    String test=Window.getApp().Update(this.title, date);
                    String ok=test.substring(test.length()-3);
                    if (ok.equals("!!!")) {
                        JOptionPane.showMessageDialog(null,test,"Update",JOptionPane.PLAIN_MESSAGE);
                        Window.Off();
                    }else {
                        JOptionPane.showMessageDialog(null,test,"Error",JOptionPane.PLAIN_MESSAGE);
                    }
                }
                else {
                    date = new String[this.input.size()];
                    for(int i=0; i<date.length; i++){
                        if(i!=buttonIndex){
                            date[i]=this.input.get(i).getText();
                        }
                        else {
                            date[i]=buttonInput;
                        }
                    }
                    String test=Window.getApp().Update(this.title, date);
                    String ok=test.substring(test.length()-3);
                    if (ok.equals("!!!")) {
                        JOptionPane.showMessageDialog(null,test,"Update",JOptionPane.PLAIN_MESSAGE);
                        Window.Off();
                    }else {
                        JOptionPane.showMessageDialog(null,test,"Error",JOptionPane.PLAIN_MESSAGE);
                    }
                }
            }
        };
        button.addActionListener(le);
        add(button);
    }
}