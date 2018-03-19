package com.example.davidjusten.empower_nutrition_admin;

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
    private TextView mCustomerTV, mItemTV, mOrderTime;
    private ImageView mImageView;
    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;
    private DatabaseReference mDbUserData, mDbRefItem, mDbRefOrder, mDbCanceled, mDbReady;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        // get ui views
        mCustomerTV = findViewById(R.id.orderDetailCustomer);
        mItemTV = findViewById(R.id.orderDetailItem);
        mImageView = findViewById(R.id.orderDetailImage);
        mOrderTime = findViewById(R.id.orderDetailTime);

        // get Firebase objects
        mAuth = FirebaseAuth.getInstance();
        mDbRefItem = FirebaseDatabase.getInstance().getReference().child("Item");
        mDbRefOrder = FirebaseDatabase.getInstance().getReference().child("Orders");

        // get item data from intent
        mItem_key = getIntent().getStringExtra("item_key");
        mItem_name = getIntent().getStringExtra("item_name");
        mItem_time = getIntent().getStringExtra("item_time");
        mUser_name = getIntent().getStringExtra("user_name");

        // get order key

        // get current user
        mCurrentUser = mAuth.getCurrentUser();
        mDbUserData = FirebaseDatabase.getInstance().getReference().child(mCurrentUser.getUid());

        // extract data
        mDbRefItem.child(mItem_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                mCustomerTV.setText(mUser_name);
                mItemTV.setText(mItem_name);
                mOrderTime.setText(mItem_time);
                Picasso.get().load(mImage_string).into(mImageView); // Todo: add defaults

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void orderReadyClicked(View view) {

        // remove value from order bucket
        mDbRefOrder.child(mItem_key).removeValue();

        // ref to item ready bucket
        mDbReady = FirebaseDatabase.getInstance().getReference().child("item_ready");

        // add
        final DatabaseReference dbRef = mDbReady.push();
        mDbUserData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dbRef.child("order_key").setValue(mItem_key);
                dbRef.child("user_name").setValue(mUser_name);
                dbRef.child("item_name").setValue(mItem_name);
                dbRef.child("item_time").setValue(mItem_time);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void orderCancelClicked(View view) {

        mDbRefOrder.child(mItem_key).removeValue();

        mDbCanceled = FirebaseDatabase.getInstance().getReference().child("item_canceled");

        final DatabaseReference dbRefCancel = mDbCanceled.push();
        mDbUserData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dbRefCancel.child("order_key").setValue(mItem_key);
                dbRefCancel.child("user_name").setValue(mUser_name);
                dbRefCancel.child("item_name").setValue(mItem_name);
                dbRefCancel.child("item_time").setValue(mItem_time);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
