package com.example.finalprojectk.object;

public class Users {
    private Integer userID;
    private String userEmail;
    private String userUsername;
    private String userPhoneNumber;
    private String userPassword;

    public Users(Integer userID, String userEmail, String userUsername, String userPhoneNumber, String userPassword) {
        this.userID = userID;
        this.userEmail = userEmail;
        this.userUsername = userUsername;
        this.userPhoneNumber = userPhoneNumber;
        this.userPassword = userPassword;
    }

    public String getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
