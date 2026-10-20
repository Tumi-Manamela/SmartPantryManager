package com.example.smartpantrymanager;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class RecipeDetailActivity extends Activity {

    private TextView tvRecipeName;
    private TextView tvRecipeDescription;
    private TextView tvRecipeIngredients;
    private TextView tvRecipeSteps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        tvRecipeName = findViewById(R.id.tvRecipeName);
        tvRecipeDescription = findViewById(R.id.tvRecipeDescription);
        tvRecipeIngredients = findViewById(R.id.tvRecipeIngredients);
        tvRecipeSteps = findViewById(R.id.tvRecipeSteps);

        Button btnBack = findViewById(R.id.btnBackFromRecipeDetail);
        btnBack.setOnClickListener(v -> finish());

        String recipeName = getIntent().getStringExtra("recipe_name");

        Recipe selectedRecipe = null;

        List<Recipe> recipes = RecipeRepository.getRecipes();

        if (recipeName != null) {
            for (Recipe recipe : recipes) {
                if (recipe.getName().equals(recipeName)) {
                    selectedRecipe = recipe;
                    break;
                }
            }
        }

        if (selectedRecipe == null) {
            tvRecipeName.setText("Recipe not found");
            tvRecipeDescription.setText(
                    "We could not find the selected recipe.");
            tvRecipeIngredients.setText("");
            tvRecipeSteps.setText("");
            return;
        }

        tvRecipeName.setText(selectedRecipe.getName());
        tvRecipeDescription.setText(selectedRecipe.getDescription());

        StringBuilder ingredientsText = new StringBuilder();
        List<String> ingredients = selectedRecipe.getIngredients();
        List<Double> quantities = selectedRecipe.getQuantities();
        List<String> units = selectedRecipe.getUnits();

        for (int i = 0; i < ingredients.size(); i++) {
            ingredientsText.append("• ")
                    .append(quantities.get(i))
                    .append(" ")
                    .append(units.get(i))
                    .append(" ")
                    .append(ingredients.get(i))
                    .append("\n");
        }

        tvRecipeIngredients.setText(ingredientsText.toString());

        StringBuilder stepsText = new StringBuilder();
        List<String> steps = selectedRecipe.getSteps();

        for (int i = 0; i < steps.size(); i++) {
            stepsText.append(i + 1)
                    .append(". ")
                    .append(steps.get(i))
                    .append("\n\n");
        }

        tvRecipeSteps.setText(stepsText.toString().trim());
    }
}
