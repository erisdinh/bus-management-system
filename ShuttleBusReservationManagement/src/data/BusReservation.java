package data;

import data.User;
import data.Bus;
import data.User;
import java.sql.Time;
import java.sql.Date;

public class BusReservation {

    private int resNum;
    private int userID;

    private String departure;
    private String destination;

    private int busNum;
    private String seat;

    // Date and Time of the reservated bus
    private Date busResDate;
    private Time busResTime;

    // Date and Time that the user reservate the bus
    private Date userResDate;
    private Time userResTime;
    
    // Status of the reservation
    private String status;

    public BusReservation() {
    }

    public BusReservation(int resNum, int userID, String departure, String destination, int busNum, String seat, Date busResDate, Time busResTime, Date userResDate, Time userResTime, String status) {
        setResNum(resNum);
        setUserID(userID);
        setDeparture(departure);
        setDestination(destination);
        setBusNum(busNum);
        setSeat(seat);
        setBusResDate(busResDate);
        setBusResTime(busResTime);
        setUserResDate(userResDate);
        setUserResTime(userResTime);
        setStatus(status);
    }

    public int getResNum() {
        return resNum;
    }

    public void setResNum(int resNum) {
        this.resNum = resNum;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getBusNum() {
        return busNum;
    }

    public void setBusNum(int busNum) {
        this.busNum = busNum;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public Date getBusResDate() {
        return busResDate;
    }

    public void setBusResDate(Date busResDate) {
        this.busResDate = busResDate;
    }

    public Time getBusResTime() {
        return busResTime;
    }

    public void setBusResTime(Time busResTime) {
        this.busResTime = busResTime;
    }

    public Date getUserResDate() {
        return userResDate;
    }

    public void setUserResDate(Date userResDate) {
        this.userResDate = userResDate;
    }

    public Time getUserResTime() {
        return userResTime;
    }

    public void setUserResTime(Time userResTime) {
        this.userResTime = userResTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
