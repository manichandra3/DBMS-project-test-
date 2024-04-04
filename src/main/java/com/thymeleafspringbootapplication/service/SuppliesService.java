package com.thymeleafspringbootapplication.service;

import com.thymeleafspringbootapplication.model.Ingredient;
import com.thymeleafspringbootapplication.model.Supplier;
import com.thymeleafspringbootapplication.model.Supplies;

import java.util.List;

public interface SuppliesService {

    // Method to save a new supply
    void saveSupply(Supplies supply);

    // Method to get all supplies
    List<Supplies> getAllSupplies();

    // Method to get a supply by its composite key
    Supplies getSupplyById(Long ingredientId, Long supplierId);

    // Method to delete a supply by its composite key
    void deleteSupplyById(Long ingredientId, Long supplierId);

    Supplier getSupplierById(Long supplierId);

    Ingredient getIngredientById(Long ingredientId);

    // Add more service methods as needed
}
