
package com.example.smartpantrymanager;

import java.util.ArrayList;
import java.util.List;

public class Recipe {

    private final String name;
    private final String description;
    private final List<String> ingredients;
    private final List<Double> quantities;
    private final List<String> units;
    private final List<String> steps;

    public Recipe(String name, String description,
                  List<String> ingredients,
                  List<Double> quantities,
                  List<String> units,
                  List<String> steps) {
        this.name = name;
        this.description = description;
        this.ingredients = new ArrayList<>(ingredients);
        this.quantities = new ArrayList<>(quantities);
        this.units = new ArrayList<>(units);
        this.steps = new ArrayList<>(steps);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getIngredients() {
        return new ArrayList<>(ingredients);
    }

    public List<Double> getQuantities() {
        return new ArrayList<>(quantities);
    }

    public List<String> getUnits() {
        return new ArrayList<>(units);
    }

    public List<String> getSteps() {
        return new ArrayList<>(steps);
    }
}
