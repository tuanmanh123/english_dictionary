import javax.swing.*;
import java.awt.*;

public class DictionaryApplication extends javafx.application.Application {

    private JButton button1;
    private JPanel panel1;
    private JButton button2;
    private JButton button3;

    DictionaryApplication() {
        JFrame frame = new JFrame("Dictionary");
        frame.setSize(1280, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);


        button1.setSize(200, 300);
    }

    public static void runApplication() {
        DictionaryApplication application = new DictionaryApplication();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Dictionary");

        frame.setSize(1280, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);


    }


}

