
import javax.swing.*;
import java.awt.*;

public class Window
{
    private JFrame window;
    public Window () {
        window=new JFrame("MY_film");
        window.setSize(600,400);
        window.add(new Panel());
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true );
    }
}
