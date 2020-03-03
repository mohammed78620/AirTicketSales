package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import component.PlaceholderTextField;


public class OfficeManagerForm extends JFrame {
    public JButton logoutButton;
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
    private String url = "jdbc:mysql://localhost:3306/airticketsales";
    private String name = "akmal";
    private String password = "]WCgDKEN69Wf>zE.";




    public OfficeManagerForm(){
        //frame setup
        super("Office manager page");
        logoutButton = new JButton("Logout");

        stockPanel = new JPanel(new BorderLayout());
        reportPanel = new JPanel(new BorderLayout());
        panel2 = new JPanel(new BorderLayout());
        panel3 = new JPanel(new BorderLayout());
        panel4 = new JPanel();
        panel5 = new JPanel();
        layeredPane = new JLayeredPane();


        //sets the layout as BorderLayout
        panel1 = new JPanel(new BorderLayout());
        panel1.add(panel2,BorderLayout.CENTER);
        panel1.add(panel3,BorderLayout.LINE_START);
        panel1.add(panel4,BorderLayout.PAGE_END);
        panel1.add(panel5,BorderLayout.LINE_END);



        //sets the bottom part of the layout
        domesticReportButton = new JButton("GenerateDomesticReport");
        interlineReportButton = new JButton("GenerateInterlineReport");
        turnoverReportButton = new JButton("GenerateTurnoverReport");


        panel4.add(logoutButton);
        panel4.add(domesticReportButton);
        panel4.add(interlineReportButton);
        panel4.add(turnoverReportButton);
        panel4.add(logoutButton);


        //sets the center part of the layout
        panel2.add(layeredPane,BorderLayout.CENTER);
        layeredPane.setPreferredSize(new Dimension(600,600));
        JPanel customerPanel = new JPanel(new BorderLayout());
        JPanel transactionPanel = new JPanel(new BorderLayout());
        transactionPanel.setLayout(new BoxLayout(transactionPanel,BoxLayout.Y_AXIS));
        JPanel advisorPanel = new JPanel(new BorderLayout());


        String[] s1 = {"item: 1","item: 2","item: 3","item: 4","item: 5","item: 1","item: 2","item: 3","item: 4","item: 5"};
        stockPanel.setLayout(new BorderLayout());
        stock = new JList(s1);
        jScrollPane1 = new JScrollPane(stock);
        stockPanel.add(jScrollPane1,BorderLayout.CENTER);
//        stock.setVisibleRowCount(4);
        stockPanel.setBounds(0,0,600,600);


        String[] s2 = {"report: 1","report: 2","report: 3","report: 4","report: 5"};
        report = new JList(s2);
        jScrollPane2 = new JScrollPane(report);
        reportPanel.add(jScrollPane2,BorderLayout.CENTER);
//        report.setVisibleRowCount(4);
        reportPanel.setBounds(0,0,600,600);

        String[] s3 = {"customer: 1","customer: 2","customer: 3","customer: 4","customer: 5"};

        JList customer = new JList(s3);
        JScrollPane jScrollPane3 = new JScrollPane(customer);
        customerPanel.add(jScrollPane3,BorderLayout.CENTER);
//        report.setVisibleRowCount(4);
        customerPanel.setBounds(0,0,600,600);

        String[] s4 = {"advisor: 1","advisor: 2","advisor: 3","advisor: 4","advisor: 5"};
        JList advisor = new JList(s4);
        JScrollPane jScrollPane4 = new JScrollPane(advisor);
        advisorPanel.add(jScrollPane4,BorderLayout.CENTER);
//        report.setVisibleRowCount(4);
        advisorPanel.setBounds(0,0,600,600);


        JLabel amountLabel = new JLabel("amount");
        PlaceholderTextField amountTextfield = new PlaceholderTextField();
        JLabel paymentTypeLabel = new JLabel("payment type");
        JComboBox<String> paymentTypeComboBox = new JComboBox<>();
        paymentTypeComboBox.addItem("creditCard");
        paymentTypeComboBox.addItem("cash");
        JLabel customerIDLabel = new JLabel("customer id");
        PlaceholderTextField customerIDTextfield = new PlaceholderTextField();
        customerIDTextfield.setPlaceholder("customer id");
        JButton sellButton = new JButton("sell");


        amountTextfield.setPlaceholder("amount");
        transactionPanel.add(amountLabel);
        transactionPanel.add(Box.createRigidArea(new Dimension(0,15)));
        transactionPanel.add(amountTextfield);
        transactionPanel.add(Box.createRigidArea(new Dimension(0,15)));
        transactionPanel.setVisible(false);
        transactionPanel.add(paymentTypeLabel);
        transactionPanel.add(Box.createRigidArea(new Dimension(0,15)));
        transactionPanel.add(paymentTypeComboBox);
        transactionPanel.add(Box.createRigidArea(new Dimension(0,15)));
        transactionPanel.add(customerIDLabel);
        transactionPanel.add(Box.createRigidArea(new Dimension(0,15)));
        transactionPanel.add(customerIDTextfield);
        transactionPanel.add(Box.createRigidArea(new Dimension(0,15)));
        transactionPanel.add(sellButton);
        transactionPanel.add(Box.createRigidArea(new Dimension(0,260)));

        transactionPanel.setBounds(0,0,400,400);

        layeredPane.add(stockPanel);
        layeredPane.add(reportPanel);
        layeredPane.add(customerPanel);
        layeredPane.add(advisorPanel);
        layeredPane.add(transactionPanel);



        //sets the right part of the layout
        changeCommissionLabel = new JLabel("Change commission rate");
        JLabel cancelTicketLabel = new JLabel("Cancel ticket");
        changeCommissionTextfield = new PlaceholderTextField();
        changeCommissionTextfield.setPlaceholder("Commission rate");
        PlaceholderTextField cancelTicketTextfield = new PlaceholderTextField();
        cancelTicketTextfield.setPlaceholder("cancel ticket");
        viewReportButton = new JButton("View report");
        viewStockButton = new JButton("View stock");
        JButton viewCustomerButton = new JButton("View customer");
        JButton viewTravelAdvisorButton = new JButton("View travel advisor");
        JButton createCustomerButton = new JButton("Create customer");
        JButton updateCustomerButton = new JButton("Update customer");
        JButton viewTransactionButton = new JButton("View transaction");

        panel5.setLayout(new BoxLayout(panel5,BoxLayout.Y_AXIS));
        panel5.add(cancelTicketLabel);
        panel5.add(Box.createRigidArea(new Dimension(0,15)));
        panel5.add(cancelTicketTextfield);
        panel5.add(Box.createRigidArea(new Dimension(0,15)));
        panel5.add(changeCommissionLabel);
        panel5.add(Box.createRigidArea(new Dimension(0,15)));
        panel5.add(changeCommissionTextfield);
        panel5.add(Box.createRigidArea(new Dimension(0,15)));
        panel5.add(viewReportButton);
        panel5.add(Box.createRigidArea(new Dimension(0,15)));
        panel5.add(viewStockButton);
        panel5.add(Box.createRigidArea(new Dimension(0,15)));
        panel5.add(viewCustomerButton);
        panel5.add(Box.createRigidArea(new Dimension(0,15)));
        panel5.add(viewTravelAdvisorButton);
        panel5.add(Box.createRigidArea(new Dimension(0,15)));
        panel5.add(createCustomerButton);
        panel5.add(Box.createRigidArea(new Dimension(0,15)));
        panel5.add(updateCustomerButton);
        panel5.add(Box.createRigidArea(new Dimension(0,15)));
        panel5.add(viewTransactionButton);
        panel5.add(Box.createRigidArea(new Dimension(0,280)));



        //sets the left part of the layout
        JLayeredPane leftLayeredPane = new JLayeredPane();
        leftLayeredPane.setPreferredSize(new Dimension(150,150));

        JPanel assignBlankPanel = new JPanel();
        assignBlankPanel.setLayout(new BoxLayout(assignBlankPanel,BoxLayout.Y_AXIS));
        assignBlankPanel.setBounds(0,0,150,450);
        JLabel assignBlank = new JLabel("Assign blank");
        PlaceholderTextField idTextfield = new PlaceholderTextField();
        idTextfield.setPlaceholder("id");
        JLabel typeLabel = new JLabel("type");
        PlaceholderTextField typeTextfield = new PlaceholderTextField();
        typeTextfield.setPlaceholder("type");
        JLabel amountLabel2 = new JLabel("amount");
        PlaceholderTextField amountTextfield2 = new PlaceholderTextField();
        amountTextfield.setPlaceholder("amount");
        JButton assignBlankButton = new JButton("assign blank");

        JPanel manageDiscountPanel = new JPanel();
        manageDiscountPanel.setLayout(new BoxLayout(manageDiscountPanel,BoxLayout.Y_AXIS));
        manageDiscountPanel.setBounds(0,0,150,150);
        manageDiscountPanel.setVisible(false);
        JLabel manageDiscountLabel = new JLabel("Manage discount");
        JLabel customerIDLabel2 = new JLabel("Customer id");
        PlaceholderTextField customerIDTextfield2 = new PlaceholderTextField();
        customerIDTextfield2.setPlaceholder("Customer id");
        ButtonGroup discountGroup = new ButtonGroup();
        JRadioButton flexibleDiscountRadio = new JRadioButton("Flexible",false);
        JRadioButton fixedDiscountRadio = new JRadioButton("Fixed", false);
        discountGroup.add(flexibleDiscountRadio);
        discountGroup.add(fixedDiscountRadio);
        PlaceholderTextField fixedRateTextfield = new PlaceholderTextField();
        fixedRateTextfield.setPlaceholder("discount %");
        JButton backButton = new JButton("back");



        //sets up panel
        JPanel updateCustomerPanel = new JPanel();
        updateCustomerPanel.setLayout(new BoxLayout(updateCustomerPanel,BoxLayout.Y_AXIS));
        updateCustomerPanel.setBounds(0,0,150,450);
        //components inside updateCustomerPanel
        JLabel IDLabel = new JLabel("ID");
        JLabel nameLabel = new JLabel("Name");
        JLabel dateOfBirthLabel = new JLabel("Date of birth");
        JLabel addressLabel = new JLabel("Address");
        JLabel postalCodeLabel = new JLabel("Postcode");
        JLabel telephoneLabel = new JLabel("telephone");
        JLabel emailLabel = new JLabel("Email");
        JButton manageDiscountButton = new JButton("Manage discount");


        PlaceholderTextField IDTextField = new PlaceholderTextField();
        IDTextField.setPlaceholder("ID");
        PlaceholderTextField nameTextField = new PlaceholderTextField();
        nameTextField.setPlaceholder("Name");
        PlaceholderTextField dateOfBirthTextField = new PlaceholderTextField();
        dateOfBirthTextField.setPlaceholder("date of birth");
        PlaceholderTextField addressTextField = new PlaceholderTextField();
        addressTextField.setPlaceholder("address");
        PlaceholderTextField postalCodeTextField = new PlaceholderTextField();
        postalCodeTextField.setPlaceholder("postcode");
        PlaceholderTextField telephoneTextField = new PlaceholderTextField();
        telephoneTextField.setPlaceholder("telephone");
        PlaceholderTextField emailTextField = new PlaceholderTextField();
        emailTextField.setPlaceholder("email");




        assignBlankPanel.add(assignBlank);
        assignBlankPanel.add(Box.createRigidArea(new Dimension(0,15)));
        assignBlankPanel.add(idTextfield);
        assignBlankPanel.add(Box.createRigidArea(new Dimension(0,15)));
        assignBlankPanel.add(typeLabel);
        assignBlankPanel.add(Box.createRigidArea(new Dimension(0,15)));
        assignBlankPanel.add(typeTextfield);
        assignBlankPanel.add(Box.createRigidArea(new Dimension(0,15)));
        assignBlankPanel.add(amountLabel2);
        assignBlankPanel.add(Box.createRigidArea(new Dimension(0,15)));
        assignBlankPanel.add(amountTextfield2);
        assignBlankPanel.add(Box.createRigidArea(new Dimension(0,15)));
        assignBlankPanel.add(assignBlankButton);
        assignBlankPanel.add(Box.createRigidArea(new Dimension(0,260)));

        //update customer panel
        updateCustomerPanel.add(IDLabel);
        updateCustomerPanel.add(Box.createRigidArea(new Dimension(0,10)));
        updateCustomerPanel.add(IDTextField);
        updateCustomerPanel.add(Box.createRigidArea(new Dimension(0,10)));
        updateCustomerPanel.add(nameLabel);
        updateCustomerPanel.add(Box.createRigidArea(new Dimension(0,10)));
        updateCustomerPanel.add(nameTextField);
        updateCustomerPanel.add(Box.createRigidArea(new Dimension(0,10)));
        updateCustomerPanel.add(dateOfBirthLabel);
        updateCustomerPanel.add(Box.createRigidArea(new Dimension(0,10)));
        updateCustomerPanel.add(dateOfBirthTextField);
        updateCustomerPanel.add(Box.createRigidArea(new Dimension(0,10)));
        updateCustomerPanel.add(addressLabel);
        updateCustomerPanel.add(Box.createRigidArea(new Dimension(0,10)));
        updateCustomerPanel.add(addressTextField);
        updateCustomerPanel.add(Box.createRigidArea(new Dimension(0,10)));
        updateCustomerPanel.add(postalCodeLabel);
        updateCustomerPanel.add(Box.createRigidArea(new Dimension(0,10)));
        updateCustomerPanel.add(postalCodeTextField);
        updateCustomerPanel.add(Box.createRigidArea(new Dimension(0,10)));
        updateCustomerPanel.add(telephoneLabel);
        updateCustomerPanel.add(Box.createRigidArea(new Dimension(0,10)));
        updateCustomerPanel.add(telephoneTextField);
        updateCustomerPanel.add(Box.createRigidArea(new Dimension(0,10)));
        updateCustomerPanel.add(emailLabel);
        updateCustomerPanel.add(Box.createRigidArea(new Dimension(0,10)));
        updateCustomerPanel.add(emailTextField);
        updateCustomerPanel.add(Box.createRigidArea(new Dimension(0,10)));
        updateCustomerPanel.add(manageDiscountButton);
        updateCustomerPanel.add(Box.createRigidArea(new Dimension(0,50)));

        //sets up manage discount panel
        manageDiscountPanel.add(manageDiscountLabel);
        manageDiscountPanel.add(customerIDLabel2);
        manageDiscountPanel.add(customerIDTextfield2);
        manageDiscountPanel.add(flexibleDiscountRadio);
        manageDiscountPanel.add(fixedDiscountRadio);
        manageDiscountPanel.add(fixedRateTextfield);
        manageDiscountPanel.add(backButton);



        leftLayeredPane.add(assignBlankPanel);
        leftLayeredPane.add(updateCustomerPanel);
        leftLayeredPane.add(manageDiscountPanel);
        panel3.add(leftLayeredPane,BorderLayout.CENTER);


        viewReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reportPanel.setVisible(true);
                stockPanel.setVisible(false);
                customerPanel.setVisible(false);
                assignBlankPanel.setVisible(false);
                updateCustomerPanel.setVisible(false);
                advisorPanel.setVisible(false);
                manageDiscountPanel.setVisible(false);



            }
        });

        viewStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stockPanel.setVisible(true);
                assignBlankPanel.setVisible(true);
                reportPanel.setVisible(false);
                customerPanel.setVisible(false);
                updateCustomerPanel.setVisible(false);
                advisorPanel.setVisible(false);
                manageDiscountPanel.setVisible(false);



            }
        });
        viewCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerPanel.setVisible(true);
                updateCustomerPanel.setVisible(true);
                reportPanel.setVisible(false);
                stockPanel.setVisible(false);
                assignBlankPanel.setVisible(false);
                advisorPanel.setVisible(false);


            }
        });
        viewTransactionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                transactionPanel.setVisible(true);
                customerPanel.setVisible(false);
                updateCustomerPanel.setVisible(false);
                reportPanel.setVisible(false);
                stockPanel.setVisible(false);
                assignBlankPanel.setVisible(false);
                advisorPanel.setVisible(false);
                manageDiscountPanel.setVisible(false);

            }
        });
        viewTravelAdvisorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                advisorPanel.setVisible(true);
                transactionPanel.setVisible(false);
                customerPanel.setVisible(false);
                updateCustomerPanel.setVisible(false);
                reportPanel.setVisible(false);
                stockPanel.setVisible(false);
                assignBlankPanel.setVisible(false);
                manageDiscountPanel.setVisible(false);
            }
        });
        manageDiscountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manageDiscountPanel.setVisible(true);
                updateCustomerPanel.setVisible(false);
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCustomerPanel.setVisible(true);
                manageDiscountPanel.setVisible(false);
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginForm loginForm = new LoginForm();
                dispose();

            }
        });
        sellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(customerIDTextfield.getText().trim().isEmpty()){
                    try{
                        // 1. get a connection
                        Connection con = DriverManager.getConnection(url, name, password);
                        //2. create a statement
                        String sql = "INSERT INTO payment"
                                + " (paymentType, amount)"
                                +"VALUES ( ?,?)";

                        PreparedStatement stm = con.prepareStatement(sql);
                        stm.setString(1, paymentTypeComboBox.getSelectedItem().toString());
                        stm.setFloat(2, Float.parseFloat(amountTextfield.getText()));

                        //3. execute sql query
                        stm.executeUpdate();

                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                }else{
                    try {
                        // 1. get a connection
                        Connection con = DriverManager.getConnection(url, name, password);

                        //2. create a statement
                        String sql = "INSERT INTO payment "
                                + " (paymentType, amount, CustomerAccountcustomerAccount_id)"
                                + "VALUES ( ?,?,?)";

                        PreparedStatement stm = con.prepareStatement(sql);

                        stm.setString(1, paymentTypeComboBox.getSelectedItem().toString());
                        stm.setFloat(2, Float.parseFloat(amountTextfield.getText()));
                        stm.setInt(3, Integer.parseInt(customerIDTextfield.getText()));

                        //3. execute sql query
                        stm.executeUpdate();
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }

                }
            }
        });
        createCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                // 1. get a connection
                Connection con = DriverManager.getConnection(url, name , password);

                //2. create a statement
                String sql = "INSERT INTO customeraccount"
                        + " (name, dateOfBirth, address, postalCode, telephone, email)"
                        + "VALUES ( ?, ?, ?, ?, ?, ?)";


                PreparedStatement stm = con.prepareStatement(sql);

                stm.setString(1, nameTextField.getText());
                stm.setDate(2, Date.valueOf(dateOfBirthTextField.getText()));
                stm.setString(3, addressTextField.getText());
                stm.setString(4, postalCodeTextField.getText());
                stm.setLong(5, Long.parseLong(telephoneTextField.getText()));
                stm.setString(6, emailTextField.getText());


                //3. execute sql query
                stm.executeUpdate();


            }catch (Exception ex){
                ex.printStackTrace();
            }
            }
        });

        updateCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    // 1. get a connection
                    Connection con = DriverManager.getConnection(url, name , password);

                    //2. create a statement
                    String sql = "UPDATE customeraccount "
                            + "SET name=?,dateOfBirth=?,address=?,postalCode=?,telephone=?,email=?"
                            + "WHERE customerAccount_id=?";

                    PreparedStatement stm = con.prepareStatement(sql);
                    stm.setString(1, nameTextField.getText());
                    stm.setDate(2,Date.valueOf(dateOfBirthTextField.getText()));
                    stm.setString(3, addressTextField.getText());
                    stm.setString(4, postalCodeTextField.getText());
                    stm.setLong(5, Long.parseLong(telephoneTextField.getText()));
                    stm.setString(6, emailTextField.getText());
                    stm.setInt(7,Integer.parseInt(IDTextField.getText()));



                    //3. execute sql query
                    stm.executeUpdate();



                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });

        add(panel1);




        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(750,500);
//        setVisible(true);
    }

}
