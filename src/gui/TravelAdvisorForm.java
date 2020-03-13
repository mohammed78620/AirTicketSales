package gui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


import component.PlaceholderTextField;

import static java.sql.DriverManager.getConnection;


public class TravelAdvisorForm extends JFrame {
    private int id;
    private String url = "jdbc:mysql://localhost:3306/databasename";
    private String name = "root";
    private String password = "kamal997";

    public TravelAdvisorForm(int id){
        super("Travel Advisor page");
        this.id = id;

        JPanel panel1 = new JPanel(new BorderLayout());

        JPanel centerPanel = new JPanel(new BorderLayout());

        JPanel rightPanel = new JPanel();

        JPanel leftPanel = new JPanel(new GridLayout(8,8,5,40));

        JPanel bottomPanel = new JPanel();

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBackground(Color.RED);
        JPanel customerPanel = new JPanel(new BorderLayout());
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


        //sets up center BorderLayout
        String[] s1 = {"customer: 1","customer: 2","customer: 3","customer: 4","customer: 5"};
        JList customers = new JList(s1);
        JScrollPane scrollPane1 = new JScrollPane(customers);
        customerPanel.add(scrollPane1,BorderLayout.CENTER);
        customerPanel.setBounds(0,0,600,600);

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

        layeredPane.add(customerPanel);
        layeredPane.add(reportPanel);
        layeredPane.add(stockPanel);
        layeredPane.add(transactionPanel);




        //sets up bottom BorderLayout
        JButton generateIndividualReport = new JButton("generate report");
        JButton logoutButton = new JButton("Logout");
        bottomPanel.add(logoutButton);
        bottomPanel.add(generateIndividualReport);
        //sets up right BorderLayout
        JButton viewCustomers = new JButton("view customer");
        JButton sellTicketButton = new JButton("sell ticket");
        JButton createAccountButton = new JButton("create account");
        JButton updateAccountButton = new JButton("update account");
        JButton viewIndividualReport = new JButton("view reports");
        JButton viewIndividualStock = new JButton("view stock");
        JLabel cancelTicket = new JLabel("cancel ticket");
        PlaceholderTextField ticketIdTextfield = new PlaceholderTextField();
        ticketIdTextfield.setPlaceholder("ticket ID");
        JButton cancelTicketButton = new JButton("cancel ticket");




        //adds to right container
        rightPanel.add(viewCustomers);
        rightPanel.add(Box.createRigidArea(new Dimension(0,15)));
        rightPanel.add(sellTicketButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0,15)));
        rightPanel.add(cancelTicket);
        rightPanel.add(Box.createRigidArea(new Dimension(0,15)));
        rightPanel.add(ticketIdTextfield);
        rightPanel.add(Box.createRigidArea(new Dimension(0,15)));
        rightPanel.add(cancelTicketButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0,15)));
        rightPanel.add(createAccountButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0,15)));
        rightPanel.add(updateAccountButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0,15)));
        rightPanel.add(viewIndividualReport);
        rightPanel.add(Box.createRigidArea(new Dimension(0,15)));
        rightPanel.add(viewIndividualStock);
        rightPanel.add(Box.createRigidArea(new Dimension(0,260)));





        //sets up left BorderLayout

        JLabel IDLabel = new JLabel("ID");
        JLabel nameLabel = new JLabel("Name");
        JLabel dateOfBirthLabel = new JLabel("Date of birth");
        JLabel addressLabel = new JLabel("Address");
        JLabel postalCodeLabel = new JLabel("Postcode");
        JLabel telephoneLabel = new JLabel("telephone");
        JLabel emailLabel = new JLabel("Email");

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

        leftPanel.add(IDLabel);
        leftPanel.add(IDTextField);
        leftPanel.add(nameLabel);
        leftPanel.add(nameTextField);
        leftPanel.add(dateOfBirthLabel);
        leftPanel.add(dateOfBirthTextField);
        leftPanel.add(addressLabel);
        leftPanel.add(addressTextField);
        leftPanel.add(postalCodeLabel);
        leftPanel.add(postalCodeTextField);
        leftPanel.add(telephoneLabel);
        leftPanel.add(telephoneTextField);
        leftPanel.add(emailLabel);
        leftPanel.add(emailTextField);




        //sets up buttonListeners
        viewCustomers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerPanel.setVisible(true);
                transactionPanel.setVisible(false);
                reportPanel.setVisible(false);
                stockPanel.setVisible(false);
            }
        });
        viewIndividualReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reportPanel.setVisible(true);
                customerPanel.setVisible(false);
                transactionPanel.setVisible(false);
                stockPanel.setVisible(false);
            }
        });
        sellTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                transactionPanel.setVisible(true);
                customerPanel.setVisible(false);
                reportPanel.setVisible(false);
                stockPanel.setVisible(false);
            }
        });
        viewIndividualStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stockPanel.setVisible(true);
                transactionPanel.setVisible(false);
                customerPanel.setVisible(false);
                reportPanel.setVisible(false);
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
                    Connection con = getConnection(url, name , password);

                    //2. create a statement
                    String sql = "INSERT INTO customeraccount"
                            + " (name, dateOfBirth, address, postalCode, telephone, email)"
                            + "VALUES ( ?, ?, ?, ?, ?, ?)";


                    PreparedStatement stm = con.prepareStatement(sql);

                    stm.setString(1, nameTextField.getText());
                    stm.setDate(2, Date.valueOf(dateOfBirthTextField.getText()));
                    stm.setString(3, addressTextField.getText());
                    stm.setString(4, postalCodeTextField.getText());
                    stm.setInt(5, Integer.parseInt(telephoneTextField.getText()));
                    stm.setString(6, emailTextField.getText());


                    //3. execute sql query
                    stm.executeUpdate();


                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        updateAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    // 1. get a connection
                    Connection con = getConnection(url, name , password);

                    //2. create a statement
                    String sql = "UPDATE customeraccount "
                            + "SET name=?,dateOfBirth=?,address=?,postalCode=?,telephone=?,email=?"
                            + "WHERE customerAccount_id=?";

                    PreparedStatement stm = con.prepareStatement(sql);

                    stm.setString(1, nameTextField.getText());
                    stm.setDate(2,Date.valueOf(dateOfBirthTextField.getText()));
                    stm.setString(3, addressTextField.getText());
                    stm.setString(4, postalCodeTextField.getText());
                    stm.setInt(5, Integer.parseInt(telephoneTextField.getText()));
                    stm.setString(6, emailTextField.getText());
                    stm.setInt(7,Integer.parseInt(IDTextField.getText()));

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
                        Connection con = getConnection(url, name, password);
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
                        Connection con = getConnection(url, name, password);

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
        cancelTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // 1. get a connection
                    Connection myConn = getConnection(url, name, password);

                    // 2. create a statement
                    String sql = "DELETE FROM payment WHERE payment_id=?";
                    PreparedStatement stm = myConn.prepareStatement(sql);
                    stm.setInt(1,Integer.parseInt(ticketIdTextfield.getText()));

                    // 3. execute sql statement
                    stm.executeUpdate();
                }catch (Exception ex){
                    ex.printStackTrace();
                }

            }
        });

        viewIndividualReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // 1. get a connection
                    Connection myConn = DriverManager.getConnection(url, name, password);

                    // 2. create a statement
                    //report needs to be added to database with more data fields
                    String sql = "SELECT (report_id, dateGenerated, endDate) "
                            + "FROM report"
                            + " WHERE staff_id=?";

                    PreparedStatement stm = myConn.prepareStatement(sql);
                    stm.setInt(1,Integer.parseInt(IDTextField.getText()));

                    // 3. execute sql statement
                    stm.executeUpdate();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        viewCustomers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // 1. get a connection
                    Connection myConn = DriverManager.getConnection(url, name, password);

                    // 2. create a statement
                    String sql = "SELECT * FROM customeraccount"
                            + " WHERE customeraccount_id=?";

                    PreparedStatement stm = myConn.prepareStatement(sql);
                    stm.setInt(1,Integer.parseInt(IDTextField.getText()));

                    // 3. execute sql statement
                    stm.executeUpdate();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        generateIndividualReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // 1. get a connection
                    Connection myConn = DriverManager.getConnection(url, name, password);

                    // 2. create a statement
                    //report needs to be added to database with more data fields
                    String sql = "INSERT INTO report "
                            + " (report_id, dateGenerated, endDate)"
                            + "VALUES ( ?,?,?,)";


                    PreparedStatement stm = myConn.prepareStatement(sql);


                    // 3. execute sql statement
                    stm.executeUpdate();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(750,500);


    }
    public int getID(){
        return id;
    }
}
