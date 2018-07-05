package data;

import java.util.Date;
import java.util.Objects;

public class Admin {

    private String adminID;
    private String adminPass;
    private String adminName;
    private Date adminDOB;
    private String adminPhoneNum;
    private String adminMail;

    public Admin() {}
    
    public Admin(String adminID, String adminPass, String adminName, Date adminDOB, String adminPhoneNum, String adminMail) {
        setAdminID(adminID);
        setAdminPass(adminPass);
        setAdminName(adminName);
        setAdminDOB(adminDOB);
        setAdminPhoneNum(adminPhoneNum);
        setAdminMail(adminMail);
    }
    
    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    public String getAdminPass() {
        return adminPass;
    }

    public void setAdminPass(String adminPass) {
        this.adminPass = adminPass;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }
    
        public Date getAdminDOB() {
        return adminDOB;
    }

    public void setAdminDOB(Date adminDOB) {
        this.adminDOB = adminDOB;
    }

    public String getAdminPhoneNum() {
        return adminPhoneNum;
    }

    public void setAdminPhoneNum(String adminPhoneNum) {
        this.adminPhoneNum = adminPhoneNum;
    }

    public String getAdminMail() {
        return adminMail;
    }

    public void setAdminMail(String adminMail) {
        this.adminMail = adminMail;
    }

    //overide hashCode()
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.adminID);
        return hash;
    }
}
