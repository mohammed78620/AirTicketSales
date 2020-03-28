package gui;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import container.*;
import component.PlaceholderTextField;
import database.DatabaseHelper;
import domain.Customer;
import domain.Discount;
import domain.SoldTicket;
import domain.Stock;
import java.time.LocalDateTime;


public class TravelAdvisorForm extends JFrame {
    private int id;
    private JPanel panel1;
    private JPanel centerPanel;
    private JPanel bottomPanel;
    private JPanel rightPanel;
    private JPanel leftPanel;
    private Connection con;
    private DatabaseHelper db = new DatabaseHelper();
    private String url = "jdbc:mysql://localhost:3306/databasename";
    private String name = "root";
    private String password = "kamal997";

    public TravelAdvisorForm(int id){
        super("Travel Advisor page");
        this.id = id;
        panel1 = new JPanel(new BorderLayout());

        centerPanel = new JPanel(new BorderLayout());
        rightPanel = new JPanel();
        leftPanel = new JPanel(new BorderLayout());
        bottomPanel = new JPanel();

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBackground(Color.RED);
        JLayeredPane leftLayeredPane = new JLayeredPane();
        leftLayeredPane.setPreferredSize(new Dimension(150,150));
        JPanel customerPanel = new JPanel(new GridLayout(8,8,5,40));
        JPanel discountPanel = new JPanel(new GridLayout(8,4,5,40));
        JPanel soldTicketsPanel = new JPanel(new GridLayout(8,9,5,40));
        JPanel reportPanel = new JPanel(new BorderLayout());
        JPanel stockPanel =  new JPanel(new GridLayout(8,4,5,40));
        add(panel1);

        //sets Panel Layout
        rightPanel.setLayout(new BoxLayout(rightPanel,BoxLayout.Y_AXIS));

        //sets up BorderLayout
        panel1.add(centerPanel,BorderLayout.CENTER);
        panel1.add(rightPanel,BorderLayout.LINE_END);
        panel1.add(leftPanel,BorderLayout.LINE_START);
        panel1.add(bottomPanel,BorderLayout.PAGE_END);
        centerPanel.add(layeredPane,BorderLayout.CENTER);
        layeredPane.setBounds(0,0,600,600);

//        sets up center BorderLayout
        customerPanel.setLayout(new BorderLayout());
        JTable customer = new JTable();
        JScrollPane jScrollPane1 = new JScrollPane(customer);
        customerPanel.add(jScrollPane1,BorderLayout.CENTER);
        customerPanel.setBounds(0,0,600,600);

        soldTicketsPanel.setLayout(new BorderLayout());
        JTable soldTickets = new JTable();
        JScrollPane jScrollPane4 = new JScrollPane(soldTickets);
        soldTicketsPanel.add(jScrollPane4,BorderLayout.CENTER);
        soldTicketsPanel.setBounds(0,0,800,600);

        discountPanel.setLayout(new BorderLayout());
        JTable discount = new JTable();
        JScrollPane jScrollPane2 = new JScrollPane(discount);
        discountPanel.add(jScrollPane2,BorderLayout.CENTER);
        discountPanel.setBounds(0,0,600,600);

        stockPanel.setLayout(new BorderLayout());
        JTable stock = new JTable();
        JScrollPane jScrollPane3 = new JScrollPane(stock);
        discountPanel.add(jScrollPane3,BorderLayout.CENTER);
        discountPanel.setBounds(0,0,600,600);

        String[] s2 = {"report: 1","report: 2","report: 3","report: 4","report: 5"};
        JList reports = new JList(s2);
        JScrollPane scrollPane12 = new JScrollPane(reports);
        reportPanel.add(scrollPane12,BorderLayout.CENTER);
        reportPanel.setBounds(0,0,600,600);


        CancelTicketPanel cancelTicketPanel = new CancelTicketPanel();
        cancelTicketPanel.setLayout(new BoxLayout(cancelTicketPanel,BoxLayout.Y_AXIS));
        cancelTicketPanel.setVisible(false);

        GenerateReportPanel generateReportPanel = new GenerateReportPanel();
        generateReportPanel.setLayout(new BoxLayout(generateReportPanel,BoxLayout.Y_AXIS));
        generateReportPanel.setVisible(false);

        layeredPane.add(customerPanel);
        layeredPane.add(reportPanel);
        layeredPane.add(stockPanel);
        layeredPane.add(cancelTicketPanel);
        layeredPane.add(discountPanel);
        layeredPane.add(generateReportPanel);
        layeredPane.add(soldTicketsPanel);

        //sets up main left BorderLayout
        ManageCustomerPanel manageCustomerPanel = new ManageCustomerPanel();
        manageCustomerPanel.setLayout(new BoxLayout(manageCustomerPanel,BoxLayout.Y_AXIS));
        manageCustomerPanel.setVisible(false);

        ManageDiscountsPanel manageDiscountsPanel = new ManageDiscountsPanel();
        manageDiscountsPanel.setLayout(new BoxLayout(manageDiscountsPanel,BoxLayout.Y_AXIS));
        manageDiscountsPanel.setVisible(false);

        UpdateCustomerPanel updateCustomerPanel = new UpdateCustomerPanel();
        updateCustomerPanel.setLayout(new BoxLayout(updateCustomerPanel,BoxLayout.Y_AXIS));
        updateCustomerPanel.setVisible(false);


        leftLayeredPane.add(manageCustomerPanel);
        leftLayeredPane.add(manageDiscountsPanel);
        leftLayeredPane.add(updateCustomerPanel);
        leftPanel.add(leftLayeredPane,BorderLayout.CENTER);

        //sets up bottom BorderLayout
        JButton viewGenerateIndividualReport = new JButton("generate report");
        JButton logoutButton = new JButton("Logout");
        bottomPanel.add(logoutButton);
        bottomPanel.add(viewGenerateIndividualReport);
        //sets up right BorderLayout
        JButton viewCustomers = new JButton("view customer");
        JButton sellTicketButton = new JButton("sell ticket");
        JButton createAccountButton = new JButton("create account");
        JButton viewIndividualReport = new JButton("view reports");
        JButton viewIndividualStock = new JButton("view stock");
        JButton viewCancelTicketButton = new JButton("cancel ticket");
        JButton refundLogButton = new JButton("refund log file");
        JButton viewSoldTicketsButton = new JButton("view tickets sold");

        //adds to right container
        rightPanel.add(viewCustomers);
        rightPanel.add(Box.createRigidArea(new Dimension(0,15)));
        rightPanel.add(viewSoldTicketsButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0,15)));
        rightPanel.add(sellTicketButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0,15)));
        rightPanel.add(viewCancelTicketButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0,15)));
        rightPanel.add(createAccountButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0,15)));
        rightPanel.add(viewIndividualReport);
        rightPanel.add(Box.createRigidArea(new Dimension(0,15)));
        rightPanel.add(refundLogButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0,15)));
        rightPanel.add(viewIndividualStock);
        rightPanel.add(Box.createRigidArea(new Dimension(0,260)));

        //sets up buttonListeners
        manageCustomerPanel.viewUpdateButton.addActionListener(e -> {
            manageCustomerPanel.setVisible(false);
            updateCustomerPanel.setVisible(true);
        });
        manageCustomerPanel.backButton.addActionListener(e -> {
            manageCustomerPanel.setVisible(false);
        });
        manageDiscountsPanel.backButton.addActionListener(e -> {
            manageCustomerPanel.setVisible(true);
            manageDiscountsPanel.setVisible(false);
        });
        updateCustomerPanel.backButton.addActionListener(e -> {
            manageCustomerPanel.setVisible(true);
            updateCustomerPanel.setVisible(false);
        });
        manageDiscountsPanel.setDiscount.addActionListener(e -> {
            manageCustomerPanel.setVisible(false);
            manageDiscountsPanel.setVisible(true);
            discountPanel.setVisible(true);
            customerPanel.setVisible(false);
            updateCustomerPanel.setVisible(false);
            reportPanel.setVisible(false);
            stockPanel.setVisible(false);
            cancelTicketPanel.setVisible(false);
            generateReportPanel.setVisible(false);
            soldTicketsPanel.setVisible(false);

            try{
                // 1. get a connection
                con = db.getConnection();

                //2. create a statement
                String sql = "UPDATE customer "
                        + "SET discount=?, discountType=?"
                        + "WHERE Type= 'valued'";

                PreparedStatement stm = con.prepareStatement(sql);

                stm.setInt(1, Integer.parseInt(manageDiscountsPanel.discountTextField.getText()));
                stm.setString(2, manageDiscountsPanel.typeBox.getSelectedItem().toString());

                //3. execute sql query
                stm.executeUpdate();



            }catch (Exception ex){
                ex.printStackTrace();
            }
        });
        manageCustomerPanel.manageDiscountButton.addActionListener(e -> {
            manageCustomerPanel.setVisible(false);
            manageDiscountsPanel.setVisible(true);
            discountPanel.setVisible(true);
            customerPanel.setVisible(false);
            updateCustomerPanel.setVisible(false);
            reportPanel.setVisible(false);
            stockPanel.setVisible(false);
            cancelTicketPanel.setVisible(false);
            generateReportPanel.setVisible(false);
            soldTicketsPanel.setVisible(false);


            List<Discount> discounts = new ArrayList<>();
            try {
                con = db.getConnection();
                Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery("SELECT customerid, forename, surname, Type, discount, discountType" +
                        " FROM customer WHERE Type= 'valued'");


                while(rs.next()) {
                    discounts.add(new Discount(rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getInt(5),
                            rs.getString(6)));
                }
                Discount b;
                DefaultTableModel model =new DefaultTableModel();
                model.addColumn("customer ID: ");
                model.addColumn("forename: ");
                model.addColumn("surname: ");
                model.addColumn("customer type: ");
                model.addColumn("discount: ");
                model.addColumn("discount type: ");
                discount.setModel(model);

                for (int i = 0; i < discounts.size(); i++) {
                    b = discounts.get(i);
                    model.addRow(b.rowArray());

                }
            }catch(SQLException ex) {
                ex.printStackTrace();
            }
        });


        updateCustomerPanel.updateButton.addActionListener(e -> {

            try{
                // 1. get a connection
                con = db.getConnection();

                //2. create a statement
                String sql = "UPDATE customer "
                        + "SET forename=?, surname=?, dateOfBirth=?,telephone=?,email=?, Type=?"
                        + "WHERE customerid=?";

                PreparedStatement stm = con.prepareStatement(sql);

                stm.setString(1, updateCustomerPanel.forenameTextField.getText());
                stm.setString(2, updateCustomerPanel.surnameTextField.getText());
                stm.setDate(3,Date.valueOf(updateCustomerPanel.dateOfBirthTextField.getText()));
                stm.setInt(4, Integer.parseInt(updateCustomerPanel.telephoneTextField.getText()));
                stm.setString(5, updateCustomerPanel.emailTextField.getText());
                stm.setString(6, updateCustomerPanel.typeBox.getSelectedItem().toString());
                stm.setInt(7,Integer.parseInt(updateCustomerPanel.IDField.getText()));

                //3. execute sql query
                stm.executeUpdate();



            }catch (Exception ex){
                ex.printStackTrace();
            }
        });
        viewSoldTicketsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerPanel.setVisible(false);
                manageCustomerPanel.setVisible(false);
                reportPanel.setVisible(false);
                stockPanel.setVisible(false);
                cancelTicketPanel.setVisible(false);
                discountPanel.setVisible(false);
                manageDiscountsPanel.setVisible(false);
                generateReportPanel.setVisible(false);
                updateCustomerPanel.setVisible(false);
                soldTicketsPanel.setVisible(true);
                leftPanel.setVisible(false);

                List<SoldTicket> ticketsSold = new ArrayList<>();
                try {
                    con = db.getConnection();

                    String sql ="SELECT sale_id, salesDate, tax," +
                            "subTotal, grandTotal, salesNote, customeraccountcustomeraccount_id," +
                            "paymentpayment_id, commisioncommision_id FROM sale WHERE staffstaff_id =? ";
                    PreparedStatement stm = con.prepareStatement(sql);
                    stm.setInt(1, getID());
                    ResultSet rs = stm.executeQuery();

                    while(rs.next()){
                        ticketsSold.add(new SoldTicket(rs.getInt(1),
                                rs.getTimestamp(2),
                                rs.getInt(3),
                                rs.getInt(4),
                                rs.getInt(5),
                                rs.getInt(6),
                                rs.getInt(7),
                                rs.getInt(8),
                                rs.getInt(9)));
                    }
                    SoldTicket b;
                    DefaultTableModel model =new DefaultTableModel();
                    model.addColumn("Sales ID: ");
                    model.addColumn("sales date: ");
                    model.addColumn("tax: ");
                    model.addColumn("sub total ");
                    model.addColumn("grand total: ");
                    model.addColumn("sales note: ");
                    model.addColumn("customer ID: ");
                    model.addColumn("payment ID: ");
                    model.addColumn("commission ID: ");
                    soldTickets.setModel(model);

                    for (int i = 0; i < ticketsSold.size(); i++) {
                        b = ticketsSold.get(i);
                        model.addRow(b.rowArray());

                    }
                }catch(SQLException ex){
                    ex.printStackTrace();
                }
            }
        });
        viewCustomers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerPanel.setVisible(true);
                manageCustomerPanel.setVisible(true);
                reportPanel.setVisible(false);
                stockPanel.setVisible(false);
                cancelTicketPanel.setVisible(false);
                discountPanel.setVisible(false);
                manageDiscountsPanel.setVisible(false);
                generateReportPanel.setVisible(false);
                soldTicketsPanel.setVisible(false);

                List<Customer> customers = new ArrayList<>();
                try {
                    con = db.getConnection();
                    Statement stm = con.createStatement();
                    ResultSet rs = stm.executeQuery("SELECT * FROM customer");

                    while(rs.next()){
                        customers.add(new Customer(rs.getInt(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getDate(4),
                                rs.getInt(5),
                                rs.getString(6),
                                rs.getString(7),
                                rs.getInt(8),
                                rs.getString(9)));
                    }
                    Customer b;
                    DefaultTableModel model =new DefaultTableModel();
                    model.addColumn("customer ID: ");
                    model.addColumn("forename: ");
                    model.addColumn("surname: ");
                    model.addColumn("date of birth: ");
                    model.addColumn("telephone: ");
                    model.addColumn("email: ");
                    model.addColumn("customer type: ");
                    model.addColumn("discount: ");
                    model.addColumn("discount type: ");
                    customer.setModel(model);

                    for (int i = 0; i < customers.size(); i++) {
                        b = customers.get(i);
                        model.addRow(b.rowArray());

                    }
                }catch(SQLException ex){
                    ex.printStackTrace();
                }
            }
        });
        viewIndividualReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reportPanel.setVisible(true);
                customerPanel.setVisible(false);
                stockPanel.setVisible(false);
                manageCustomerPanel.setVisible(false);
                cancelTicketPanel.setVisible(false);
                generateReportPanel.setVisible(false);
                soldTicketsPanel.setVisible(false);
            }
        });

        JFrame sellFrame = new JFrame("Sell ticket");
        JPanel sellPanel = new JPanel();
        sellPanel.setBackground(Color.WHITE);

        sellFrame.setSize(500,500);
        sellPanel.setSize(sellFrame.getSize());
        sellPanel.setLayout(null);

        sellFrame.add(sellPanel);

        // TESTING COMMAND
        //sellFrame.setVisible(true);

        // **** Set Sell Ticket components ****

        // Title
        JLabel sellTicketTitle = new JLabel("Sell a ticket");

        // Enter customer ID
        JLabel customerIDText = new JLabel("Customer ID:");
        customerIDText.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        PlaceholderTextField customerIDBox = new PlaceholderTextField();
        customerIDBox.setPlaceholder("CustomerID");

        // Destination input
        JLabel destinationLabel = new JLabel("Destination:");
        destinationLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        PlaceholderTextField destinationBox = new PlaceholderTextField();
        destinationBox.setPlaceholder("Destination");

        // Enter price
        JLabel ticketAmountLabel = new JLabel("Amount:");
        ticketAmountLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        PlaceholderTextField ticketAmount = new PlaceholderTextField();
        ticketAmount.setPlaceholder("Amount");

        // Blank drop-down box
        JLabel blankTypeLabel = new JLabel("BlankType");
        blankTypeLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        String [] blankTypes = {"444", "440", "420", "201", "101"};
        JComboBox blankTypeBox = new JComboBox(blankTypes);

        // Payment type drop-down box
        JLabel paymentTypeLabel = new JLabel("Payment type");
        paymentTypeLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        String [] paymentTypes = {"Cash", "Card", "Pay later"};
        JComboBox paymentTypeBox = new JComboBox(paymentTypes);

        // Card details
        PlaceholderTextField cardName= new PlaceholderTextField(); cardName.setPlaceholder("Name");
        PlaceholderTextField cardNo = new PlaceholderTextField(); cardNo.setPlaceholder("Card Number");
        PlaceholderTextField cardCode = new PlaceholderTextField(); cardCode.setPlaceholder("CVC");
        PlaceholderTextField cardDate = new PlaceholderTextField(); cardDate.setPlaceholder("Expiry Date");
        PlaceholderTextField cardAddress = new PlaceholderTextField(); cardAddress.setPlaceholder("Card Address");

        // Show total price
        JLabel priceTitleLabel = new JLabel("Total Price:");
        JLabel priceLabel = new JLabel("0.00");

        // Confirm Purchase button
        JButton sellButton = new JButton("Confirm Payment");

        // **** Set Sell Ticket Panel Layout ****
        sellPanel.add(sellTicketTitle);
        sellTicketTitle.setBounds(25, 10, 175, 40);
        sellTicketTitle.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 27));
        sellTicketTitle.setHorizontalAlignment((int)CENTER_ALIGNMENT);
        sellTicketTitle.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Enter customer ID
        sellPanel.add(customerIDText);
        sellPanel.add(customerIDBox);
        customerIDText.setBounds(sellTicketTitle.getX(), sellTicketTitle.getY() + 60, 100, 20);
        customerIDBox.setBounds(customerIDText.getX() + 105, customerIDText.getY(), 125, 20);

        // Enter Destination
        sellPanel.add(destinationBox);
        sellPanel.add(destinationLabel);
        destinationLabel.setBounds(customerIDText.getX(), customerIDText.getY() + 30, 100, 20);
        destinationBox.setBounds(destinationLabel.getX() + 105, destinationLabel.getY(), 125, 20);

        // Enter Amount of ticket
        sellPanel.add(ticketAmountLabel);
        sellPanel.add(ticketAmount);
        ticketAmountLabel.setBounds(destinationLabel.getX(), destinationLabel.getY() + 30, 100, 20);
        ticketAmount.setBounds(ticketAmountLabel.getX() + 105, ticketAmountLabel.getY(), 125, 20);

        sellPanel.add(blankTypeLabel); sellPanel.add(blankTypeBox);
        blankTypeLabel.setBounds(ticketAmountLabel.getX(), ticketAmountLabel.getY() + 30, 100, 20);
        blankTypeBox.setBounds(blankTypeLabel.getX() + 105, blankTypeLabel.getY(), 125, 20);

        // Payment Title location
        sellPanel.add(paymentTypeLabel);
        paymentTypeLabel.setBounds(blankTypeLabel.getX(), blankTypeLabel.getY() + 40, 125, 35);

        //Payment type dropdown location
        sellPanel.add(paymentTypeBox);
        paymentTypeBox.setBounds(paymentTypeLabel.getX(), paymentTypeLabel.getY() + 40, 150, 20);

        // Card Details Positions
        sellPanel.add(cardName); sellPanel.add(cardNo); sellPanel.add(cardCode); sellPanel.add(cardDate); sellPanel.add(cardAddress);
        cardName.setBounds(paymentTypeBox.getX(), paymentTypeBox.getY() + 35, 125, 20);
        cardNo.setBounds(cardName.getX(), cardName.getY() + 30, 125, 20);
        cardCode.setBounds(cardNo.getX(), cardNo.getY() + 30, 125, 20);
        cardDate.setBounds(cardCode.getX(), cardCode.getY() + 30, 125, 20);
        cardAddress.setBounds(cardDate.getX(), cardDate.getY() + 30, 125, 20);

        cardName.setVisible(false);
        cardNo.setVisible(false);
        cardCode.setVisible(false);
        cardDate.setVisible(false);
        cardAddress.setVisible(false);

        // Set visibility of Card Details
        paymentTypeBox.addActionListener(event -> {
            JComboBox comboBox =  (JComboBox) event.getSource();

            if(comboBox.getSelectedItem().equals("Card")){
                cardName.setVisible(true);
                cardNo.setVisible(true);
                cardCode.setVisible(true);
                cardDate.setVisible(true);
                cardAddress.setVisible(true);
            }
            else {
                cardName.setVisible(false);
                cardNo.setVisible(false);
                cardCode.setVisible(false);
                cardDate.setVisible(false);
                cardAddress.setVisible(false);
            }
        });

        sellPanel.add(priceTitleLabel); sellPanel.add(priceLabel);
        priceTitleLabel.setBounds(cardAddress.getX(), cardAddress.getY() + 30, 100, 25);
        priceLabel.setBounds(priceTitleLabel.getX() + 75, priceTitleLabel.getY(), 100, 25);

        // Purchase Button
        sellPanel.add(sellButton);
        sellButton.setBounds(priceLabel.getX() + 175, priceLabel.getY(), 150, 25);
        sellButton.addActionListener(e -> {
            int custID = Integer.parseInt(customerIDBox.getText());
            long millis = System.currentTimeMillis();
            Date date = new java.sql.Date(millis);
            System.out.println(date);
            String dest = destinationBox.getText();
            String amnt = ticketAmount.getText();
            int blankType = Integer.parseInt(String.valueOf(blankTypeBox.getSelectedItem()));
            try{
                // Retrieves the blank needed from the database
                String selectBlank = "SELECT BlankID FROM stock WHERE StaffID = " + getID() +" AND Type = " + blankType + " AND Status = 'Assigned'";
                PreparedStatement selectBlankStm = null;
                selectBlankStm = con.prepareStatement(selectBlank);
                ResultSet rs = selectBlankStm.executeQuery(selectBlank);
                String currentBlank = "empty";
                while (rs.next()) {
                    currentBlank = rs.getString("BlankID");
                }

                // Adds the ticket to the sold_tickets section.
                String addTicket =  "INSERT INTO sold_tickets (Date, Price, Destination, BlankID, CustomerID) " +
                        "VALUES ('"
                        + date + "',"
                        + amnt + ",'"
                        + dest + "',"
                        + currentBlank + ","
                        + custID + ")";
                PreparedStatement addTicketStm = null;
                addTicketStm = con.prepareStatement(addTicket);
                addTicketStm.executeUpdate();

                String setBlank = "UPDATE stock SET Status = 'Used' WHERE BlankID = " + currentBlank;
                PreparedStatement setBlankStm = null;
                setBlankStm = con.prepareStatement(setBlank);
                setBlankStm.executeUpdate();
                System.out.println("UPDATED");
                JPanel receiptPanel = new JPanel();
                receiptPanel.setBounds(0, 0, 500,500);
                sellFrame.add(receiptPanel);
                sellPanel.setVisible(false);

                JLabel purchaseMessage = new JLabel("Purchase Completed");
                purchaseMessage.setBounds(250, 250, 100,100);
                purchaseMessage.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 27));
                receiptPanel.add(purchaseMessage);
                JButton close = new JButton("Close");
                close.setBounds(purchaseMessage.getX(), purchaseMessage.getY() + 100, 50, 25);
                receiptPanel.add(close);

                close.addActionListener(e2 -> {
                    sellFrame.dispose();
                });


            } catch (SQLException e1){
                e1.printStackTrace();
                JOptionPane.showMessageDialog(new JFrame(), "Purchase has failed", "Error", JOptionPane.ERROR_MESSAGE);
            }

        });

        sellTicketButton.addActionListener(e -> {
            sellFrame.setVisible(true);
        });

        viewIndividualStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manageCustomerPanel.setVisible(false);
                manageDiscountsPanel.setVisible(false);
                discountPanel.setVisible(true);
                customerPanel.setVisible(false);
                updateCustomerPanel.setVisible(false);
                reportPanel.setVisible(false);
                stockPanel.setVisible(true);
                cancelTicketPanel.setVisible(false);
                generateReportPanel.setVisible(false);
                soldTicketsPanel.setVisible(false);

                List<Stock> stocks = new ArrayList<>();
                try {

                    con = db.getConnection();
                    String sql = "SELECT blankid, type, status, dateadded" +
                            " FROM stock WHERE staffid=?";
                    PreparedStatement stm = con.prepareStatement(sql);
                    stm.setInt(1, getID());
                    ResultSet rs = stm.executeQuery();

                    while (rs.next()) {
                        stocks.add(new Stock(rs.getInt(1),
                                rs.getInt(2),
                                rs.getString(3),
                                rs.getTimestamp(4)));
                    }
                    Stock b;
                    DefaultTableModel model = new DefaultTableModel();
                    model.addColumn("blank ID: ");
                    model.addColumn("blank type: ");
                    model.addColumn("status: ");
                    model.addColumn("date added: ");
                    stock.setModel(model);

                    for (int i = 0; i < stocks.size(); i++) {
                        b = stocks.get(i);
                        model.addRow(b.rowArray());

                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });



        viewCancelTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelTicketPanel.setVisible(true);
                stockPanel.setVisible(false);
                customerPanel.setVisible(false);
                reportPanel.setVisible(false);
                manageCustomerPanel.setVisible(false);
                generateReportPanel.setVisible(false);
                updateCustomerPanel.setVisible(false);
                discountPanel.setVisible(false);
                soldTicketsPanel.setVisible(false);
                leftPanel.setVisible(true);
            }
        });
        cancelTicketPanel.cancelButton.addActionListener(e -> {
            try {

                // 1. get a connection
                con = db.getConnection();

                // 2. create a statement
                String sql = "DELETE FROM payment WHERE paymentid=?";
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setInt(1,Integer.parseInt(cancelTicketPanel.ticketIdTextfield.getText()));

                // 3. execute sql statement
                stm.executeUpdate();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        });
        cancelTicketPanel.refundButton.addActionListener(e -> {
            try {

                // 1. get a connection
                con = db.getConnection();

                // 2. create a statement
                String sql = "INSERT INTO refund"
                        + " (customerID, ticketID, amount, refundType, dateCancelled)"
                        + "VALUES (?, ?, ?, ?, ?)";

                PreparedStatement stm = con.prepareStatement(sql);
                LocalDateTime localDateTime = LocalDateTime.now();
                Timestamp timestamp = Timestamp.valueOf(localDateTime);

                stm.setInt(1,Integer.parseInt(cancelTicketPanel.IDTextField.getText()));
                stm.setInt(2,Integer.parseInt(cancelTicketPanel.ticketIdTextfield.getText()));
                stm.setInt(3,Integer.parseInt(cancelTicketPanel.amountTextfield.getText()));
                stm.setString(4, cancelTicketPanel.typeBox.getSelectedItem().toString());
                stm.setTimestamp(5, timestamp);


                // 3. execute sql statement
                stm.executeUpdate();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        });

        refundLogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    con = db.getConnection();
                    Statement mySmt = con.createStatement();
                    String sql = "SELECT ticketID, customerID, refundType, amount, dateCancelled FROM refund";
                    ResultSet rs = mySmt.executeQuery(sql);
                    File theFile = new File("refund.txt");
                    FileWriter fw = new FileWriter(theFile);
                    BufferedWriter bw = new BufferedWriter(fw);
                    String line;
                    while(rs.next()){
                        line = "["+rs.getTimestamp(5)+"]"+
                                " Ticket " + rs.getInt(1)+" has been refunded to Customer "
                                +rs.getInt(2)+" via "
                                +rs.getString(3)+", amount refunded is "
                                +rs.getInt(4)+".";
                        bw.write(line);
                        bw.newLine();
                    }
                    bw.close();
                    fw.close();

                    Desktop.getDesktop().open(theFile);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
    });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginForm loginForm = new LoginForm();
                dispose();
            }
        });
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // 1. get a connection
                    con = db.getConnection();

                    //2. create a statement
                    String sql = "INSERT INTO customer"
                            + " (customerID,forename, surname, dateOfBirth, telephone, email, Type)"
                            + "VALUES ( ?, ?, ?, ?, ?, ?, ?)";


                    PreparedStatement stm = con.prepareStatement(sql);

                    stm.setString(1, updateCustomerPanel.IDField.getText());
                    stm.setString(2, updateCustomerPanel.forenameTextField.getText());
                    stm.setString(3, updateCustomerPanel.surnameTextField.getText());
                    stm.setDate(4, Date.valueOf(updateCustomerPanel.dateOfBirthTextField.getText()));
                    stm.setInt(5, Integer.parseInt(updateCustomerPanel.telephoneTextField.getText()));
                    stm.setString(6, updateCustomerPanel.emailTextField.getText());
                    stm.setString(7, updateCustomerPanel.typeBox.getSelectedItem().toString());


                    //3. execute sql query
                    stm.executeUpdate();


                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });


