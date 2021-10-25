package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HistoryDetails extends AppCompatActivity {

    TextView itemName;
    TextView priceAmount;
    TextView dateVal;
    HistoryObj currentObj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_details);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        currentObj = bundle.getParcelable("histObj");

        itemName = (TextView) findViewById(R.id.itemName);
        priceAmount = (TextView) findViewById(R.id.price);
        dateVal = (TextView) findViewById(R.id.datePurchased);


        itemName.setText("Product: " + currentObj.getItem());
        priceAmount.setText("Price: " + String.valueOf(currentObj.get_total()));
        dateVal.setText("Purchased Date: " + currentObj.get_date());
    }
}