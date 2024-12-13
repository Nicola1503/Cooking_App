package db;

import constants.CommonConstants;

import java.sql.*;

public class JDBCRecipes {
    //i will check the result from searchbar
    //we will look through database for recipes
    public static boolean search_for_recipe(String recipe) {
        try {
            // connect to the database
            Connection connection = DriverManager.getConnection(CommonConstants.DB_URL, CommonConstants.DB_USERNAME,
                    CommonConstants.DB_PASSWORD);
            //create select query
            PreparedStatement findRecipe = connection.prepareStatement(
                    "select * from " + CommonConstants.DB_RECIPE_TABLE_NAME +
                            " where recipe = ?"
            );
            findRecipe.setString(1, recipe);

            ResultSet resultSet = findRecipe.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                return false;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static boolean add_new_recipe(String recipe) {
        try {
            // connect to the database
            Connection connection = DriverManager.getConnection(CommonConstants.DB_URL, CommonConstants.DB_USERNAME,
                    CommonConstants.DB_PASSWORD);

            // create insert query
            PreparedStatement insertRecipe = connection.prepareStatement(
                    "insert into " + CommonConstants.DB_RECIPE_TABLE_NAME + "(recipe)" +
                            "values(?)"
            );
            // insert parameters in the insert query
            insertRecipe.setString(1, recipe);

            // update db with new user
            insertRecipe.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }
}
