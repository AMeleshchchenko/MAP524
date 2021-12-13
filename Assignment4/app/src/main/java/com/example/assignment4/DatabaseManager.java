package com.example.assignment4;

import android.content.Context;
import android.os.Looper;

import androidx.room.Room;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import android.os.Handler;

public class DatabaseManager {

    interface DatabaseListener{
        void CocktailsListener(List<Cocktail> list);
    }

    DatabaseListener listener;

    ExecutorService databaseExecutor = Executors.newFixedThreadPool(4);
    Handler db_handler = new Handler(Looper.getMainLooper());

    static CocktailsDatabase db;

    public static void buildDBInstance(Context context){
        db = Room.databaseBuilder(context, CocktailsDatabase.class, "database-cocktails").build();
    }

    public CocktailsDatabase getDBInstance(Context context){
        if(db == null){
            buildDBInstance(context);
        }
        return db;
    }

    public void getAllCocktails(){
        databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                List<Cocktail> list = db.getDao().getAllCocktails();
                db_handler.post(new Runnable() {
                    @Override
                    public void run() {
                        // run in main thread
                        listener.CocktailsListener(list);
                    }
                });
            }
        });
    }

//    public void getAllCocktailsByID(int cocktailId){
//        databaseExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                Cocktail obj = db.getDao().getAllCocktails(cocktailId);
//                db_handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        listener.CocktailsListener(obj);
//                    }
//                })
//            }
//        });
//    }

    public void addNewCocktail(Cocktail c){
        databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                db.getDao().insertCocktail(c);
            }
        });
    }
}
