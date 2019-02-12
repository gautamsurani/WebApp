package tech.fraction.webapp.model;

public class CustomerListModel {


    String name;
    String emailId;
    String phoneNo;
    String role;

    public CustomerListModel(String name, String emailId, String phoneNo, String role) {
        this.name = name;
        this.emailId = emailId;
        this.phoneNo = phoneNo;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