//        viewIndividualReport.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    // 1. get a connection
//                    Connection myConn = DriverManager.getConnection(url, name, password);
//
//                    // 2. create a statement
//                    //report needs to be added to database with more data fields
//                    String sql = "SELECT (report_id, dateGenerated, endDate) "
//                            + "FROM report"
//                            + " WHERE staff_id=?";
//
//                    PreparedStatement stm = myConn.prepareStatement(sql);
//                    stm.setInt(1,Integer.parseInt(IDTextField.getText()));
//
//                    // 3. execute sql statement
//                    stm.executeUpdate();
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
//            }
//        });
//
//
        viewGenerateIndividualReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateReportPanel.setVisible(true);
                cancelTicketPanel.setVisible(false);
                stockPanel.setVisible(false);
                customerPanel.setVisible(false);
                reportPanel.setVisible(false);
                manageCustomerPanel.setVisible(false);
                discountPanel.setVisible(false);
                manageDiscountsPanel.setVisible(false);
                soldTicketsPanel.setVisible(false);
            }
        });
        generateReportPanel.generateButton.addActionListener(e -> {
            try {
                    // 1. get a connection
                    con = db.getConnection();

                    // 2. create a statement
                    //report needs to be added to database with more data fields
                    String sql = "INSERT INTO report "
                            + " (staffid, reportID, dateAdded, Type,reportFile)"
                            + "VALUES ( ?,?,?,?,?)";
                    PreparedStatement stm = con.prepareStatement(sql);
                stm.setInt(1, Integer.parseInt(generateReportPanel.staffIDTextfield.getText()));
                stm.setInt(2, Integer.parseInt(generateReportPanel.reportIDTextfield.getText()));
                stm.setDate(3,Date.valueOf(generateReportPanel.dateAddedTextField.getText()));
                stm.setString(4, generateReportPanel.typeBox.getSelectedItem().toString());
                stm.setString(5, generateReportPanel.reportFileTextField.getText());

                    // 3. execute sql statement
                    stm.executeUpdate();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(750,500);


    }
    public int getID(){
        return id;
    }
    public Connection getConnection(){
        try{
            con = DriverManager.getConnection(url,name,password);


        }catch (SQLException s){
            s.printStackTrace();
        }
        finally {
            return con;
        }
    }
}

