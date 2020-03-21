package gui;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import container.CancelTicketPanel;
import container.ManageCustomerPanel;
import container.ManageDiscountsPanel;
import container.UpdateCustomerPanel;
import component.PlaceholderTextField;
import database.DatabaseHelper;
import domain.Customer;
import domain.Discount;



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
        JPanel reportPanel = new JPanel(new BorderLayout());
        JPanel transactionPanel = new JPanel();
        transactionPanel.setLayout(new BoxLayout(transactionPanel,BoxLayout.Y_AXIS));
        JPanel stockPanel = new JPanel(new BorderLayout());
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

        discountPanel.setLayout(new BorderLayout());
        JTable discount = new JTable();
        JScrollPane jScrollPane2 = new JScrollPane(discount);
        discountPanel.add(jScrollPane2,BorderLayout.CENTER);
        discountPanel.setBounds(0,0,600,600);

        String[] s2 = {"report: 1","report: 2","report: 3","report: 4","report: 5"};
        JList reports = new JList(s2);
        JScrollPane scrollPane12 = new JScrollPane(reports);
        reportPanel.add(scrollPane12,BorderLayout.CENTER);
        reportPanel.setBounds(0,0,600,600);

        String[] s3 = {"stocks: 1","stocks: 2","stocks: 3","stocks: 4","stocks: 5"};
        JList stocks = new JList(s3);
        JScrollPane scrollPane13 = new JScrollPane(stocks);
        stockPanel.add(scrollPane13,BorderLayout.CENTER);
        stockPanel.setBounds(0,0,600,600);

        // sell ticket layout
        JLabel amountLabel = new JLabel("amount");
        PlaceholderTextField amountTextfield = new PlaceholderTextField();
        JLabel paymentTypeLabel = new JLabel("payment type");
        JComboBox<String> paymentTypeComboBox = new JComboBox();
        paymentTypeComboBox.addItem("credit card");
        paymentTypeComboBox.addItem("cash");
        amountTextfield.setPlaceholder("amount");
        JLabel customerIDLabel = new JLabel("customer id");
        PlaceholderTextField customerIDTextfield = new PlaceholderTextField();
        customerIDTextfield.setPlaceholder("customer id");
        JButton sellButton = new JButton("sell");

        transactionPanel.add(amountLabel);
        transactionPanel.add(Box.createRigidArea(new Dimension(0,15)));
        transactionPanel.add(amountTextfield);
        transactionPanel.add(Box.createRigidArea(new Dimension(0,15)));
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
        transactionPanel.setVisible(false);

        CancelTicketPanel cancelTicketPanel = new CancelTicketPanel();
        cancelTicketPanel.setLayout(new BoxLayout(cancelTicketPanel,BoxLayout.Y_AXIS));
        cancelTicketPanel.setVisible(false);

        layeredPane.add(transactionPanel);
        layeredPane.add(customerPanel);
        layeredPane.add(reportPanel);
        layeredPane.add(stockPanel);
        layeredPane.add(cancelTicketPanel);
        layeredPane.add(discountPanel);

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
        JButton generateIndividualReport = new JButton("generate report");
        JButton logoutButton = new JButton("Logout");
        bottomPanel.add(logoutButton);
        bottomPanel.add(generateIndividualReport);
        //sets up right BorderLayout
        JButton viewCustomers = new JButton("view customer");
        JButton sellTicketButton = new JButton("sell ticket");
        JButton createAccountButton = new JButton("create account");
        JButton viewIndividualReport = new JButton("view reports");
        JButton viewIndividualStock = new JButton("view stock");
        JButton viewCancelTicketButton = new JButton("cancel ticket");

        //adds to right container
        rightPanel.add(viewCustomers);
        rightPanel.add(Box.createRigidArea(new Dimension(0,15)));
        rightPanel.add(sellTicketButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0,15)));
        rightPanel.add(viewCancelTicketButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0,15)));
        rightPanel.add(createAccountButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0,15)));
        rightPanel.add(viewIndividualReport);
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
        manageCustomerPanel.manageDiscountButton.addActionListener(e -> {
        manageCustomerPanel.setVisible(false);
        manageDiscountsPanel.setVisible(true);
        discountPanel.setVisible(true);
        customerPanel.setVisible(false);
        transactionPanel.setVisible(false);
        updateCustomerPanel.setVisible(false);
        reportPanel.setVisible(false);
        stockPanel.setVisible(false);
        cancelTicketPanel.setVisible(false);


                List<Discount> discounts = new ArrayList<>();
                try {
                    con = db.getConnection();
                    Statement stm = con.createStatement();
                    ResultSet rs = stm.executeQuery("SELECT customerAccount_id, name, customerType, discount" +
                            " FROM customeraccount WHERE customerType= 'valued'");


                    while(rs.next()) {
                        discounts.add(new Discount(rs.getInt(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getString(4)));
                    }
                    Discount b;
                    DefaultTableModel model =new DefaultTableModel();
                    model.addColumn("customer ID: ");
                    model.addColumn("name: ");
                    model.addColumn("customer type: ");
                    model.addColumn("discount: ");
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
                    String sql = "UPDATE customeraccount "
                            + "SET name=?,dateOfBirth=?,address=?,postalCode=?,telephone=?,email=?, customerType=?"
                            + "WHERE customerAccount_id=?";

                    PreparedStatement stm = con.prepareStatement(sql);

                    stm.setString(1, updateCustomerPanel.nameTextField.getText());
                    stm.setDate(2,Date.valueOf(updateCustomerPanel.dateOfBirthTextField.getText()));
                    stm.setString(3, updateCustomerPanel.addressTextField.getText());
                    stm.setString(4, updateCustomerPanel.postalCodeTextField.getText());
                    stm.setInt(5, Integer.parseInt(updateCustomerPanel.telephoneTextField.getText()));
                    stm.setString(6, updateCustomerPanel.emailTextField.getText());
                    stm.setString(7, updateCustomerPanel.typeBox.getSelectedItem().toString());
                    stm.setInt(8,Integer.parseInt(updateCustomerPanel.IDField.getText()));

                    //3. execute sql query
                    stm.executeUpdate();



                }catch (Exception ex){
                    ex.printStackTrace();
                }
        });

        viewCustomers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerPanel.setVisible(true);
                manageCustomerPanel.setVisible(true);
                transactionPanel.setVisible(false);
                reportPanel.setVisible(false);
                stockPanel.setVisible(false);
                cancelTicketPanel.setVisible(false);
                discountPanel.setVisible(false);
                manageDiscountsPanel.setVisible(false);

                 List<Customer> customers = new ArrayList<>();
                try {
                    con = db.getConnection();
                    Statement stm = con.createStatement();
                    ResultSet rs = stm.executeQuery("SELECT * FROM customeraccount");

                    while(rs.next()){
                        customers.add(new Customer(rs.getInt(1),
                                rs.getString(2),
                                rs.getDate(3),
                                rs.getString(4),
                                rs.getString(5),
                                rs.getInt(6),
                                rs.getString(7),
                                rs.getString(8),
                                rs.getString(9)));
                    }
                    Customer b;
                    DefaultTableModel model =new DefaultTableModel();
                    model.addColumn("customer ID: ");
                    model.addColumn("name: ");
                    model.addColumn("date of birth ");
                    model.addColumn("address: ");
                    model.addColumn("postal code: ");
                    model.addColumn("telephone: ");
                    model.addColumn("email: ");
                    model.addColumn("customer type: ");
                    model.addColumn("discount: ");
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
                transactionPanel.setVisible(false);
                stockPanel.setVisible(false);
                manageCustomerPanel.setVisible(false);
                cancelTicketPanel.setVisible(false);
            }
        });
        sellTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                transactionPanel.setVisible(true);
                customerPanel.setVisible(false);
                reportPanel.setVisible(false);
                stockPanel.setVisible(false);
                manageCustomerPanel.setVisible(false);
                updateCustomerPanel.setVisible(false);
                manageDiscountsPanel.setVisible(false);
                cancelTicketPanel.setVisible(false);
            }
        });
        viewIndividualStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stockPanel.setVisible(true);
                transactionPanel.setVisible(false);
                customerPanel.setVisible(false);
                reportPanel.setVisible(false);
                manageCustomerPanel.setVisible(false);
                cancelTicketPanel.setVisible(false);
            }
        });
        viewCancelTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelTicketPanel.setVisible(true);
                stockPanel.setVisible(false);
                transactionPanel.setVisible(false);
                customerPanel.setVisible(false);
                reportPanel.setVisible(false);
                manageCustomerPanel.setVisible(false);
            }
        });
        cancelTicketPanel.cancelButton.addActionListener(e -> {
            try {

                // 1. get a connection
                con = db.getConnection();

                // 2. create a statement
                String sql = "DELETE FROM payment WHERE payment_id=?";
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
                        + " (description, amount, refundType)"
                        + "VALUES ( ?, ?, ?)";
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setString(1,cancelTicketPanel.descriptionTextfield.getText());
                stm.setInt(2,Integer.parseInt(cancelTicketPanel.amountTextfield.getText()));
                stm.setString(3, cancelTicketPanel.typeBox.getSelectedItem().toString());

                // 3. execute sql statement
                stm.executeUpdate();
            }catch (Exception ex){
                ex.printStackTrace();
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
                    String sql = "INSERT INTO customeraccount"
                            + " (name, dateOfBirth, address, postalCode, telephone, email)"
                            + "VALUES ( ?, ?, ?, ?, ?, ?)";


                    PreparedStatement stm = con.prepareStatement(sql);

                    stm.setString(1, updateCustomerPanel.nameTextField.getText());
                    stm.setDate(2, Date.valueOf(updateCustomerPanel.dateOfBirthTextField.getText()));
                    stm.setString(3, updateCustomerPanel.addressTextField.getText());
                    stm.setString(4, updateCustomerPanel.postalCodeTextField.getText());
                    stm.setInt(5, Integer.parseInt(updateCustomerPanel.telephoneTextField.getText()));
                    stm.setString(6, updateCustomerPanel.emailTextField.getText());


                    //3. execute sql query
                    stm.executeUpdate();


                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });

        sellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(customerIDTextfield.getText().trim().isEmpty()){
                    try{
                        // 1. get a connection
                        con = db.getConnection();
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
                        con = db.getConnection();

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
//        generateIndividualReport.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    // 1. get a connection
//                    con = db.getConnection();
//
//                    // 2. create a statement
//                    //report needs to be added to database with more data fields
//                    String sql = "INSERT INTO report "
//                            + " (report_id, dateGenerated, endDate)"
//                            + "VALUES ( ?,?,?,)";
//
//
//                    PreparedStatement stm = con.prepareStatement(sql);
//
//
//                    // 3. execute sql statement
//                    stm.executeUpdate();
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
//            }
//        });

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


