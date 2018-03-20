package com.example.davidjusten.empower_nutrition_admin;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
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

    private static List<Food> mOrderList;
    private static Context mContext;

    public Order2Adapter(Context context){
        mContext = context;
    }
    @NonNull
    @Override
    public Order2ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false);
        return new Order2ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Order2ViewHolder holder, int position) {
        Food f = mOrderList.get(position);

        holder.orderItem.setText(f.getItem());
        holder.orderQuantity.setText(String.valueOf(f.getQuantity()));
        holder.orderTime.setText(f.getTime());
        holder.orderCustomer.setText(f.getUser());

        // set orderID
        String s = f.getOrderId();
        holder.orderId.setText(s);

        // set layout background
        int i = Integer.valueOf(s);
        if (i % 2 == 0){
            holder.itemLayout.setBackgroundColor(mContext.getResources().getColor(android.R.color.white));
        }


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
