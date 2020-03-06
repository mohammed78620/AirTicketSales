package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import component.PlaceholderTextField;


public class SystemAdminForm extends JFrame {
    private JPanel panel1;
    private JPanel centerPanel;
    private JPanel bottomPanel;
    private JPanel rightPanel;
    private JPanel leftPanel;
    private JLabel changeCommissionLabel;
    private PlaceholderTextField changeCommissionTextField;
    private Connection con;
    private PreparedStatement stm = null;
    private ResultSet rs = null;
    private String url = "jdbc:mysql://localhost:3306/airticketsales";
    private String name = "akmal";
    private String password = "]WCgDKEN69Wf>zE.";

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
        JPanel user = new JPanel();
        JPanel stockPanel = new JPanel();
        JPanel databasePanel = new JPanel();
        centerPanel.add(layeredPane,BorderLayout.CENTER);
        layeredPane.setPreferredSize(new Dimension(600,600));


        String[] s1 = {"user: 1","user: 2","user: 3","user: 4","user: 5"};
        user.setLayout(new BorderLayout());
        JList advisors = new JList(s1);
        JScrollPane jScrollPane11 = new JScrollPane(advisors);
        user.add(jScrollPane11,BorderLayout.CENTER);
        user.setBounds(0,0,600,600);

        String[] s2 = {"stock: 1","stock: 2","stock: 3","stock: 4","stock: 5"};
        stockPanel.setLayout(new BorderLayout());
        JList stock = new JList(s2);
        JScrollPane jScrollPane12 = new JScrollPane(stock);
        stockPanel.add(jScrollPane12,BorderLayout.CENTER);
        stockPanel.setBounds(0,0,600,600);

        String[] s3 = {"database: 1","database: 2","database: 3","database: 4","database: 5"};
        databasePanel.setLayout(new BorderLayout());
        JList databases = new JList(s3);
        JScrollPane jScrollPane13 = new JScrollPane(databases);
        databasePanel.add(jScrollPane13,BorderLayout.CENTER);
        databasePanel.setBounds(0,0,600,600);



        layeredPane.add(user);
        layeredPane.add(stockPanel);
        layeredPane.add(databasePanel);

        //sets up bottom of borderLayout
        JButton logoutButton = new JButton("Logout");
        JButton generateTurnoverReportButton = new JButton("GenerateTurnoverReport");
        bottomPanel.add(generateTurnoverReportButton);
        bottomPanel.add(logoutButton);



        //sets up right of borderLayout
        rightPanel.setLayout(new BoxLayout(rightPanel,BoxLayout.Y_AXIS));
        JButton viewBackupButton = new JButton("viewBackupDatabase");
        JButton backupDatabaseButton = new JButton("backupDatabase");
        JButton restoreDatabaseButton = new JButton("restoreDatabase");
        JButton addUserButton = new JButton("Add user");
        JButton updateUserButton = new JButton("Update user");
        JButton removeUserButton = new JButton("Remove User");
        JButton viewUserButton = new JButton("View users");
        JButton viewStockButton = new JButton("View stock");

