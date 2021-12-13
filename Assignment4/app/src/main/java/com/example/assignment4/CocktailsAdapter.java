package com.example.assignment4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class CocktailsAdapter extends RecyclerView.Adapter<CocktailsAdapter.TasksViewHolder> {
    interface cocktailClickListener{
        public void cocktailClicked(Cocktail selectedCocktail);
    }

    private Context mCtx;
    public List<Cocktail> cocktailList;
    cocktailClickListener listener;

    public CocktailsAdapter(Context mCtx, List<Cocktail> cocktailList){
        this.mCtx = mCtx;
        this.cocktailList = cocktailList;
        listener = (cocktailClickListener)mCtx;
    }

    @Override
    public TasksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_cocktails, parent, false);
        return new TasksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TasksViewHolder holder, int position) {
        Cocktail t = cocktailList.get(position);
        holder.cocktailNameTextView.setText(t.getCocktailName() + ": " + t.getCocktailCategory() + ": " + t.getCocktailInstructions());
        holder.cocktailNameTextView.setText(t.getCocktailName());
    }

    @Override
    public int getItemCount() {
        return cocktailList.size();
    }

    class TasksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView cocktailNameTextView;
        TextView cocktailIngredientsTextView;

        public TasksViewHolder(View itemView){
            super(itemView);
            cocktailNameTextView = itemView.findViewById(R.id.cocktail);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Cocktail cocktail = cocktailList.get(getAdapterPosition());
            listener.cocktailClicked(cocktail);
        }
    }
}
