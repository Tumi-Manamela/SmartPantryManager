package com.example.smartpantrymanager;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class RecipeMatcher {

    private final PantryDatabaseHelper databaseHelper;

    public RecipeMatcher(PantryDatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public List<Recipe> getMatchingRecipes(List<Recipe> allRecipes) {
        Map<String, IngredientStock> pantry = loadPantry();
        List<Recipe> matchingRecipes = new ArrayList<>();

        for (Recipe recipe : allRecipes) {
            List<String> names = recipe.getIngredients();
            List<Double> quantities = recipe.getQuantities();
            List<String> units = recipe.getUnits();

            boolean matches = true;

            for (int i = 0; i < names.size(); i++) {
                String requiredName = normalizeName(names.get(i));
                double requiredQuantity = quantities.get(i);
                String requiredUnit = normalizeUnit(units.get(i));

                IngredientStock stock = pantry.get(requiredName);

                if (stock == null
                        || !stock.unit.equals(requiredUnit)
                        || stock.quantity < requiredQuantity) {
                    matches = false;
                    break;
                }
            }

            if (matches) {
                matchingRecipes.add(recipe);
            }
        }

        return matchingRecipes;
    }

    private Map<String, IngredientStock> loadPantry() {
        Map<String, IngredientStock> pantry = new HashMap<>();

        Cursor cursor = databaseHelper.getAllIngredients();

        try {
            while (cursor.moveToNext()) {
                String name = cursor.getString(
                        cursor.getColumnIndexOrThrow(
                                PantryDatabaseHelper.COLUMN_NAME));

                double quantity = cursor.getDouble(
                        cursor.getColumnIndexOrThrow(
                                PantryDatabaseHelper.COLUMN_QUANTITY));

                String unit = cursor.getString(
                        cursor.getColumnIndexOrThrow(
                                PantryDatabaseHelper.COLUMN_UNIT));

                String key = normalizeName(name);
                String normalizedUnit = normalizeUnit(unit);

                IngredientStock existing = pantry.get(key);

                if (existing != null && existing.unit.equals(normalizedUnit)) {
                    existing.quantity += quantity;
                } else {
                    pantry.put(key, new IngredientStock(
                            quantity, normalizedUnit));
                }
            }
        } finally {
            cursor.close();
        }

        return pantry;
    }

    private String normalizeName(String name) {
        String normalized = name.trim().toLowerCase(Locale.ROOT);

        if (normalized.endsWith("ies") && normalized.length() > 3) {
            return normalized.substring(0, normalized.length() - 3) + "y";
        }

        if (normalized.endsWith("s")
                && !normalized.endsWith("ss")
                && normalized.length() > 1) {
            return normalized.substring(0, normalized.length() - 1);
        }

        return normalized;
    }

    private String normalizeUnit(String unit) {
        String normalized = unit.trim().toLowerCase(Locale.ROOT);

        switch (normalized) {
            case "grams":
                return "g";
            case "kilograms":
                return "kg";
            case "millilitres":
            case "milliliters":
                return "ml";
            case "litres":
            case "liters":
                return "l";
            case "pieces":
            case "piece":
                return "pieces";
            default:
                return normalized;
        }
    }

    private static class IngredientStock {
        double quantity;
        String unit;

        IngredientStock(double quantity, String unit) {
            this.quantity = quantity;
            this.unit = unit;
        }
    }
}
