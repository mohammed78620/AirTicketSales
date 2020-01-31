import javax.swing.*;
import java.awt.*;

public class OfficeManagerForm extends JFrame {
    private JButton logoutButton;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    public OfficeManagerForm(){
        //frame setup
        super("Office manager page");

        setLayout(new GridLayout(2,2,3,3));
        logoutButton = new JButton("Logout");
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();

        panel1.setBackground(Color.BLACK);
        panel2.setBackground(Color.WHITE);
        panel3.setBackground(Color.BLUE);
        panel4.setBackground(Color.RED);

        add(panel1);
        add(panel2);
        add(panel3);
        add(panel4);

        panel4.setLayout(new BoxLayout(panel4,BoxLayout.PAGE_AXIS));
        panel4.add(logoutButton);


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(750,750);
        setVisible(true);
    }
}
