package data;

import java.util.Date;
import java.util.Objects;

public class User {

    private String id;
    private String pass;
    private String name;
    private Date dob;
    private String phoneNum;
    private String mail;
    private String homeCampus;
    private String accType;

    public User() {
    }

    public User(String id, String pass, String accType, String name, Date dob, String phoneNum, String mail, String homeCampus) {
        setId(id);
        setPass(pass);
        setName(name);
        setDob(dob);
        setPhoneNum(phoneNum);
        setPhoneNum(phoneNum);
        setMail(mail);
        setHomeCampus(homeCampus);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
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

    public String getHomeCampus() {
        return homeCampus;
    }

    public void setHomeCampus(String homeCampus) {
        this.homeCampus = homeCampus;
    }

    public String getAccType() {
        return accType;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }

    //overide hashCode()
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.id);
        return hash;
    }
}
