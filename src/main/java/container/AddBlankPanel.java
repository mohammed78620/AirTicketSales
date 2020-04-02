package container;

import component.PlaceholderTextField;

import javax.swing.*;
import java.awt.*;

public class AddBlankPanel extends JPanel {
    public JComboBox typeBox;
    public PlaceholderTextField amountTextfield;
    public JButton addBlankButton;
    public JButton backButton;
    public AddBlankPanel(){
        setVisible(false);
        setBounds(0,0,150,450);
        JLabel typeLabel = new JLabel("blank type");
        typeBox = new JComboBox();
        typeBox.addItem("444");
        typeBox.addItem("440");
        typeBox.addItem("420");
        typeBox.addItem("201");
        typeBox.addItem("101");
        amountTextfield = new PlaceholderTextField();
        amountTextfield.setPlaceholder("amount");
        addBlankButton = new JButton("add blank");
        backButton = new JButton("back");

        add(typeLabel);
        add(Box.createRigidArea(new Dimension(0,15)));
        add(typeBox);
        add(Box.createRigidArea(new Dimension(0,15)));
        add(amountTextfield);
        add(Box.createRigidArea(new Dimension(0,15)));
        add(addBlankButton);
        add(Box.createRigidArea(new Dimension(0,15)));
        add(backButton);
        add(Box.createRigidArea(new Dimension(0,250)));

    }
}
