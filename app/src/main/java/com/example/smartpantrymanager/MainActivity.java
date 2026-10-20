package com.example.smartpantrymanager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView tvPantryCount;
    private PantryDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main), (v, insets) -> {
                    Insets systemBars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars());
                    v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom);
                    return insets;
                });

        tvPantryCount = findViewById(R.id.tvPantryCount);
        databaseHelper = new PantryDatabaseHelper(this);

        Button btnAddIngredient = findViewById(R.id.btnAddIngredient);
        btnAddIngredient.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this, AddIngredientActivity.class);
            startActivity(intent);
        });

        Button btnViewPantry = findViewById(R.id.btnViewPantry);
        btnViewPantry.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this, PantryActivity.class);
            startActivity(intent);
        });

        Button btnRecipes = findViewById(R.id.btnRecipes);
        btnRecipes.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this, RecipesActivity.class);
            startActivity(intent);
        });

        Button btnSettings = findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updatePantryCount();
    }

    private void updatePantryCount() {
        Cursor cursor = databaseHelper.getAllIngredients();
        int count = 0;

        if (cursor != null) {
            count = cursor.getCount();
            cursor.close();
        }

        tvPantryCount.setText("Pantry ingredients: " + count);
    }
}
