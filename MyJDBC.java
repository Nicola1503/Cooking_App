package db;

import constants.CommonConstants;

import java.sql.*;

//JDBC - Java Database Connectivity
//this class will be our gateway in accessing our MySQL database
public class MyJDBC {
    // register new user to the database
    //true - register success
    //false - register failure

    public static boolean register(String username, String password){
        try {
            //1. check if the username already exists in the database
            if (!checkUser(username)) {
                // connect to the database
                Connection connection = DriverManager.getConnection(CommonConstants.DB_URL, CommonConstants.DB_USERNAME,
                        CommonConstants.DB_PASSWORD);

                // create insert query
                PreparedStatement insertUser = connection.prepareStatement(
                        "insert into " + CommonConstants.DB_USERS_TABLE_NAME + "(username, password)" +
                                "values(?, ?)"
                );
                // insert parameters in the insert query
                insertUser.setString(1, username);
                insertUser.setString(2, password);

                // update db with new user
                insertUser.executeUpdate();
                return true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    //check if username already exists in the database
    //false = user doesn't exist
    //true - user exists in the database

    public static boolean checkUser(String username){
        try {
            Connection connection = DriverManager.getConnection(CommonConstants.DB_URL, CommonConstants.DB_USERNAME,
                    CommonConstants.DB_PASSWORD);

            PreparedStatement checkUserExists = connection.prepareStatement(
                    " SELECT * FROM " + CommonConstants.DB_USERS_TABLE_NAME +
                            " WHERE USERNAME = ? "
            );
            checkUserExists.setString(1,username);

            ResultSet resultSet = checkUserExists.executeQuery();

            //check to see if the result is empty
            //if it is empty it means that there was na data
            // row that contains the username (i.e user does not exist)

            if(!resultSet.isBeforeFirst()){
                return false;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    //validate login credentials by checking to see if username/password pair exists in the database
    public static boolean validateLogin(String username, String password){
        try {
            // connect to the database
            Connection connection = DriverManager.getConnection(CommonConstants.DB_URL, CommonConstants.DB_USERNAME,
                    CommonConstants.DB_PASSWORD);

            //create select query
            PreparedStatement validateUser = connection.prepareStatement(
                    "select * from " + CommonConstants.DB_USERS_TABLE_NAME +
                            " where username = ? and password = ?"
            );
            validateUser.setString(1, username);
            validateUser.setString(2,password);

            ResultSet resultSet = validateUser.executeQuery();

            if(!resultSet.isBeforeFirst()){
                return false;
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
}
