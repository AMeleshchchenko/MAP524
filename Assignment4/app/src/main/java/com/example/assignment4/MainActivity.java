package com.example.assignment4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CocktailsAdapter.cocktailClickListener,
        NetworkingService.NetworkingListener, DatabaseManager.DatabaseListener {

    ArrayList<Cocktail> cocktails = new ArrayList<Cocktail>();
    RecyclerView recyclerView;
    CocktailsAdapter adapter;
    NetworkingService networkingService;
    JsonService jsonService;
    DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        networkingService = ((myApp)getApplication()).getNetworkingService();
        jsonService = ((myApp)getApplication()).getJsonService();
        networkingService.listener = this;
        recyclerView = findViewById(R.id.cocktailsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        cocktails = new ArrayList<Cocktail>(0);

        // This code works without the database
        adapter = new CocktailsAdapter(this, cocktails);
        recyclerView.setAdapter(adapter);
        setTitle("Search for new Cocktails...");

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // add a new cocktail
                cocktails.add(new Cocktail("Pina Colada", "cpf4j51504371346.jpg", "Ordinary Drink", "Mix with crushed ice in blender until smooth. Pour into chilled glass, garnish and serve."));

            }
        });

        dbManager = new DatabaseManager();
        dbManager.getDBInstance(this);
        dbManager.getAllCocktails();
        dbManager.listener = this;

        //List<Cocktail> allCocktailsFromDB = dbManager.getDBInstance(this).getDao().getAllCocktails();

    }

    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem searchViewMenuItem = menu.findItem(R.id.search);

        SearchView searchView = (SearchView) searchViewMenuItem.getActionView();
        String searchFor = searchView.getQuery().toString();

        if(!searchFor.isEmpty()){
            searchView.setIconified(false);
            searchView.setQuery(searchFor, false);
        }

        searchView.setQueryHint("Search for cocktails");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("query", query);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.length() >= 3){
                    networkingService.searchForCocktail(newText);
                }
                else{
                    cocktails = new ArrayList<>(0);
                    adapter.cocktailList = cocktails;
                    adapter.notifyDataSetChanged();
                }
                return false;
            }
        });
        return true;
    }

    @Override
    public void cocktailClicked(Cocktail selectedCocktail) {
        System.out.println("********************* " + selectedCocktail + " has been clicked");
        Intent intent = new Intent(this, CocktailActivity.class);
        intent.putExtra("cocktailName", selectedCocktail.getCocktailName());
        intent.putExtra("cocktailCategory", selectedCocktail.getCocktailCategory());
        intent.putExtra("cocktailInstructions", selectedCocktail.getCocktailInstructions());
        intent.putExtra("imgurl", selectedCocktail.getCocktailImage());
        System.out.println("******************* Before Start Activity");
        startActivity(intent);
        System.out.println("******************* After Start Activity");
    }

    @Override
    public void dataListener(String jsonString) {
        //System.out.println("in datalistern ****" + jsonString);
        cocktails = jsonService.getCocktailsFromJSON(jsonString);
        adapter = new CocktailsAdapter(this, cocktails);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void imageListener(Bitmap image) {

    }

    @Override
    public void CocktailsListener(List<Cocktail> list) {
        CocktailsAdapter adapter = new CocktailsAdapter(this, list);
        recyclerView.setAdapter(adapter);
        adapter.listener = this;
        setTitle("Search for new Cocktails...");
    }

    public void addNewCocktail(String cocktailName){
        Cocktail newC = new Cocktail();
        dbManager.addNewCocktail(newC);
        dbManager.getAllCocktails();
    }
}