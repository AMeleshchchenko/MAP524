package com.example.assignment2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class History_Activity extends AppCompatActivity {
    AlertDialog.Builder builder;

    InventoryRecyclerAdapter adapter;
    RecyclerView histList;
    ArrayList<HistoryObj> listHistObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        histList = findViewById(R.id.historyRecycler);
        if(getIntent().hasExtra("bundle")){
            Bundle bundleFromManagerActivity = getIntent().getBundleExtra("bundle");
            listHistObj = bundleFromManagerActivity.getParcelableArrayList("histList");
        }

        histList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new InventoryRecyclerAdapter(listHistObj, this, new InventoryRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(HistoryObj item) {
                goTo(item);
            }
        });
        histList.setAdapter(adapter);
    }

    public void goTo(HistoryObj item){
        Intent detailsIntent = new Intent(this, HistoryDetails.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("histObj", item);
        detailsIntent.putExtra("bundle", bundle);
        startActivity(detailsIntent);
    }

}
