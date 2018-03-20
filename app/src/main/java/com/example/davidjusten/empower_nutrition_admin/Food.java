package com.example.davidjusten.empower_nutrition_admin;

/**
 * Created by davidjusten on 3/19/18.
 */


public class Food {

    private String mUser, mItem, mQuantity, mTime, mOrderId;

    public Food() {
    }

    public Food(String user, String item, String quantity, String time, String orderId) {
        mUser = user;
        mItem = item;
        mTime = time;
        mQuantity = quantity;
        mOrderId = orderId;

    }

    public String getUser() {
        return mUser;
    }

    public void setUser(String mUser) {
        this.mUser = mUser;
    }

    public String getItem() {
        return mItem;
    }

    public void setItem(String mItem) {
        this.mItem = mItem;
    }

    public String getQuantity() {
        return mQuantity;
    }

    public void setQuantity(String mQuantity) {
        this.mQuantity = mQuantity;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String mTime) {
        this.mTime = mTime;
    }

    public String getOrderId() {
        return mOrderId;
    }

    public void setOrderId(String mOrderId) {
        this.mOrderId = mOrderId;
    }
}
