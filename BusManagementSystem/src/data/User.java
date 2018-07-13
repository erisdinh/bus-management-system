package data;

import java.sql.Date;
import java.util.Objects;

public class User {

    private int id;
    private String pass;
    private String accType;
    private String name;
    private Date dob;
    private String major;
    private String homeCampus;
    private String phoneNum;
    private String mail;

    public User() {}
    
    public User(int id, String pass, String accType, String name, Date dob, String major, String homeCampus, String phoneNum, String mail) {
        setId(id);
        setPass(pass);
        setAccType(accType);
        setName(name);
        setDob(dob);
        setMajor(major);
        setHomeCampus(homeCampus);
        setPhoneNum(phoneNum);
        setMail(mail);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getAccType() {
        return accType;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getHomeCampus() {
        return homeCampus;
    }

    public void setHomeCampus(String homeCampus) {
        this.homeCampus = homeCampus;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }



    //overide hashCode()
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.id);
        return hash;
    }
}
