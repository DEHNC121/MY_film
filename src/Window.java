
import javax.swing.*;
import java.awt.*;

public class Window
{
    private static JFrame window;
    private static App app;

    public Window (int width,int height,App a) {
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

    public static App getApp() {
        return app;
    }

}
