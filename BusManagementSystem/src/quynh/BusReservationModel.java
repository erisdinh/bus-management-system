package quynh;

import data.Admin;
import data.Bus;
import data.User;
import java.util.Date;

public class BusReservationModel {
    
    private int resNum;
    private User user;
    private Admin admin;
    private Bus bus;
    
    
    // Date and Time of the reservated bus
    private Date busResDate;
    private Date busResTime;
    
    // Date and Time that the user reservate the bus
    private Date userResDate;
    private Date userResTime;
}
