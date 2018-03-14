package com.example.davidjusten.empower_nutrition_admin;

/**
 * Created by davidjusten on 3/14/18.
 */

public class Order {

    private String mUserame;
    private String mItem;

    public Order(){}

    public Order(String username, String item){
        mUserame = username;
        mItem = item;
    }

    public String getUserame() {
        return mUserame;
    }

    public void setUserame(String mUserame) {
        this.mUserame = mUserame;
    }

    public String getItem() {
        return mItem;
    }

    public void setItem(String mItem) {
        this.mItem = mItem;
    }
}
