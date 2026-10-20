package com.example.smartpantrymanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class PantryActivity extends Activity {

    private PantryDatabaseHelper databaseHelper;
    private ListView listView;
    private TextView tvEmpty;
    private PantryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantry);

        databaseHelper = new PantryDatabaseHelper(this);

        listView = findViewById(R.id.listIngredients);
        tvEmpty = findViewById(R.id.tvEmptyPantry);

        Button btnBack = findViewById(R.id.btnBackToHome);
        btnBack.setOnClickListener(v -> finish());

        adapter = new PantryAdapter(
                this,
                databaseHelper.getAllIngredients()
        );

        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Cursor cursor = databaseHelper.getAllIngredients();

            try {
                if (cursor.moveToPosition(position)) {
                    int ingredientId = cursor.getInt(
                            cursor.getColumnIndexOrThrow("_id"));

                    String name = cursor.getString(
                            cursor.getColumnIndexOrThrow("name"));

                    String quantity = String.valueOf(cursor.getDouble(
                            cursor.getColumnIndexOrThrow("quantity")));

                    String unit = cursor.getString(
                            cursor.getColumnIndexOrThrow("unit"));

                    String expiry = cursor.getString(
                            cursor.getColumnIndexOrThrow("expiry_date"));

                    Intent intent = new Intent(
                            PantryActivity.this,
                            AddIngredientActivity.class
                    );

                    intent.putExtra("ingredient_id", ingredientId);
                    intent.putExtra("ingredient_name", name);
                    intent.putExtra("ingredient_quantity", quantity);
                    intent.putExtra("ingredient_unit", unit);
                    intent.putExtra("ingredient_expiry", expiry);

                    startActivity(intent);
                }
            } finally {
                cursor.close();
            }
        });

        listView.setOnItemLongClickListener(
                (parent, view, position, id) -> {
                    Cursor cursor = databaseHelper.getAllIngredients();

                    try {
                        if (cursor.moveToPosition(position)) {
                            int ingredientId = cursor.getInt(
                                    cursor.getColumnIndexOrThrow("_id"));

                            String name = cursor.getString(
                                    cursor.getColumnIndexOrThrow("name"));

                            new AlertDialog.Builder(PantryActivity.this)
                                    .setTitle("Delete ingredient?")
                                    .setMessage("Do you want to delete "
                                            + name + "?")
                                    .setNegativeButton("Cancel",
                                            (dialog, which) ->
                                                    dialog.dismiss())
                                    .setPositiveButton("Delete",
                                            (dialog, which) -> {
                                                boolean deleted =
                                                        databaseHelper
                                                                .deleteIngredient(
                                                                        ingredientId);

                                                if (deleted) {
                                                    Toast.makeText(
                                                            PantryActivity.this,
                                                            "Ingredient deleted.",
                                                            Toast.LENGTH_SHORT
                                                    ).show();

                                                    loadIngredients();
                                                } else {
                                                    Toast.makeText(
                                                            PantryActivity.this,
                                                            "Could not delete ingredient.",
                                                            Toast.LENGTH_SHORT
                                                    ).show();
                                                }
                                            })
                                    .show();
                        }
                    } finally {
                        cursor.close();
                    }

                    return true;
                });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (databaseHelper != null && adapter != null) {
            loadIngredients();
        }
    }

    private void loadIngredients() {
        Cursor newCursor = databaseHelper.getAllIngredients();
        adapter.changeCursor(newCursor);

        boolean isEmpty = newCursor.getCount() == 0;

        tvEmpty.setVisibility(
                isEmpty ? View.VISIBLE : View.GONE);

        listView.setVisibility(
                isEmpty ? View.GONE : View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        if (adapter != null) {
            adapter.changeCursor(null);
        }

        super.onDestroy();
    }
}
