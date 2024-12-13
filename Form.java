package GUI;

import constants.CommonConstants;

import javax.swing.*;

public class Form extends JFrame {
    //create constructor
    public Form(String title){
        //set title of title bar
        super(title);

        //set size of the GUI
        setSize(550, 700);

        //configure GUI to end process after closing
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Set layout to null to disable layout management so we can use absolute positioning
        //to place the components wherever we want
        setLayout(null);

        //prevent GUI from changing size
        setResizable(false);

        //change the background color of the GUI
        getContentPane().setBackground(CommonConstants.PRIMARY_COLOR);
    }
}
