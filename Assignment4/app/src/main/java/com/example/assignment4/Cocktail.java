package com.example.assignment4;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Cocktail {
    @PrimaryKey
    private int id;

    private String cocktailName;
    private String cocktailCategory;
    private String cocktailInstructions;
    private String cocktailImage;

    Cocktail(){

    }

    public Cocktail(String name, String image, String category, String instructions){
        this.cocktailName = name;
        this.cocktailCategory = category;
        this.cocktailInstructions = instructions;
        this.cocktailImage = image;
    }

    public void setCocktailCategory(String category){
        this.cocktailCategory = category;
    }


    public String getCocktailCategory(){
        return cocktailCategory;
    }

    public void setCocktailInstructions(String instructions){
        this.cocktailInstructions = instructions;
    }


    public String getCocktailInstructions(){
        return cocktailInstructions;
    }

    public String getCocktailImage(){
        return cocktailImage;
    }

    public void setCocktailImage(String image){
        this.cocktailImage = image;
    }

    public int getId(){
        return id;
    }

    public String getCocktailName(){
        return cocktailName;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setCocktailName(String cocktailName) {
        this.cocktailName = cocktailName;
    }
}
