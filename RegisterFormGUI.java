package GUI;

import constants.CommonConstants;
import db.MyJDBC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegisterFormGUI extends Form{
    public RegisterFormGUI() {
        super("Register");
        addGuiComponents();

    }
    private void addGuiComponents(){
        //create register label
        JLabel registerLabel = new JLabel("Register");

        //configure component's x,y position and width/height val relative to the screen
        registerLabel.setBounds(0,25,520,100);

        //change the font color
        registerLabel.setForeground(CommonConstants.TEXT_COLOR);

        //change the font size
        registerLabel.setFont(new Font("Dialog", Font.BOLD, 40));

        //center text
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        //add component to GUI
        add(registerLabel);

        //create username label
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(30,150,400,25);
        usernameLabel.setForeground(CommonConstants.TEXT_COLOR);
        usernameLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        //create username text field
        JTextField usernameField = new JTextField();
        usernameField.setBounds(30,200,450,55);
        usernameField.setBackground(CommonConstants.SECONDRY_COLOR);
        usernameField.setForeground(CommonConstants.TEXT_COLOR);
        usernameField.setFont(new Font("Dialog", Font.PLAIN, 24));

        add(usernameLabel);
        add(usernameField);

        //create password label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(30,255,400,25);
        passwordLabel.setForeground(CommonConstants.TEXT_COLOR);
        passwordLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        //create username text field
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(30,300,450,55);
        passwordField.setBackground(CommonConstants.SECONDRY_COLOR);
        passwordField.setForeground(CommonConstants.TEXT_COLOR);
        passwordField.setFont(new Font("Dialog", Font.PLAIN, 24));

        add(passwordLabel);
        add(passwordField);

        //create re-enter password label
        JLabel rePasswordLabel = new JLabel("Re-enter Password:");
        rePasswordLabel.setBounds(30,350,400,25);
        rePasswordLabel.setForeground(CommonConstants.TEXT_COLOR);
        rePasswordLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        //create username text field
        JPasswordField rePasswordField = new JPasswordField();
        rePasswordField.setBounds(30,395,450,55);
        rePasswordField.setBackground(CommonConstants.SECONDRY_COLOR);
        rePasswordField.setForeground(CommonConstants.TEXT_COLOR);
        rePasswordField.setFont(new Font("Dialog", Font.PLAIN, 24));

        add(rePasswordLabel);
        add(rePasswordField);


        //create login button
        JButton registerButton = new JButton("Register");
        registerButton.setFont(new Font("Dialog", Font.BOLD, 18));

        //change the cursor when hoover over the button
        registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerButton.setBackground(CommonConstants.TEXT_COLOR);
        registerButton.setBounds(125,520,250,50);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //get username
                String username = usernameField.getText();
                 // get password
                String password = new String(passwordField.getPassword());

                //get repassword
                String rePassword = new String(rePasswordField.getPassword());

                //validate user input

                if(validateUserInput(username,password,rePassword)){
                    // register user to the database
                    if(MyJDBC.register(username, password)){
                        //dcispose of this GUi
                        RegisterFormGUI.this.dispose();

                        //take user back to the login gui
                        LoginFormGUI loginFormGUI = new LoginFormGUI();
                        loginFormGUI.setVisible(true);

                        // create a result dialog
                        JOptionPane.showMessageDialog(loginFormGUI,
                                "Registered Account Successfully");
                    } else {
                        //register failed (likely due to the user already existing in the database)
                        JOptionPane.showMessageDialog(RegisterFormGUI.this, "Error: Username already taken");
                    }
                } else {
                    //invalid user input
                    JOptionPane.showMessageDialog(RegisterFormGUI.this,
                            "Error: Username must be at least 6 characters long \n" +
                                    "and/or Passwords must match");
                }
            }
        });
        add(registerButton);

        //create register label (used to load the register GUI)
        JLabel loginLabel = new JLabel("Have an account? Login here");
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginLabel.setForeground(CommonConstants.TEXT_COLOR);

        //add functionality so that when clicked it will launch the login form GUI
        loginLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //dispose of this GUI
                RegisterFormGUI.this.dispose();

                //launch the register gui
                new LoginFormGUI().setVisible(true);
            }
        });
        loginLabel.setBounds(125,600,250,30);
        add(loginLabel);

    }

    private boolean validateUserInput(String username, String password, String rePassword){
        //all fields must have a value
        if(username.length() == 0 || password.length() == 0 || rePassword.length() == 0) return false;

        //username has to be at least 6 characters long
        if (username.length() < 6) return false;

        //password and repassword must be the same
        if (!password.equals(rePassword)) return false;

        //passes validation
        return true;
    }
}
