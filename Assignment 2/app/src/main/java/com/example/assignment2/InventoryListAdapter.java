package com.example.assignment2;

import static java.lang.Double.parseDouble;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;

public class InventoryListAdapter extends BaseAdapter {

    Context context;
    ArrayList<Inventory> inventory;

    public InventoryListAdapter(Context context, ArrayList<Inventory> inventory) {
        this.context = context;
        this.inventory = inventory;
    }

    @Override
    public int getCount() {
        return inventory.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
      if(view == null){
          view = LayoutInflater.from(context).inflate(R.layout.list_row_item, null);
      }

      LinearLayout itemLayout = (LinearLayout) view.findViewById(R.id.itemLayout);

      TextView itemText = (TextView) view.findViewById(R.id.inventory_item);
      TextView priceText = (TextView) view.findViewById(R.id.inventory_price);
      TextView quantityText = (TextView) view.findViewById(R.id.inventory_quantity);

      itemText.setText(inventory.get(i).item);
      priceText.setText(inventory.get(i).price);
      quantityText.setText(String.valueOf(Math.round(parseDouble(inventory.get(i).quantity))));


      return view;
    }
}
