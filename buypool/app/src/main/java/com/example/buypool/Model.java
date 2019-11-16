package com.example.buypool;

public class Model {
    private String title;
    private String desription;
    private String date;
    private String address;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    private String phoneNumber;




    private String UserNameOnCard;

    private int img;

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

    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getUserNameOnCard() { return UserNameOnCard;    }
    public void setUserNameOnCard(String userNameOnCard) { UserNameOnCard = userNameOnCard;    }
}
