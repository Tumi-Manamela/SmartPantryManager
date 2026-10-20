package com.example.smartpantrymanager;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddIngredientActivity extends Activity {

    private EditText etName, etQuantity, etUnit, etExpiryDate;
    private PantryDatabaseHelper databaseHelper;

    private int ingredientId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingredient);

        databaseHelper = new PantryDatabaseHelper(this);

        etName = findViewById(R.id.etIngredientName);
        etQuantity = findViewById(R.id.etIngredientQuantity);
        etUnit = findViewById(R.id.etIngredientUnit);
        etExpiryDate = findViewById(R.id.etIngredientExpiry);

        Button btnSave = findViewById(R.id.btnSaveIngredient);
        Button btnCancel = findViewById(R.id.btnCancelIngredient);

        ingredientId = getIntent().getIntExtra("ingredient_id", -1);

        if (ingredientId != -1) {
            setTitle("Edit Ingredient");

            etName.setText(getIntent().getStringExtra("ingredient_name"));
            etQuantity.setText(
                    getIntent().getStringExtra("ingredient_quantity"));
            etUnit.setText(getIntent().getStringExtra("ingredient_unit"));

            String expiryDate =
                    getIntent().getStringExtra("ingredient_expiry");

            if (expiryDate != null) {
                etExpiryDate.setText(expiryDate);
            }

            btnSave.setText("Update Ingredient");
        } else {
            setTitle("Add Ingredient");
        }

        btnSave.setOnClickListener(v -> saveIngredient());
        btnCancel.setOnClickListener(v -> finish());
    }

    private void saveIngredient() {
        String name = etName.getText().toString().trim();
        String quantityText = etQuantity.getText().toString().trim();
        String unit = etUnit.getText().toString().trim();
        String expiryDate = etExpiryDate.getText().toString().trim();

        if (name.isEmpty() || quantityText.isEmpty() || unit.isEmpty()) {
            Toast.makeText(this,
                    "Please enter the name, quantity and unit.",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        double quantity;

        try {
            quantity = Double.parseDouble(quantityText);
        } catch (NumberFormatException e) {
            Toast.makeText(this,
                    "Enter a valid quantity.",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if (quantity <= 0) {
            Toast.makeText(this,
                    "Quantity must be greater than zero.",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if (expiryDate.isEmpty()) {
            expiryDate = null;
        }

        boolean saved;

        if (ingredientId == -1) {
            saved = databaseHelper.addIngredient(
                    name, quantity, unit, expiryDate);
        } else {
            saved = databaseHelper.updateIngredient(
                    ingredientId, name, quantity, unit, expiryDate);
        }

        if (saved) {
            String message = ingredientId == -1
                    ? "Ingredient saved successfully!"
                    : "Ingredient updated successfully!";

            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);
            finish();
        } else {
            Toast.makeText(this,
                    "Could not save the ingredient. Please try again.",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
