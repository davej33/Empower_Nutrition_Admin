package com.example.davidjusten.empower_nutrition_admin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.davidjusten.empower_nutrition_admin.helpers.OnSwipeTouchListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class OpenOrdersActivity extends AppCompatActivity {

    private static final String LOG_TAG = OpenOrdersActivity.class.getSimpleName();
    private RecyclerView mOrderRV;
    private DatabaseReference mDb;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseRecyclerAdapter<Order, OrderViewHolder> adapter;
    private FirebaseAuth mAuth;
    private GestureDetector mDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_orders);

        mOrderRV = findViewById(R.id.orderRecyclerView);
        mOrderRV.setHasFixedSize(true);
        mOrderRV.setLayoutManager(new LinearLayoutManager(this));

        mDb = FirebaseDatabase.getInstance().getReference().child("Orders");
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null) {
                    Intent loginIntent = new Intent(OpenOrdersActivity.this, SignupActivity.class);
                    loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(loginIntent);

                }
            }
        };

    }


    @Override
    protected void onStart() {
        super.onStart();


        mAuth.addAuthStateListener(mAuthListener);
        Query query = mDb;
        FirebaseRecyclerOptions<Order> options = new FirebaseRecyclerOptions.Builder<Order>()
                .setQuery(query, Order.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<Order, OrderViewHolder>(options) {

            @Override
            protected void onBindViewHolder(@NonNull OrderViewHolder holder, int position, @NonNull final Order model) {
                holder.setItem(model.getItemName());
                holder.setUsername(model.getUserName());
                holder.setTime(model.getTime());

                final String item_key = getRef(position).getKey();
                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent orderDetaiIntent = new Intent(OpenOrdersActivity.this, OrderDetailActivity.class);
                        orderDetaiIntent.putExtra("item_key", item_key);
                        orderDetaiIntent.putExtra("item_name", model.getItemName());
                        orderDetaiIntent.putExtra("item_time", model.getTime());
                        Log.i(LOG_TAG, "item time ------- " + model.getTime());
                        startActivity(orderDetaiIntent);
                    }
                });
            }

            @NonNull
            @Override
            public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false);
                return new OrderViewHolder(view);
            }
        };
        adapter.startListening();
        mOrderRV.setAdapter(adapter);

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

        public void setTime(String time){
            TextView time_text = mView.findViewById(R.id.orderTime);
            time_text.setText(time);
        }

    }
}
