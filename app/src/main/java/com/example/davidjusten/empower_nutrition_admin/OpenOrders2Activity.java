package com.example.davidjusten.empower_nutrition_admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

public class OpenOrders2Activity extends AppCompatActivity {

    private List<Food> mList;
    private RecyclerView mRv;
    private Order2Adapter mAdapter;
    private static String orderKey;

    public static String getKey() {
        return orderKey;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_orders2);

        mRv = findViewById(R.id.open_orders2_RV);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mRv.setHasFixedSize(true);
        mAdapter = new Order2Adapter(this);
        mRv.setAdapter(mAdapter);

        mList = new ArrayList<>();

        DatabaseReference mOrdersRef = FirebaseDatabase.getInstance().getReference().child("Orders");
        Query query = mOrdersRef;
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                orderKey = dataSnapshot.getKey();
                int i = 0;
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    String item = data.child("name").getValue().toString();
                    String user = data.child("user").getValue().toString();
                    String quant = data.child("quantity").getValue().toString();
                    String time = data.child("time").getValue().toString();
                    String orderId = data.child("orderID").getValue().toString();
                    String childKey = String.valueOf(i++);

                    Food f = new Food(user, item, quant, time, orderId, childKey);
                    mList.add(f);

                }

                Order2Adapter.setmOrderList(mList);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                int i = 0;
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    String item = data.child("name").getValue().toString();
                    String user = data.child("user").getValue().toString();
                    String quant = data.child("quantity").getValue().toString();
                    String time = data.child("time").getValue().toString();
                    String orderId = data.child("orderID").getValue().toString();
                    String childKey = String.valueOf(i++);

                    Food f = new Food(user, item, quant, time, orderId, childKey);
                    mList.add(f);

                }

                Order2Adapter.setmOrderList(mList);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
