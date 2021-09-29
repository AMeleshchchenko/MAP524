package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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

    Button btnAdd;
    Button btnMinus;
    Button btnMultiply;
    Button btnDivide;
    Button btnEquals;
    Button btnClear;

    Button btnAdvanced;

    TextView inputText;
    TextView historyText;

    private Calculator myCalculator = new Calculator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        btnAdd.setOnClickListener(this::onClick);
        btnMinus.setOnClickListener(this::onClick);
        btnMultiply.setOnClickListener(this::onClick);
        btnDivide.setOnClickListener(this::onClick);
        btnEquals.setOnClickListener(this::onClick);
        btnClear.setOnClickListener(this::onClick);
        btnAdvanced.setOnClickListener(this::onClick);
    }

    private void setupUIViews() {
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

        btnAdd = (Button) findViewById(R.id.addOp);
        btnMinus = (Button) findViewById(R.id.minusOp);
        btnMultiply = (Button) findViewById(R.id.multiplyOp);
        btnDivide = (Button) findViewById(R.id.divideOp);

        btnEquals = (Button) findViewById(R.id.equalsOp);
        btnClear = (Button) findViewById(R.id.clearOp);

        btnAdvanced = (Button) findViewById(R.id.advanced);

        inputText = (TextView) findViewById(R.id.result);
        historyText = (TextView) findViewById(R.id.history);
    }

    @Override
    public void onClick(View view) {

        if(((Button)view).getText().toString().equals("C")){
            myCalculator.reset();
            clearOnClick(view);
        }
        else if(((Button)view).getText().toString().equals("=")){
            equalOnClick(view);
        }
        else if(((Button)view).getText().toString().equals("ADVANCE - WITH HISTORY") || ((Button)view).getText().toString().equals("STANDARD - NO HISTORY")){
            advanceOnClick(view);
        }
        else {
            inputText.setText(inputText.getText().toString() + " " + ((Button) view).getText().toString());
            String val = String.valueOf(inputText.getText()).substring(inputText.getText().length() - 1);
            myCalculator.push(val);
        }
    }


    public void equalOnClick(View view){
        int result = myCalculator.calculate();
        inputText.setText(inputText.getText() + " = " +  String.valueOf(result));
        if(btnAdvanced.getText().equals("STANDARD - NO HISTORY")){
            historyText.setText(historyText.getText() + "\n" + inputText.getText());
        }
    }

    public void clearOnClick(View view){
        inputText.setText("");
    }

    public void advanceOnClick(View view){
        if(btnAdvanced.getText().equals("ADVANCE - WITH HISTORY")){
            btnAdvanced.setText("STANDARD - NO HISTORY");
            btnAdvanced.setBackgroundColor(getResources().getColor(R.color.DimGray));
        }
        else if(btnAdvanced.getText().equals("STANDARD - NO HISTORY")){
            btnAdvanced.setText("ADVANCE - WITH HISTORY");
            btnAdvanced.setBackgroundColor(getResources().getColor(R.color.MediumSlateBlue));
            historyText.setText("");
        }
    }
}


