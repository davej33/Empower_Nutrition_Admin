package com.example.davidjusten.empower_nutrition_admin;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by davidjusten on 3/19/18.
 */

public class Order2Adapter extends RecyclerView.Adapter<Order2Adapter.Order2ViewHolder> {

    private static final String LOG_TAG = Order2Adapter.class.getSimpleName();
    private static List<Food> mOrderList;
    private static Context mContext;
    private static Food mCurrentItem;
    private int mLastPosition;
    private static Food mClickedItem;
    private static String mClickedItemKey;
    private static int mAdapterPositionClicked;

    public Order2Adapter(Context context){
        mContext = context;
    }

    public static Object getClickedItem() {
        return mClickedItem;
    }

    @NonNull
    @Override
    public Order2ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false);
        return new Order2ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Order2ViewHolder holder, final int position) {


        mCurrentItem = mOrderList.get(position);
        Log.i(LOG_TAG,"ADAPTER current item: " + mCurrentItem.getItem());
        Log.i(LOG_TAG,"ADAPTER item is ready: " + mCurrentItem.getReady());


        holder.orderItem.setText(mCurrentItem.getItem());
        holder.orderQuantity.setText(String.valueOf(mCurrentItem.getQuantity()));
        if(mCurrentItem.getReady().equals("true")){
            holder.orderItem.setTextColor(mContext.getResources().getColor(android.R.color.holo_green_light));
            holder.orderQuantity.setTextColor(mContext.getResources().getColor(android.R.color.holo_green_light));
        }
        holder.orderTime.setText(mCurrentItem.getTime());
        holder.orderCustomer.setText(mCurrentItem.getUser());

        // set orderID
        String s = mCurrentItem.getOrderId();
        holder.orderId.setText(s);

        // set layout background
        int i = Integer.valueOf(s);
        if (i % 2 == 0){
            holder.itemLayout.setBackgroundColor(mContext.getResources().getColor(android.R.color.white));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get last position
                mAdapterPositionClicked = holder.getAdapterPosition();
                mClickedItem = mOrderList.get(mAdapterPositionClicked);
                Intent intent = new Intent(mContext,OrderDetailActivity.class);
                mContext.startActivity(intent);
            }
        });

    }

    public static int getItemPosition(){
        return mAdapterPositionClicked;
    }
    @Override
    public int getItemCount() {
        if (mOrderList != null) {
            return mOrderList.size();
        } else {
            return 0;
        }
    }

    public List<Food> getmOrderList() {
        return mOrderList;
    }

    public static void setmOrderList(List<Food> orderList) {
        mOrderList = orderList;
    }

    public class Order2ViewHolder extends RecyclerView.ViewHolder {

        TextView orderItem;
        TextView orderQuantity;
        TextView orderTime;
        TextView orderCustomer;
        TextView orderId;
        ConstraintLayout itemLayout;

        public Order2ViewHolder(View itemView) {
            super(itemView);

            orderCustomer = itemView.findViewById(R.id.orderUserName);
            orderItem = itemView.findViewById(R.id.orderItem);
            orderQuantity = itemView.findViewById(R.id.orderQuantity);
            orderTime = itemView.findViewById(R.id.orderTime);
            itemLayout = itemView.findViewById(R.id.order_item_layout);
            orderId = itemView.findViewById(R.id.orderId);
        }
    }
}
