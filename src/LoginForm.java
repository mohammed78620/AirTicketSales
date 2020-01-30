import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame {
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private PlaceholderTextField usernameText;
    private PlaceholderTextField passwordText;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JButton loginButton;


    public LoginForm(){
        //frame setup
        super("Login Page");
        setLayout(new GridLayout(2,2,3,3));
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        add(panel1);
        add(panel2);
        add(panel3);
        add(panel4);
        usernameLabel = new JLabel("username");
        panel1.add(usernameLabel);

        usernameText = new PlaceholderTextField(15);
        usernameText.setPlaceholder("username");
        panel2.add(usernameText);


        passwordLabel = new JLabel("password");
        panel3.add(passwordLabel);

        passwordText = new PlaceholderTextField(15);
        passwordText.setPlaceholder("password");
        panel4.add(passwordText);

        loginButton = new JButton("Login");
        panel4.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                OfficeManagerForm officeManagerForm = new OfficeManagerForm();
                officeManagerForm.setVisible(true);
            }
        });



        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(750,750);
        setVisible(true);
    }
}
