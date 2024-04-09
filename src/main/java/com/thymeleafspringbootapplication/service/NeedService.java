package com.thymeleafspringbootapplication.service;

import com.thymeleafspringbootapplication.model.Ingredient;
import com.thymeleafspringbootapplication.model.Need;
import com.thymeleafspringbootapplication.model.Product;

import java.util.List;

public interface NeedService {
    // Method to save a new supply
    void saveNeed(Need need);

    // Method to get all supplies
    List<Need> getAllNeed();

    // Method to get a supply by its composite key
    Need getNeedById(Long ingredientId, Long supplierId);

    // Method to delete a supply by its composite key
    void deleteNeedById(Long ingredientId, Long supplierId);

    Product getProductById(Long productId);

    Ingredient getIngredientById(Long ingredientId);

    // Add more service methods as needed
}
