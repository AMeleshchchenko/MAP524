package com.example.assignment2;

import android.os.Parcel;
import android.os.Parcelable;

public class HistoryObj implements Parcelable {

    double totalAmount;
    int quantityPurchased;
    String datePurchased;
    String itemName;

    public HistoryObj(){


    }

    public HistoryObj(String itemname, int qty, double total, String datepurchased){
        itemName = itemname;
        quantityPurchased = qty;
        totalAmount = total;
        datePurchased = datepurchased;
    }

    protected HistoryObj(Parcel in) {
        totalAmount = in.readDouble();
        quantityPurchased = in.readInt();
        datePurchased = in.readString();
        itemName = in.readString();
    }

    public static final Creator<HistoryObj> CREATOR = new Creator<HistoryObj>() {
        @Override
        public HistoryObj createFromParcel(Parcel in) {
            return new HistoryObj(in);
        }

        @Override
        public HistoryObj[] newArray(int size) {
            return new HistoryObj[size];
        }
    };

    public String getItem(){
        return itemName;
    }
    public int getQty(){
        return quantityPurchased;
    }

    public String get_date(){
        return datePurchased;
    }

    public double get_total(){
        return totalAmount;
    }
    public void setItem(String item){
        itemName = item;
    }
    public void setQty(int qty){
        quantityPurchased = qty;
    }

    public void setdate(String date){
        datePurchased = date;
    }

    public void set_total(double total){
        totalAmount = total;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(totalAmount);
        parcel.writeInt(quantityPurchased);
        parcel.writeString(datePurchased);
        parcel.writeString(itemName);
    }
}
