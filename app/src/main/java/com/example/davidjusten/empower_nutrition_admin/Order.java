package com.example.davidjusten.empower_nutrition_admin;

/**
 * Created by davidjusten on 3/14/18.
 */

public class Order {

    private String mUsername;
    private String mItem;
    private String mTime;

    public Order() {
    }

    public Order(String userName, String itemName, String time) {
        mUsername = userName;
        mItem = itemName;
        mTime = time;
    }

    public String getUserName() {
        return mUsername;
    }

    public void setUserName(String mUsername) {
        this.mUsername = mUsername;
    }

    public String getItemName() {
        return mItem;
    }

    public void setItemName(String mItem) {
        this.mItem = mItem;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String mTime) {
        this.mTime = mTime;
    }
}
