package com.example.assignment4;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class CocktailActivity extends AppCompatActivity implements NetworkingService.NetworkingListener
      {


    NetworkingService networkingService;
    JsonService jsonService;
    ListView listOfCocktails;
    TextView cocktailNameText;
    TextView cocktailCategoryText;
    TextView cocktailInstructionsText;
    ImageView cocktailImageView;
    String cocktailImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocktails);

        String cocktailName = getIntent().getStringExtra("cocktailName");
        String cocktailCategory = getIntent().getStringExtra("cocktailCategory");
        String cocktailInstructions = getIntent().getStringExtra("cocktailInstructions");
        cocktailImg = getIntent().getStringExtra("imgurl");
        System.out.println("IMG URL IS ********************************************" + cocktailImg);

        networkingService = ((myApp)getApplication()).getNetworkingService();
        jsonService = ((myApp)getApplication()).getJsonService();
        networkingService.listener = this;

        networkingService.getCategoryDataForCocktail(cocktailName);

        cocktailNameText = findViewById(R.id.cocktailName);
        cocktailCategoryText = findViewById(R.id.cocktailCategory);
        cocktailInstructionsText = findViewById(R.id.cocktailInstructions);
        cocktailImageView = findViewById(R.id.cocktailImage);
        listOfCocktails = findViewById(R.id.cocktailsList);

        cocktailNameText.setText(cocktailName);
        cocktailCategoryText.setText("Category: " + cocktailCategory);
        cocktailInstructionsText.setText(cocktailInstructions);

        System.out.println("******************** " + cocktailName);
        System.out.println("******************** " + cocktailCategory);
        System.out.println("******************** " + cocktailInstructions);
        System.out.println("******************** " + cocktailImg);


    }

    @Override
    public void dataListener(String josnString){

        networkingService.getImageData(cocktailImg);
    }

    @Override
    public void imageListener(Bitmap image){
        cocktailImageView.setImageBitmap(image);
    }

//    @Override
//    public void CocktailsListener(List<Cocktail> list) {
//
//    }


//    @Override
//    public void dataBaseReturnWithList(List<Cocktail> cocktailList) {
//        ArrayList<Cocktail> allCocktails = new ArrayList<Cocktail>(cocktailList);
//
//        CocktailsAdapter listAdapter = new CocktailsAdapter(this, allCocktails);
//        listOfCocktails.setAdapter((ListAdapter) listAdapter);
//    }
}
