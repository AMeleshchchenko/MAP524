package com.example.assignment2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class InventoryRecyclerAdapter extends RecyclerView.Adapter<InventoryRecyclerAdapter.viewHolder> {

    ArrayList<HistoryObj> listOfPurchaseHistory;
    Context mContext;

    public interface OnItemClickListener{
        void onItemClick(HistoryObj item);
    }

    private final OnItemClickListener listener;

    public InventoryRecyclerAdapter(ArrayList<HistoryObj> listOfPurchaseHistory, Context context, OnItemClickListener listenerFromActivity) {
        this.listOfPurchaseHistory = listOfPurchaseHistory;
        this.mContext = context;
        listener = listenerFromActivity;
    }

    public static class viewHolder extends RecyclerView.ViewHolder{
        private final TextView itemText;
        private final TextView quantityText;
        private final TextView priceText;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            itemText = itemView.findViewById(R.id.inventory_item);
            quantityText = itemView.findViewById(R.id.inventory_quantity);
            priceText = itemView.findViewById(R.id.inventory_price);
        }

        public TextView getItemText() {
            return itemText;
        }

        public TextView getQuantityText() {
            return quantityText;
        }

        public TextView getPriceText() {
            return priceText;
        }
    }

    @NonNull
    @Override
    public InventoryRecyclerAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater myInflator = LayoutInflater.from(mContext);
        View view = myInflator.inflate(R.layout.history_recycler_list_row, parent, false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InventoryRecyclerAdapter.viewHolder holder, int position) {
        HistoryObj tempObj = listOfPurchaseHistory.get(position);
        holder.getItemText().setText(tempObj.getItem());
        holder.getQuantityText().setText(String.valueOf(tempObj.getQty()));
        holder.getPriceText().setText(String.valueOf(tempObj.get_total()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(listOfPurchaseHistory.get(position));

            }
        });
    }

    @Override
    public int getItemCount() {
        return listOfPurchaseHistory.size();
    }
}