        JLabel changeCommissionLabel = new JLabel("change commission rate");
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
        rightPanel.add(viewBackupButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
        rightPanel.add(backupDatabaseButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
        rightPanel.add(restoreDatabaseButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
        rightPanel.add(changeCommissionLabel);
        rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
        rightPanel.add(changeCommissionTextField);
        rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
        rightPanel.add(addUserButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
        rightPanel.add(updateUserButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
        rightPanel.add(removeUserButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
        rightPanel.add(viewUserButton);
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
        JLayeredPane leftLayeredPane = new JLayeredPane();
        leftLayeredPane.setPreferredSize(new Dimension(150,150));
        JPanel updateTravelAdvisorPanel = new JPanel();
        updateTravelAdvisorPanel.setLayout(new BoxLayout(updateTravelAdvisorPanel,BoxLayout.Y_AXIS));
        updateTravelAdvisorPanel.setBounds(0,0,150,450);
        JPanel updateStockPanel = new JPanel();
        updateStockPanel.setVisible(false);
        updateStockPanel.setLayout(new BoxLayout(updateStockPanel,BoxLayout.Y_AXIS));
        updateStockPanel.setBounds(0,0,150,450);

        JLabel idLabel = new JLabel("iD");
        JLabel usernameLabel = new JLabel("Username");
        JLabel passwordLabel = new JLabel("Password");
        JLabel nameLabel = new JLabel("Name");
        JLabel addressLabel = new JLabel("Address");
        JLabel telephoneLabel = new JLabel("Telephone");
        JLabel emailLabel = new JLabel("Email");
        JLabel staffTypeLabel = new JLabel("staff type");

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
        JComboBox staffType = new JComboBox();
        staffType.addItem("sa");
        staffType.addItem("om");
        staffType.addItem("ta");



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
        JComboBox typeBox1 = new JComboBox();
        typeBox1.addItem("402");
        typeBox1.addItem("401");
        PlaceholderTextField amountTextfield1 = new PlaceholderTextField();
        amountTextfield1.setPlaceholder("amount");
        JComboBox typeBox2 = new JComboBox();
        typeBox2.addItem("402");
        typeBox2.addItem("401");
        PlaceholderTextField amountTextfield2 = new PlaceholderTextField();
        amountTextfield2.setPlaceholder("amount");
        PlaceholderTextField idTextfield3 = new PlaceholderTextField();
        idTextfield3.setPlaceholder("id");

        JButton assignBlankButton = new JButton("assign blank");
        JButton addBlankButton = new JButton("add blank");
        JButton removeBlankButton = new JButton("remove blank");
        //adds to update travel advisor panel


        updateTravelAdvisorPanel.add(idLabel);
        updateTravelAdvisorPanel.add(Box.createRigidArea(new Dimension(0,5)));
        updateTravelAdvisorPanel.add(idTextfield);
        updateTravelAdvisorPanel.add(Box.createRigidArea(new Dimension(0,5)));
        updateTravelAdvisorPanel.add(usernameLabel);
        updateTravelAdvisorPanel.add(Box.createRigidArea(new Dimension(0,5)));
        updateTravelAdvisorPanel.add(usernameTextfield);
        updateTravelAdvisorPanel.add(Box.createRigidArea(new Dimension(0,5)));
        updateTravelAdvisorPanel.add(passwordLabel);
        updateTravelAdvisorPanel.add(Box.createRigidArea(new Dimension(0,5)));
        updateTravelAdvisorPanel.add(passwordTextfield);
        updateTravelAdvisorPanel.add(Box.createRigidArea(new Dimension(0,5)));
        updateTravelAdvisorPanel.add(nameLabel);
        updateTravelAdvisorPanel.add(Box.createRigidArea(new Dimension(0,5)));
        updateTravelAdvisorPanel.add(nameTextfield);
        updateTravelAdvisorPanel.add(Box.createRigidArea(new Dimension(0,5)));
        updateTravelAdvisorPanel.add(addressLabel);
        updateTravelAdvisorPanel.add(Box.createRigidArea(new Dimension(0,5)));
        updateTravelAdvisorPanel.add(addressTextfield);
        updateTravelAdvisorPanel.add(Box.createRigidArea(new Dimension(0,5)));
        updateTravelAdvisorPanel.add(telephoneLabel);
        updateTravelAdvisorPanel.add(Box.createRigidArea(new Dimension(0,5)));
        updateTravelAdvisorPanel.add(telephoneTextfield);
        updateTravelAdvisorPanel.add(Box.createRigidArea(new Dimension(0,5)));
        updateTravelAdvisorPanel.add(emailLabel);
        updateTravelAdvisorPanel.add(Box.createRigidArea(new Dimension(0,5)));
        updateTravelAdvisorPanel.add(emailTextfield);
        updateTravelAdvisorPanel.add(Box.createRigidArea(new Dimension(0,5)));
        updateTravelAdvisorPanel.add(staffTypeLabel);
        updateTravelAdvisorPanel.add(Box.createRigidArea(new Dimension(0,5)));
        updateTravelAdvisorPanel.add(staffType);
        updateTravelAdvisorPanel.add(Box.createRigidArea(new Dimension(0,50)));


        //adds components to update stock panel
        updateStockPanel.add(assignBlanksLabel);
        updateStockPanel.add(Box.createRigidArea(new Dimension(0,2)));
        updateStockPanel.add(idLabel2);
        updateStockPanel.add(Box.createRigidArea(new Dimension(0,2)));
        updateStockPanel.add(idTextfield2);
        updateStockPanel.add(Box.createRigidArea(new Dimension(0,2)));
        updateStockPanel.add(typeLabel1);
        updateStockPanel.add(Box.createRigidArea(new Dimension(0,2)));
        updateStockPanel.add(typeBox1);
        updateStockPanel.add(Box.createRigidArea(new Dimension(0,2)));
        updateStockPanel.add(amountLabel);
        updateStockPanel.add(Box.createRigidArea(new Dimension(0,2)));
        updateStockPanel.add(amountTextfield1);
        updateStockPanel.add(Box.createRigidArea(new Dimension(0,2)));
        updateStockPanel.add(assignBlankButton);
        updateStockPanel.add(Box.createRigidArea(new Dimension(0,2)));
        updateStockPanel.add(addBlankLabel);
        updateStockPanel.add(Box.createRigidArea(new Dimension(0,2)));
        updateStockPanel.add(typeLabel2);
        updateStockPanel.add(Box.createRigidArea(new Dimension(0,2)));
        updateStockPanel.add(typeBox2);
        updateStockPanel.add(Box.createRigidArea(new Dimension(0,2)));
        updateStockPanel.add(amountLabel2);
        updateStockPanel.add(Box.createRigidArea(new Dimension(0,2)));
        updateStockPanel.add(amountTextfield2);
        updateStockPanel.add(Box.createRigidArea(new Dimension(0,2)));
        updateStockPanel.add(addBlankButton);
        updateStockPanel.add(Box.createRigidArea(new Dimension(0,2)));
        updateStockPanel.add(removeBlankLabel);
        updateStockPanel.add(Box.createRigidArea(new Dimension(0,2)));
        updateStockPanel.add(idLabel3);
        updateStockPanel.add(Box.createRigidArea(new Dimension(0,2)));
        updateStockPanel.add(idTextfield3);
        updateStockPanel.add(Box.createRigidArea(new Dimension(0,2)));
        updateStockPanel.add(removeBlankButton);
        updateStockPanel.add(Box.createRigidArea(new Dimension(0,50)));





        //adds to updates stock pane


        leftLayeredPane.add(updateTravelAdvisorPanel);
        leftLayeredPane.add(updateStockPanel);
        leftPanel.add(leftLayeredPane,BorderLayout.CENTER);




        //sets button listeners

        viewUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user.setVisible(true);
                stockPanel.setVisible(false);
                updateTravelAdvisorPanel.setVisible(true);
                updateStockPanel.setVisible(false);
                databasePanel.setVisible(false);
            }
        });
        viewStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stockPanel.setVisible(true);
                user.setVisible(false);
                updateStockPanel.setVisible(true);
                updateTravelAdvisorPanel.setVisible(false);
                databasePanel.setVisible(false);
            }
        });
        viewBackupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                databasePanel.setVisible(true);
                stockPanel.setVisible(false);
                user.setVisible(false);
                updateStockPanel.setVisible(false);
                updateTravelAdvisorPanel.setVisible(false);
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginForm loginForm = new LoginForm();
                dispose();
            }
        });
        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // 1. get a connection
                    con = DriverManager.getConnection(url, name, password);

                    //2. create a statement
                    String sql = "INSERT INTO staff "
                            + " (name, address, telephone, email, username, password, staffType)"
                            + "VALUES ( ?, ?, ?, ?, ?, ?, ?)";
                    stm = con.prepareStatement(sql);

                    stm.setString(1, nameTextfield.getText());
                    stm.setString(2, addressTextfield.getText());
                    stm.setInt(3,Integer.parseInt(telephoneTextfield.getText()));
                    stm.setString(4, emailTextfield.getText());
                    stm.setString(5, usernameTextfield.getText());
                    stm.setString(6, passwordTextfield.getText());
                    stm.setString(7,staffType.getSelectedItem().toString());

                    //3. execute sql query
                    stm.executeUpdate();



                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });

        updateUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // 1. get a connection
                    con = DriverManager.getConnection(url, name, password);

                    //2. create a statement
                    String sql = "UPDATE staff "
                            + " SET name=?, address=?, telephone=?, email=?, username=?, password=?,staffType=?"
                            + " WHERE staff_id=?";

                    stm = con.prepareStatement(sql);

                    stm.setString(1,usernameTextfield.getText());
                    stm.setString(2,addressTextfield.getText());
                    stm.setInt(3,Integer.parseInt(telephoneTextfield.getText()));
                    stm.setString(4,emailTextfield.getText());
                    stm.setString(5,usernameTextfield.getText());
                    stm.setString(6,passwordTextfield.getText());
                    stm.setString(7,staffType.getSelectedItem().toString());
                    stm.setInt(8,Integer.parseInt(idTextfield.getText()));




                    //3. execute sql query
                    stm.executeUpdate();

                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        removeUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // 1. get a connection
                    con = DriverManager.getConnection(url, name, password);

                    //2. create a statement

                    String sql = "DELETE FROM staff WHERE staff_id=?";
                    stm = con.prepareStatement(sql);

                    stm.setInt(1, Integer.parseInt(idTextfield.getText()));

                    //3. execute sql query
                    stm.executeUpdate();


                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(750,500);
//        setVisible(true);
    }
}
