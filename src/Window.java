
import javax.swing.*;
import java.awt.*;

public class Window
{
    private static JFrame window;
    private static App app;
    public static Font Sfont;

    public Window (int width,int height,App a) {
        if(Sfont==null)
        {
            Sfont = new Font("SanSerif",Font.BOLD,14);
        }
        if(a!=null)
        {
            app=a;
        }
        window=new JFrame("MY_film");
        window.setSize(width,height);
        window.add(new Panel());
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true );
    }

    public Window (int width,int height,JPanel jp,String name) {
        window=new JFrame(name);
        window.setSize(width,height);
        window.add(jp);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setVisible(true );

    }

    public static void Off()
    {
        window.dispose();
    }


    public Window (int width,int height,JScrollPane jsp,String name) {
        window=new JFrame(name);
        window.setSize(width,height);
        window.add(jsp);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setVisible(true );
    }

    public static App getApp() {
        return app;
    }

}
