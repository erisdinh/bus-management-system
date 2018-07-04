package data;

import java.util.Date;
import java.util.Objects;

public class User {

    private int userID;
    private String userPass;
    private String userName;
    private Date userDOB;
    private int userPhoneNum;
    private String userMail;
    private String userHomeCampus;
    
    public User() {}
    
    public User(int userID, String userPass, String userName, Date userDate, int userPhoneNum, String userMail, String userHomeCampus) {
        setUserID(userID);
        setUserPass(userPass);
        setUserName(userName);
        setUserDOB(userDOB);
        setUserPhoneNum(userPhoneNum);
        setUserMail(userMail);
        setUserHomeCampus(userHomeCampus);
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getUserDOB() {
        return userDOB;
    }

    public void setUserDOB(Date userDOB) {
        this.userDOB = userDOB;
    }

    public int getUserPhoneNum() {
        return userPhoneNum;
    }

    public void setUserPhoneNum(int userPhoneNum) {
        this.userPhoneNum = userPhoneNum;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserHomeCampus() {
        return userHomeCampus;
    }

    public void setUserHomeCampus(String userHomeCampus) {
        this.userHomeCampus = userHomeCampus;
    }

    //overide hashCode()
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.userID);
        return hash;
    }
}
