package com.example.assignment4;

import android.content.Context;
import android.os.Looper;
import android.os.Handler;

import androidx.room.Room;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class CocktailsDataBaseClient {

    interface DatabaseActionsListener {
        public void dataBaseReturnWithList(List<Cocktail> cocktailList);
    }

    static DatabaseActionsListener listener;

    public static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(4);
    static Handler handler = new Handler(Looper.getMainLooper());
    static CocktailsDatabase dbClient;
    static Context dbContext;


    CocktailsDataBaseClient(Context context){
        dbContext = context;
        dbClient = Room.databaseBuilder(context, CocktailsDatabase.class, "database-cocktails").build();

    }

    public static CocktailsDatabase getDbClient(){
        if(dbClient == null){
            dbClient = new CocktailsDataBaseClient(dbContext).dbClient;
        }
        return dbClient;
    }

    public static void insertNewCocktail(Cocktail newCocktail){
        // background thread
        databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dbClient.getDao().insertCocktail(newCocktail);
            }
        });
    }


    public static void getAllCocktails(){
        databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                List<Cocktail> listFromDB = dbClient.getDao().getAllCocktails();

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.dataBaseReturnWithList(listFromDB);
                    }
                });
            }
        });
    }

}
