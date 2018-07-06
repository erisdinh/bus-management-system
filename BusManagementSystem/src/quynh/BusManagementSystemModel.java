package quynh;

import data.User;
import data.User;
import data.Bus;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class BusManagementSystemModel {
    
    private ArrayList<User> users;
    private ArrayList<Bus> buses;

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Bus> getBuses() {
        return buses;
    }

    public void setBuses(ArrayList<Bus> buses) {
        this.buses = buses;
    }

    public BusManagementSystemModel() {
    }

    // Get User list from Database
    public void getUserDatabase(Connection connection) {
        try {

            System.out.println("Run User Database");

            // Create statement
            Statement statement = connection.createStatement();

            // Retrieve the User table
            ResultSet resultSet = statement.executeQuery("SELECT * FROM UserName");

            users = new ArrayList<User>();

            while (resultSet.next()) {

                // Retrieve each admin data
                String ID = resultSet.getString("ID").trim();
                System.out.println(ID);
                String pass = resultSet.getString("Password").trim();
                String accType = resultSet.getString("AccType");
                String name = resultSet.getString("Name").trim();
                Date dob = resultSet.getDate("DOB");
                String phoneNum = resultSet.getString("PhoneNum").trim();
                String mail = resultSet.getString("Mail").trim();
                String homeCampus = resultSet.getString("HomeCampus").trim();
                

                // Store temporary user data into tempUser
                User tempUser = new User(ID, pass, accType, name, dob, phoneNum, mail, homeCampus);

                // Add tempAdmin to admins list
                users.add(tempUser);
            }

            System.out.println("End User Database");

        } catch (SQLException e) {
            System.out.println("Cannot get User data");
        }
    }

    // Get User By ID
    public User getUserByID(String ID) {
        User user = new User();
        User tempUser = new User();

        // Check whether there is an admin having the same ID or not
        for (int i = 0; i < this.users.size(); i++) {

            // Get ith User and its ID
            tempUser = users.get(i);
            String tempID = tempUser.getId();

            // If there is an admin having the same ID, return this admin
            if (ID.equals(tempID)) {
                return user = tempUser;
            }
        }

        return user;
    }

    // Check the admin with password (Sign in)
    public boolean isUser(String ID, String password) {

        boolean isUser = false;

        // Get the admin that have the same ID then store its password in tempPass
        User tempUser = new User();
        tempUser = getUserByID(ID);
        String tempPass = tempUser.getPass();

        // If the password is the same, return true
        if (password.equals(tempPass)) {
            isUser = true;
        }
        return isUser;
    }
}
