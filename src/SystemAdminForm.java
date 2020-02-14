import javax.swing.*;
import java.awt.*;

public class SystemAdminForm extends JFrame {
    private JPanel panel1;
    private JPanel centerPanel;
    private JPanel bottomPanel;
    private JPanel rightPanel;
    private JPanel leftPanel;
    private JLabel changeCommissionLabel;
    private PlaceholderTextField changeCommissionTextField;

    public SystemAdminForm(){
        super("System Administrator page");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        centerPanel = new JPanel();
        bottomPanel = new JPanel();
        rightPanel = new JPanel();
        leftPanel = new JPanel();

        // sets up JPanel BorderLayout
        panel1 = new JPanel(new BorderLayout());
        panel1.add(centerPanel,BorderLayout.CENTER);
        panel1.add(bottomPanel,BorderLayout.PAGE_END);
        panel1.add(rightPanel,BorderLayout.LINE_END);
        panel1.add(leftPanel,BorderLayout.LINE_START);

        add(panel1);


        //sets up center of borderLayout
        centerPanel.setBackground(Color.RED);


        //sets up bottom of borderLayout
        bottomPanel.setBackground(Color.BLACK);

        //sets up right of borderLayout
        rightPanel.setBackground(Color.green);
        rightPanel.setLayout(new BoxLayout(rightPanel,BoxLayout.Y_AXIS));
        JButton backupDatabaseButton = new JButton("backupDatabase");
        JButton restoreDatabaseButton = new JButton("restoreDatabase");
        JLabel changeCommissionLabel = new JLabel("change commision rate");
        PlaceholderTextField changeCommissionTextField = new PlaceholderTextField();

        rightPanel.add(backupDatabaseButton);
        rightPanel.add(restoreDatabaseButton);


        //sets up left of borderLayout
        leftPanel.setBackground(Color.pink);


        setSize(750,750);
        setVisible(true);
    }
}
