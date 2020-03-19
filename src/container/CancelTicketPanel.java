package container;

import component.PlaceholderTextField;
import javax.swing.*;
import java.awt.*;

public class CancelTicketPanel extends JPanel {
    public JButton cancelButton = new JButton("cancel ticket");
    public JComboBox typeBox;
    public JLabel cancelTicketLabel = new JLabel("cancel ticket: ");
    public JLabel amountLabel = new JLabel("amount: ");
    public PlaceholderTextField amountTextfield = new PlaceholderTextField();
    public PlaceholderTextField ticketIdTextfield = new PlaceholderTextField();
    public JLabel descriptionLabel = new JLabel("description: ");
    public PlaceholderTextField descriptionTextfield = new PlaceholderTextField();
    public JLabel typeLabel = new JLabel("refund type");

public CancelTicketPanel(){
    setVisible(false);
    setBounds(0, 0, 400, 350);
    typeBox = new JComboBox();
    typeBox.addItem("credit card");
    typeBox.addItem("cash");
    add(cancelTicketLabel);
    add(Box.createRigidArea(new Dimension(0,15)));
    add(ticketIdTextfield);
    add(Box.createRigidArea(new Dimension(0,15)));
    add(amountLabel);
    add(Box.createRigidArea(new Dimension(0,15)));
    add(amountTextfield);
    add(Box.createRigidArea(new Dimension(0,15)));
    add(descriptionLabel);
    add(Box.createRigidArea(new Dimension(0,15)));
    add(descriptionTextfield);
    add(Box.createRigidArea(new Dimension(0,15)));
    add(typeLabel);
    add(Box.createRigidArea(new Dimension(0,15)));
    add(typeBox);
    add(Box.createRigidArea(new Dimension(0,15)));
    add(cancelButton);
}

}
