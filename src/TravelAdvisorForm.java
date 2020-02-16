import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TravelAdvisorForm extends JFrame {
    public TravelAdvisorForm(){
        super("Travel Advisor page");

        JPanel panel1 = new JPanel(new BorderLayout());

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.MAGENTA);

        JPanel rightPanel = new JPanel();

        JPanel leftPanel = new JPanel(new GridLayout(8,8,5,40));

        JPanel bottomPanel = new JPanel();

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBackground(Color.RED);
        JPanel customerPanel = new JPanel(new BorderLayout());
        JPanel transactionPanel = new JPanel(new GridLayout(2,2,5,200));
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
        String[] s1 = {"advisor: 1","advisor: 2","advisor: 3","advisor: 4","advisor: 5"};
        JList customers = new JList(s1);
        JScrollPane scrollPane1 = new JScrollPane(customers);
        customerPanel.add(scrollPane1,BorderLayout.CENTER);
        customerPanel.setBounds(0,0,600,600);
        
        


        JLabel amountLabel = new JLabel("amount");
        PlaceholderTextField amountTextfield = new PlaceholderTextField();
        JLabel paymentTypeLabel = new JLabel("payment type");
        PlaceholderTextField paymentTypeTextfield = new PlaceholderTextField();
        paymentTypeTextfield.setPlaceholder("payment type");
        amountTextfield.setPlaceholder("amount");
        transactionPanel.add(amountLabel);
        transactionPanel.add(amountTextfield);
        transactionPanel.add(paymentTypeLabel);
        transactionPanel.add(paymentTypeTextfield);

        transactionPanel.setBounds(0,0,400,400);


        layeredPane.add(customerPanel);
        layeredPane.add(transactionPanel);




        //sets up bottom BorderLayout
        JButton logoutButton = new JButton("Logout");
        bottomPanel.add(logoutButton);
        //sets up right BorderLayout
        JButton viewCustomers = new JButton("viewCustomer");
        JButton sellTicketButton = new JButton("sellTicket");
        JButton createAccountButton = new JButton("create account");
        JButton updateAccountButton = new JButton("update account");
        JLabel cancelTicket = new JLabel("cancel ticket");
        PlaceholderTextField ticketIdTextfield = new PlaceholderTextField();
        ticketIdTextfield.setPlaceholder("ticket ID");




        //adds to right container
        rightPanel.add(viewCustomers);
        rightPanel.add(Box.createRigidArea(new Dimension(0,15)));
        rightPanel.add(sellTicketButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0,15)));
        rightPanel.add(cancelTicket);
        rightPanel.add(Box.createRigidArea(new Dimension(0,15)));
        rightPanel.add(ticketIdTextfield);
        rightPanel.add(Box.createRigidArea(new Dimension(0,15)));
        rightPanel.add(createAccountButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0,15)));
        rightPanel.add(updateAccountButton);
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
            }
        });
        sellTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                transactionPanel.setVisible(true);
                customerPanel.setVisible(false);

            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(750,500);
        setVisible(true);


    }
}
