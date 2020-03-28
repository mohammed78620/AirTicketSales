package container;

import component.PlaceholderTextField;
import javax.swing.*;
import java.awt.*;

public class CancelTicketPanel extends JPanel {
    public JButton cancelButton = new JButton("confirm cancellation");
    public JButton refundButton = new JButton("refund ticket");
    public JComboBox typeBox;
    public JLabel cancelTicketLabel = new JLabel("cancel ticket: ");
    public JLabel amountLabel = new JLabel("amount: ");
    public PlaceholderTextField amountTextfield = new PlaceholderTextField();
    public PlaceholderTextField ticketIdTextfield = new PlaceholderTextField();
    public JLabel typeLabel = new JLabel("refund type: ");
    public JLabel IDLabel = new JLabel("customer ID: ");
    public PlaceholderTextField IDTextField = new PlaceholderTextField();


public CancelTicketPanel(){
    setVisible(false);
    setBounds(0, 0, 400, 400);
    typeBox = new JComboBox();
    typeBox.addItem("credit card");
    typeBox.addItem("cash");
    add(cancelTicketLabel);
    add(Box.createRigidArea(new Dimension(0,5)));
    add(ticketIdTextfield);
    ticketIdTextfield.setPlaceholder("ticket ID");
    add(Box.createRigidArea(new Dimension(0,5)));
    add(cancelButton);
    add(Box.createRigidArea(new Dimension(0,5)));
    add(IDLabel);
    add(Box.createRigidArea(new Dimension(0,5)));
    add(IDTextField);
    IDTextField.setPlaceholder("customer ID");
    add(Box.createRigidArea(new Dimension(0,5)));
    add(amountLabel);
    add(Box.createRigidArea(new Dimension(0,5)));
    add(amountTextfield);
    amountTextfield.setPlaceholder("amount");
    add(Box.createRigidArea(new Dimension(0,5)));
    add(typeLabel);
    add(Box.createRigidArea(new Dimension(0,5)));
    add(typeBox);
    add(Box.createRigidArea(new Dimension(0,5)));
    add(refundButton);
    add(Box.createRigidArea(new Dimension(0,5)));
}

}
