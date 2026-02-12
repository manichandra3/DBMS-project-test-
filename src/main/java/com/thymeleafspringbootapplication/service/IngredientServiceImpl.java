package com.thymeleafspringbootapplication.service;

import com.thymeleafspringbootapplication.exception.ResourceNotFoundException;
import com.thymeleafspringbootapplication.model.Ingredient;
import com.thymeleafspringbootapplication.repository.IngredientRepository;
import com.thymeleafspringbootapplication.repository.NeedRepository;
import com.thymeleafspringbootapplication.repository.SuppliesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService{

    private static final Logger logger = LoggerFactory.getLogger(IngredientServiceImpl.class);

    private final IngredientRepository ingredientRepository;
    private final NeedRepository needRepository;
    private final SuppliesRepository suppliesRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository, NeedRepository needRepository, SuppliesRepository suppliesRepository) {
        this.ingredientRepository = ingredientRepository;
        this.needRepository = needRepository;
        this.suppliesRepository = suppliesRepository;
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
    @Transactional
    public void deleteIngredientById(long id) {
        if (!ingredientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Ingredient not found for id :: " + id);
        }
        logger.info("Deleting ingredient with id {} and its related records", id);
        needRepository.deleteByIngredientId(id);
        suppliesRepository.deleteByIngredientId(id);
        ingredientRepository.deleteById(id);
        logger.info("Successfully deleted ingredient with id {}", id);
    }
}
