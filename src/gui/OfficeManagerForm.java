package gui;

import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;

import component.PlaceholderTextField;
import container.AssignBlankPanel;
import container.ManageDiscountsPanel;
import database.DatabaseHelper;


public class OfficeManagerForm extends JFrame {
    private int userID;
    DatabaseHelper db = new DatabaseHelper();
    Connection con = db.getConnection();


    public OfficeManagerForm(int currentID) {
        // Frame setup
        super("Welcome Office Manager");
        this.userID = currentID;
        int width = 960;
        int height = 720;

        JTabbedPane tabPane = new JTabbedPane();
        JPanel centrePanel = new JPanel(new BorderLayout());
        JPanel leftPanel = new JPanel(new BorderLayout());
        JPanel rightPanel = new JPanel();

        // Sets the layout of the JFrame
        this.add(centrePanel, BorderLayout.CENTER);
        this.add(leftPanel, BorderLayout.LINE_START);
        this.add(rightPanel, BorderLayout.LINE_END);

        // **** CENTRE PANEL ****

        // Set Panels into tabs
        centrePanel.add(tabPane);
        JPanel stockPanel = new JPanel(new BorderLayout());
        JPanel reportPanel = new JPanel(new BorderLayout());
        JPanel customerPanel = new JPanel(new BorderLayout());
        JPanel advisorPanel = new JPanel(new BorderLayout());
        JPanel tranPanel = new JPanel(new BorderLayout());
        AssignBlankPanel assignBlankPanel = new AssignBlankPanel();
        ManageDiscountsPanel manageDiscountsPanel = new ManageDiscountsPanel();


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

        // Show stock table in Stock panel
        JTable stockTable = new JTable();
        DefaultTableModel dtmStock = new DefaultTableModel();
        stockTable.setModel(dtmStock);
        dtmStock.setColumnIdentifiers(new Object[]{"ID", "Assigned to ID", "Type", "Status", "Date Added"});

        try {
            String getStock = "SELECT * FROM stock";
            PreparedStatement getStockStm = null;
            getStockStm = con.prepareStatement(getStock);
            ResultSet getStockRs = getStockStm.executeQuery(getStock);

            while (getStockRs.next()) {
                int blankID = getStockRs.getInt(1);
                int staffID = getStockRs.getInt(2);
                int type = getStockRs.getInt(3);
                String status = getStockRs.getString(4);
                String date = getStockRs.getString(5);
                dtmStock.addRow(new Object[]{blankID, staffID, type, status, date});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        stockPanel.add(new JScrollPane(stockTable));

        // **** Temporary Report List ****
        String[] s2 = {"report: 1", "report: 2", "report: 3", "report: 4", "report: 5"};
        JList report = new JList(s2);
        JScrollPane jScrollPane2 = new JScrollPane(report);
        reportPanel.add(jScrollPane2, BorderLayout.CENTER);
//        report.setVisibleRowCount(4);
        reportPanel.setBounds(0, 0, 600, 600);

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
        dtmTrans.setColumnIdentifiers(new Object[]{"ID", "Date", "Price", "BlankID", "CustomerID"});

        try {
            String getTran = "SELECT * FROM sold_tickets";
            PreparedStatement getTranStm = null;
            getTranStm = con.prepareStatement(getTran);
            ResultSet getTranRs = getTranStm.executeQuery(getTran);

            while (getTranRs.next()) {
                int ticketID = getTranRs.getInt("TicketID");
                String date = getTranRs.getString("Date");
                int price = getTranRs.getInt("Price");
                int blankID = getTranRs.getInt("BlankID");
                int customerID = getTranRs.getInt("CustomerID");
                dtmTrans.addRow(new Object[]{ticketID, date, price, blankID, customerID});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        tranPanel.add(new JScrollPane(transactionTable));

        // ************************* Sell ticket JFrame *********************************

        JFrame sellFrame = new JFrame("Sell ticket");
        JPanel sellPanel = new JPanel();
        sellPanel.setBackground(Color.WHITE);

        sellFrame.setSize(500, 500);
        sellPanel.setSize(sellFrame.getSize());
        sellPanel.setLayout(null);

        sellFrame.add(sellPanel);

        // ************************* Create customer Panel *******************************
        JPanel createCustomerPanel = new JPanel();
        createCustomerPanel.setBackground(Color.WHITE);
        createCustomerPanel.setSize(sellFrame.getSize());
        createCustomerPanel.setVisible(false);

        JLabel createCustomerTitle = new JLabel("Sell a ticket");
        createCustomerPanel.add(createCustomerTitle);
        createCustomerTitle.setBounds(25, 10, 175, 40);
        createCustomerTitle.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 27));
        createCustomerTitle.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        createCustomerTitle.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        

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

        // Create Customer button
        JButton createCustomer = new JButton("Add Customer");

        // Enter price
        JLabel ticketAmountLabel = new JLabel("Amount:");
        ticketAmountLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        PlaceholderTextField ticketAmount = new PlaceholderTextField();
        ticketAmount.setPlaceholder("Amount");

        // Blank drop-down box
        JLabel blankTypeLabel = new JLabel("BlankType");
        blankTypeLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        String[] blankTypes = {"444", "440", "420", "201", "101"};
        JComboBox blankTypeBox = new JComboBox(blankTypes);

        // Payment type drop-down box
        JLabel paymentTypeLabel = new JLabel("Payment type");
        paymentTypeLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        String[] paymentTypes = {"Cash", "Card", "Pay later"};
        JComboBox paymentTypeBox = new JComboBox(paymentTypes);

        // Card details
        PlaceholderTextField cardName = new PlaceholderTextField();
        cardName.setPlaceholder("Name");
        PlaceholderTextField cardNo = new PlaceholderTextField();
        cardNo.setPlaceholder("Card Number");
        PlaceholderTextField cardCode = new PlaceholderTextField();
        cardCode.setPlaceholder("CVC");
        PlaceholderTextField cardDate = new PlaceholderTextField();
        cardDate.setPlaceholder("Expiry Date");
        PlaceholderTextField cardAddress = new PlaceholderTextField();
        cardAddress.setPlaceholder("Card Address");

        // Show total price
        JLabel priceTitleLabel = new JLabel("Total Price:");
        JLabel priceLabel = new JLabel("0.00");

        // Confirm Purchase button
        JButton sellButton = new JButton("Confirm Payment");

        // **** Set Sell Ticket Panel Layout ****
        sellPanel.add(sellTicketTitle);
        sellTicketTitle.setBounds(25, 10, 175, 40);
        sellTicketTitle.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 27));
        sellTicketTitle.setHorizontalAlignment((int) CENTER_ALIGNMENT);
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

        // Blank type
        sellPanel.add(blankTypeLabel);
        sellPanel.add(blankTypeBox);
        blankTypeLabel.setBounds(ticketAmountLabel.getX(), ticketAmountLabel.getY() + 30, 100, 20);
        blankTypeBox.setBounds(blankTypeLabel.getX() + 105, blankTypeLabel.getY(), 125, 20);

        // Payment Title location
        sellPanel.add(paymentTypeLabel);
        paymentTypeLabel.setBounds(blankTypeLabel.getX(), blankTypeLabel.getY() + 40, 125, 35);

        //Payment type dropdown location
        sellPanel.add(paymentTypeBox);
        paymentTypeBox.setBounds(paymentTypeLabel.getX(), paymentTypeLabel.getY() + 40, 150, 20);

        // Card Details Positions
        sellPanel.add(cardName);
        sellPanel.add(cardNo);
        sellPanel.add(cardCode);
        sellPanel.add(cardDate);
        sellPanel.add(cardAddress);
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
            JComboBox comboBox = (JComboBox) event.getSource();

            if (comboBox.getSelectedItem().equals("Card")) {
                cardName.setVisible(true);
                cardNo.setVisible(true);
                cardCode.setVisible(true);
                cardDate.setVisible(true);
                cardAddress.setVisible(true);
            } else {
                cardName.setVisible(false);
                cardNo.setVisible(false);
                cardCode.setVisible(false);
                cardDate.setVisible(false);
                cardAddress.setVisible(false);
            }
        });

        sellPanel.add(priceTitleLabel);
        sellPanel.add(priceLabel);
        priceTitleLabel.setBounds(cardAddress.getX(), cardAddress.getY() + 30, 100, 25);
        priceLabel.setBounds(priceTitleLabel.getX() + 75, priceTitleLabel.getY(), 100, 25);

        // Create Customer button position
        sellPanel.add(createCustomer);
        createCustomer.setBounds(priceLabel.getX() + 50, priceLabel.getY(), 125, 25);
        sellFrame.add(createCustomerPanel);

        // Purchase Button
        sellPanel.add(sellButton);
        sellButton.setBounds(priceLabel.getX() + 190, priceLabel.getY(), 140, 25);
        sellButton.addActionListener(e -> {
            int custID = Integer.parseInt(customerIDBox.getText());
            long millis = System.currentTimeMillis();
            Date date = new java.sql.Date(millis);
            System.out.println(date);
            String dest = destinationBox.getText();
            String amnt = ticketAmount.getText();
            int blankType = Integer.parseInt(String.valueOf(blankTypeBox.getSelectedItem()));

            // Check for blank type
            if (blankType == 420){

            }
            //

            try {
                // Retrieves the blank needed from the database
                String selectBlank = "SELECT BlankID FROM stock WHERE StaffID = " + userID + " AND Type = " + blankType + " AND Status = 'Assigned'";
                PreparedStatement selectBlankStm = null;
                selectBlankStm = con.prepareStatement(selectBlank);
                ResultSet rs = selectBlankStm.executeQuery(selectBlank);
                String currentBlank = "empty";
                while (rs.next()) {
                    currentBlank = rs.getString("BlankID");
                }

                // Adds the ticket to the sold_tickets section.
                String addTicket = "INSERT INTO sold_tickets (Date, Price, Destination, BlankID, CustomerID) " +
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
                receiptPanel.setBounds(0, 0, 500, 500);
                sellFrame.add(receiptPanel);
                sellPanel.setVisible(false);

                JLabel purchaseMessage = new JLabel("Purchase Completed");
                purchaseMessage.setBounds(250, 250, 100, 100);
                purchaseMessage.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 27));
                receiptPanel.add(purchaseMessage);
                JButton close = new JButton("Close");
                close.setBounds(purchaseMessage.getX(), purchaseMessage.getY() + 100, 50, 25);
                receiptPanel.add(close);

                close.addActionListener(e2 -> {
                    sellFrame.dispose();
                });


            } catch (SQLException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(new JFrame(), "Purchase has failed", "Error", JOptionPane.ERROR_MESSAGE);
            }

        });

        // Create Customer Button Listener
        createCustomer.addActionListener(e ->{
            createCustomerPanel.setVisible(true);
            sellPanel.setVisible(false);

        });

        // *********************** Main panel layout and Sub-panels ********************************

        // Tabbed Pane Action Listener
        tabPane.addChangeListener(e -> {
            JTabbedPane tPane = (JTabbedPane) e.getSource();
            if (tPane.getSelectedIndex() == tPane.indexOfTab("Stock")) {
                assignBlankPanel.setVisible(true);
                manageDiscountsPanel.setVisible(false);
            } else if (tPane.getSelectedIndex() == tPane.indexOfTab("Customers")) {
                manageDiscountsPanel.setVisible(true);
                assignBlankPanel.setVisible(false);
            } else if (tPane.getSelectedIndex() == tPane.indexOfTab("Reports")) {
                assignBlankPanel.setVisible(false);
                manageDiscountsPanel.setVisible(false);
            } else if (tPane.getSelectedIndex() == tPane.indexOfTab("Transactions")) {
                manageDiscountsPanel.setVisible(false);
                assignBlankPanel.setVisible(false);
            } else if (tPane.getSelectedIndex() == tPane.indexOfTab("Travel Advisors")) {
                manageDiscountsPanel.setVisible(false);
                assignBlankPanel.setVisible(false);
            }
        });

        // Right Panel
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        JButton sellTicketButton = new JButton("Sell Ticket");
        rightPanel.add(sellTicketButton);

        sellTicketButton.addActionListener(e -> {
            sellFrame.setVisible(true);
        });

        JButton genReport = new JButton("Generate Report");
        rightPanel.add(genReport);

        JButton logoutButton = new JButton("Logout");
        rightPanel.add(logoutButton);

        logoutButton.addActionListener(e -> {
            LoginForm loginForm = new LoginForm();
            this.dispose();
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        });

        //sets the left part of the layout
        JLayeredPane leftLayeredPane = new JLayeredPane();
        leftLayeredPane.setPreferredSize(new Dimension(150, 150));

        // Assign Blank panel Layout
        //assignBlankPanel.setVisible(false);
        assignBlankPanel.setLayout(new BoxLayout(assignBlankPanel, BoxLayout.Y_AXIS));
        assignBlankPanel.setBounds(0, 0, 150, 450);
        assignBlankPanel.setVisible(true);
        leftLayeredPane.add(assignBlankPanel);

        // Manage Discounts panel
        manageDiscountsPanel.setLayout(new BoxLayout(manageDiscountsPanel, BoxLayout.Y_AXIS));
        manageDiscountsPanel.setVisible(false);
        leftLayeredPane.add(manageDiscountsPanel);


        leftPanel.add(leftLayeredPane, BorderLayout.CENTER);

        // Set Action listeners for each

        manageDiscountsPanel.backButton.addActionListener(e -> {
            manageDiscountsPanel.setVisible(false);
        });

        manageDiscountsPanel.setDiscount.addActionListener(e -> {
            manageDiscountsPanel.setVisible(true);
        });

        assignBlankPanel.assignBlankButton.addActionListener(e -> {
            int[] rows = stockTable.getSelectedRows();
            try {
                //2. create a statement
                String sql = "UPDATE stock "
                        + " SET StaffID=?"
                        + " WHERE BlankID=?";
                PreparedStatement stm = con.prepareStatement(sql);

                stm.setInt(1, Integer.parseInt(assignBlankPanel.idStaffTextfield.getText()));
                for (int i = 0; i < rows.length; i++) {
                    stm.setInt(2, Integer.parseInt(stockTable.getValueAt(rows[i], 0).toString()));
                    //3. execute sql query
                    stm.executeUpdate();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(width, height);
//        setVisible(true);
        System.out.println("this user has logged in ->" + userID);

    }
}
