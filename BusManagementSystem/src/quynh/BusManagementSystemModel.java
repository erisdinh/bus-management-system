package quynh;

import data.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import javafx.collections.ObservableList;

public class BusManagementSystemModel {

    private User currentUser = new User();
    private ArrayList<User> users;
    private ArrayList<Bus> buses;
    private ArrayList<BusReservation> busReservations = new ArrayList<BusReservation>();
    private BusReservation newBusReservation = new BusReservation();
    private ArrayList<BusReservation> userBusReservations;

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

    public ArrayList<BusReservation> getBusReservations() {
        return busReservations;
    }

    public void setBusReservations(ArrayList<BusReservation> busReservations) {
        this.busReservations = busReservations;
    }

    public BusReservation getNewBusReservation() {
        return newBusReservation;
    }

    public void setNewBusReservation(BusReservation newBusReservation) {
        this.newBusReservation = newBusReservation;
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
                int ID = resultSet.getInt("ID");
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

    // Get Bus list from database
    public ArrayList<Bus> getBusDatabase(Connection connection) {
        try {

            System.out.println("Run Bus Database");

            // Create a statement
            Statement statement = connection.createStatement();

            // Retrieve Bus table
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Bus");

            buses = new ArrayList<Bus>();

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

    public ArrayList<BusReservation> getBusReservationDatabase(Connection connection) {

        try {

            System.out.println("Run Bus Reservation Database");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM BusReservation");

            busReservations = new ArrayList<BusReservation>();

            while (resultSet.next()) {

                int resNum = resultSet.getInt("ResNum");
                int userID = resultSet.getInt("UserID");
                String departure = resultSet.getString("Departure").trim();
                String destination = resultSet.getString("Destination").trim();
                int busNum = resultSet.getInt("BusNum");
                String seat = resultSet.getString("Seat").trim();
                Date busResDate = resultSet.getDate("BusResDate");
                Time busResTime = resultSet.getTime("BusResTime");
                Date userResDate = resultSet.getDate("UserResDate");
                Time userResTime = resultSet.getTime("userResTime");
                String status = resultSet.getString("Status").trim();

                BusReservation tempBusReservation = new BusReservation(resNum, userID, departure, destination, busNum, seat, busResDate, busResTime, userResDate, userResTime, status);

                busReservations.add(tempBusReservation);
            }

            System.out.println("End Bus Reservation Database");
        } catch (SQLException e) {
            System.out.println("Cannot connect to Bus Reservation Database");
        }

        return this.busReservations;
    }

    // Get User By ID
    public User getUserByID(int ID) {
        User user = new User();
        User tempUser = new User();

        // Check whether there is an admin having the same ID or not
        for (int i = 0; i < this.users.size(); i++) {

            // Get ith User and its ID
            tempUser = users.get(i);
            int tempID = tempUser.getId();

            // If there is an admin having the same ID, return this admin
            if (ID == tempID) {
                return user = tempUser;
            }
        }

        return user;
    }

    // Check the admin with password (Sign in)
    public boolean isUser(int ID, String password) {

        boolean isUser = false;

        // Get the user that have the same ID then store its password in tempPass
        User tempUser = new User();
        tempUser = getUserByID(ID);
        String tempID = Integer.toString(tempUser.getId());
        if (tempID != null) {

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

    // Get the last row of reservations
    public BusReservation getLastBusReservation() {
        BusReservation lastBusReservation = new BusReservation();
        lastBusReservation = busReservations.get(busReservations.size() - 1);
        System.out.println(lastBusReservation.getResNum());
        return lastBusReservation;
    }

    public int getNewResNum() {
        BusReservation lastBusReservation = new BusReservation();
        lastBusReservation = getLastBusReservation();

        int newResNum = lastBusReservation.getResNum() + 1;
        return newResNum;
    }

    // Put the reservation to database
    public boolean putReservationToDatabase(Connection connection) {
        boolean reserveOK = false;

        try {

            // Prepare a statement to execute
            PreparedStatement statement = connection.prepareStatement("INSERT INTO BusReservation (UserID, Departure, Destination, BusNum, Seat, BusResDate, BusResTime, UserResDate, UserResTime, Status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            // Pass all the reservation information into each column in the database to the statement
            statement.setInt(1, currentUser.getId());
            statement.setString(2, newBusReservation.getDeparture());
            statement.setString(3, newBusReservation.getDestination());
            statement.setInt(4, newBusReservation.getBusNum());
            statement.setString(5, newBusReservation.getSeat());
            statement.setDate(6, newBusReservation.getBusResDate());
            statement.setTime(7, newBusReservation.getBusResTime());
            statement.setDate(8, newBusReservation.getUserResDate());
            statement.setTime(9, newBusReservation.getUserResTime());
            statement.setString(10, newBusReservation.getStatus());

            statement.executeUpdate();

            reserveOK = true;
        } catch (SQLException e) {
            System.out.println("Cannot put the reservation to Database");
        }
        return reserveOK;
    }

    // Get currrentUserID Bus Reservation List
    public ArrayList<BusReservation> getUserReservations(int userID) {
        int tempUserID;

        userBusReservations = new ArrayList<>();

        for (int i = 0; i < busReservations.size(); i++) {
            tempUserID = busReservations.get(i).getUserID();
            if (userID == tempUserID) {
                userBusReservations.add(busReservations.get(i));
            }
        }

        return userBusReservations;
    }

    // Update data from database
    public void updateAllFromDatabase(Connection connection) {
        getUserDatabase(connection);
        getBusDatabase(connection);
        getBusReservationDatabase(connection);
        System.out.println("Updated");
    }

    public ArrayList<BusReservation> searchByResNum(ArrayList<BusReservation> list, int resNum) {

        String strResNum = Integer.toString(resNum);
        System.out.println(resNum);

        ArrayList<BusReservation> reservationByResNum = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {

            BusReservation tempBusReservation = list.get(i);
            String tempResNum = Integer.toString(tempBusReservation.getResNum());
            System.out.println(tempResNum);

            if (tempResNum.contains(strResNum)) {
                System.out.println("Contains");
                reservationByResNum.add(tempBusReservation);
            }
        }

        return reservationByResNum;
    }

    // Search by departure
    public ArrayList<BusReservation> searchByDeparture(ArrayList<BusReservation> list, String departure) {
        ArrayList<BusReservation> reservationByDeparture = new ArrayList<>();        
        
        for (int i = 0; i < list.size(); i++) {

            BusReservation tempBusReservation = list.get(i);
            String tempDeparture = tempBusReservation.getDeparture();

            if (tempDeparture.contains(departure)) {
                System.out.println("Contains");
                reservationByDeparture.add(tempBusReservation);
            }
        }

        return reservationByDeparture;
    }
    
    // Search by destination
    public ArrayList<BusReservation> searchByDestination(ArrayList<BusReservation> list, String destination) {
        ArrayList<BusReservation> reservationByDestination = new ArrayList<>();
        
        for (int i = 0; i < list.size(); i++) {

            BusReservation tempBusReservation = list.get(i);
            String tempDestination = tempBusReservation.getDeparture();

            if (tempDestination.contains(destination)) {
                System.out.println("Contains");
                reservationByDestination.add(tempBusReservation);
            }
        }
        
        return reservationByDestination;
    }
    
    // Search by Date
    public ArrayList<BusReservation> searchByDate (ArrayList<BusReservation> list, Date date) {
        
        ArrayList<BusReservation> reservationByDate = new ArrayList<>();
        
        for (int i = 0; i < list.size(); i++) {

            BusReservation tempBusReservation = list.get(i);
            Date tempDate = tempBusReservation.getBusResDate();

            if (tempDate.equals(date)) {
                System.out.println("Contains");
                reservationByDate.add(tempBusReservation);
            }
        }
        
        return reservationByDate;
    }
    
    public ArrayList<BusReservation> searchByStatus (ArrayList<BusReservation> list, String status) {
        
        ArrayList<BusReservation> reservationByStatus = new ArrayList<>();
        
        for (int i = 0; i < list.size(); i++) {

            BusReservation tempBusReservation = list.get(i);
            String tempStatus = tempBusReservation.getStatus();

            if (tempStatus.contains(status)) {
                System.out.println("Contains");
                reservationByStatus.add(tempBusReservation);
            }
        }
        
        return reservationByStatus;
        
    }
}
