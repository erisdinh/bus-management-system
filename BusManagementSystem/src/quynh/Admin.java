package quynh;

import java.util.Date;
import java.util.Objects;

public class Admin {

    private int adminID;
    private String adminPass;
    private String adminName;
    private int adminPhoneNum;
    private String adminMail;

    public Admin() {}
    
    public Admin(int adminID, String adminPass, String adminName, int adminPhoneNum, String adminMail) {
        setAdminID(adminID);
        setAdminPass(adminPass);
        setAdminName(adminName);
        setAdminPhoneNum(adminPhoneNum);
        setAdminMail(adminMail);
    }
    
    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
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

    public int getAdminPhoneNum() {
        return adminPhoneNum;
    }

    public void setAdminPhoneNum(int adminPhoneNum) {
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
