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

        centerPanel = new JPanel(new BorderLayout());
        bottomPanel = new JPanel();
        rightPanel = new JPanel();
        leftPanel = new JPanel(new BorderLayout());

        // sets up JPanel BorderLayout
        panel1 = new JPanel(new BorderLayout());
        panel1.add(centerPanel,BorderLayout.CENTER);
        panel1.add(bottomPanel,BorderLayout.PAGE_END);
        panel1.add(rightPanel,BorderLayout.LINE_END);
        panel1.add(leftPanel,BorderLayout.LINE_START);

        add(panel1);


        //sets up center of borderLayout
        JLayeredPane layeredPane = new JLayeredPane();
        JPanel advisorPanel = new JPanel();
        JPanel stockPanel = new JPanel();
        centerPanel.add(layeredPane,BorderLayout.CENTER);
        layeredPane.setPreferredSize(new Dimension(600,600));


        String[] s1 = {"advisor: 1","advisor: 2","advisor: 3","advisor: 4","advisor: 5"};
        advisorPanel.setLayout(new BorderLayout());
        JList advisors = new JList(s1);
        JScrollPane jScrollPane11 = new JScrollPane(advisors);
        advisorPanel.add(jScrollPane11,BorderLayout.CENTER);
        advisorPanel.setBounds(0,0,600,600);

        String[] s2 = {"stock: 1","stock: 2","stock: 3","stock: 4","stock: 5"};
        stockPanel.setLayout(new BorderLayout());
        JList stock = new JList(s2);
        JScrollPane jScrollPane12 = new JScrollPane(stock);
        stockPanel.add(jScrollPane12,BorderLayout.CENTER);
        stockPanel.setBounds(0,0,600,600);

        layeredPane.add(advisorPanel);
        layeredPane.add(stockPanel);

        //sets up bottom of borderLayout
        JButton logoutButton = new JButton("Logout");
        JButton generateTurnoverReportButton = new JButton("GenerateTurnoverReport");
        bottomPanel.add(generateTurnoverReportButton);
        bottomPanel.add(logoutButton);



        //sets up right of borderLayout
        rightPanel.setLayout(new BoxLayout(rightPanel,BoxLayout.Y_AXIS));
        JButton backupDatabaseButton = new JButton("backupDatabase");
        JButton restoreDatabaseButton = new JButton("restoreDatabase");
        JButton addTravelAdvisorButton = new JButton("addTravelAdvisor");
        JButton updateTravelAdvisorButton = new JButton("updateTravelAdvisor");
        JButton removeTravelAdvisorButton = new JButton("removeTravelAdvisor");
        JButton viewTravelAdvisorButton = new JButton("viewAdvisors");
        JButton viewStockButton = new JButton("viewStock");

        JLabel changeCommissionLabel = new JLabel("change commision rate");
        JLabel changeCurrencyrate = new JLabel("change rate");
        JComboBox rates = new JComboBox();
        PlaceholderTextField ratesTextfield = new PlaceholderTextField();
        ratesTextfield.setPlaceholder("rate");
        rates.addItem("GBP/USD");
        rates.addItem("EUR/USD");
        rates.addItem("USD/CAD");
        rates.addItem("USD/JPY");

        PlaceholderTextField changeCommissionTextField = new PlaceholderTextField();
        changeCommissionTextField.setPlaceholder("commission rate");
        rightPanel.add(backupDatabaseButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
        rightPanel.add(restoreDatabaseButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
        rightPanel.add(changeCommissionLabel);
        rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
        rightPanel.add(changeCommissionTextField);
        rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
        rightPanel.add(addTravelAdvisorButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
        rightPanel.add(updateTravelAdvisorButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
        rightPanel.add(removeTravelAdvisorButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
        rightPanel.add(viewTravelAdvisorButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
        rightPanel.add(viewStockButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
        rightPanel.add(changeCurrencyrate);
        rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
        rightPanel.add(rates);
        rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
        rightPanel.add(ratesTextfield);
        rightPanel.add(Box.createRigidArea(new Dimension(0,10)));



        //sets up left of borderLayout
        leftPanel.setBackground(Color.pink);
        JLayeredPane leftLayeredPane = new JLayeredPane();
        leftLayeredPane.setPreferredSize(new Dimension(150,150));
        JPanel updateTravelAdvisorPanel = new JPanel(new GridLayout(7,2,0,50));
        updateTravelAdvisorPanel.setBounds(0,0,150,450);
        JPanel updateStockPanel = new JPanel();
        updateStockPanel.setLayout(new BoxLayout(updateStockPanel,BoxLayout.Y_AXIS));
        updateStockPanel.setBounds(0,0,150,450);

        JLabel idLabel = new JLabel("iD");
        JLabel usernameLabel = new JLabel("Username");
        JLabel passwordLabel = new JLabel("Password");
        JLabel nameLabel = new JLabel("Name");
        JLabel addressLabel = new JLabel("Address");
        JLabel telephoneLabel = new JLabel("Telephone");
        JLabel emailLabel = new JLabel("Email");

        PlaceholderTextField idTextfield = new PlaceholderTextField();
        idTextfield.setPlaceholder("ID");
        PlaceholderTextField usernameTextfield = new PlaceholderTextField();
        usernameTextfield.setPlaceholder("username");
        PlaceholderTextField passwordTextfield = new PlaceholderTextField();
        passwordTextfield.setPlaceholder("password");
        PlaceholderTextField nameTextfield = new PlaceholderTextField();
        nameTextfield.setPlaceholder("name");
        PlaceholderTextField addressTextfield = new PlaceholderTextField();
        addressTextfield.setPlaceholder("address");
        PlaceholderTextField telephoneTextfield = new PlaceholderTextField();
        telephoneTextfield.setPlaceholder("telephone");
        PlaceholderTextField emailTextfield = new PlaceholderTextField();
        emailTextfield.setPlaceholder("email");

        JLabel assignBlanksLabel = new JLabel("Assign blank");
        JLabel idLabel2 = new JLabel("id");
        JLabel typeLabel1 = new JLabel("type");
        JLabel amountLabel = new JLabel("amount");
        JLabel addBlankLabel = new JLabel("Add blank");
        JLabel typeLabel2 = new JLabel("type");
        JLabel amountLabel2 = new JLabel("amount");
        JLabel removeBlankLabel = new JLabel("Remove blank");
        JLabel idLabel3 = new JLabel("id");

        PlaceholderTextField idTextfield2 = new PlaceholderTextField();
        idTextfield2.setPlaceholder("id");
        PlaceholderTextField typeTextfield1 = new PlaceholderTextField();
        typeTextfield1.setPlaceholder("type");
        PlaceholderTextField amountTextfield1 = new PlaceholderTextField();
        amountTextfield1.setPlaceholder("amount");
        PlaceholderTextField typeTextfield2 = new PlaceholderTextField();
        typeTextfield2.setPlaceholder("402");
        PlaceholderTextField amountTextfield2 = new PlaceholderTextField();
        amountTextfield2.setPlaceholder("amount");
        PlaceholderTextField idTextfield3 = new PlaceholderTextField();
        idTextfield3.setPlaceholder("id");

        //adds to update travel advisor panel


        updateTravelAdvisorPanel.add(idLabel);
        updateTravelAdvisorPanel.add(idTextfield);
        updateTravelAdvisorPanel.add(usernameLabel);
        updateTravelAdvisorPanel.add(usernameTextfield);
        updateTravelAdvisorPanel.add(passwordLabel);
        updateTravelAdvisorPanel.add(passwordTextfield);
        updateTravelAdvisorPanel.add(nameLabel);
        updateTravelAdvisorPanel.add(nameTextfield);
        updateTravelAdvisorPanel.add(addressLabel);
        updateTravelAdvisorPanel.add(addressTextfield);
        updateTravelAdvisorPanel.add(telephoneLabel);
        updateTravelAdvisorPanel.add(telephoneTextfield);
        updateTravelAdvisorPanel.add(emailLabel);
        updateTravelAdvisorPanel.add(emailTextfield);

        //adds components to update stock panel
        updateStockPanel.add(assignBlanksLabel);
        updateStockPanel.add(Box.createRigidArea(new Dimension(0,5)));
        updateStockPanel.add(idLabel2);
        updateStockPanel.add(Box.createRigidArea(new Dimension(0,5)));
        updateStockPanel.add(idTextfield2);
        updateStockPanel.add(Box.createRigidArea(new Dimension(0,5)));
        updateStockPanel.add(typeLabel1);
        updateStockPanel.add(Box.createRigidArea(new Dimension(0,5)));
        updateStockPanel.add(typeTextfield1);
        updateStockPanel.add(Box.createRigidArea(new Dimension(0,5)));
        updateStockPanel.add(amountLabel);
        updateStockPanel.add(Box.createRigidArea(new Dimension(0,5)));
        updateStockPanel.add(amountTextfield1);
        updateStockPanel.add(Box.createRigidArea(new Dimension(0,5)));
        updateStockPanel.add(addBlankLabel);
        updateStockPanel.add(Box.createRigidArea(new Dimension(0,5)));
        updateStockPanel.add(typeLabel2);
        updateStockPanel.add(Box.createRigidArea(new Dimension(0,5)));
        updateStockPanel.add(typeTextfield2);
        updateStockPanel.add(Box.createRigidArea(new Dimension(0,5)));
        updateStockPanel.add(amountLabel2);
        updateStockPanel.add(Box.createRigidArea(new Dimension(0,5)));
        updateStockPanel.add(amountTextfield2);
        updateStockPanel.add(Box.createRigidArea(new Dimension(0,5)));
        updateStockPanel.add(removeBlankLabel);
        updateStockPanel.add(Box.createRigidArea(new Dimension(0,5)));
        updateStockPanel.add(idLabel3);
        updateStockPanel.add(Box.createRigidArea(new Dimension(0,5)));
        updateStockPanel.add(idTextfield3);
        updateStockPanel.add(Box.createRigidArea(new Dimension(0,50)));





        //adds to updates stock pane


        leftLayeredPane.add(updateTravelAdvisorPanel);
        leftLayeredPane.add(updateStockPanel);
        leftPanel.add(leftLayeredPane,BorderLayout.CENTER);




        //sets button listeners

        viewTravelAdvisorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                advisorPanel.setVisible(true);
                stockPanel.setVisible(false);
                updateTravelAdvisorPanel.setVisible(true);
                updateStockPanel.setVisible(false);

            }
        });
        viewStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stockPanel.setVisible(true);
                advisorPanel.setVisible(false);
                updateStockPanel.setVisible(true);
                updateTravelAdvisorPanel.setVisible(false);
            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(750,500);
        setVisible(true);
    }
}
