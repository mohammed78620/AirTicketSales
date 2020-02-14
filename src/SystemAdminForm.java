import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        JLayeredPane layeredPane = new JLayeredPane();
        JPanel advisorPanel = new JPanel();
        JPanel stockPanel = new JPanel();
        centerPanel.add(layeredPane);
        layeredPane.setPreferredSize(new Dimension(400,350));


        String[] s1 = {"advisor: 1","advisor: 2","advisor: 3","advisor: 4","advisor: 5"};
        advisorPanel.setLayout(new BorderLayout());
        JList advisors = new JList(s1);
        JScrollPane jScrollPane11 = new JScrollPane(advisors);
        advisorPanel.add(jScrollPane11);
        advisorPanel.setBounds(0,0,600,600);

        String[] s2 = {"stock: 1","stock: 2","stock: 3","stock: 4","stock: 5"};
        stockPanel.setLayout(new BorderLayout());
        JList stock = new JList(s2);
        JScrollPane jScrollPane12 = new JScrollPane(stock);
        stockPanel.add(jScrollPane12);
        stockPanel.setBounds(0,0,600,600);

        layeredPane.add(advisorPanel);
        layeredPane.add(stockPanel);

        //sets up bottom of borderLayout
        bottomPanel.setBackground(Color.BLACK);
        JButton logoutButton = new JButton("Logout");
        JButton generateTurnoverReportButton = new JButton("GenerateTurnoverReport");
        bottomPanel.add(generateTurnoverReportButton);
        bottomPanel.add(logoutButton);



        //sets up right of borderLayout
        rightPanel.setBackground(Color.green);
        rightPanel.setLayout(new BoxLayout(rightPanel,BoxLayout.Y_AXIS));
        JButton backupDatabaseButton = new JButton("backupDatabase");
        JButton restoreDatabaseButton = new JButton("restoreDatabase");
        JButton removeTravelAdvisorButton = new JButton("removeTravelAdvisor");
        JButton viewTravelAdvisorButton = new JButton("viewAdvisors");
        JButton viewStockButton = new JButton("viewStock");

        JLabel changeCommissionLabel = new JLabel("change commision rate");
        PlaceholderTextField changeCommissionTextField = new PlaceholderTextField();
        changeCommissionTextField.setPlaceholder("commission rate");
        changeCommissionTextField.setPreferredSize(new Dimension(200,30));
        changeCommissionTextField.setMaximumSize(changeCommissionTextField.getPreferredSize());
        rightPanel.add(backupDatabaseButton);
        rightPanel.add(restoreDatabaseButton);
        rightPanel.add(changeCommissionLabel);
        rightPanel.add(changeCommissionTextField);
        rightPanel.add(removeTravelAdvisorButton);
        rightPanel.add(viewTravelAdvisorButton);
        rightPanel.add(viewStockButton);

        //sets up left of borderLayout
        leftPanel.setBackground(Color.pink);

        //sets button listeners

        viewTravelAdvisorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                advisorPanel.setVisible(true);
                stockPanel.setVisible(false);

            }
        });
        viewStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stockPanel.setVisible(true);
                advisorPanel.setVisible(false);
            }
        });


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(750,750);
        setVisible(true);
    }
}
