
package com.example.smartpantrymanager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecipeRepository {

    public static List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<>();

        recipes.add(new Recipe(
                "Tomato Salad",
                "A fresh and simple tomato salad.",
                Arrays.asList("tomatoes", "onion"),
                Arrays.asList(2.0, 1.0),
                Arrays.asList("pieces", "pieces"),
                Arrays.asList(
                        "Wash and chop the tomatoes.",
                        "Peel and finely slice the onion.",
                        "Mix together and serve."
                )
        ));

        recipes.add(new Recipe(
                "Tomato Omelette",
                "A quick egg and tomato breakfast.",
                Arrays.asList("eggs", "tomatoes"),
                Arrays.asList(2.0, 1.0),
                Arrays.asList("pieces", "pieces"),
                Arrays.asList(
                        "Chop the tomato.",
                        "Beat the eggs in a bowl.",
                        "Cook the tomato and eggs in a pan until set."
                )
        ));

        recipes.add(new Recipe(
                "Tomato Pasta",
                "Pasta with a simple tomato sauce.",
                Arrays.asList("pasta", "tomatoes"),
                Arrays.asList(200.0, 2.0),
                Arrays.asList("g", "pieces"),
                Arrays.asList(
                        "Cook the pasta according to its packet instructions.",
                        "Chop and cook the tomatoes in a pan.",
                        "Mix the tomato sauce with the pasta."
                )
        ));

        recipes.add(new Recipe(
                "Vegetable Fried Rice",
                "A simple rice dish with vegetables.",
                Arrays.asList("rice", "carrots", "onion"),
                Arrays.asList(200.0, 1.0, 1.0),
                Arrays.asList("g", "pieces", "pieces"),
                Arrays.asList(
                        "Cook the rice.",
                        "Chop the carrots and onion.",
                        "Cook the vegetables in a pan, add the rice and mix."
                )
        ));

        recipes.add(new Recipe(
                "Mashed Potatoes",
                "Soft and creamy mashed potatoes.",
                Arrays.asList("potatoes", "milk"),
                Arrays.asList(4.0, 100.0),
                Arrays.asList("pieces", "ml"),
                Arrays.asList(
                        "Peel and boil the potatoes until soft.",
                        "Drain the water.",
                        "Mash with the milk until smooth."
                )
        ));

        recipes.add(new Recipe(
                "Banana Pancakes",
                "Easy pancakes made with banana.",
                Arrays.asList("bananas", "eggs", "flour"),
                Arrays.asList(2.0, 2.0, 100.0),
                Arrays.asList("pieces", "pieces", "g"),
                Arrays.asList(
                        "Mash the bananas.",
                        "Mix with eggs and flour.",
                        "Cook small portions in a lightly greased pan."
                )
        ));

        recipes.add(new Recipe(
                "Cheese Omelette",
                "An omelette with melted cheese.",
                Arrays.asList("eggs", "cheese"),
                Arrays.asList(2.0, 50.0),
                Arrays.asList("pieces", "g"),
                Arrays.asList(
                        "Beat the eggs.",
                        "Pour into a heated pan.",
                        "Add cheese and fold when the eggs are cooked."
                )
        ));

        recipes.add(new Recipe(
                "Carrot Soup",
                "A warm carrot soup.",
                Arrays.asList("carrots", "onion", "potatoes"),
                Arrays.asList(3.0, 1.0, 1.0),
                Arrays.asList("pieces", "pieces", "pieces"),
                Arrays.asList(
                        "Chop the carrots, onion and potato.",
                        "Boil in water until soft.",
                        "Blend carefully until smooth."
                )
        ));

        recipes.add(new Recipe(
                "Chicken and Rice",
                "A straightforward chicken and rice meal.",
                Arrays.asList("chicken", "rice", "onion"),
                Arrays.asList(250.0, 200.0, 1.0),
                Arrays.asList("g", "g", "pieces"),
                Arrays.asList(
                        "Cook the rice.",
                        "Cook the chicken thoroughly in a pan.",
                        "Add chopped onion and serve with the rice."
                )
        ));

        recipes.add(new Recipe(
                "Potato and Onion Bake",
                "A simple baked potato dish.",
                Arrays.asList("potatoes", "onion", "cheese"),
                Arrays.asList(3.0, 1.0, 50.0),
                Arrays.asList("pieces", "pieces", "g"),
                Arrays.asList(
                        "Slice the potatoes and onion.",
                        "Layer in an oven-safe dish.",
                        "Top with cheese and bake until tender."
                )
        ));

        recipes.add(new Recipe(
                "Apple Banana Fruit Salad",
                "A quick fresh fruit salad.",
                Arrays.asList("apples", "bananas"),
                Arrays.asList(2.0, 2.0),
                Arrays.asList("pieces", "pieces"),
                Arrays.asList(
                        "Wash the apples.",
                        "Slice the apples and bananas.",
                        "Mix and serve fresh."
                )
        ));

        recipes.add(new Recipe(
                "Garlic Tomato Toast",
                "Toast topped with tomatoes and garlic.",
                Arrays.asList("bread", "tomatoes", "garlic"),
                Arrays.asList(2.0, 1.0, 1.0),
                Arrays.asList("slices", "pieces", "cloves"),
                Arrays.asList(
                        "Toast the bread.",
                        "Chop the tomatoes and garlic finely.",
                        "Spoon the mixture onto the toast."
                )
        ));

        recipes.add(new Recipe(
                "Spinach Omelette",
                "Eggs cooked with spinach.",
                Arrays.asList("eggs", "spinach"),
                Arrays.asList(2.0, 50.0),
                Arrays.asList("pieces", "g"),
                Arrays.asList(
                        "Wash and chop the spinach.",
                        "Beat the eggs.",
                        "Cook the spinach and eggs together in a pan."
                )
        ));

        recipes.add(new Recipe(
                "Tuna Pasta",
                "A quick pasta dish with tuna.",
                Arrays.asList("pasta", "tuna"),
                Arrays.asList(200.0, 1.0),
                Arrays.asList("g", "can"),
                Arrays.asList(
                        "Cook the pasta.",
                        "Drain the tuna.",
                        "Mix the tuna with the pasta and serve."
                )
        ));

        recipes.add(new Recipe(
                "Yoghurt Banana Bowl",
                "A simple breakfast bowl.",
                Arrays.asList("yoghurt", "bananas"),
                Arrays.asList(150.0, 1.0),
                Arrays.asList("g", "pieces"),
                Arrays.asList(
                        "Slice the banana.",
                        "Place the yoghurt in a bowl.",
                        "Add the banana and serve."
                )
        ));

        return recipes;
    }
}
