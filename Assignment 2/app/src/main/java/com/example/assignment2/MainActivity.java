package com.example.assignment2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TaskInfo;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    int buyIndex;
    double amountPurchased = 0;
    Button btnZero;
    Button btnOne;
    Button btnTwo;
    Button btnThree;
    Button btnFour;
    Button btnFive;
    Button btnSix;
    Button btnSeven;
    Button btnEight;
    Button btnNine;

    Button btnBuy;
    Button btnClear;

    Button btnManager;

    TextView prodType;
    TextView totalPrice;
    TextView prodQuantity;

    AlertDialog.Builder builder;

    InventoryListAdapter adapter;
    ListView invList;
    ArrayList<Inventory> listOfInventory;

    ArrayList<HistoryObj> historyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        builder = new AlertDialog.Builder(this);

        invList = (ListView) findViewById(R.id.inventoryList);
        listOfInventory = new ArrayList<>(1);
        historyList = new ArrayList<>(1);

        listOfInventory.add(new Inventory("Pants", "20.44", "10"));
        listOfInventory.add(new Inventory("Shoes", "10.44", "100"));
        listOfInventory.add(new Inventory("Hats", "5.9", "30"));

        adapter = new InventoryListAdapter(this, listOfInventory);
        invList.setAdapter(adapter);

        setupUIViews();

        btnZero.setOnClickListener(this::onClick);
        btnOne.setOnClickListener(this::onClick);
        btnTwo.setOnClickListener(this::onClick);
        btnThree.setOnClickListener(this::onClick);
        btnFour.setOnClickListener(this::onClick);
        btnFive.setOnClickListener(this::onClick);
        btnSix.setOnClickListener(this::onClick);
        btnSeven.setOnClickListener(this::onClick);
        btnEight.setOnClickListener(this::onClick);
        btnNine.setOnClickListener(this::onClick);
        btnBuy.setOnClickListener(this::onClick);
        btnClear.setOnClickListener(this::onClick);
        btnManager.setOnClickListener(this::onClick);


        invList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                double total = 0;
                double price = 0;
                double quantity = 0;
                DecimalFormat formatter = new DecimalFormat("###.##");

               prodType.setText(String.valueOf(listOfInventory.get(i).getItem()));
               price = parseDouble(listOfInventory.get(i).getPrice());
               total  = price * amountPurchased;

                System.out.println(formatter.format(price * amountPurchased));
               totalPrice.setText(String.format(String.valueOf(formatter.format(total))));

               buyIndex = i;


            }
        });
    }

    public double doubleDigit(double val){
        return Math.round(val*100.0)/100;
    }

    private void setupUIViews(){
        btnZero = (Button) findViewById(R.id.zero);
        btnOne = (Button) findViewById(R.id.one);
        btnTwo = (Button) findViewById(R.id.two);
        btnThree = (Button) findViewById(R.id.three);
        btnFour = (Button) findViewById(R.id.four);
        btnFive = (Button) findViewById(R.id.five);
        btnSix = (Button) findViewById(R.id.six);
        btnSeven = (Button) findViewById(R.id.seven);
        btnEight = (Button) findViewById(R.id.eight);
        btnNine = (Button) findViewById(R.id.nine);
        btnBuy = (Button) findViewById(R.id.buy);
        btnClear = (Button) findViewById(R.id.clear);
        btnManager = (Button) findViewById(R.id.manager);

        prodType = (TextView) findViewById(R.id.productType);
        totalPrice = (TextView) findViewById(R.id.total);
        prodQuantity = (TextView) findViewById(R.id.quantity);
    }

    @Override
    public void onClick(View view) {
        if(((Button)view).getText().toString().equals("Buy")){
                buyOnClick(view);
                clearOnClick(view);
        }
        else if(((Button)view).getText().toString().equals("C")){
            clearOnClick(view);
        }
        else if(((Button)view).getText().toString().equals("Manager")){
            Intent managerIntent = new Intent(this, Manager_Activity.class);

            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("historylist", historyList);
            managerIntent.putExtra("bundle", bundle);
            startActivity(managerIntent);
        }
        else{

            if(prodQuantity.getText().equals("Quantity")){
                prodQuantity.setText(((Button) view).getText().toString());
            } else {
                prodQuantity.setText(prodQuantity.getText().toString() + ((Button) view).getText().toString());
            }
            amountPurchased = parseDouble(String.valueOf(prodQuantity.getText()));
        }
    }

    public void clearOnClick(View view){
        prodQuantity.setText("Quantity");
        prodType.setText("Product Type");
        totalPrice.setText("Total");
    }

    public void buyOnClick(View view){
        double quantity = parseDouble(listOfInventory.get(buyIndex).getQuantity());
        String itemName;
        String date;
        int qty;
        double totalAmt;

         if(prodType.getText().toString().equals("Product Type") || prodQuantity.getText().toString().equals("Quantity")){
             Toast.makeText(this, "All fields are required!!!", Toast.LENGTH_LONG).show();
         }
         else if(amountPurchased > quantity){
            Toast.makeText(this, "Not enough quantity in the stock!!!", Toast.LENGTH_LONG).show();
        }
        else{
             listOfInventory.get(buyIndex).setQuantity(String.valueOf(quantity - amountPurchased));

             Inventory tempInv = listOfInventory.get(buyIndex);
             itemName = tempInv.getItem();
             qty = (int)amountPurchased;
             totalAmt = Double.parseDouble(String.valueOf(totalPrice.getText()));
             date = String.valueOf(Calendar.getInstance().getTime());

             historyList.add(new HistoryObj(itemName, qty, totalAmt, date));
             builder.create();
             builder.setTitle("Thank you for your purchase");
             builder.setMessage("Your purchase is " + Math.round(amountPurchased) + " " + prodType.getText().toString() + " for " + totalPrice.getText().toString());
             builder.show();

             invList.setAdapter(adapter);
        }

    }
}