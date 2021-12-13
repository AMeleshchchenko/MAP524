package com.example.assignment4;

import static android.text.TextUtils.getLayoutDirectionFromLocale;
import static android.text.TextUtils.indexOf;
import static android.text.TextUtils.substring;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonService {
    public ArrayList<Cocktail> getCocktailsFromJSON(String json){
        ArrayList<Cocktail> arrayList = new ArrayList<>(0);

        System.out.println("start of json is *****" + json.substring(30, 52));

        String newstr = json.substring(10, json.length() - 1);
        //System.out.println(newstr);
        //json.substring(10, json.length());
        try {

            JSONArray json_cocktails = new JSONArray(newstr);

            System.out.println("****** length " + json_cocktails.length());
            for(int i = 0; i < json_cocktails.length(); i++){
                String fullCocktailName = json_cocktails.getString(i);
                // For the cocktail name
                int indexname = fullCocktailName.indexOf("strDrink") + 11;
                int indexname2 = fullCocktailName.indexOf("strDrinkAlternate") - 3;

                // For the cocktail category
                int indexname3 = fullCocktailName.indexOf("strCategory") + 14;
                int indexname4 = fullCocktailName.indexOf("strIBA") - 3;

                // For the cocktail instructions
                int indexname5 = fullCocktailName.indexOf("strInstructions") + 18;
                int indexname6 = fullCocktailName.indexOf("strInstructionsES") - 3;

                // For the cocktail image
                int indexname7 = fullCocktailName.indexOf("strDrinkThumb") + 71;
                int indexname8 = fullCocktailName.indexOf("strIngredient1") - 3;


                //String cocktailName = substring(fullCocktailName,0,indexOf(fullCocktailName,'"'));
                String cocktailName = substring(fullCocktailName, indexname, indexname2);
                //String cocktailName = substring(fullCocktailName, 31, index);
                //String cocktailName = newstr;
                System.out.println(cocktailName);
                String cocktailCategory = substring(fullCocktailName,indexname3, indexname4);

                String cocktailInstructions = substring(fullCocktailName, indexname5, indexname6);

                String cocktailImage = substring(fullCocktailName, indexname7, indexname8);

                System.out.println(cocktailImage + " *****************IMAGE RECEIVED");
                //2mcozt1504817403.jpg
                Cocktail c = new Cocktail(cocktailName, cocktailImage, cocktailCategory, cocktailInstructions);
                arrayList.add(c);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public CocktailData getCocktailData(String jsonString){
        CocktailData cocktailData = new CocktailData();

        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray cocktailArray = jsonObject.getJSONArray("drinks");
            JSONObject cocktailDataObject = cocktailArray.getJSONObject(0);

            System.out.println("IS JASON OBJECT STORING ANYTING" + cocktailDataObject);

            String categoryCocktailValue = cocktailDataObject.getString("strCategory");
            String instructionsCocktailValue = cocktailDataObject.getString("strInstructions");

            String imageValue = cocktailDataObject.getString("strDrinkThumb"); //image of cocktail

            JSONObject categoryObject = jsonObject.getJSONObject("drinks");
            String categoryValue = categoryObject.getString("category");

            JSONObject instructionsObject = jsonObject.getJSONObject("drinks");
            String instructionsValue = instructionsObject.getString("instructions");

            cocktailData = new CocktailData(imageValue, categoryValue, instructionsValue);
            System.out.println("********* Get Data: " + imageValue + " " + categoryValue + " " + instructionsValue);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return cocktailData;
    }

}
