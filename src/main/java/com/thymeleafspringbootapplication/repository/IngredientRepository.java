package com.thymeleafspringbootapplication.repository;

import com.thymeleafspringbootapplication.model.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
}
