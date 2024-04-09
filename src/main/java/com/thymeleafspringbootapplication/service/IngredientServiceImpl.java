package com.thymeleafspringbootapplication.service;

import com.thymeleafspringbootapplication.model.Ingredient;
import com.thymeleafspringbootapplication.repository.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService{
    IngredientRepository ingredientRepository;

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
        Optional<Ingredient> optionalIngredient = ingredientRepository.findById(id);
        Ingredient ingredient;
        if(optionalIngredient.isPresent()) {
            ingredient = optionalIngredient.get();
        } else {
            throw new RuntimeException("Ingredient not found for id :: " + id);
        }
        return ingredient;
    }

    @Override
    public void deleteIngredientById(long id) {
        ingredientRepository.deleteById(id);
    }
}
