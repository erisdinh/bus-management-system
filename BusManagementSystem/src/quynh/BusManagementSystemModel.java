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
    public void getAdminDatabase(Connection connection) {
        try {

            System.out.println("Run Admin Database");

            // Create statement
            Statement statement = connection.createStatement();

            // Retrieve the Admin table
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Admin");

            admins = new ArrayList<Admin>();

            while (resultSet.next()) {

                // Retrieve each admin data
                String ID = resultSet.getString("ID").trim();
                System.out.println(ID);
                String pass = resultSet.getString("Password").trim();
                String name = resultSet.getString("Name").trim();
                Date dob = resultSet.getDate("DOB");
                String phoneNum = resultSet.getString("PhoneNum").trim();
                String mail = resultSet.getString("Mail").trim();

                // Store temporary admin data into tempAdmin
                Admin tempAdmin = new Admin(ID, pass, name, dob, phoneNum, mail);

                // Add tempAdmin to admins list
                admins.add(tempAdmin);
            }

            System.out.println("End Admin Database");

        } catch (Exception e) {
            System.out.println("Cannot get admin data");
        }
    }

    
    // Get Admin By ID
    public Admin getAdminByID(String ID) {
        Admin admin = new Admin();
        Admin tempAdmin = new Admin();

        // Check whether there is an admin having the same ID or not
        for (int i = 0; i < this.admins.size(); i++) {
            
            // Get ith Admin and its ID
            tempAdmin = admins.get(i);
            String tempID = tempAdmin.getAdminID();
            
            // If there is an admin having the same ID, return this admin
            if (ID.equals(tempID)) {
                return admin = tempAdmin;
            }
        }

        return admin;
    }

    // Check the admin with password (Sign in)
    public boolean isAdmin(String ID, String password) {
        
        boolean isAdmin = false;
        
        // Get the admin that have the same ID then store its password in tempPass
        Admin tempAdmin = new Admin();
        tempAdmin = getAdminByID(ID);
        String tempPass = tempAdmin.getAdminPass();
        
        // If the password is the same, return true
        if (password.equals(tempPass)) {
            isAdmin = true;
        }
        return isAdmin;
    }

}
