package constants;

import java.awt.*;

public class Common_Constants {
    //colour hex values
    public static final Color PRIMARY_COLOR = Color.decode("#e4d9c8");
    public static final Color SECONDRY_COLOR = Color.decode("#ffffff");
    public static final Color TEXT_COLOR = Color.decode("#d64620");

    //mysql credentials
    public static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/CookingApp";
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "Password";
    public static final String DB_USERS_TABLE_NAME = "benutzer";
    public static final String DB_RECIPE_TABLE_NAME = "recipes";
}
