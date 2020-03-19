package container;

import component.PlaceholderTextField;
import javax.swing.*;


public class UpdateCustomerPanel extends JPanel {
    public JButton backButton = new JButton("back");
    public JButton updateButton = new JButton("update details");
    public JLabel IDLabel = new JLabel("customer ID: ");
    public JLabel nameLabel = new JLabel("name: ");
    public JLabel dateOfBirthLabel = new JLabel("date of birth: ");
    public JLabel addressLabel = new JLabel("address: ");
    public JLabel postalCodeLabel = new JLabel("postcode: ");
    public JLabel telephoneLabel = new JLabel("telephone: ");
    public JLabel emailLabel = new JLabel("email: ");

    public PlaceholderTextField IDField = new PlaceholderTextField();
    public PlaceholderTextField nameTextField = new PlaceholderTextField();
    public PlaceholderTextField dateOfBirthTextField = new PlaceholderTextField();
    public PlaceholderTextField addressTextField = new PlaceholderTextField();
    public PlaceholderTextField postalCodeTextField = new PlaceholderTextField();
    public PlaceholderTextField telephoneTextField = new PlaceholderTextField();
    public PlaceholderTextField emailTextField = new PlaceholderTextField();

    public UpdateCustomerPanel(){
        setVisible(false);
        setBounds(0,0,150,400);
        add(IDLabel);
        add(IDField);
        add(nameLabel);
        add(nameTextField);
        add(dateOfBirthLabel);
        add(dateOfBirthTextField);
        add(addressLabel);
        add(addressTextField);
        add(postalCodeLabel);
        add(postalCodeTextField);
        add(telephoneLabel);
        add(telephoneTextField);
        add(emailLabel);
        add(emailTextField);
        add(backButton);
        add(updateButton);



    }
}
