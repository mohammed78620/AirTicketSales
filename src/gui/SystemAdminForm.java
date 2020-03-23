package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import component.PlaceholderTextField;
import container.AddBlankPanel;
import container.AssignBlankPanel;
import container.RemoveBlankPanel;
import container.UpdateStockPanel;
import database.Database;
import database.DatabaseHelper;
import domain.*;


public class SystemAdminForm extends JFrame {
    private int id;
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
    private String name = "root";
    private String password = "ba!E%xxd-9F7_NdQ";
    private DatabaseHelper db = new DatabaseHelper();
    private DefaultTableModel userModel;
    private DefaultTableModel stockModel;
    private DefaultTableModel rateModel;
    private DefaultTableModel commissionModel;
    private DefaultTableModel backupModel;

    public SystemAdminForm(int id){
        super("System Administrator page");
        this.id = id;

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
        JTabbedPane centerPane = new JTabbedPane();
        JPanel userPanel = new JPanel();
        JPanel stockPanel = new JPanel();
        JPanel databasePanel = new JPanel();
        JPanel ratesPanel = new JPanel();
        JPanel commissionPanel = new JPanel();
        centerPanel.add(centerPane,BorderLayout.CENTER);




        userPanel.setLayout(new BorderLayout());
        JTable users = new JTable();
        userModel = new DefaultTableModel();
        userModel.addColumn("staff_id");
        userModel.addColumn("forename");
        userModel.addColumn("surname");
        userModel.addColumn("address");
        userModel.addColumn("telephone");
        userModel.addColumn("email");
        userModel.addColumn("username");
        userModel.addColumn("password");
        userModel.addColumn("staffType");
        users.setModel(userModel);
        JScrollPane jScrollPane11 = new JScrollPane(users);
        viewUser();
        userPanel.add(jScrollPane11,BorderLayout.CENTER);
        userPanel.setBounds(0,0,600,600);


        stockPanel.setLayout(new BorderLayout());
        JTable stock = new JTable();
        stockModel =new DefaultTableModel();
        stockModel.addColumn("blank id");
        stockModel.addColumn("staff id");
        stockModel.addColumn("blank type");
        stockModel.addColumn("used");
        stockModel.addColumn("received");
        stock.setModel(stockModel);
        JScrollPane jScrollPane12 = new JScrollPane(stock);
        stockPanel.add(jScrollPane12,BorderLayout.CENTER);
        stockPanel.setBounds(0,0,400,600);


        databasePanel.setLayout(new BorderLayout());
        JTable backup = new JTable();
        backupModel = new DefaultTableModel();
        backupModel.addColumn("backup id");
        backupModel.addColumn("date added");
        backup.setModel(backupModel);
        JScrollPane jScrollPane13 = new JScrollPane(backup);
        viewBackup();
        databasePanel.add(jScrollPane13,BorderLayout.CENTER);
        databasePanel.setBounds(0,0,400,600);


        ratesPanel.setLayout(new BorderLayout());
        JTable rate = new JTable();
        rateModel = new DefaultTableModel();
        rateModel.addColumn("id");
        rateModel.addColumn("currency");
        rateModel.addColumn("rate");
        rate.setModel(rateModel);
        JScrollPane jScrollPane14 = new JScrollPane(rate);
        viewRate();
        ratesPanel.add(jScrollPane14,BorderLayout.CENTER);
        ratesPanel.setBounds(0,0,600,600);

        commissionPanel.setLayout(new BorderLayout());
        JTable commission = new JTable();
        commissionModel = new DefaultTableModel();
        commissionModel.addColumn("id");
        commissionModel.addColumn("commission rate");
        commissionModel.addColumn("active");
        commission.setModel(commissionModel);
        JScrollPane jScrollPane5 = new JScrollPane(commission);
        viewCommission();
        commissionPanel.add(jScrollPane5,BorderLayout.CENTER);
        commissionPanel.setBounds(0,0,600,600);


        centerPane.add("users",userPanel);
        centerPane.add("stock",stockPanel);
        centerPane.add("databases",databasePanel);
        centerPane.add("rates",ratesPanel);
        centerPane.add("commission",commissionPanel);
        centerPane.add("backup",databasePanel);



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
        JButton removeUserButton = new JButton("Remove user");
        JButton viewUserButton = new JButton("View users");
        JButton viewStockButton = new JButton("View stock");
        JButton viewCommission = new JButton("view Commission");
        JButton viewExchangeRates = new JButton("view rates");
        rightPanel.add(viewBackupButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
        rightPanel.add(backupDatabaseButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0,10)));
        rightPanel.add(restoreDatabaseButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0,5)));
        rightPanel.add(addUserButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0,5)));
        rightPanel.add(updateUserButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0,5)));
        rightPanel.add(removeUserButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0,5)));
        rightPanel.add(viewUserButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0,5)));
        rightPanel.add(viewStockButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0,5)));
        rightPanel.add(viewExchangeRates);
        rightPanel.add(Box.createRigidArea(new Dimension(0,5)));
        rightPanel.add(viewCommission);
        rightPanel.add(Box.createRigidArea(new Dimension(0,105)));



        //sets up left of borderLayout
        JLayeredPane leftLayeredPane = new JLayeredPane();
        leftLayeredPane.setPreferredSize(new Dimension(150,150));
        JPanel updateTravelAdvisorPanel = new JPanel();
        updateTravelAdvisorPanel.setVisible(false);
        updateTravelAdvisorPanel.setLayout(new BoxLayout(updateTravelAdvisorPanel,BoxLayout.Y_AXIS));
        updateTravelAdvisorPanel.setBounds(0,0,150,450);
        UpdateStockPanel updateStockPanel = new UpdateStockPanel();
        updateStockPanel.setLayout(new BoxLayout(updateStockPanel,BoxLayout.Y_AXIS));
        AssignBlankPanel assignBlankPanel = new AssignBlankPanel();
        assignBlankPanel.setLayout(new BoxLayout(assignBlankPanel,BoxLayout.Y_AXIS));
        AddBlankPanel addBlankPanel = new AddBlankPanel();
        addBlankPanel.setLayout(new BoxLayout(addBlankPanel,BoxLayout.Y_AXIS));
        RemoveBlankPanel removeBlankPanel = new RemoveBlankPanel();
        removeBlankPanel.setLayout(new BoxLayout(removeBlankPanel,BoxLayout.Y_AXIS));
        JPanel ratesUpdatePanel = new JPanel();
        ratesUpdatePanel.setVisible(false);
        ratesUpdatePanel.setLayout(new BoxLayout(ratesUpdatePanel,BoxLayout.Y_AXIS));
        ratesUpdatePanel.setBounds(0,0,150,450);
        JPanel addRatePanel = new JPanel();
        addRatePanel.setVisible(false);
        addRatePanel.setLayout(new BoxLayout(addRatePanel,BoxLayout.Y_AXIS));
        addRatePanel.setBounds(0,0,150,450);
        JPanel updateRatePanel = new JPanel();
        updateRatePanel.setVisible(false);
        updateRatePanel.setLayout(new BoxLayout(updateRatePanel,BoxLayout.Y_AXIS));
        updateRatePanel.setBounds(0,0,150,450);
        JPanel removeRatePanel = new JPanel();
        removeRatePanel.setVisible(false);
        removeRatePanel.setLayout(new BoxLayout(removeRatePanel,BoxLayout.Y_AXIS));
        removeRatePanel.setBounds(0,0,150,450);
        JPanel commissionUpdatePanel = new JPanel();
        commissionUpdatePanel.setVisible(false);
        commissionUpdatePanel.setLayout(new BoxLayout(commissionUpdatePanel,BoxLayout.Y_AXIS));
        commissionUpdatePanel.setBounds(0,0,150,450);

        JLabel idLabel = new JLabel("iD");
        JLabel usernameLabel = new JLabel("Username");
        JLabel passwordLabel = new JLabel("Password");
        JLabel forenameLabel = new JLabel("Forename");
        JLabel surnameLabel = new JLabel("Surname");
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
        PlaceholderTextField forenameTextfield = new PlaceholderTextField();
        forenameTextfield.setPlaceholder("forename");
        PlaceholderTextField surnameTextfield = new PlaceholderTextField();
        surnameTextfield.setPlaceholder("surname");
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






        JButton backButton1 = new JButton("back");

        JButton viewAddRateButton = new JButton("add rate");
        JButton viewUpdateRateButton = new JButton("update rate");
        JButton viewRemoveRateButton = new JButton("remove rate");


        JLabel currencyLabel = new JLabel("currency");
        JLabel rate1Label = new JLabel("rate");
        JLabel rateID1Label = new JLabel("rate id");
        JLabel rate2Label = new JLabel("rate");
        JLabel rateID2Label = new JLabel("rate id");


        PlaceholderTextField currencyTextfield = new PlaceholderTextField();
        currencyTextfield.setPlaceholder("currency");
        PlaceholderTextField rate1Textfield = new PlaceholderTextField();
        rate1Textfield.setPlaceholder("rate");
        PlaceholderTextField rateID1Textfield = new PlaceholderTextField();
        rateID1Textfield.setPlaceholder("rate id");
        PlaceholderTextField rate2Textfield = new PlaceholderTextField();
        rate2Textfield.setPlaceholder("rate");
        PlaceholderTextField rateID2Textfield = new PlaceholderTextField();
        rateID2Textfield.setPlaceholder("rate id");

        JButton addRateButton = new JButton("add rate");
        JButton updateRateButton = new JButton("update rate");
        JButton removeRateButton = new JButton("remove rate");
        JButton backButton2 = new JButton("back");
        JButton backButton3 = new JButton("back");
        JButton backButton4 = new JButton("back");



        JTabbedPane commissionPane = new JTabbedPane();
        commissionUpdatePanel.add(commissionPane);
        JPanel addCommissionPanel = new JPanel();
        addCommissionPanel.setLayout(new BoxLayout(addCommissionPanel,BoxLayout.Y_AXIS));
        JPanel updateCommissionPanel = new JPanel();
        updateCommissionPanel.setLayout(new BoxLayout(updateCommissionPanel,BoxLayout.Y_AXIS));
        JPanel removeCommissionPanel = new JPanel();
        removeCommissionPanel.setLayout(new BoxLayout(removeCommissionPanel,BoxLayout.Y_AXIS));


        JLabel commissionRateLabel1 = new JLabel("commission rate");
        JLabel commissionRateLabel2 = new JLabel("commission rate");
        JLabel isActiveLabel = new JLabel("is active");

        PlaceholderTextField commissionRateTextfield1 = new PlaceholderTextField();
        commissionRateTextfield1.setPlaceholder("rate");
        PlaceholderTextField commissionRateTextfield2 = new PlaceholderTextField();
        commissionRateTextfield2.setPlaceholder("rate");
        JComboBox isActiveBox = new JComboBox();
        isActiveBox.addItem("no");
        isActiveBox.addItem("yes");


        JButton addCommissionButton = new JButton("add");
        JButton updateCommissionButton = new JButton("update");
        JButton removeCommissionButton = new JButton("remove");

        addCommissionPanel.add(commissionRateLabel1);
        addCommissionPanel.add(Box.createRigidArea(new Dimension(0,15)));
        addCommissionPanel.add(commissionRateTextfield1);
        addCommissionPanel.add(Box.createRigidArea(new Dimension(0,15)));
        addCommissionPanel.add(addCommissionButton);
        addCommissionPanel.add(Box.createRigidArea(new Dimension(0,500)));

        updateCommissionPanel.add(commissionRateLabel2);
        updateCommissionPanel.add(Box.createRigidArea(new Dimension(0,15)));
        updateCommissionPanel.add(commissionRateTextfield2);
        updateCommissionPanel.add(Box.createRigidArea(new Dimension(0,15)));
        updateCommissionPanel.add(isActiveLabel);
        updateCommissionPanel.add(Box.createRigidArea(new Dimension(0,15)));
        updateCommissionPanel.add(isActiveBox);
        updateCommissionPanel.add(Box.createRigidArea(new Dimension(0,15)));
        updateCommissionPanel.add(Box.createRigidArea(new Dimension(0,15)));
        updateCommissionPanel.add(updateCommissionButton);
        updateCommissionPanel.add(Box.createRigidArea(new Dimension(0,400)));

        removeCommissionPanel.add(removeCommissionButton);
        removeCommissionPanel.add(Box.createRigidArea(new Dimension(0,400)));

        commissionPane.add("add commission",addCommissionPanel);
        commissionPane.add("update commission",updateCommissionPanel);
        commissionPane.add("remove commission",removeCommissionPanel);

        ratesUpdatePanel.add(viewAddRateButton);
        ratesUpdatePanel.add(Box.createRigidArea(new Dimension(0,15)));
        ratesUpdatePanel.add(viewUpdateRateButton);
        ratesUpdatePanel.add(Box.createRigidArea(new Dimension(0,15)));
        ratesUpdatePanel.add(viewRemoveRateButton);


        //add rate panel
        addRatePanel.add(Box.createRigidArea(new Dimension(0,15)));
        addRatePanel.add(currencyLabel);
        addRatePanel.add(Box.createRigidArea(new Dimension(0,15)));
        addRatePanel.add(currencyTextfield);
        addRatePanel.add(Box.createRigidArea(new Dimension(0,15)));
        addRatePanel.add(rate1Label);
        addRatePanel.add(Box.createRigidArea(new Dimension(0,15)));
        addRatePanel.add(rate1Textfield);
        addRatePanel.add(Box.createRigidArea(new Dimension(0,15)));
        addRatePanel.add(addRateButton);
        addRatePanel.add(Box.createRigidArea(new Dimension(0,15)));
        addRatePanel.add(backButton2);
        addRatePanel.add(Box.createRigidArea(new Dimension(0,350)));

        //update rate panel
        updateRatePanel.add(rateID1Label);
        updateRatePanel.add(Box.createRigidArea(new Dimension(0,15)));
        updateRatePanel.add(rateID1Textfield);
        updateRatePanel.add(Box.createRigidArea(new Dimension(0,15)));
        updateRatePanel.add(rate2Label);
        updateRatePanel.add(Box.createRigidArea(new Dimension(0,15)));
        updateRatePanel.add(rate2Textfield);
        updateRatePanel.add(Box.createRigidArea(new Dimension(0,15)));
        updateRatePanel.add(updateRateButton);
        updateRatePanel.add(Box.createRigidArea(new Dimension(0,15)));
        updateRatePanel.add(backButton3);
        updateRatePanel.add(Box.createRigidArea(new Dimension(0,350)));

        //remove rate panel
        removeRatePanel.add(rateID2Label);
        removeRatePanel.add(Box.createRigidArea(new Dimension(0,15)));
        removeRatePanel.add(rateID2Textfield);
        removeRatePanel.add(Box.createRigidArea(new Dimension(0,15)));
        removeRatePanel.add(removeRateButton);
        removeRatePanel.add(Box.createRigidArea(new Dimension(0,15)));
        removeRatePanel.add(backButton4);
        removeRatePanel.add(Box.createRigidArea(new Dimension(0,350)));



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
        updateTravelAdvisorPanel.add(forenameLabel);
        updateTravelAdvisorPanel.add(Box.createRigidArea(new Dimension(0,5)));
        updateTravelAdvisorPanel.add(forenameTextfield);
        updateTravelAdvisorPanel.add(Box.createRigidArea(new Dimension(0,5)));
        updateTravelAdvisorPanel.add(surnameLabel);
        updateTravelAdvisorPanel.add(Box.createRigidArea(new Dimension(0,5)));
        updateTravelAdvisorPanel.add(surnameTextfield);
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






        //adds to leftPanel stock pane


        leftLayeredPane.add(updateTravelAdvisorPanel);
        leftLayeredPane.add(updateStockPanel);
        leftLayeredPane.add(assignBlankPanel);
        leftLayeredPane.add(addBlankPanel);
        leftLayeredPane.add(removeBlankPanel);
        leftLayeredPane.add(ratesUpdatePanel);
        leftLayeredPane.add(addRatePanel);
        leftLayeredPane.add(updateRatePanel);
        leftLayeredPane.add(removeRatePanel);
        leftLayeredPane.add(commissionUpdatePanel);
        leftPanel.add(leftLayeredPane,BorderLayout.CENTER);




        //sets button listeners
        updateStockPanel.viewAssignBlankButton.addActionListener(e -> {
            updateStockPanel.setVisible(false);
            assignBlankPanel.setVisible(true);
        });
        updateStockPanel.viewAddBlankButton.addActionListener(e -> {
            addBlankPanel.setVisible(true);
            updateStockPanel.setVisible(false);
        });
        updateStockPanel.viewRemoveBlankButton.addActionListener(e -> {
            removeBlankPanel.setVisible(true);
            updateStockPanel.setVisible(false);
        });
        assignBlankPanel.backButton.addActionListener(e -> {
            updateStockPanel.setVisible(true);
            assignBlankPanel.setVisible(false);
        });
        assignBlankPanel.assignBlankButton.addActionListener(e -> {
            int[] rows = stock.getSelectedRows();

            try {
                // 1. get a connection
                con = db.getConnection();

                //2. create a statement
                String sql = "UPDATE stock "
                        + " SET StaffID=?"
                        + " WHERE BlankID=?";
                PreparedStatement stm = con.prepareStatement(sql);

                stm.setInt(1, Integer.parseInt(assignBlankPanel.idStaffTextfield.getText()));
                for (int i = 0; i < rows.length; i++) {

                    stm.setInt(2, Integer.parseInt(stock.getValueAt(rows[i],0).toString()));
                    //3. execute sql query
                    stm.executeUpdate();
                }
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        });
        addBlankPanel.addBlankButton.addActionListener(e ->{
            try {
                // 1. get a connection
                con = db.getConnection();

                //2. create a statement
                String sql = "INSERT INTO stock "
                        + " (Type)"
                        + "VALUES ( ?)";
                stm = con.prepareStatement(sql);

                int amount = Integer.parseInt(addBlankPanel.amountTextfield.getText());

                for(int i = 0; i < amount; i++) {
                    stm.setInt(1, Integer.parseInt(addBlankPanel.typeBox.getSelectedItem().toString()));

                    //3. execute sql query
                    stm.executeUpdate();

                }


            }catch (Exception ex){
                ex.printStackTrace();
            }
        });
        addBlankPanel.backButton.addActionListener(e -> {
            addBlankPanel.setVisible(false);
            updateStockPanel.setVisible(true);
        });

        removeBlankPanel.removeBlankButton.addActionListener(e -> {
            try {
                // 1. get a connection
                con = db.getConnection();

                // 2. create a statement
                String sql = "DELETE FROM stock WHERE BlankID=?";
                PreparedStatement stm = con.prepareStatement(sql);

                int[] rows = stock.getSelectedRows();

                for (int i = 0; i <rows.length ; i++) {
                    stm.setInt(1,Integer.parseInt(stock.getValueAt(rows[i],0).toString()));
                    // 3. execute sql statement
                    stm.executeUpdate();
                }

            }catch (SQLException ex){
                ex.printStackTrace();
            }
        });
        removeBlankPanel.backButton.addActionListener(e -> {
            removeBlankPanel.setVisible(false);
            updateStockPanel.setVisible(true);
        });
        backButton1.addActionListener(e -> {
            updateStockPanel.setVisible(true);
            ratesUpdatePanel.setVisible(false);
            updateRatePanel.setVisible(false);

        });

        viewUserButton.addActionListener(e -> {
            userPanel.setVisible(true);
            stockPanel.setVisible(false);
            updateTravelAdvisorPanel.setVisible(true);
            updateStockPanel.setVisible(false);
            databasePanel.setVisible(false);
            ratesPanel.setVisible(false);
            ratesUpdatePanel.setVisible(false);
            updateRatePanel.setVisible(false);

            userModel.setRowCount(0);
            viewUser();

        });
        viewStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stockPanel.setVisible(true);
                userPanel.setVisible(false);
                updateStockPanel.setVisible(true);
                updateTravelAdvisorPanel.setVisible(false);
                databasePanel.setVisible(false);
                ratesPanel.setVisible(false);
                ratesUpdatePanel.setVisible(false);
                updateRatePanel.setVisible(false);

                stockModel.setRowCount(0);
                viewStock();
            }
        });



        viewBackupButton.addActionListener(e -> {
            databasePanel.setVisible(true);
            stockPanel.setVisible(false);
            userPanel.setVisible(false);
            updateStockPanel.setVisible(false);
            updateTravelAdvisorPanel.setVisible(false);
            ratesPanel.setVisible(false);
            ratesUpdatePanel.setVisible(false);
            updateRatePanel.setVisible(false);
            backupModel.setRowCount(0);
            viewBackup();


        });
        viewExchangeRates.addActionListener(e -> {
            ratesPanel.setVisible(true);
            ratesUpdatePanel.setVisible(true);
            databasePanel.setVisible(false);
            stockPanel.setVisible(false);
            userPanel.setVisible(false);
            updateStockPanel.setVisible(false);
            updateTravelAdvisorPanel.setVisible(false);
            updateRatePanel.setVisible(false);

            rateModel.setRowCount(0);
            viewRate();
        });
        viewCommission.addActionListener(e -> {
            commissionUpdatePanel.setVisible(true);
            ratesPanel.setVisible(false);
            ratesUpdatePanel.setVisible(false);
            databasePanel.setVisible(false);
            stockPanel.setVisible(false);
            userPanel.setVisible(false);
            updateStockPanel.setVisible(false);
            updateTravelAdvisorPanel.setVisible(false);
            updateRatePanel.setVisible(false);

            commissionModel.setRowCount(0);
            viewCommission();
        });
        viewAddRateButton.addActionListener(e -> {
            addRatePanel.setVisible(true);
            ratesUpdatePanel.setVisible(false);
            databasePanel.setVisible(false);
            stockPanel.setVisible(false);
            userPanel.setVisible(false);
            updateStockPanel.setVisible(false);
            updateTravelAdvisorPanel.setVisible(false);
            updateRatePanel.setVisible(false);
        });
        backButton2.addActionListener(e -> {
            addRatePanel.setVisible(false);
            ratesUpdatePanel.setVisible(true);
        });
        viewUpdateRateButton.addActionListener(e -> {
            updateRatePanel.setVisible(true);
            ratesUpdatePanel.setVisible(false);
            ratesUpdatePanel.setVisible(false);
            databasePanel.setVisible(false);
            stockPanel.setVisible(false);
            userPanel.setVisible(false);
            updateStockPanel.setVisible(false);
            updateTravelAdvisorPanel.setVisible(false);

        });
        backButton3.addActionListener(e -> {
            ratesUpdatePanel.setVisible(true);
            updateRatePanel.setVisible(false);
        });
        viewRemoveRateButton.addActionListener(e -> {
            removeRatePanel.setVisible(true);
            updateRatePanel.setVisible(false);
            ratesUpdatePanel.setVisible(false);
            ratesUpdatePanel.setVisible(false);
            databasePanel.setVisible(false);
            stockPanel.setVisible(false);
            userPanel.setVisible(false);
            updateStockPanel.setVisible(false);
            updateTravelAdvisorPanel.setVisible(false);
        });
        backButton4.addActionListener(e -> {
            removeRatePanel.setVisible(false);
            ratesUpdatePanel.setVisible(true);
        });
        logoutButton.addActionListener(e -> {
            LoginForm loginForm = new LoginForm();
            dispose();
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        addUserButton.addActionListener(e -> {
            try {
                // 1. get a connection
                con = db.getConnection();

                //2. create a statement
                String sql = "INSERT INTO staff "
                        + " (Forename,Surname, address, telephone, email, username, password, Type)"
                        + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);

                stm.setString(1, forenameTextfield.getText());
                stm.setString(2, surnameTextfield.getText());
                stm.setString(3, addressTextfield.getText());
                stm.setString(4, telephoneTextfield.getText());
                stm.setString(5, emailTextfield.getText());
                stm.setString(6, usernameTextfield.getText());
                stm.setString(7, passwordTextfield.getText());
                stm.setString(8,staffType.getSelectedItem().toString());

                //3. execute sql query
                stm.executeUpdate();



            }catch (Exception ex){
                ex.printStackTrace();
            }
        });

        updateUserButton.addActionListener(e -> {
            try {
                // 1. get a connection
                con = db.getConnection();

                //2. create a statement
                String sql = "UPDATE staff "
                        + " SET forename=?,surname=? , address=?, telephone=?, email=?, username=?, password=?,Type=?"
                        + " WHERE ID=?";

                stm = con.prepareStatement(sql);

                stm.setString(1,forenameTextfield.getText());
                stm.setString(2,surnameTextfield.getText());
                stm.setString(3,addressTextfield.getText());
                stm.setString(4,telephoneTextfield.getText());
                stm.setString(5,emailTextfield.getText());
                stm.setString(6,usernameTextfield.getText());
                stm.setString(7,passwordTextfield.getText());
                stm.setString(8,staffType.getSelectedItem().toString());
                stm.setInt(9,Integer.parseInt(idTextfield.getText()));




                //3. execute sql query
                stm.executeUpdate();

            }catch(Exception ex){
                ex.printStackTrace();
            }
        });
        removeUserButton.addActionListener(e -> {
            try {
                // 1. get a connection
                con = db.getConnection();

                //2. create a statement

                String sql = "DELETE FROM staff WHERE ID=?";
                stm = con.prepareStatement(sql);

                stm.setInt(1, Integer.parseInt(idTextfield.getText()));

                //3. execute sql query
                stm.executeUpdate();


            }catch (Exception ex){
                ex.printStackTrace();
            }
        });


        addRateButton.addActionListener(e -> {
            try {
                // 1. get a connection
                con = db.getConnection();

                //2. create a statement
                String sql = "INSERT INTO exchange_rate "
                        + " (CurrencyCode,Rate)"
                        + "VALUES ( ?, ?)";
                stm = con.prepareStatement(sql);

                stm.setString(1, currencyTextfield.getText());
                stm.setFloat(2, Float.parseFloat(rate1Textfield.getText()));

                //3. execute sql statement
                stm.executeUpdate();

            }catch(SQLException ex){
                ex.printStackTrace();
            }

        });
        updateRateButton.addActionListener(e -> {
            try{
                // 1. get a connection
                con = db.getConnection();

                //2. create a statement
                String sql = "UPDATE exchange_rate "
                        + " SET Rate=? "
                        + "WHERE RateID=?";
                stm = con.prepareStatement(sql);

                stm.setFloat(1, Float.parseFloat(rate2Textfield.getText()));
                stm.setInt(2, Integer.parseInt(rateID1Textfield.getText()));

                //3. execute sql statement
                stm.executeUpdate();


            }catch(SQLException ex){
                ex.printStackTrace();
            }
        });
        removeRateButton.addActionListener(e -> {
            try{
                // 1. get a connection
                con = db.getConnection();

                //2. create a statement
                String sql = "DELETE FROM exchange_rate WHERE RateID=?";
                stm = con.prepareStatement(sql);

                stm.setInt(1, Integer.parseInt(rateID2Textfield.getText()));

                //3. execute sql statement
                stm.executeUpdate();

            }catch (SQLException ex){
                ex.printStackTrace();
            }
        });
        addCommissionButton.addActionListener(e -> {
            try{
                // 1. get a connection
                con = db.getConnection();

                //2. create a statement
                String sql = "INSERT INTO commission "
                        + " (CommissionRate)"
                        + "VALUES (?)";
                stm = con.prepareStatement(sql);

                stm.setFloat(1, Float.parseFloat(commissionRateTextfield1.getText()));

                //3. execute sql statement
                stm.executeUpdate();

            }catch (SQLException ex){
                ex.printStackTrace();
            }
        });
        updateCommissionButton.addActionListener(e -> {
            try {
                con = db.getConnection();
                if (isActiveBox.getSelectedItem().toString().equals("no")) {

                    int[] rows = commission.getSelectedRows();
                    String sql = "UPDATE commission "
                            + " SET CommissionRate=?,IsActive=0"
                            + " WHERE CommissionID=?";
                    PreparedStatement stm = con.prepareStatement(sql);

                    stm.setFloat(1, Float.parseFloat(commissionRateTextfield2.getText()));

                    for (int i = 0; i <rows.length ; i++) {
                        System.out.println(commission.getValueAt(rows[i],0).toString());
                        stm.setInt(2, Integer.parseInt(commission.getValueAt(rows[i],0).toString()));
                        stm.executeUpdate();
                    }


                }else {
                    int cell = Integer.parseInt(commission.getValueAt(commission.getSelectedRow(),0).toString());
                    String sql = "UPDATE commission "
                            + " SET active=0";

                    PreparedStatement stm = con.prepareStatement(sql);
                    stm.executeUpdate();

                    String sql2 =  "UPDATE commission"
                            + " SET active=1 "
                            + " WHERE CommissionID=? ";
                    stm = con.prepareStatement(sql2);
                    stm.setInt(1,cell);
                    stm.executeUpdate();


                }
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        });
        removeCommissionButton.addActionListener(e -> {
            try{
                con = db.getConnection();
                int[] rows = commission.getSelectedRows();

                String sql = "DELETE FROM commission "
                        + " WHERE CommissionID=? ";

                PreparedStatement stm = con.prepareStatement(sql);
                for (int i = 0; i <rows.length ; i++) {
                    stm.setInt(1, Integer.parseInt(commission.getValueAt(rows[i],0).toString()));
                    stm.executeUpdate();
                }

            }catch (SQLException ex){
                ex.printStackTrace();
            }
        });
        backupDatabaseButton.addActionListener(e -> {
            Database.backupDatabase();
        });
        restoreDatabaseButton.addActionListener(e -> {
            int cell = Integer.parseInt(backup.getValueAt(backup.getSelectedRow(),0).toString());
            Database.restoreDatabase(cell);
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(750,500);
//        setVisible(true);
        System.out.println("this user has logged in ->" + getID());
    }
    public int getID(){
        return id;
    }
    public void viewUser(){
        List<User> userList = new ArrayList<>();
        try{
            con = db.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM staff");

            while(rs.next()){
                userList.add(new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9)));
            }
            User u;

            for (int i = 0; i < userList.size() ; i++) {
                u = userList.get(i);
                userModel.addRow(u.rowArray());
            }

        }catch (SQLException ex){
           ex.printStackTrace();
        }
    }
    public void viewStock(){
        List<Blank> stockList = new ArrayList<>();
        try {
            con = db.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM stock");

            while(rs.next()){
                stockList.add(new Blank(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getDate(5)));

            }
            Blank b;

            for (int i = 0; i < stockList.size(); i++) {
                b = stockList.get(i);
                stockModel.addRow(b.rowArray());

            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    public void viewRate(){
        List<Rate> rateList = new ArrayList<>();
        try {
            con = db.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM exchange_rate");

            while(rs.next()){
                rateList.add(new Rate(rs.getInt(1),
                        rs.getString(2),
                        rs.getFloat(3)));

            }
            Rate r;

            for (int i = 0; i < rateList.size(); i++) {
                r = rateList.get(i);
                rateModel.addRow(r.rowArray());

            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    public void viewCommission(){
        List<Commission> commissionList = new ArrayList<>();
        try {
            con = db.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM commission");

            while(rs.next()){
                commissionList.add(new Commission(rs.getInt(1),
                        rs.getFloat(2),
                        rs.getBoolean(3)));

            }
            Commission c;

            for (int i = 0; i < commissionList.size(); i++) {
                c = commissionList.get(i);
                commissionModel.addRow(c.rowArray());

            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    public void viewBackup(){
        List<Backup> backupList = new ArrayList<>();
        try{
            con = db.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM backups");

            while(rs.next()){
                backupList.add(new Backup(rs.getInt(1),
                        rs.getDate(2)));
            }
            Backup b;

            for (int i = 0; i <backupList.size() ; i++) {
                b = backupList.get(i);
                backupModel.addRow(b.rowArray());
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }


}
