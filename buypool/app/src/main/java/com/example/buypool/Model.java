package com.example.buypool;

public class Model {
    private String title , desription;
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
}
