package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class Manager_Activity extends AppCompatActivity implements View.OnClickListener{
    Button btnHistory;
    ArrayList<HistoryObj> histList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        if(getIntent().hasExtra("bundle")){
            Bundle bundleFromMainActivity = getIntent().getBundleExtra("bundle");
            histList = bundleFromMainActivity.getParcelableArrayList("historylist");
        }

        for(int i = 0; i < histList.size(); i++){
            System.out.println(histList.get(i).getItem());
        }

        btnHistory = (Button) findViewById(R.id.history);
        btnHistory.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View view) {
        if(((Button)view).getText().toString().equals("History")){
            Intent historyIntent = new Intent(this, History_Activity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("histList", histList);
            historyIntent.putExtra("bundle", bundle);
            startActivity(historyIntent);
        }
    }
}