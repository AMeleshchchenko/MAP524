package com.example.assignment4;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DAOCocktailDB {
    @Insert
    void insertCocktail(Cocktail c);

    @Query("SELECT * FROM Cocktail")
    List<Cocktail> getAllCocktails();
}
