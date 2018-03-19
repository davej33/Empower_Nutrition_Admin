package com.example.davidjusten.empower_nutrition_admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    private RecyclerView mRv;
    private List<Food> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_orders2);

        mList = new ArrayList<>();

        DatabaseReference mOrdersRef = FirebaseDatabase.getInstance().getReference().child("Orders");
        Query q = mOrdersRef;
        q.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.i("OO2", "children count: " + dataSnapshot.getChildrenCount());
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    String name = data.child("name").getValue().toString();
                    String desc = data.child("desc").getValue().toString();
                    String image = data.child("image").getValue().toString();
                    String price = data.child("price").getValue().toString();
                    Long quant = (Long) data.child("quantity").getValue();
                    String type = data.child("type").getValue().toString();

                    Food f = new Food(name, price, desc, quant, image, type);
                    mList.add(f);

                }
                Log.i("OO2", "List count: " + mList.size());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

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
