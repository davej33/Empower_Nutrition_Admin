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

    private static final String LOG_TAG = OpenOrders2Activity.class.getSimpleName();
    private List<Food> mList;
    private RecyclerView mRv;
    private static Order2Adapter mAdapter;
    private static String orderKey;
    private static DataSnapshot mSnapshot;

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
                mSnapshot = dataSnapshot;
                int i = 0;
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    String item = data.child("name").getValue().toString();
                    String user = data.child("user").getValue().toString();
                    String quant = data.child("quantity").getValue().toString();
                    String time = data.child("time").getValue().toString();
                    String orderId = data.child("orderID").getValue().toString();
                    String childKey = String.valueOf(i++);
                    String isReady = data.child("isReady").getValue().toString();
                    String orderKey = dataSnapshot.getKey();
                    Log.i(LOG_TAG, "order key: " + orderKey);

                    Food f = new Food(user, item, quant, time, orderId, childKey, isReady, orderKey);
                    mList.add(f);

                }

                Order2Adapter.setmOrderList(mList);
                updateAdapter();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.i("tag", "Child removed run");
                int i = 0;
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    String item = data.child("name").getValue().toString();
                    String user = data.child("user").getValue().toString();
                    String quant = data.child("quantity").getValue().toString();
                    String time = data.child("time").getValue().toString();
                    String orderId = data.child("orderID").getValue().toString();
                    String childKey = String.valueOf(i++);
                    String isReady = data.child("isReady").getValue().toString();

                    Food f = new Food(user, item, quant, time, orderId, childKey, isReady, orderKey);
                    mList.add(f);

                }

                Order2Adapter.setmOrderList(mList);
                updateAdapter();
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

    public static void updateAdapter() {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateAdapter();
    }
}
