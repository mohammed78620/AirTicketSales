package container;

import component.PlaceholderTextField;
import javax.swing.*;


public class UpdateCustomerPanel extends JPanel {
    public JButton backButton = new JButton("back");
    public JButton updateButton = new JButton("update");
    public JLabel IDLabel = new JLabel("customer ID: ");
    public JLabel forenameLabel = new JLabel("forename: ");
    public JLabel surnameLabel = new JLabel("surname: ");
    public JLabel dateOfBirthLabel = new JLabel("date of birth: ");
    public JLabel telephoneLabel = new JLabel("telephone: ");
    public JLabel emailLabel = new JLabel("email: ");
    public JLabel customerTypeLabel = new JLabel("customer type: ");
    public JComboBox typeBox = new JComboBox();

    public PlaceholderTextField IDField = new PlaceholderTextField();
    public PlaceholderTextField dateOfBirthTextField = new PlaceholderTextField();
    public PlaceholderTextField forenameTextField = new PlaceholderTextField();
    public PlaceholderTextField surnameTextField = new PlaceholderTextField();
    public PlaceholderTextField telephoneTextField = new PlaceholderTextField();
    public PlaceholderTextField emailTextField = new PlaceholderTextField();
    public UpdateCustomerPanel(){
        setVisible(false);
        setBounds(0,0,150,400);
        add(IDLabel);
        add(IDField);
        add(forenameLabel);
        add(forenameTextField);
        add(surnameLabel);
        add(surnameTextField);
        add(dateOfBirthLabel);
        add(dateOfBirthTextField);
        add(telephoneLabel);
        add(telephoneTextField);
        add(emailLabel);
        add(emailTextField);
        add(customerTypeLabel);
        typeBox.addItem("regular");
        typeBox.addItem("valued");
        add(typeBox);
        add(updateButton);
        add(backButton);



    }
}
