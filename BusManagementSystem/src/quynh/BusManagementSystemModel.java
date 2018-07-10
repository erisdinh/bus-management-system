package quynh;

import data.User;
import data.Bus;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class BusManagementSystemModel {

    private User currentUser = new User();
    private ArrayList<User> users;
    private ArrayList<Bus> buses = new ArrayList<Bus>();
    private ArrayList<Integer> busesNum = new ArrayList<Integer>();

    // Bus 1
    private ArrayList<String> northTraToHmcNo1 = new ArrayList<String>();
    private ArrayList<String> northHmcToDavisNo1 = new ArrayList<String>();
    private ArrayList<String> southDavisToHmcNo1 = new ArrayList<String>();
    private ArrayList<String> southHmcToTraNo1 = new ArrayList<String>();

    // Bus 2
    private ArrayList<String> southDavisToHmcNo2 = new ArrayList<String>();
    private ArrayList<String> southHmcToTraNo2 = new ArrayList<String>();
    private ArrayList<String> northTraToHmcNo2 = new ArrayList<String>();
    private ArrayList<String> northHmcToDavisNo2 = new ArrayList<String>();

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
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

    public ArrayList<Integer> getBusesNum() {

        for (int i = 0; i < this.buses.size(); i++) {
            int busNum = buses.get(i).getBusNum();
            busesNum.add(busNum);
        }

        return busesNum;
    }

    public void setBusesNum(ArrayList<Integer> busesNum) {
        this.busesNum = busesNum;
    }

    public ArrayList<String> getNorthTraToHmcNo1() {
        return northTraToHmcNo1;
    }

    public void setNorthTraToHmcNo1(ArrayList<String> northTraToHmcNo1) {
        this.northTraToHmcNo1 = northTraToHmcNo1;
    }

    public ArrayList<String> getNorthHmcToDavisNo1() {
        return northHmcToDavisNo1;
    }

    public void setNorthHmcToDavisNo1(ArrayList<String> northHmcToDavisNo1) {
        this.northHmcToDavisNo1 = northHmcToDavisNo1;
    }

    public ArrayList<String> getSouthDavisToHmcNo1() {
        return southDavisToHmcNo1;
    }

    public void setSouthDavisToHmcNo1(ArrayList<String> southDavisToHmcNo1) {
        this.southDavisToHmcNo1 = southDavisToHmcNo1;
    }

    public ArrayList<String> getSouthHmcToTraNo1() {
        return southHmcToTraNo1;
    }

    public void setSouthHmcToTraNo1(ArrayList<String> southHmcToTraNo1) {
        this.southHmcToTraNo1 = southHmcToTraNo1;
    }

    public ArrayList<String> getSouthDavisToHmcNo2() {
        return southDavisToHmcNo2;
    }

    public void setSouthDavisToHmcNo2(ArrayList<String> southDavisToHmcNo2) {
        this.southDavisToHmcNo2 = southDavisToHmcNo2;
    }

    public ArrayList<String> getSouthHmcToTraNo2() {
        return southHmcToTraNo2;
    }

    public void setSouthHmcToTraNo2(ArrayList<String> southHmcToTraNo2) {
        this.southHmcToTraNo2 = southHmcToTraNo2;
    }

    public ArrayList<String> getNorthTraToHmcNo2() {
        return northTraToHmcNo2;
    }

    public void setNorthTraToHmcNo2(ArrayList<String> northTraToHmcNo2) {
        this.northTraToHmcNo2 = northTraToHmcNo2;
    }

    public ArrayList<String> getNorthHmcToDavisNo2() {
        return northHmcToDavisNo2;
    }

    public void setNorthHmcToDavisNo2(ArrayList<String> northHmcToDavisNo2) {
        this.northHmcToDavisNo2 = northHmcToDavisNo2;
    }

    public BusManagementSystemModel() {
    }

    // Get User list from Database
    public ArrayList<User> getUserDatabase(Connection connection) {
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

        return this.users;
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

        // Get the user that have the same ID then store its password in tempPass
        User tempUser = new User();
        tempUser = getUserByID(ID);

        if (tempUser.getId() != null) {

            String tempPass = tempUser.getPass();

            // If the password is the same, return true
            if (password.equals(tempPass)) {
                isUser = true;
            }

        } else {

            isUser = false;
        }

        return isUser;
    }

    // Get Bus list from database
    public ArrayList<Bus> getBusDatabase(Connection connection) {

        try {

            System.out.println("Run Bus Database");

            // Create a statement
            Statement statement = connection.createStatement();

            // Retrieve Bus table
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Bus");

            // Retrieve bus data from database
            while (resultSet.next()) {

                // Retrieve each bus data
                int busNum = resultSet.getInt("BusNum");
                String licensePlate = resultSet.getString("LicensePlate").trim();
                String type = resultSet.getString("Type").trim();
                int numOfSeat = resultSet.getInt("NumOfSeat");

                Bus tempBus = new Bus(busNum, licensePlate, type, numOfSeat);

                this.buses.add(tempBus);
            }

            System.out.println("End Bus Database");

        } catch (SQLException e) {
            System.out.println("Cannot access to Bus database");
        }

        return this.buses;
    }

    // Get bus data by Bus number
    public Bus getBusByBusNum(int busNum) {

        Bus bus = new Bus();
        Bus tempBus = new Bus();

        for (int i = 0; i < buses.size(); i++) {

            tempBus = buses.get(i);

            if (busNum == (tempBus.getBusNum())) {
                bus = tempBus;
            }

        }

        return bus;
    }

    // populate the category in comboBox
//    public void populateBusesNum() {
//        
//        // Using HashMap to get unique busNum
//        HashMap<Integer, Integer> list = new HashMap<>();
//        int key = 0;
//        for (int i = 0; i < this.buses.size(); i++) {
//            int busNum = this.buses.get(i).getBusNumber();
//            if (!list.containsKey(busNum)) {
//                list.put(busNum, key);
//                key++;
//            } else {
//            }
//        }
//        for (int busNum : list.keySet()) {
//            busesNum.add(busNum);
//        }
//    }
    // Initialize Bus Hours List
    public void initializeBusHours() {

        int i = 6;
        while (i < 21) {

            northTraToHmcNo1.add(i + ":30");
            southDavisToHmcNo2.add(i + ":30");
            i++;

            northHmcToDavisNo1.add(i + ":00");
            southHmcToTraNo2.add(i + ":00");

            if (i < 20) {
                southDavisToHmcNo1.add(i + ":30");
                northTraToHmcNo2.add(i + ":30");

                i++;
                southHmcToTraNo1.add(i + ":00");
                northHmcToDavisNo2.add(i + ":00");
            }
        }
    }

    // Put the reservation to database
    public void reserveToDatabase() {
        boolean reserveOK;

    }

}
