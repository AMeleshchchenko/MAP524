package com.example.assignment4;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(version = 1, entities = {Cocktail.class})
public abstract class CocktailsDatabase extends RoomDatabase {
    abstract public DAOCocktailDB getDao();
}
