package com.example.davidjusten.empower_nutrition_admin;

/**
 * Created by davidjusten on 3/19/18.
 */


public class Food {

    private String mName, mPrice, mDesc, mImage;
    private String mType;
    private long mQuantity;

    public Food() {
    }

    public Food(String name, String price, String desc, long quantity, String image, String type) {
        mName = name;
        mPrice = price;
        mDesc = desc;
        mImage = image;
        mType = type;
        mQuantity = quantity;

    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String mPrice) {
        this.mPrice = mPrice;
    }

    public String getDesc() {
        return mDesc;
    }

    public void setDesc(String mDesc) {
        this.mDesc = mDesc;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String mImage) {
        this.mImage = mImage;
    }

    public String getType() {
        return mType;
    }

    public void setType(String mType) {
        this.mType = mType;
    }

    public long getQuantity() {
        return mQuantity;
    }

    public void setQuantity(int mQuantity) {
        this.mQuantity = mQuantity;
    }
}
