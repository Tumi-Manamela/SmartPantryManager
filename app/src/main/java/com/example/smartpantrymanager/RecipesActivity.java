package com.example.smartpantrymanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecipesActivity extends Activity {

    private PantryDatabaseHelper databaseHelper;
    private ListView listRecipes;
    private TextView tvNoRecipes;
    private List<Recipe> matchingRecipes;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        databaseHelper = new PantryDatabaseHelper(this);

        listRecipes = findViewById(R.id.listRecipes);
        tvNoRecipes = findViewById(R.id.tvNoRecipes);

        Button btnBack = findViewById(R.id.btnBackFromRecipes);
        btnBack.setOnClickListener(v -> finish());

        matchingRecipes = new ArrayList<>();
        List<String> recipeNames = new ArrayList<>();

        adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                recipeNames) {
            @Override
            public View getView(int position, View convertView,
                                ViewGroup parent) {
                TextView row = (TextView) super.getView(
                        position, convertView, parent);
                row.setTextSize(18);
                row.setPadding(20, 24, 20, 24);
                return row;
            }
        };

        listRecipes.setAdapter(adapter);

        listRecipes.setOnItemClickListener((parent, view, position, id) -> {
            Recipe selectedRecipe = matchingRecipes.get(position);

            Intent intent = new Intent(
                    RecipesActivity.this,
                    RecipeDetailActivity.class);

            intent.putExtra("recipe_name", selectedRecipe.getName());
            startActivity(intent);
        });

        loadMatchingRecipes(recipeNames);
    }

    private void loadMatchingRecipes(List<String> recipeNames) {
        RecipeMatcher matcher = new RecipeMatcher(databaseHelper);

        matchingRecipes.clear();
        matchingRecipes.addAll(
                matcher.getMatchingRecipes(RecipeRepository.getRecipes()));

        recipeNames.clear();

        for (Recipe recipe : matchingRecipes) {
            recipeNames.add(recipe.getName()
                    + "\n" + recipe.getDescription());
        }

        adapter.notifyDataSetChanged();

        boolean isEmpty = matchingRecipes.isEmpty();
        tvNoRecipes.setVisibility(isEmpty ? View.VISIBLE : View.GONE);
        listRecipes.setVisibility(isEmpty ? View.GONE : View.VISIBLE);
    }
}
