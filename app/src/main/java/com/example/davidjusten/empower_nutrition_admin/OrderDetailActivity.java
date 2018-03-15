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

    private String mItem_key, mItem_name, mItem_time, mImage_string;
    private TextView mCustomerTV, mItemTV, mOrderTime;
    private ImageView mImageView;
    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;
    private DatabaseReference user_data, mDbRef;

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
        mDbRef = FirebaseDatabase.getInstance().getReference().child("Item");

        // get item key from intent
        mItem_key = getIntent().getStringExtra("item_key");
        mItem_name = getIntent().getStringExtra("item_name");
        mItem_time = getIntent().getStringExtra("item_time");
        Log.i(LOG_TAG,"item time ---- " + mItem_time);

        // get current user
        mCurrentUser = mAuth.getCurrentUser();
        user_data = FirebaseDatabase.getInstance().getReference().child(mCurrentUser.getUid());

        // extract data
        mDbRef.child(mItem_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                mItem_name = (String) dataSnapshot.child("name").getValue();
                Log.i("log", "mItem_name: " + mItem_name);
                mImage_string = (String) dataSnapshot.child("image").getValue();
                Log.i("log", "image string: " + mImage_string);

                mCustomerTV.setText(mCurrentUser.getEmail());
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
    }

    public void orderCancelClicked(View view) {
    }

    public void orderNoShowClicked(View view) {
    }
}
