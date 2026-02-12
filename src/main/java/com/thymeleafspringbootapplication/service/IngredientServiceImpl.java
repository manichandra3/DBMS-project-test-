package com.thymeleafspringbootapplication.service;

import com.thymeleafspringbootapplication.exception.ResourceNotFoundException;
import com.thymeleafspringbootapplication.model.Ingredient;
import com.thymeleafspringbootapplication.repository.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService{

    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public void saveIngredient(Ingredient ingredient) {
        ingredientRepository.save(ingredient);
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        Iterable<Ingredient> ingredientIterable = ingredientRepository.findAll();
        List<Ingredient> ingredientList = new ArrayList<>();
        ingredientIterable.forEach(ingredientList::add);
        return ingredientList;
    }

    @Override
    public Ingredient getIngredientById(long id) {
        return ingredientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ingredient not found for id :: " + id));
    }

    @Override
    public void deleteIngredientById(long id) {
        ingredientRepository.deleteById(id);
    }
}
