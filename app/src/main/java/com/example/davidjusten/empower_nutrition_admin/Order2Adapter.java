package com.example.davidjusten.empower_nutrition_admin;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by davidjusten on 3/19/18.
 */

public class Order2Adapter extends RecyclerView.Adapter<Order2Adapter.Order2ViewHolder> {

    private static List<Food> mOrderList;

    @NonNull
    @Override
    public Order2ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false);
        return new Order2ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Order2ViewHolder holder, int position) {
        Food f = mOrderList.get(position);

        holder.orderItem.setText(f.getName());
        holder.orderQuantity.setText(String.valueOf(f.getQuantity()));
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

        public Order2ViewHolder(View itemView) {
            super(itemView);

            orderCustomer = itemView.findViewById(R.id.orderUserName);
            orderItem = itemView.findViewById(R.id.orderItem);
            orderQuantity = itemView.findViewById(R.id.orderQuantity);
            orderTime = itemView.findViewById(R.id.orderTime);
        }
    }
}
