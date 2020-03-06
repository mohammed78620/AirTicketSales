package gui;

import component.PlaceholderTextField;
import controller.LoginController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame {
    private String username;
    private String password;
    private final JLabel usernameLabel;
    private final JLabel passwordLabel;
    private PlaceholderTextField usernameText;
    private PlaceholderTextField passwordText;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JButton loginButton;
    private LoginController loginController;


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
                //gets the username and password from JtextField
                username = usernameText.getText();
                password = passwordText.getText();

                if(!(username.isEmpty() || password.isEmpty())) {
//                System.out.println("the username is: " +username +" the password is: " + password);
                    LoginController loginController = new LoginController(username, password);
                    //sets the login page as invisible and opens officemanagerform
                    if (loginController.loginAuthenticated()) {
                        dispose();
                    }
                }



            }
        });


        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(350,250);

    }
    public void openOfficeManagerForm(){

    }
    public void openTicketAdvisorForm(){

    }
    public void openTravelAdvisorForm(){

    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
}
