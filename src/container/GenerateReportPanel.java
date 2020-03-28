package container;

import component.PlaceholderTextField;
import javax.swing.*;
import java.awt.*;

public class GenerateReportPanel extends JPanel {

    public JLabel staffIDBLabel = new JLabel("staff ID: ");
    public PlaceholderTextField staffIDTextfield = new PlaceholderTextField();
    public JLabel reportIDLabel = new JLabel("report ID: ");
    public PlaceholderTextField reportIDTextfield = new PlaceholderTextField();
    public JLabel dateAddedLabel = new JLabel("date: ");
    public PlaceholderTextField dateAddedTextField = new PlaceholderTextField();
    public JLabel typeLabel = new JLabel("report type");
    public JComboBox typeBox;
    public JLabel reportFileLabel = new JLabel("report file: ");
    public PlaceholderTextField reportFileTextField = new PlaceholderTextField();
    public JButton generateButton = new JButton("generate ");



    public GenerateReportPanel(){
        setVisible(false);
        setBounds(0, 0, 400, 400);
        typeBox = new JComboBox();
        typeBox.addItem("interline");
        typeBox.addItem("domestic");
        add(staffIDBLabel);
        add(Box.createRigidArea(new Dimension(0,15)));
        add(staffIDTextfield);
        add(Box.createRigidArea(new Dimension(0,15)));
        add(reportIDLabel);
        add(Box.createRigidArea(new Dimension(0,15)));
        add(reportIDTextfield);
        add(Box.createRigidArea(new Dimension(0,15)));
        add(dateAddedLabel);
        add(Box.createRigidArea(new Dimension(0,15)));
        add(dateAddedTextField);
        add(Box.createRigidArea(new Dimension(0,15)));
        add(typeLabel);
        add(Box.createRigidArea(new Dimension(0,15)));
        add(typeBox);
        add(Box.createRigidArea(new Dimension(0,15)));
        add(reportFileLabel);
        add(Box.createRigidArea(new Dimension(0,15)));
        add(reportFileTextField);
        add(Box.createRigidArea(new Dimension(0,15)));
        add(generateButton);
        add(Box.createRigidArea(new Dimension(0,15)));
    }

}
