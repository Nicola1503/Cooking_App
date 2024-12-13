package GUI;

import constants.CommonConstants;
import db.JDBCRecipes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




public class WelcomePage extends Form{
    public WelcomePage() {
        super("Welcome page");
        addGuiComponents();
    }
    private void addGuiComponents(){
        //create a recipe label
        JLabel searchLabel = new JLabel("Recipes");


        // configure component's x,y position and width/height val relative to the screen
        searchLabel.setBounds(0,0,520,100);

        //change the font color
        searchLabel.setForeground(CommonConstants.TEXT_COLOR);

        //change the font size
        searchLabel.setFont(new Font("Dialog", Font.BOLD, 40));

        //center text
        searchLabel.setHorizontalAlignment(SwingConstants.CENTER);

        //add comp. to GUI
        add(searchLabel);

        //create a search bar
        JTextField searchbar = new JTextField();
        searchbar.setBounds(30,100,400,50);
        searchbar.setBackground(CommonConstants.SECONDRY_COLOR);
        searchbar.setForeground(CommonConstants.TEXT_COLOR);
        searchbar.setFont(new Font("Dialog", Font.PLAIN, 24));
        add(searchbar);

        //searchbar button
        JButton searchbarButton = new JButton("Search");
        searchbarButton.setFont(new Font("Dialog", Font.BOLD, 18));

        //change the cursor when hoover over by the button
        searchbarButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchbarButton.setBackground(CommonConstants.TEXT_COLOR);
        searchbarButton.setBounds(125,520,250,50);
        searchbarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchVar = searchbar.getText();

                if(JDBCRecipes.search_for_recipe(searchVar)){
                    //data exists in database
                    JOptionPane.showMessageDialog(WelcomePage.this, "Recipe found");

//                    // end the Gui
//                        WelcomePage.this.dispose();

                } else {
                    JButton createRecipeButton = new JButton("Create a new recipe");
                    add(createRecipeButton);
                    //data doesn't exist in database
                    JOptionPane.showMessageDialog(WelcomePage.this, "Recipe was not found");
                    //creating a new recipe button


                    createRecipeButton.setFont(new Font("Dialog", Font.ITALIC, 18));
                    createRecipeButton.setBounds(200, 250, 250,50);
                    // creating a file in sql
                    JDBCRecipes.add_new_recipe(searchVar);


                }
            }
        });
        add(searchbarButton);
    }


}
