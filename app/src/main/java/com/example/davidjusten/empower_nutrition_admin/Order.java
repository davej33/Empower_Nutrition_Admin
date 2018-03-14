package com.example.davidjusten.empower_nutrition_admin;

/**
 * Created by davidjusten on 3/14/18.
 */

public class Order {

    private String mUsername;
    private String mItem;

    public Order(){}

    public Order(String userName, String itemName){
        mUsername = userName;
        mItem = itemName;
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
}
