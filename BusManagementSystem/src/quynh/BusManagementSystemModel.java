package quynh;

import data.Admin;
import data.User;
import data.Bus;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class BusManagementSystemModel {

    private ArrayList<Admin> admins;
    private ArrayList<User> users;
    private ArrayList<Bus> buses;

    public ArrayList<Admin> getAdmins() {
        return admins;
    }

    public void setAdmins(ArrayList<Admin> admins) {
        this.admins = admins;
    }

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

    
    // Get Admin list from Database
    public ArrayList getAdminDatabase(Connection connection) {
        try {

            // Create statement
            Statement statement = connection.createStatement();

            // Retrieve the Admin table
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Admin");

            admins = new ArrayList<Admin>();

            while (resultSet.next()) {

                // Retrieve each admin data
                int ID = resultSet.getInt("ID");
                String pass = resultSet.getString("Password");
                String name = resultSet.getString("Name");
                Date dob = resultSet.getDate("DOB");
                String phoneNum = resultSet.getString("PhoneNum");
                String mail = resultSet.getString("Mail");

                // Store temporary admin data into tempAdmin
                Admin tempAdmin = new Admin(ID, pass, name, dob, phoneNum, mail);

                // Add tempAdmin to admins list
                admins.add(tempAdmin);
            }

        } catch (Exception e) {
            System.out.println("Cannot get admin data");
        }

        return admins;
    }

}
