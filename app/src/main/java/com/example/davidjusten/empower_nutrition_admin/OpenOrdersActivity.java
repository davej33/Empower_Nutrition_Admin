package com.example.davidjusten.empower_nutrition_admin;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.nio.FloatBuffer;

public class OpenOrdersActivity extends AppCompatActivity {

    private RecyclerView mOrderRV;
    private DatabaseReference mDb;
    private FirebaseRecyclerAdapter<Order, OrderViewHolder> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_orders);

        mOrderRV = findViewById(R.id.orderRecyclerView);
        mOrderRV.setHasFixedSize(true);
        mOrderRV.setLayoutManager(new LinearLayoutManager(this));

        mDb = FirebaseDatabase.getInstance().getReference().child("Orders");
        String s = mDb.child("itemName").toString();
        Log.i("tag","itemName test --------- " + s);
    }

    @Override
    protected void onStart() {
        super.onStart();

//        mAuth.addAuthStateListener(mAuthListener);
        Query query = mDb;
        FirebaseRecyclerOptions<Order> options = new FirebaseRecyclerOptions.Builder<Order>()
                .setQuery(query, Order.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<Order, OrderViewHolder>(options) {

            Order mOrder;
            @Override
            protected void onBindViewHolder(@NonNull OrderViewHolder holder, int position, @NonNull Order model) {
                holder.setItem(model.getItem());
                holder.setUsername(model.getUserame());

                mOrder = model;
            }

            @NonNull
            @Override
            public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false);
                return new OrderViewHolder(view);
            }
        };
        mOrderRV.setAdapter(adapter);
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.startListening();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public OrderViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setUsername(String username) {
            TextView username_text = mView.findViewById(R.id.orderUserName);
            username_text.setText(username);
        }

        public void setItem(String item) {
            TextView item_text = mView.findViewById(R.id.orderItem);
            item_text.setText(item);
        }
    }
}
