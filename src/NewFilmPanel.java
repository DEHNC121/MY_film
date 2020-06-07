import javax.swing.*;

public class NewFilmPanel extends JPanel {

    public NewFilmPanel(){

        String s=Window.getApp().getTypes().get("film");

        System.out.print(s);

    }
}
