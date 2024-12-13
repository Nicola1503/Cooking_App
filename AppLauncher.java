package GUI;

import javax.swing.*;

public class AppLauncher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // instantiate a LoginFormGUI object and make it visible
                new LoginFormGUI().setVisible(true);
//                new RegisterFormGUI().setVisible(true);

                //check user test
//                System.out.println(MyJDBC.checkUser("password"));

                //check register test
//                System.out.println(MyJDBC.register("username", "password"));

                //check validate login test
//                System.out.println(MyJDBC.validateLogin("username", "password"));

                //instantiate the welcome page screen
//                new WelcomePage().setVisible(true);
            }
        });
    }
}
