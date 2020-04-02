package container;

import javax.swing.*;
import java.awt.*;

public class RemoveBlankPanel extends JPanel{
    public JButton removeBlankButton;
    public JButton backButton;

    public RemoveBlankPanel(){
        setVisible(false);
        setBounds(0,0,150,450);

        removeBlankButton = new JButton("remove blank");
        backButton = new JButton("back");

        add(removeBlankButton);
        add(Box.createRigidArea(new Dimension(0,15)));
        add(backButton);

    }
}
