package com.example.buypool;

public class Model {
    //This Activity used to create getter and setter methods ,
    // uses to set and get information with cardsviews
    private String title;
    private String desription;
    private String date;
    private String address;
    private int cardID;

    private String phoneNumber;

    private int cardStatus;
    private String UserNameOnCard;

    private int img;


    public int getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(int cardStatus) {
        this.cardStatus = cardStatus;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesription(String desription) {
        this.desription = desription;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getDesription() {
        return desription;
    }

    public int getImg() {
        return img;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserNameOnCard() {
        return UserNameOnCard;
    }

    public void setUserNameOnCard(String userNameOnCard) {
        UserNameOnCard = userNameOnCard;
    }

    public int getCardID() {
        return cardID;
    }

    public void setCardID(int cardID) {
        this.cardID = cardID;
    }
}
