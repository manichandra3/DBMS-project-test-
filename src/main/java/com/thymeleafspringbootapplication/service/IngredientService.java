package com.thymeleafspringbootapplication.service;

import com.thymeleafspringbootapplication.model.Ingredient;

import java.util.List;

public interface IngredientService {
    // Method to save a new supply
    void saveIngredient(Ingredient ingredient);

    // Method to get all Ingredients
    List<Ingredient> getAllIngredients();

    // Method to get an Ingredient by its composite key
    Ingredient getSupplyById(long id);

    // Method to delete an Ingredient by its composite key
    void deleteIngredientById(long id);

    // Add more service methods as needed
}
