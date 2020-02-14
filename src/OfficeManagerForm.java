import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OfficeManagerForm extends JFrame {
    private JButton logoutButton;
    private JButton domesticReportButton;
    private JButton interlineReportButton;
    private JButton turnoverReportButton;
    private JButton viewReportButton;
    private JButton viewStockButton;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel panel5;
    private JLabel changeCommissionLabel;
    private PlaceholderTextField changeCommissionTextfield;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JList stock;
    private JList report;
    private JLayeredPane layeredPane;
    private JPanel stockPanel;
    private JPanel reportPanel;




    public OfficeManagerForm(){
        //frame setup
        super("Office manager page");
        logoutButton = new JButton("Logout");

        stockPanel = new JPanel();
        reportPanel = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        panel5 = new JPanel();
        layeredPane = new JLayeredPane();




        stockPanel.setBackground(Color.RED);
        reportPanel.setBackground(Color.MAGENTA);
        panel2.setBackground(Color.green);
        panel3.setBackground(Color.cyan);
        panel4.setBackground(Color.ORANGE);
        panel5.setBackground(Color.pink);



        //sets the layout as BorderLayout
        panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        panel1.add(panel2,BorderLayout.CENTER);
        panel1.add(panel3,BorderLayout.LINE_START);
        panel1.add(panel4,BorderLayout.PAGE_END);
        panel1.add(panel5,BorderLayout.LINE_END);



        //sets the bottom part of the layout
        domesticReportButton = new JButton("GenerateDomesticReport");
        interlineReportButton = new JButton("GenerateInterlineReport");
        turnoverReportButton = new JButton("GenerateTurnoverReport");
        panel4.setLayout(new BoxLayout(panel4,BoxLayout.X_AXIS));

        panel4.add(logoutButton);
        panel4.setPreferredSize(new Dimension(500,100));
        panel4.add(domesticReportButton);
        panel4.add(interlineReportButton);
        panel4.add(turnoverReportButton);
        panel4.add(logoutButton);


        //sets the center part of the layout
        panel2.add(layeredPane);
        layeredPane.setPreferredSize(new Dimension(400,350));

        String[] s1 = {"item: 1","item: 2","item: 3","item: 4","item: 5","item: 1","item: 2","item: 3","item: 4","item: 5"};
        stockPanel.setLayout(new BorderLayout());
        stock = new JList(s1);
        jScrollPane1 = new JScrollPane(stock);
        stockPanel.add(jScrollPane1);
        stock.setVisibleRowCount(4);
        stockPanel.setBounds(0,0,600,600);


        String[] s2 = {"report: 1","report: 2","report: 3","report: 4","report: 5"};
        reportPanel.setLayout(new BorderLayout());
        report = new JList(s2);
        jScrollPane2 = new JScrollPane(report);
        reportPanel.add(jScrollPane2);
        report.setVisibleRowCount(4);
        reportPanel.setBounds(0,0,600,600);

        layeredPane.add(stockPanel);
        layeredPane.add(reportPanel);



        //sets the right part of the layout
        changeCommissionLabel = new JLabel("Change Commission Rate");
        changeCommissionTextfield = new PlaceholderTextField();
        changeCommissionTextfield.setPreferredSize(new Dimension(200,20));
        changeCommissionTextfield.setMaximumSize(changeCommissionTextfield.getPreferredSize());
        changeCommissionTextfield.setPlaceholder("commission rate");
        viewReportButton = new JButton("viewReport");
        viewStockButton = new JButton("viewStock");

        panel5.setLayout(new BoxLayout(panel5,BoxLayout.Y_AXIS));
        panel5.add(changeCommissionLabel);
        panel5.add(changeCommissionTextfield);
        panel5.add(viewReportButton);
        panel5.add(viewStockButton);

        viewReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reportPanel.setVisible(true);
                stockPanel.setVisible(false);

            }
        });

        viewStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reportPanel.setVisible(false);
                stockPanel.setVisible(true);

            }
        });


        add(panel1);




        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(750,500);
        setVisible(true);
    }
}
