package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.sql.*;

import component.PlaceholderTextField;
import database.DatabaseHelper;


public class OfficeManagerForm extends JFrame {
    private int userID;
    DatabaseHelper db = new DatabaseHelper();
    Connection con = db.getConnection();


    public OfficeManagerForm(int currentID){
        // Frame setup
        super("Welcome Office Manager");
        this.userID = currentID;

        JTabbedPane tabPane = new JTabbedPane();
        JPanel centrePanel = new JPanel(new BorderLayout());
        JPanel leftPanel = new JPanel(new BorderLayout());
        JPanel lowerPanel = new JPanel();
        JPanel rightPanel = new JPanel();

        // Sets the layout of the JFrame
        this.add(centrePanel, BorderLayout.CENTER);
        this.add(leftPanel, BorderLayout.LINE_START);
        this.add(lowerPanel, BorderLayout.PAGE_END);
        this.add(rightPanel, BorderLayout.LINE_END);

        // **** LOWER PANEL ****

        // Make buttons
        JButton domesticReportButton = new JButton("GenerateDomesticReport");
        JButton interlineReportButton = new JButton("GenerateInterlineReport");
        JButton turnoverReportButton = new JButton("GenerateTurnoverReport");
        JButton logoutButton = new JButton("Logout");

        // Add buttons panel
        lowerPanel.add(domesticReportButton);
        lowerPanel.add(interlineReportButton);
        lowerPanel.add(turnoverReportButton);
        lowerPanel.add(logoutButton);

        // **** CENTRE PANEL ****

        // Set Panels into tabs
        centrePanel.add(tabPane);
        JPanel stockPanel = new JPanel(new BorderLayout());
        JPanel reportPanel = new JPanel(new BorderLayout());
        JPanel customerPanel = new JPanel(new BorderLayout());
        JPanel sellPanel = new JPanel(new BorderLayout());
        JPanel advisorPanel = new JPanel(new BorderLayout());
        JPanel tranPanel = new JPanel(new BorderLayout());
        JPanel updateCustomerPanel = new JPanel();
        JPanel assignBlankPanel = new JPanel();
        JPanel manageDiscountPanel = new JPanel();


        // Add tabs to the TabbedPane
        ImageIcon stockIcon = new ImageIcon("data/stockIcon.png");
        tabPane.addTab("Stock", stockIcon, stockPanel, "See all stock");

        ImageIcon docIcon = new ImageIcon("data/docIcon.png");
        tabPane.addTab("Reports", docIcon, reportPanel, "See all reports");

        ImageIcon cartIcon = new ImageIcon("data/cartIcon.png");
        tabPane.addTab("Customers", cartIcon, customerPanel, "See all customers");

        ImageIcon tranIcon = new ImageIcon("data/tranIcon.png");
        tabPane.addTab("Transactions", tranIcon, tranPanel, "See all sold tickets");

        ImageIcon userIcon = new ImageIcon("data/userIcon.png");
        tabPane.addTab("Travel Advisors", userIcon, advisorPanel, "See all travel advisors");

        // Set Sell Ticket Panel
        JLabel sellTicketTitle = new JLabel("Sell a ticket");
        PlaceholderTextField amountTextfield = new PlaceholderTextField();
        amountTextfield.setPlaceholder("Amount");
        JLabel paymentTypeLabel = new JLabel("Payment type");
        JCheckBox cashPaymentBox = new JCheckBox("Cash", false);
        JCheckBox cardPaymentBox = new JCheckBox("Card", false);
        PlaceholderTextField customerIDTextfield = new PlaceholderTextField();
        customerIDTextfield.setPlaceholder("CustomerID");

        JButton sellButton = new JButton("Sell");


        // Show stock table in Stock panel
        JTable stockTable = new JTable();
        DefaultTableModel dtmStock = new DefaultTableModel();
        stockTable.setModel(dtmStock);
        dtmStock.setColumnIdentifiers(new Object[]{"ID", "Assigned to ID", "Type", "Flight Type"});

        try {
            String getStock = "SELECT * FROM stock";
            PreparedStatement getStockStm = null;
            getStockStm = con.prepareStatement(getStock);
            ResultSet getStockRs = getStockStm.executeQuery(getStock);

            while (getStockRs.next()) {
                int blankID = getStockRs.getInt(1);
                int staffID = getStockRs.getInt(2);
                int type = getStockRs.getInt(3);
                String fType = getStockRs.getString(4);
                //int customerID = getTranRs.getInt("CustomerID");
                dtmStock.addRow(new Object[]{blankID, staffID, type, fType});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        stockPanel.add(new JScrollPane(stockTable));

        // **** Temporary Report List ****
        String[] s2 = {"report: 1","report: 2","report: 3","report: 4","report: 5"};
        JList report = new JList(s2);
        JScrollPane jScrollPane2 = new JScrollPane(report);
        reportPanel.add(jScrollPane2,BorderLayout.CENTER);
//        report.setVisibleRowCount(4);
        reportPanel.setBounds(0,0,600,600);

        // Show Customer table in customer panel
        JTable customerTable = new JTable();
        DefaultTableModel dtmCust = new DefaultTableModel();
        customerTable.setModel(dtmCust);
        dtmCust.setColumnIdentifiers(new Object[]{"ID", "Forename", "Surname", "D.O.B", "Telephone", "Email", "Type", "Discount"});

        try {
            String getCustomer = "SELECT * FROM customer";
            PreparedStatement getCustomerStm = null;
            getCustomerStm = con.prepareStatement(getCustomer);
            ResultSet getCustomerRs = getCustomerStm.executeQuery(getCustomer);

            while (getCustomerRs.next()) {
                int id = getCustomerRs.getInt(1);
                String fname = getCustomerRs.getString(2);
                String sname = getCustomerRs.getString(3);
                String date = getCustomerRs.getString(4);
                int tel = getCustomerRs.getInt(5);
                String email = getCustomerRs.getString(6);
                String type = getCustomerRs.getString(7);
                int discount = getCustomerRs.getInt(8);
                //int customerID = getTranRs.getInt("CustomerID");
                dtmCust.addRow(new Object[]{id, fname, sname, date, tel, email, type, discount});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        customerPanel.add(new JScrollPane(customerTable));

        // Show Advisors in the advisor panel
        JTable advisorTable = new JTable();
        DefaultTableModel dtmAdvisor = new DefaultTableModel();
        advisorTable.setModel(dtmAdvisor);
        dtmAdvisor.setColumnIdentifiers(new Object[]{"ID", "Forename", "Surname", "D.O.B", "Telephone", "Email"});

        try {
            String getAdvisor = "SELECT ID, Forename, Surname, Address, Telephone, Email FROM staff WHERE Type = 'ta'";
            PreparedStatement getAdvisorStm = null;
            getAdvisorStm = con.prepareStatement(getAdvisor);
            ResultSet getStaffRs = getAdvisorStm.executeQuery(getAdvisor);

            while (getStaffRs.next()) {
                int id = getStaffRs.getInt(1);
                String fname = getStaffRs.getString(2);
                String sname = getStaffRs.getString(3);
                String date = getStaffRs.getString(4);
                int tel = getStaffRs.getInt(5);
                String email = getStaffRs.getString(6);
                //int customerID = getTranRs.getInt("CustomerID");
                dtmAdvisor.addRow(new Object[]{id, fname, sname, date, tel, email});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        advisorPanel.add(new JScrollPane(advisorTable));

        // Sold Transactions panel
        JTable transactionTable = new JTable();
        DefaultTableModel dtmTrans = new DefaultTableModel();
        transactionTable.setModel(dtmTrans);
        dtmTrans.setColumnIdentifiers(new Object[]{"ID", "Date", "Price", "Discount Type"});

        try {
            String getTran = "SELECT * FROM soldtickets";
            PreparedStatement getTranStm = null;
            getTranStm = con.prepareStatement(getTran);
            ResultSet getTranRs = getTranStm.executeQuery(getTran);

            while (getTranRs.next()) {
                int ticketID = getTranRs.getInt("TicketID");
                String date = getTranRs.getString("Date");
                int price = getTranRs.getInt("Price");
                String discountType = getTranRs.getString("DiscountType");
                //int customerID = getTranRs.getInt("CustomerID");
                dtmTrans.addRow(new Object[]{ticketID, date, price, discountType});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        tranPanel.add(new JScrollPane(transactionTable));

        /*
        // Set Sell Ticket Panel Layout
        sellPanel.add(sellTicketTitle);
        sellTicketTitle.setBounds(25, 10, 200, 100);
        sellTicketTitle.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));

        sellPanel.add(amountTextfield);
        amountTextfield.setBounds(sellTicketTitle.getX(), 175, 50, 25);

        sellPanel.add(paymentTypeLabel);
        paymentTypeLabel.setBounds(amountTextfield.getX(), 250, 100, 25);
        paymentTypeLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));

        sellPanel.add(cashPaymentBox); sellPanel.add(cardPaymentBox);
        //cashPaymentBox.setBounds()

        sellPanel.add(customerIDTextfield);
        sellPanel.add(sellButton);
        sellPanel.setLayout(null);

        sellPanel.setPreferredSize(centrePanel.getPreferredSize());
         */

        // Tabbed Pane Action Listener
        tabPane.addChangeListener(e -> {
            JTabbedPane tPane = (JTabbedPane) e.getSource();
            if (tPane.getSelectedIndex() == tPane.indexOfTab("Stock")){
                assignBlankPanel.setVisible(true);
                manageDiscountPanel.setVisible(false);
                updateCustomerPanel.setVisible(false);
            } else if (tPane.getSelectedIndex() == tPane.indexOfTab("Customers")){
                updateCustomerPanel.setVisible(true);
                manageDiscountPanel.setVisible(true);
                assignBlankPanel.setVisible(false);
            } else if(tPane.getSelectedIndex() == tPane.indexOfTab("Reports")) {
                assignBlankPanel.setVisible(false);
                updateCustomerPanel.setVisible(false);
                manageDiscountPanel.setVisible(false);
            } else if(tPane.getSelectedIndex() == tPane.indexOfTab("Transactions")){
                manageDiscountPanel.setVisible(false);
                updateCustomerPanel.setVisible(false);
                assignBlankPanel.setVisible(false);
            } else if (tPane.getSelectedIndex() == tPane.indexOfTab("Travel Advisors")){
                assignBlankPanel.setVisible(true);
                updateCustomerPanel.setVisible(false);
                manageDiscountPanel.setVisible(false);
            }
        });

        //sets the right part of the layout
        JLabel changeCommissionLabel = new JLabel("Change commission rate");
        JLabel cancelTicketLabel = new JLabel("Cancel ticket");
        PlaceholderTextField changeCommissionTextfield = new PlaceholderTextField();
        changeCommissionTextfield.setPlaceholder("New rate");
        PlaceholderTextField cancelTicketTextfield = new PlaceholderTextField();
        cancelTicketTextfield.setPlaceholder("Ticket ID");
        JButton cancelTicketButton = new JButton("cancel ticket");
        JButton createCustomerButton = new JButton("Create customer");
        JButton updateCustomerButton = new JButton("Update customer");

        rightPanel.setLayout(new BoxLayout(rightPanel,BoxLayout.Y_AXIS));
        rightPanel.add(cancelTicketLabel);
        rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
        rightPanel.add(cancelTicketTextfield);
        rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
        rightPanel.add(cancelTicketButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
        rightPanel.add(changeCommissionLabel);
        rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
        rightPanel.add(changeCommissionTextfield);
        rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
        rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
        rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
        rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
        rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
        rightPanel.add(createCustomerButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
        rightPanel.add(updateCustomerButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
        rightPanel.add(Box.createRigidArea(new Dimension(0,280)));



        //sets the left part of the layout
        JLayeredPane leftLayeredPane = new JLayeredPane();
        leftLayeredPane.setPreferredSize(new Dimension(150,150));

        assignBlankPanel.setLayout(new BoxLayout(assignBlankPanel,BoxLayout.Y_AXIS));
        //assignBlankPanel.setVisible(false);
        assignBlankPanel.setBounds(0,0,150,450);
        JLabel assignBlank = new JLabel("Assign blank");
        JLabel idLabel = new JLabel("id");
        PlaceholderTextField idTextfield = new PlaceholderTextField();
        idTextfield.setPlaceholder("blank id");
        JLabel idLabel2 = new JLabel("staff id");
        PlaceholderTextField idTextfield2 = new PlaceholderTextField();
        idTextfield2.setPlaceholder("staff id");
        JButton assignBlankButton = new JButton("assign blank");
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
        assignBlankPanel.add(idLabel);
        assignBlankPanel.add(Box.createRigidArea(new Dimension(0,15)));
        assignBlankPanel.add(idTextfield);
        assignBlankPanel.add(Box.createRigidArea(new Dimension(0,15)));
        assignBlankPanel.add(idLabel2);
        assignBlankPanel.add(Box.createRigidArea(new Dimension(0,15)));
        assignBlankPanel.add(idTextfield2);
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
        leftPanel.add(leftLayeredPane,BorderLayout.CENTER);


        // Set Action listeners for each
        cancelTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    // Create a statement
                    String sql = "DELETE from payment WHERE payment_id=?";
                    PreparedStatement stm = con.prepareStatement(sql);
                    stm.setInt(1, Integer.parseInt(cancelTicketTextfield.getText()));

                    // Execute SQL statement
                    stm.executeUpdate();
                }catch (Exception ex){
                    ex.printStackTrace();
                }
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
                try {
                    con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });
        sellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(customerIDTextfield.getText().trim().isEmpty()){
                    try{
                        String sql = "INSERT INTO payment"
                                + " (paymentType, amount)"
                                +"VALUES ( ?,?)";

                        PreparedStatement stm = con.prepareStatement(sql);
                        //stm.setString(1, paymentTypeComboBox.getSelectedItem().toString());
                        stm.setFloat(2, Float.parseFloat(amountTextfield.getText()));

                        //3. execute sql query
                        stm.executeUpdate();

                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                }else{
                    try {
                        //2. create a statement
                        String sql = "INSERT INTO payment "
                                + " (paymentType, amount, CustomerAccountcustomerAccount_id)"
                                + "VALUES ( ?,?,?)";

                        PreparedStatement stm = con.prepareStatement(sql);

                        //stm.setString(1, paymentTypeComboBox.getSelectedItem().toString());
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
        assignBlankButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //2. create a statement
                    String sql = "UPDATE blank "
                            + " SET Staffstaff_id=?"
                            + " WHERE blank_id=?";

                    PreparedStatement stm = con.prepareStatement(sql);
                    stm.setInt(1, Integer.parseInt(idTextfield2.getText()));
                    stm.setInt(2, Integer.parseInt(idTextfield.getText()));

                    //3. execute sql query
                    stm.executeUpdate();


                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        });

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(960,720);
//        setVisible(true);
        System.out.println("this user has logged in ->" + getID());

    }
    public int getID(){
        return userID;
    }
}
