package com.example.davidjusten.empower_nutrition_admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class OrderDetailActivity extends AppCompatActivity {

    private static final String LOG_TAG = OrderDetailActivity.class.getSimpleName();

    private String mItem_key, mItem_name, mItem_time, mUser_name, mImage_string;
    private TextView mCustomerTV, mItemTV, mOrderTime, mQuantity;
    private ImageView mImageView;
    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;
    private DatabaseReference mDbUserData, mDbRefItem, mDbRefOrder, mDbCanceled, mDbReady;
    private Food mCurrentItem;
    private Object mItemPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        // get FirebaseAuth objects
        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();
        mDbUserData = FirebaseDatabase.getInstance().getReference().child(mCurrentUser.getUid());
        mDbRefOrder = FirebaseDatabase.getInstance().getReference().child("Orders");

        // get current Item
        mCurrentItem = (Food) Order2Adapter.getClickedItem();
        Log.i(LOG_TAG,"currently clicked item: " + mCurrentItem.getItem());

        // get order key

        mItem_key = mCurrentItem.getKey();
        Log.i(LOG_TAG,"currently clicked key: " + mCurrentItem.getKey());
        // get ui views
        mCustomerTV = findViewById(R.id.orderDetailCustomer);
        mItemTV = findViewById(R.id.orderDetailItem);
        mImageView = findViewById(R.id.orderDetailImage);
        mOrderTime = findViewById(R.id.orderDetailTime);
        mQuantity = findViewById(R.id.orderDetailQuantity);

        // set views
        mCustomerTV.setText(mCurrentItem.getUser());
        mItemTV.setText(mCurrentItem.getItem());
        mOrderTime.setText(mCurrentItem.getTime());
        mQuantity.setText(mCurrentItem.getQuantity());
    }

    public void orderCompleteClicked(View view) {

        // remove value from order bucket
        Log.i(LOG_TAG, "remove item key / child key: " + mItem_key + " / " + mCurrentItem.getChildId());
        mDbRefOrder.child(mItem_key).child(mCurrentItem.getChildId()).removeValue();

        // ref to item ready bucket
        mDbReady = FirebaseDatabase.getInstance().getReference().child("sale_complete");

        // add
        final DatabaseReference dbRef = mDbReady.push();
        dbRef.setValue(mCurrentItem);
//        OpenOrders2Activity.updateAdapter();
        startActivity(new Intent(OrderDetailActivity.this, OpenOrders2Activity.class));
    }

    public void orderReadyClicked(View view) {
        Log.i(LOG_TAG,"current item: " + mCurrentItem.getItem());
        mDbRefOrder.child(mItem_key).child(mCurrentItem.getChildId()).child("isReady").setValue("true");//
        startActivity(new Intent(OrderDetailActivity.this, OpenOrders2Activity.class));


        // send notification
    }
}

