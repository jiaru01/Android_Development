package com.example.buypool;

import android.app.Application;

public class CurrentUserInfo extends Application {

    private int currentUserID;
    private String userName;
    private String phoneNumber;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public int getID() {
        return currentUserID;
    }

    public void setID(int currentUserID) {
        this.currentUserID = currentUserID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
