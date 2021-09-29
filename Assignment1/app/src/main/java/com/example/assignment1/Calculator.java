package com.example.assignment1;

import static java.lang.Integer.parseInt;

import java.util.ArrayList;

public class Calculator {
    private ArrayList<String> arrayList;
    int num1;
    int num2;
    String operator;

    public Calculator(){
        num1 = 0;
        num2 = 0;
        operator = "";
        arrayList = new ArrayList<>();
    }

    public void push(String value){
        arrayList.add(value);
    }
    // push values into the arrayList

    private int doSingleCalc(int num1, int num2, String operator){
        if(operator.equals("+")){
            return num1 + num2;
        }
        else if(operator.equals("-")){
            return num1 - num2;
        }
        else if(operator.equals("*")){
            return num1 * num2;
        }
        else if(operator.equals("/")){
            return num1 / num2;
        }
        return 0;
    }
    // Check which type of operator has been called, and based on that operator do the appropriate
    // calculation

    public boolean isNum(String num){
        try{
            int num1 = Integer.parseInt(num);
            return true;
        } catch(Exception e){
            return false;
        }
    }

    public int calculate(){
        int num1 = 0;
        int num2 = 0;
        String operator = "";
        boolean flag = true;

        for(String val : arrayList){ //loops through each character in the array list
            if(isNum(val)){
                // checks if it is the first number
                if(flag == true){
                    num1 = parseInt(val);
                } else {
                    num2 = parseInt(val);
                    num1 = doSingleCalc(num1, num2, operator);

                }
            } else {
                operator = val;
                flag = false;
            }
        }

        return num1;
    }

    public void reset(){
        arrayList = new ArrayList<String>();
    }
}

