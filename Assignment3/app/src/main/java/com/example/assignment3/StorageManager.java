package com.example.assignment3;

import android.app.Activity;
import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class StorageManager {
    String fileName = "tasks.txt";

    public void saveAverageInternal(Activity activity,Double average){

    }

    public void writeAvg(Activity activity, int num){
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = activity.openFileOutput(fileName, Context.MODE_APPEND); // continue writting
            fileOutputStream.write((String.valueOf(num) +"$").getBytes());

        }catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            try {
                fileOutputStream.close();
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
    }

    public String readAvg(Activity activity){
        FileInputStream fileInputStream = null;
        int index = 0;
        int read;
        int count = 0;
        double avgVal = 0;
        String avgString = "";

        StringBuffer buffer = new StringBuffer();
        try {
            fileInputStream = activity.openFileInput(fileName);
            while(( read = fileInputStream.read() )!= -1 ){
                buffer.append((char)read);
            }

            for(int i = 0; i < buffer.toString().length(); i++){
                if(buffer.toString().toCharArray()[i] == '$'){
                    String valstr = buffer.toString().substring(index, i);
                    System.out.println("valstr is " + valstr);
                    System.out.println("fullstringbuffer i s " + buffer.toString());
                    double val = Double.parseDouble(buffer.toString().substring(index, i));
                    avgVal += val;
                    count += 1;
                    index = i + 1;
                }
            }
            avgString = (int)avgVal + " in " + count + " attempts";

        }catch (IOException ex){ex.printStackTrace();}
        finally {
            try {
                fileInputStream.close();
            }catch (IOException ex){ex.printStackTrace();}

        }
        return avgString;
    }

    public void resetSavedResult(Activity activity){
        FileOutputStream fileOutputStream = null;
        try{
            fileOutputStream = activity.openFileOutput(fileName, Context.MODE_PRIVATE);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try{
                fileOutputStream.close();
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
    }
}
